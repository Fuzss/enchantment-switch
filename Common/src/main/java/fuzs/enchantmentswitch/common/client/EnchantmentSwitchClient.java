package fuzs.enchantmentswitch.common.client;

import fuzs.enchantmentswitch.common.EnchantmentSwitch;
import fuzs.enchantmentswitch.common.client.handler.SlotOverlayHandler;
import fuzs.enchantmentswitch.common.client.handler.StoredEnchantmentsTooltipHandler;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.GuiLayersContext;
import fuzs.puzzleslib.common.api.client.core.v1.context.KeyMappingsContext;
import fuzs.puzzleslib.common.api.client.event.v1.ClientTickEvents;
import fuzs.puzzleslib.common.api.client.event.v1.gui.ItemTooltipCallback;
import fuzs.puzzleslib.common.api.client.event.v1.gui.RenderTooltipCallback;
import fuzs.puzzleslib.common.api.client.event.v1.gui.ScreenEvents;
import fuzs.puzzleslib.common.api.client.key.v1.KeyActivationHandler;
import fuzs.puzzleslib.common.api.client.key.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class EnchantmentSwitchClient implements ClientModConstructor {
    public static final KeyMapping EDIT_ENCHANTMENTS_KEY_MAPPING = KeyMappingHelper.registerUnboundKeyMapping(
            EnchantmentSwitch.id("edit_enchantments"));

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ItemTooltipCallback.EVENT.register(StoredEnchantmentsTooltipHandler::onItemTooltip);
        ScreenEvents.afterRender(AbstractContainerScreen.class).register(SlotOverlayHandler::onAfterRender);
        RenderTooltipCallback.EVENT.register(SlotOverlayHandler::onRenderTooltip);
        ClientTickEvents.END.register(SlotOverlayHandler::onEndClientTick);
    }

    @Override
    public void onRegisterGuiLayers(GuiLayersContext context) {
        context.registerGuiLayer(GuiLayersContext.HOTBAR,
                EnchantmentSwitch.id("slot_overlay"),
                SlotOverlayHandler::renderGuiLayer);
    }

    @Override
    public void onRegisterKeyMappings(KeyMappingsContext context) {
        context.registerKeyMapping(EDIT_ENCHANTMENTS_KEY_MAPPING,
                KeyActivationHandler.of()
                        .withGameHandler(SlotOverlayHandler::executeTriggerAction)
                        .withScreenHandler((Class<AbstractContainerScreen<?>>) (Class<?>) AbstractContainerScreen.class,
                                (AbstractContainerScreen<?> screen) -> {
                                    SlotOverlayHandler.executeTriggerAction(screen.minecraft);
                                }));
    }
}
