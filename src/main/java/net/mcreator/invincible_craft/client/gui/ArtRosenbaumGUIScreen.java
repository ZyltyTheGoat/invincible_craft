package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.ArtRosenbaumGUIMenu;
import net.mcreator.invincible_craft.network.ArtRosenbaumGUIButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ArtRosenbaumGUIScreen extends AbstractContainerScreen<ArtRosenbaumGUIMenu> {
	private final static HashMap<String, Object> guistate = ArtRosenbaumGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_art_armor_invincibleog;
	ImageButton imagebutton_art_armor_omniman;
	ImageButton imagebutton_art_armor_invincibleseason3;
	ImageButton imagebutton_art_armor_invinciblecomic;
	ImageButton imagebutton_art_armor_invinciblesinister;
	ImageButton imagebutton_art_armor_invinciblemohawk;
	ImageButton imagebutton_art_armor_duplikate;

	public ArtRosenbaumGUIScreen(ArtRosenbaumGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/art_rosenbaum_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 15 && mouseX < leftPos + 39 && mouseY > topPos + 16 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_invincible_suit_og"), mouseX, mouseY);
		if (mouseX > leftPos + 51 && mouseX < leftPos + 75 && mouseY > topPos + 16 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_omniman_suit"), mouseX, mouseY);
		if (mouseX > leftPos + 96 && mouseX < leftPos + 120 && mouseY > topPos + 16 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_invincible_suit_season_3"), mouseX, mouseY);
		if (mouseX > leftPos + 132 && mouseX < leftPos + 156 && mouseY > topPos + 16 && mouseY < topPos + 40)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_invincible_suit_comic"), mouseX, mouseY);
		if (mouseX > leftPos + 15 && mouseX < leftPos + 39 && mouseY > topPos + 43 && mouseY < topPos + 67)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_invincible_suit_season_31"), mouseX, mouseY);
		if (mouseX > leftPos + 51 && mouseX < leftPos + 75 && mouseY > topPos + 43 && mouseY < topPos + 67)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_invincible_suit_season_32"), mouseX, mouseY);
		if (mouseX > leftPos + 96 && mouseX < leftPos + 120 && mouseY > topPos + 43 && mouseY < topPos + 67)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.tooltip_make_invincible_suit_season_33"), mouseX, mouseY);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui.label_sslsuit_shop"), 64, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_art_armor_invincibleog = new ImageButton(this.leftPos + 15, this.topPos + 16, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_invincibleog.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIButtonMessage(0, x, y, z, textstate));
				ArtRosenbaumGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_armor_invincibleog", imagebutton_art_armor_invincibleog);
		this.addRenderableWidget(imagebutton_art_armor_invincibleog);
		imagebutton_art_armor_omniman = new ImageButton(this.leftPos + 51, this.topPos + 16, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_omniman.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIButtonMessage(1, x, y, z, textstate));
				ArtRosenbaumGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_armor_omniman", imagebutton_art_armor_omniman);
		this.addRenderableWidget(imagebutton_art_armor_omniman);
		imagebutton_art_armor_invincibleseason3 = new ImageButton(this.leftPos + 96, this.topPos + 16, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_invincibleseason3.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIButtonMessage(2, x, y, z, textstate));
				ArtRosenbaumGUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_armor_invincibleseason3", imagebutton_art_armor_invincibleseason3);
		this.addRenderableWidget(imagebutton_art_armor_invincibleseason3);
		imagebutton_art_armor_invinciblecomic = new ImageButton(this.leftPos + 132, this.topPos + 16, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_invinciblecomic.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIButtonMessage(3, x, y, z, textstate));
				ArtRosenbaumGUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_armor_invinciblecomic", imagebutton_art_armor_invinciblecomic);
		this.addRenderableWidget(imagebutton_art_armor_invinciblecomic);
		imagebutton_art_armor_invinciblesinister = new ImageButton(this.leftPos + 15, this.topPos + 43, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_invinciblesinister.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIButtonMessage(4, x, y, z, textstate));
				ArtRosenbaumGUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_armor_invinciblesinister", imagebutton_art_armor_invinciblesinister);
		this.addRenderableWidget(imagebutton_art_armor_invinciblesinister);
		imagebutton_art_armor_invinciblemohawk = new ImageButton(this.leftPos + 51, this.topPos + 43, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_invinciblemohawk.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIButtonMessage(5, x, y, z, textstate));
				ArtRosenbaumGUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_armor_invinciblemohawk", imagebutton_art_armor_invinciblemohawk);
		this.addRenderableWidget(imagebutton_art_armor_invinciblemohawk);
		imagebutton_art_armor_duplikate = new ImageButton(this.leftPos + 96, this.topPos + 43, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_armor_duplikate.png"), 24, 48, e -> {
		});
		guistate.put("button:imagebutton_art_armor_duplikate", imagebutton_art_armor_duplikate);
		this.addRenderableWidget(imagebutton_art_armor_duplikate);
	}
}
