package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.world.inventory.QuickTravelGUIMenu;
import net.mcreator.invincible_craft.network.QuickTravelGUIButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class QuickTravelGUIScreen extends AbstractContainerScreen<QuickTravelGUIMenu> {
	private final static HashMap<String, Object> guistate = QuickTravelGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox travel_x;
	public static EditBox travel_y;
	public static EditBox travel_z;
	Button button_confirm;
	ImageButton imagebutton_earth_icon;
	ImageButton imagebutton_moon_icon;

	public QuickTravelGUIScreen(QuickTravelGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 216;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/quick_travel_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		travel_x.render(guiGraphics, mouseX, mouseY, partialTicks);
		travel_y.render(guiGraphics, mouseX, mouseY, partialTicks);
		travel_z.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 46 && mouseX < leftPos + 70 && mouseY > topPos + 135 && mouseY < topPos + 159)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.quick_travel_gui.tooltip_teleports_you_to_the_selected_lo"), mouseX, mouseY);
		if (mouseX > leftPos + 109 && mouseX < leftPos + 133 && mouseY > topPos + 135 && mouseY < topPos + 159)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.quick_travel_gui.tooltip_teleports_you_to_the_selected_lo1"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (travel_x.isFocused())
			return travel_x.keyPressed(key, b, c);
		if (travel_y.isFocused())
			return travel_y.keyPressed(key, b, c);
		if (travel_z.isFocused())
			return travel_z.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		travel_x.tick();
		travel_y.tick();
		travel_z.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String travel_xValue = travel_x.getValue();
		String travel_yValue = travel_y.getValue();
		String travel_zValue = travel_z.getValue();
		super.resize(minecraft, width, height);
		travel_x.setValue(travel_xValue);
		travel_y.setValue(travel_yValue);
		travel_z.setValue(travel_zValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.quick_travel_gui.label_quick_travel"), 57, 14, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		travel_x = new EditBox(this.font, this.leftPos + 31, this.topPos + 33, 118, 18, Component.translatable("gui.invincible_craft.quick_travel_gui.travel_x")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_x").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_x").getString());
				else
					setSuggestion(null);
			}
		};
		travel_x.setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_x").getString());
		travel_x.setMaxLength(32767);
		guistate.put("text:travel_x", travel_x);
		this.addWidget(this.travel_x);
		travel_y = new EditBox(this.font, this.leftPos + 31, this.topPos + 69, 118, 18, Component.translatable("gui.invincible_craft.quick_travel_gui.travel_y")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_y").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_y").getString());
				else
					setSuggestion(null);
			}
		};
		travel_y.setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_y").getString());
		travel_y.setMaxLength(32767);
		guistate.put("text:travel_y", travel_y);
		this.addWidget(this.travel_y);
		travel_z = new EditBox(this.font, this.leftPos + 31, this.topPos + 105, 118, 18, Component.translatable("gui.invincible_craft.quick_travel_gui.travel_z")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_z").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_z").getString());
				else
					setSuggestion(null);
			}
		};
		travel_z.setSuggestion(Component.translatable("gui.invincible_craft.quick_travel_gui.travel_z").getString());
		travel_z.setMaxLength(32767);
		guistate.put("text:travel_z", travel_z);
		this.addWidget(this.travel_z);
		button_confirm = Button.builder(Component.translatable("gui.invincible_craft.quick_travel_gui.button_confirm"), e -> {
			if (true) {
				textstate.put("textin:travel_x", travel_x.getValue());
				textstate.put("textin:travel_y", travel_y.getValue());
				textstate.put("textin:travel_z", travel_z.getValue());
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new QuickTravelGUIButtonMessage(0, x, y, z, textstate));
				QuickTravelGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 58, this.topPos + 167, 61, 20).build();
		guistate.put("button:button_confirm", button_confirm);
		this.addRenderableWidget(button_confirm);
		imagebutton_earth_icon = new ImageButton(this.leftPos + 51, this.topPos + 140, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_earth_icon.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:travel_x", travel_x.getValue());
				textstate.put("textin:travel_y", travel_y.getValue());
				textstate.put("textin:travel_z", travel_z.getValue());
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new QuickTravelGUIButtonMessage(1, x, y, z, textstate));
				QuickTravelGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_earth_icon", imagebutton_earth_icon);
		this.addRenderableWidget(imagebutton_earth_icon);
		imagebutton_moon_icon = new ImageButton(this.leftPos + 114, this.topPos + 140, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_moon_icon.png"), 16, 32, e -> {
			if (true) {
				textstate.put("textin:travel_x", travel_x.getValue());
				textstate.put("textin:travel_y", travel_y.getValue());
				textstate.put("textin:travel_z", travel_z.getValue());
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new QuickTravelGUIButtonMessage(2, x, y, z, textstate));
				QuickTravelGUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_moon_icon", imagebutton_moon_icon);
		this.addRenderableWidget(imagebutton_moon_icon);
	}
}
