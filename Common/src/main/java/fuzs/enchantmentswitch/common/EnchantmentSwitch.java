package fuzs.enchantmentswitch.common;

import fuzs.enchantmentswitch.common.config.ClientConfig;
import fuzs.enchantmentswitch.common.init.ModRegistry;
import fuzs.enchantmentswitch.common.network.client.ServerboundSetEnchantmentsMessage;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.PayloadTypesContext;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentSwitch implements ModConstructor {
    public static final String MOD_ID = "enchantmentswitch";
    public static final String MOD_NAME = "Enchantment Switch";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).client(ClientConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    @Override
    public void onRegisterPayloadTypes(PayloadTypesContext context) {
        context.playToServer(ServerboundSetEnchantmentsMessage.class, ServerboundSetEnchantmentsMessage.STREAM_CODEC);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
