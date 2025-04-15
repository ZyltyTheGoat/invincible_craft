package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.StatPanelGUIMenu;
import net.mcreator.invincible_craft.procedures.ReturnXPProcedure;
import net.mcreator.invincible_craft.procedures.ReturnVitalityProcedure;
import net.mcreator.invincible_craft.procedures.ReturnStrengthProcedure;
import net.mcreator.invincible_craft.procedures.ReturnStrengthAdjustmentProcedure;
import net.mcreator.invincible_craft.procedures.ReturnSpeedProcedure;
import net.mcreator.invincible_craft.procedures.ReturnSpeedAdjustmentProcedure;
import net.mcreator.invincible_craft.procedures.ReturnSPProcedure;
import net.mcreator.invincible_craft.procedures.ReturnPowerProcedure;
import net.mcreator.invincible_craft.procedures.ReturnNameProcedure;
import net.mcreator.invincible_craft.procedures.ReturnLevelProcedure;
import net.mcreator.invincible_craft.procedures.ReturnIntelligenceProcedure;
import net.mcreator.invincible_craft.procedures.ReturnFlightAdjustmentProcedure;
import net.mcreator.invincible_craft.procedures.ReturnAgeProcedure;
import net.mcreator.invincible_craft.procedures.AdjStrengthUpConProcedure;
import net.mcreator.invincible_craft.procedures.AdjStrengthDownConProcedure;
import net.mcreator.invincible_craft.procedures.AdjSpeedUpConProcedure;
import net.mcreator.invincible_craft.procedures.AdjSpeedDownConProcedure;
import net.mcreator.invincible_craft.procedures.AdjFlightUpConProcedure;
import net.mcreator.invincible_craft.procedures.AdjFlightDownConProcedure;
import net.mcreator.invincible_craft.network.StatPanelGUIButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class StatPanelGUIScreen extends AbstractContainerScreen<StatPanelGUIMenu> {
	private final static HashMap<String, Object> guistate = StatPanelGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_stat_plus;
	ImageButton imagebutton_stat_plus1;
	ImageButton imagebutton_stat_plus2;
	ImageButton imagebutton_stat_plus3;
	ImageButton imagebutton_stat;
	ImageButton imagebutton_stat_2;
	ImageButton imagebutton_stat1;
	ImageButton imagebutton_stat_21;
	ImageButton imagebutton_stat2;
	ImageButton imagebutton_stat_22;

	public StatPanelGUIScreen(StatPanelGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 196;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/stat_panel_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 16 && mouseX < leftPos + 40 && mouseY > topPos + 53 && mouseY < topPos + 77)
			guiGraphics.renderTooltip(font, Component.literal(ReturnXPProcedure.execute(entity)), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("invincible_craft:textures/screens/stat_image_strength.png"), this.leftPos + 15, this.topPos + 107, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("invincible_craft:textures/screens/stat_image_speed.png"), this.leftPos + 15, this.topPos + 125, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("invincible_craft:textures/screens/stat_image_vitality.png"), this.leftPos + 15, this.topPos + 143, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("invincible_craft:textures/screens/stat_image_intelligence.png"), this.leftPos + 15, this.topPos + 161, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("invincible_craft:textures/screens/test.png"), this.leftPos + 179, this.topPos + 0, 0, 0, 106, 126, 106, 126);

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
		guiGraphics.drawString(this.font,

				ReturnStrengthProcedure.execute(entity), 33, 111, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.stat_panel_gui.label_sslplayer_info"), 51, 13, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnNameProcedure.execute(entity), 15, 31, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnPowerProcedure.execute(entity), 15, 49, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnAgeProcedure.execute(entity), 15, 40, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.stat_panel_gui.label_stats"), 15, 85, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSpeedProcedure.execute(entity), 33, 130, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnVitalityProcedure.execute(entity), 33, 148, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.stat_panel_gui.label_reputation"), 15, 67, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnIntelligenceProcedure.execute(entity), 33, 166, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnLevelProcedure.execute(entity), 15, 58, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSPProcedure.execute(entity), 15, 94, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.stat_panel_gui.label_ssladjust_speed"), 193, 13, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.stat_panel_gui.label_adjust_movement_speed"), 184, 51, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.invincible_craft.stat_panel_gui.label_adjust_flight_speed"), 183, 86, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnStrengthAdjustmentProcedure.execute(entity), 216, 33, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnSpeedAdjustmentProcedure.execute(entity), 217, 71, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnFlightAdjustmentProcedure.execute(entity), 217, 106, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_stat_plus = new ImageButton(this.leftPos + 132, this.topPos + 107, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_plus.png"), 16, 32, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(0, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_stat_plus", imagebutton_stat_plus);
		this.addRenderableWidget(imagebutton_stat_plus);
		imagebutton_stat_plus1 = new ImageButton(this.leftPos + 132, this.topPos + 125, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_plus1.png"), 16, 32, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(1, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_stat_plus1", imagebutton_stat_plus1);
		this.addRenderableWidget(imagebutton_stat_plus1);
		imagebutton_stat_plus2 = new ImageButton(this.leftPos + 132, this.topPos + 143, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_plus2.png"), 16, 32, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(2, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_stat_plus2", imagebutton_stat_plus2);
		this.addRenderableWidget(imagebutton_stat_plus2);
		imagebutton_stat_plus3 = new ImageButton(this.leftPos + 132, this.topPos + 161, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_plus3.png"), 16, 32, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(3, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_stat_plus3", imagebutton_stat_plus3);
		this.addRenderableWidget(imagebutton_stat_plus3);
		imagebutton_stat = new ImageButton(this.leftPos + 195, this.topPos + 27, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat.png"), 16, 32, e -> {
			if (AdjStrengthDownConProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(4, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (AdjStrengthDownConProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat", imagebutton_stat);
		this.addRenderableWidget(imagebutton_stat);
		imagebutton_stat_2 = new ImageButton(this.leftPos + 251, this.topPos + 27, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_2.png"), 16, 32, e -> {
			if (AdjStrengthUpConProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(5, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (AdjStrengthUpConProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat_2", imagebutton_stat_2);
		this.addRenderableWidget(imagebutton_stat_2);
		imagebutton_stat1 = new ImageButton(this.leftPos + 195, this.topPos + 65, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat1.png"), 16, 32, e -> {
			if (AdjSpeedDownConProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(6, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (AdjSpeedDownConProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat1", imagebutton_stat1);
		this.addRenderableWidget(imagebutton_stat1);
		imagebutton_stat_21 = new ImageButton(this.leftPos + 251, this.topPos + 65, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_21.png"), 16, 32, e -> {
			if (AdjSpeedUpConProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(7, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (AdjSpeedUpConProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat_21", imagebutton_stat_21);
		this.addRenderableWidget(imagebutton_stat_21);
		imagebutton_stat2 = new ImageButton(this.leftPos + 195, this.topPos + 100, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat2.png"), 16, 32, e -> {
			if (AdjFlightDownConProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(8, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (AdjFlightDownConProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat2", imagebutton_stat2);
		this.addRenderableWidget(imagebutton_stat2);
		imagebutton_stat_22 = new ImageButton(this.leftPos + 252, this.topPos + 100, 16, 16, 0, 0, 16, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_stat_22.png"), 16, 32, e -> {
			if (AdjFlightUpConProcedure.execute(entity)) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelGUIButtonMessage(9, x, y, z, textstate));
				StatPanelGUIButtonMessage.handleButtonAction(entity, 9, x, y, z, textstate);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (AdjFlightUpConProcedure.execute(entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_stat_22", imagebutton_stat_22);
		this.addRenderableWidget(imagebutton_stat_22);
	}
}
