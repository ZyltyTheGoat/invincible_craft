package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.PowerPickerGUIMenu;
import net.mcreator.invincible_craft.network.PowerPickerGUIButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PowerPickerGUIScreen extends AbstractContainerScreen<PowerPickerGUIMenu> {
	private final static HashMap<String, Object> guistate = PowerPickerGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	Button button_viltrumite;
	Button button_no_power;
	Button button_dimensional_travel;
	Button button_duplication;
	Button button_battle_beast;
	Button button_atom_eve;

	public PowerPickerGUIScreen(PowerPickerGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 376;
		this.imageHeight = 216;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/power_picker_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
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
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.power_picker_gui.label_power_picker"), 16, 14, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_viltrumite = Button.builder(Component.translatable("gui.invincible_craft.power_picker_gui.button_viltrumite"), e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new PowerPickerGUIButtonMessage(0, x, y, z, textstate));
				PowerPickerGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 16, this.topPos + 68, 77, 20).build();
		guistate.put("button:button_viltrumite", button_viltrumite);
		this.addRenderableWidget(button_viltrumite);
		button_no_power = Button.builder(Component.translatable("gui.invincible_craft.power_picker_gui.button_no_power"), e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new PowerPickerGUIButtonMessage(1, x, y, z, textstate));
				PowerPickerGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 16, this.topPos + 41, 66, 20).build();
		guistate.put("button:button_no_power", button_no_power);
		this.addRenderableWidget(button_no_power);
		button_dimensional_travel = Button.builder(Component.translatable("gui.invincible_craft.power_picker_gui.button_dimensional_travel"), e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new PowerPickerGUIButtonMessage(2, x, y, z, textstate));
				PowerPickerGUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 16, this.topPos + 95, 119, 20).build();
		guistate.put("button:button_dimensional_travel", button_dimensional_travel);
		this.addRenderableWidget(button_dimensional_travel);
		button_duplication = Button.builder(Component.translatable("gui.invincible_craft.power_picker_gui.button_duplication"), e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new PowerPickerGUIButtonMessage(3, x, y, z, textstate));
				PowerPickerGUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 16, this.topPos + 122, 82, 20).build();
		guistate.put("button:button_duplication", button_duplication);
		this.addRenderableWidget(button_duplication);
		button_battle_beast = Button.builder(Component.translatable("gui.invincible_craft.power_picker_gui.button_battle_beast"), e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new PowerPickerGUIButtonMessage(4, x, y, z, textstate));
				PowerPickerGUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 16, this.topPos + 149, 87, 20).build();
		guistate.put("button:button_battle_beast", button_battle_beast);
		this.addRenderableWidget(button_battle_beast);
		button_atom_eve = Button.builder(Component.translatable("gui.invincible_craft.power_picker_gui.button_atom_eve"), e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new PowerPickerGUIButtonMessage(5, x, y, z, textstate));
				PowerPickerGUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 16, this.topPos + 176, 66, 20).build();
		guistate.put("button:button_atom_eve", button_atom_eve);
		this.addRenderableWidget(button_atom_eve);
	}
}
