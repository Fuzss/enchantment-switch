package fuzs.enchantmentswitch.common.data.client;

import fuzs.enchantmentswitch.common.EnchantmentSwitch;
import fuzs.enchantmentswitch.common.client.EnchantmentSwitchClient;
import fuzs.enchantmentswitch.common.client.gui.screens.inventory.EditEnchantmentsScreen;
import fuzs.enchantmentswitch.common.client.util.EnchantmentTooltipHelper;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.addKeyCategory(EnchantmentSwitch.MOD_ID, EnchantmentSwitch.MOD_NAME);
        this.add(EnchantmentSwitchClient.EDIT_ENCHANTMENTS_KEY_MAPPING, "Edit Enchantments");
        this.add(EditEnchantmentsScreen.COMPONENT_EDIT_ENCHANTMENTS, "Edit Enchantments");
        this.add(EnchantmentTooltipHelper.KEY_INCOMPATIBLE_ENCHANTMENTS,
                "This enchantment is incompatible with: %s");
    }
}
