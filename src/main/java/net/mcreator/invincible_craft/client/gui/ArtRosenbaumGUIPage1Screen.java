package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.ArtRosenbaumGUIPage1Menu;
import net.mcreator.invincible_craft.procedures.DisplayPreviousPageProcedure;
import net.mcreator.invincible_craft.procedures.DisplayNextPageProcedure;
import net.mcreator.invincible_craft.network.ArtRosenbaumGUIPage1ButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ArtRosenbaumGUIPage1Screen extends AbstractContainerScreen<ArtRosenbaumGUIPage1Menu> {
	private final static HashMap<String, Object> guistate = ArtRosenbaumGUIPage1Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_art_icon_invincible_og;
	ImageButton imagebutton_art_icon_invincible_season3;
	ImageButton imagebutton_art_icon_invincible_sinister_mar;
	ImageButton imagebutton_art_icon_mohawk_mark;
	ImageButton imagebutton_art_icon_invincible_unmasked_mar;
	ImageButton imagebutton_art_icon_invincible_omni_mark;
	ImageButton imagebutton_art_icon_no_goggles_mark;
	ImageButton imagebutton_stat_2;
	ImageButton imagebutton_stat;

	public ArtRosenbaumGUIPage1Screen(ArtRosenbaumGUIPage1Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/art_rosenbaum_gui_page_1.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 15 && mouseX < leftPos + 39 && mouseY > topPos + 25 && mouseY < topPos + 49)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_original"), mouseX, mouseY);
		if (mouseX > leftPos + 51 && mouseX < leftPos + 75 && mouseY > topPos + 25 && mouseY < topPos + 49)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_season_3"), mouseX, mouseY);
		if (mouseX > leftPos + 96 && mouseX < leftPos + 120 && mouseY > topPos + 25 && mouseY < topPos + 49)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_sinister_m"), mouseX, mouseY);
		if (mouseX > leftPos + 132 && mouseX < leftPos + 156 && mouseY > topPos + 25 && mouseY < topPos + 49)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_mohawk_mar"), mouseX, mouseY);
		if (mouseX > leftPos + 33 && mouseX < leftPos + 57 && mouseY > topPos + 52 && mouseY < topPos + 76)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_unmasked_m"), mouseX, mouseY);
		if (mouseX > leftPos + 74 && mouseX < leftPos + 98 && mouseY > topPos + 52 && mouseY < topPos + 76)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_omnimark"), mouseX, mouseY);
		if (mouseX > leftPos + 114 && mouseX < leftPos + 138 && mouseY > topPos + 52 && mouseY < topPos + 76)
			guiGraphics.renderTooltip(font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.tooltip_make_invincible_suit_no_goggles"), mouseX, mouseY);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_1.label_suit_shop"), 60, 7, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_art_icon_invincible_og = new ImageButton(this.leftPos + 15, this.topPos + 25, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_invincible_og.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(0, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_invincible_og", imagebutton_art_icon_invincible_og);
		this.addRenderableWidget(imagebutton_art_icon_invincible_og);
		imagebutton_art_icon_invincible_season3 = new ImageButton(this.leftPos + 51, this.topPos + 25, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_invincible_season3.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(1, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_invincible_season3", imagebutton_art_icon_invincible_season3);
		this.addRenderableWidget(imagebutton_art_icon_invincible_season3);
		imagebutton_art_icon_invincible_sinister_mar = new ImageButton(this.leftPos + 96, this.topPos + 25, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_invincible_sinister_mar.png"), 24, 48,
				e -> {
					if (true) {
						InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(2, x, y, z, textstate));
						ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
					}
				});
		guistate.put("button:imagebutton_art_icon_invincible_sinister_mar", imagebutton_art_icon_invincible_sinister_mar);
		this.addRenderableWidget(imagebutton_art_icon_invincible_sinister_mar);
		imagebutton_art_icon_mohawk_mark = new ImageButton(this.leftPos + 132, this.topPos + 25, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_mohawk_mark.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(3, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_mohawk_mark", imagebutton_art_icon_mohawk_mark);
		this.addRenderableWidget(imagebutton_art_icon_mohawk_mark);
		imagebutton_art_icon_invincible_unmasked_mar = new ImageButton(this.leftPos + 33, this.topPos + 52, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_invincible_unmasked_mar.png"), 24, 48,
				e -> {
					if (true) {
						InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(4, x, y, z, textstate));
						ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
					}
				});
		guistate.put("button:imagebutton_art_icon_invincible_unmasked_mar", imagebutton_art_icon_invincible_unmasked_mar);
		this.addRenderableWidget(imagebutton_art_icon_invincible_unmasked_mar);
		imagebutton_art_icon_invincible_omni_mark = new ImageButton(this.leftPos + 74, this.topPos + 52, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_invincible_omni_mark.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(5, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_invincible_omni_mark", imagebutton_art_icon_invincible_omni_mark);
		this.addRenderableWidget(imagebutton_art_icon_invincible_omni_mark);
		imagebutton_art_icon_no_goggles_mark = new ImageButton(this.leftPos + 114, this.topPos + 52, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_no_goggles_mark.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(6, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_no_goggles_mark", imagebutton_art_icon_no_goggles_mark);
		this.addRenderableWidget(imagebutton_art_icon_no_goggles_mark);
		imagebutton_stat_2 = new ImageButton(this.leftPos + 150, this.topPos + 57, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_2.png"), 16, 32, e -> {
			if (DisplayNextPageProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(7, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (DisplayNextPageProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat_2", imagebutton_stat_2);
		this.addRenderableWidget(imagebutton_stat_2);
		imagebutton_stat = new ImageButton(this.leftPos + 6, this.topPos + 57, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat.png"), 16, 32, e -> {
			if (DisplayPreviousPageProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage1ButtonMessage(8, x, y, z, textstate));
				ArtRosenbaumGUIPage1ButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (DisplayPreviousPageProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat", imagebutton_stat);
		this.addRenderableWidget(imagebutton_stat);
	}
}
