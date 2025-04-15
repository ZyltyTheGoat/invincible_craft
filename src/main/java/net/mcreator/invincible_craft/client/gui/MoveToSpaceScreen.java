package net.mcreator.invincible_craft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.invincible_craft.world.inventory.MoveToSpaceMenu;
import net.mcreator.invincible_craft.network.MoveToSpaceButtonMessage;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MoveToSpaceScreen extends AbstractContainerScreen<MoveToSpaceMenu> {
	private final static HashMap<String, Object> guistate = MoveToSpaceMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	ImageButton imagebutton_go_space;

	public MoveToSpaceScreen(MoveToSpaceMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 40;
		this.imageHeight = 40;
	}

	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/screens/move_to_space.png");

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
	}

	@Override
	public void init() {
		super.init();
		imagebutton_go_space = new ImageButton(this.leftPos + 3, this.topPos + 3, 32, 32, 0, 0, 32, new ResourceLocation("invincible_craft:textures/screens/atlas/imagebutton_go_space.png"), 32, 64, e -> {
			if (true) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new MoveToSpaceButtonMessage(0, x, y, z, textstate));
				MoveToSpaceButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_go_space", imagebutton_go_space);
		this.addRenderableWidget(imagebutton_go_space);
	}
}
