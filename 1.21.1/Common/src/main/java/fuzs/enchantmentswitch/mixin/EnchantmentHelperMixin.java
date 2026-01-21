package fuzs.enchantmentswitch.mixin;

import fuzs.enchantmentswitch.init.ModRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
abstract class EnchantmentHelperMixin {

    @Inject(method = "getEnchantmentsForCrafting", at = @At("RETURN"), cancellable = true)
    private static void getEnchantmentsForCrafting(ItemStack itemStack, CallbackInfoReturnable<ItemEnchantments> callback) {
        // Include stored enchantments so other mods (like Enchanting Infuser) see them
        // when checking for existing enchantments, preventing duplicates
        ItemEnchantments storedEnchantments = itemStack.getOrDefault(ModRegistry.STORED_ENCHANTMENTS_DATA_COMPONENT_TYPE.value(),
                ItemEnchantments.EMPTY);
        if (!storedEnchantments.isEmpty()) {
            ItemEnchantments originalResult = callback.getReturnValue();
            ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(originalResult);
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : storedEnchantments.entrySet()) {
                // Only add if not already present (stored enchantments have lower priority)
                if (!mutable.keySet().contains(entry.getKey())) {
                    mutable.set(entry.getKey(), entry.getIntValue());
                }
            }
            callback.setReturnValue(mutable.toImmutable());
        }
    }
}
