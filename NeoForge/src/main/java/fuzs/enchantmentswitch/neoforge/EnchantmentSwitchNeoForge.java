package fuzs.enchantmentswitch.neoforge;

import fuzs.enchantmentswitch.common.EnchantmentSwitch;
import fuzs.enchantmentswitch.common.data.tags.ModEnchantmentTagProvider;
import fuzs.enchantmentswitch.common.data.tags.ModItemTagProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.fml.common.Mod;

@Mod(EnchantmentSwitch.MOD_ID)
public class EnchantmentSwitchNeoForge {

    public EnchantmentSwitchNeoForge() {
        ModConstructor.construct(EnchantmentSwitch.MOD_ID, EnchantmentSwitch::new);
        DataProviderBuilder.of(EnchantmentSwitch.MOD_ID)
                .addProvider(ModItemTagProvider::new, ModEnchantmentTagProvider::new);
    }
}
