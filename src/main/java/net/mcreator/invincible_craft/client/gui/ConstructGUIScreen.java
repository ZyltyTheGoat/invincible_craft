package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.ConstructGUIMenu;
import net.mcreator.invincible_craft.network.ConstructGUIButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ConstructGUIScreen extends AbstractContainerScreen<ConstructGUIMenu> {
	private final static HashMap<String, Object> guistate = ConstructGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_atom_eve_construct_sword;
	ImageButton imagebutton_atom_eve_construct_hammer;
	ImageButton imagebutton_atom_eve_construct_gauntlet;
	ImageButton imagebutton_atom_eve_construct_shield;
	ImageButton imagebutton_atom_eve_construct_trident;
	ImageButton imagebutton_atom_eve_construct_armor;

	public ConstructGUIScreen(ConstructGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 12 && mouseX < leftPos + 36 && mouseY > topPos + 2 && mouseY < topPos + 26)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.construct_gui.tooltip_construct_sword"), mouseX, mouseY);
		if (mouseX > leftPos + 105 && mouseX < leftPos + 129 && mouseY > topPos + -21 && mouseY < topPos + 3)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.construct_gui.tooltip_construct_hammer"), mouseX, mouseY);
		if (mouseX > leftPos + 175 && mouseX < leftPos + 199 && mouseY > topPos + 22 && mouseY < topPos + 46)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.construct_gui.tooltip_construct_gauntlet"), mouseX, mouseY);
		if (mouseX > leftPos + 172 && mouseX < leftPos + 196 && mouseY > topPos + 114 && mouseY < topPos + 138)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.construct_gui.tooltip_construct_shield"), mouseX, mouseY);
		if (mouseX > leftPos + 76 && mouseX < leftPos + 100 && mouseY > topPos + 176 && mouseY < topPos + 200)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.construct_gui.tooltip_construct_trident"), mouseX, mouseY);
		if (mouseX > leftPos + -18 && mouseX < leftPos + 6 && mouseY > topPos + 126 && mouseY < topPos + 150)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.construct_gui.tooltip_construct_armor"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("invincible_craft:textures/screens/construct_gui.png"), this.leftPos + -39, this.topPos + -47, 0, 0, 256, 256, 256, 256);

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
	}

	@Override
	public void init() {
		super.init();
		imagebutton_atom_eve_construct_sword = new ImageButton(this.leftPos + 12, this.topPos + 2, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_atom_eve_construct_sword.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ConstructGUIButtonMessage(0, x, y, z, textstate));
				ConstructGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_atom_eve_construct_sword", imagebutton_atom_eve_construct_sword);
		this.addRenderableWidget(imagebutton_atom_eve_construct_sword);
		imagebutton_atom_eve_construct_hammer = new ImageButton(this.leftPos + 106, this.topPos + -21, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_atom_eve_construct_hammer.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ConstructGUIButtonMessage(1, x, y, z, textstate));
				ConstructGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_atom_eve_construct_hammer", imagebutton_atom_eve_construct_hammer);
		this.addRenderableWidget(imagebutton_atom_eve_construct_hammer);
		imagebutton_atom_eve_construct_gauntlet = new ImageButton(this.leftPos + 175, this.topPos + 22, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_atom_eve_construct_gauntlet.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ConstructGUIButtonMessage(2, x, y, z, textstate));
				ConstructGUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_atom_eve_construct_gauntlet", imagebutton_atom_eve_construct_gauntlet);
		this.addRenderableWidget(imagebutton_atom_eve_construct_gauntlet);
		imagebutton_atom_eve_construct_shield = new ImageButton(this.leftPos + 171, this.topPos + 114, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_atom_eve_construct_shield.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ConstructGUIButtonMessage(3, x, y, z, textstate));
				ConstructGUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_atom_eve_construct_shield", imagebutton_atom_eve_construct_shield);
		this.addRenderableWidget(imagebutton_atom_eve_construct_shield);
		imagebutton_atom_eve_construct_trident = new ImageButton(this.leftPos + 76, this.topPos + 175, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_atom_eve_construct_trident.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ConstructGUIButtonMessage(4, x, y, z, textstate));
				ConstructGUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_atom_eve_construct_trident", imagebutton_atom_eve_construct_trident);
		this.addRenderableWidget(imagebutton_atom_eve_construct_trident);
		imagebutton_atom_eve_construct_armor = new ImageButton(this.leftPos + -18, this.topPos + 126, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_atom_eve_construct_armor.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ConstructGUIButtonMessage(5, x, y, z, textstate));
				ConstructGUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_atom_eve_construct_armor", imagebutton_atom_eve_construct_armor);
		this.addRenderableWidget(imagebutton_atom_eve_construct_armor);
	}
}
