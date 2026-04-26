package fuzs.enchantmentswitch.common.client.gui.components;

import fuzs.enchantmentswitch.common.EnchantmentSwitch;
import fuzs.enchantmentswitch.common.client.gui.screens.inventory.EnchantmentState;
import fuzs.puzzleslib.common.api.client.gui.v2.tooltip.TooltipBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.Enchantment;

public class ClickableEnchantmentButton extends ImageButton {
    public static final WidgetSprites BUTTON_SPRITES = new WidgetSprites(EnchantmentSwitch.id("enchantments/button"),
            EnchantmentSwitch.id("enchantments/button_disabled"),
            EnchantmentSwitch.id("enchantments/button_highlighted"));
    public static final WidgetSprites TICK_SPRITES = new WidgetSprites(EnchantmentSwitch.id("enchantments/tick"),
            EnchantmentSwitch.id("enchantments/tick_disabled"),
            EnchantmentSwitch.id("enchantments/tick_highlighted"));
    public static final WidgetSprites CROSS_SPRITES = new WidgetSprites(EnchantmentSwitch.id("enchantments/cross"),
            EnchantmentSwitch.id("enchantments/cross_disabled"),
            EnchantmentSwitch.id("enchantments/cross_highlighted"));

    private final boolean isPresent;
    private Component hoveredMessage = CommonComponents.EMPTY;

    public ClickableEnchantmentButton(int x, int y, Holder<Enchantment> enchantment, EnchantmentState enchantmentState, OnPress onPress) {
        super(x, y, 126, 20, BUTTON_SPRITES, onPress);
        this.isPresent = enchantmentState.isPresent();
        this.active = !enchantmentState.isInactive();
        this.setMessage(enchantmentState.getDisplayName(enchantment));
        TooltipBuilder.create(enchantmentState.getTooltip(enchantment)).splitLines().build(this);
    }

    @Override
    public Component getMessage() {
        if (!this.isActive()) {
            return this.inactiveMessage;
        } else if (this.isHoveredOrFocused()) {
            return this.hoveredMessage;
        } else {
            return this.message;
        }
    }

    @Override
    public void setMessage(Component message) {
        this.message = message;
        this.inactiveMessage = ComponentUtils.mergeStyles(message, Style.EMPTY.withColor(0x685E4A));
        this.hoveredMessage = ComponentUtils.mergeStyles(message, Style.EMPTY.withColor(ChatFormatting.YELLOW));
    }

    @Override
    public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractContents(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                this.getStateSprite(),
                this.getX() + 2,
                this.getY() + 2,
                16,
                16);
        this.extractScrollingStringOverContents(guiGraphics.textRendererForWidget(this,
                GuiGraphicsExtractor.HoveredTextEffects.NONE), this.getMessage(), 2);
    }

    private Identifier getStateSprite() {
        WidgetSprites sprites = this.isPresent ? TICK_SPRITES : CROSS_SPRITES;
        return sprites.get(this.isActive(), this.isHoveredOrFocused());
    }

    @Override
    public void extractScrollingStringOverContents(ActiveTextCollector activeTextCollector, Component component, int textBorder) {
        int startX = this.getX() + 20 + textBorder;
        int endX = this.getX() + this.getWidth() - textBorder;
        int startY = this.getY();
        int endY = this.getY() + this.getHeight();
        activeTextCollector.acceptScrollingWithDefaultCenter(component, startX, endX, startY, endY);
    }
}
