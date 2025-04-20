package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.ArtRosenbaumGUIPage2Menu;
import net.mcreator.invincible_craft.procedures.DisplayPreviousPageProcedure;
import net.mcreator.invincible_craft.procedures.DisplayNextPageProcedure;
import net.mcreator.invincible_craft.network.ArtRosenbaumGUIPage2ButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ArtRosenbaumGUIPage2Screen extends AbstractContainerScreen<ArtRosenbaumGUIPage2Menu> {
	private final static HashMap<String, Object> guistate = ArtRosenbaumGUIPage2Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_stat_2;
	ImageButton imagebutton_stat;
	ImageButton imagebutton_art_icon_omni_man;
	ImageButton imagebutton_art_icon_dupli_kate;

	public ArtRosenbaumGUIPage2Screen(ArtRosenbaumGUIPage2Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/art_rosenbaum_gui_page_2.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.art_rosenbaum_gui_page_2.label_suit_shop"), 60, 7, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_stat_2 = new ImageButton(this.leftPos + 150, this.topPos + 57, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_2.png"), 16, 32, e -> {
			if (DisplayNextPageProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage2ButtonMessage(0, x, y, z, textstate));
				ArtRosenbaumGUIPage2ButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
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
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage2ButtonMessage(1, x, y, z, textstate));
				ArtRosenbaumGUIPage2ButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
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
		imagebutton_art_icon_omni_man = new ImageButton(this.leftPos + 51, this.topPos + 25, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_omni_man.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage2ButtonMessage(2, x, y, z, textstate));
				ArtRosenbaumGUIPage2ButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_omni_man", imagebutton_art_icon_omni_man);
		this.addRenderableWidget(imagebutton_art_icon_omni_man);
		imagebutton_art_icon_dupli_kate = new ImageButton(this.leftPos + 87, this.topPos + 25, 24, 24, 0, 0, 24, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_art_icon_dupli_kate.png"), 24, 48, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new ArtRosenbaumGUIPage2ButtonMessage(3, x, y, z, textstate));
				ArtRosenbaumGUIPage2ButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_art_icon_dupli_kate", imagebutton_art_icon_dupli_kate);
		this.addRenderableWidget(imagebutton_art_icon_dupli_kate);
	}
}
