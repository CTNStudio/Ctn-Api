package ctn.ctnapi.client.gui.widget;

import ctn.ctnapi.client.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class TextImageButton extends ImageButton {
	@Nullable
	private final Font font;
	@Nullable
	private final Component text;
	private final int color;
	private final int textMargin;

	public TextImageButton(int x, int y,
	                       int width, int height,
	                       WidgetSprites sprites, Component message, OnPress onPress,
	                       Builder builder) {
		super(x, y, width, height, sprites, onPress, message);
		this.font = builder.font;
		this.text = builder.text;
		this.color = builder.color;
		this.textMargin = builder.textMargin;
		setTooltip(builder.tooltip);
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
		renderText(guiGraphics);
	}

	public void renderText(GuiGraphics guiGraphics) {
		if (text == null || font == null) {
			return;
		}
		guiGraphics.drawString(font, getMessage(), getX() + TEXT_MARGIN, getY() + (getHeight() - font.lineHeight) / 2, color);
	}

	public int getTextMargin() {
		return textMargin;
	}

	public Component getText() {
		return text;
	}

	public static class Builder {
		@Nullable
		private Component text;
		private Font font;
		private int textMargin = 0;
		@Nullable
		private Tooltip tooltip;
		private int color;

		public Builder() {
			this.font = Minecraft.getInstance().font;
		}

		public Builder text(Font font, Component component, int textMargin) {
			this.font = font;
			this.text = component;
			this.textMargin = textMargin;
			return this;
		}

		public Builder text(Component text) {
			this.text = text;
			return this;
		}

		public Builder font(Font font) {
			this.font = font;
			return this;
		}

		public Builder textMargin(int textMargin) {
			this.textMargin = textMargin;
			return this;
		}

		public Builder color(int color) {
			this.color = color;
			return this;
		}

		public Builder color(String rgbString) {
			this.color = ColorUtil.rgbColor(rgbString);
			return this;
		}

		public Builder tooltip(@Nullable Tooltip tooltip) {
			this.tooltip = tooltip;
			return this;
		}

		public TextImageButton build(int x, int y,
		                             int width, int height,
		                             WidgetSprites sprites, Component message, OnPress onPress) {
			return new TextImageButton(x, y, width, height, sprites, message, onPress, this);
		}
	}
}