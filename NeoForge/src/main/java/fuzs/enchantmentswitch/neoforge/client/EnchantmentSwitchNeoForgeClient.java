package fuzs.enchantmentswitch.neoforge.client;

import fuzs.enchantmentswitch.common.EnchantmentSwitch;
import fuzs.enchantmentswitch.common.client.EnchantmentSwitchClient;
import fuzs.enchantmentswitch.common.data.client.ModLanguageProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = EnchantmentSwitch.MOD_ID, dist = Dist.CLIENT)
public class EnchantmentSwitchNeoForgeClient {

    public EnchantmentSwitchNeoForgeClient() {
        ClientModConstructor.construct(EnchantmentSwitch.MOD_ID, EnchantmentSwitchClient::new);
        DataProviderBuilder.of(EnchantmentSwitch.MOD_ID).addProvider(ModLanguageProvider::new);
    }
}
