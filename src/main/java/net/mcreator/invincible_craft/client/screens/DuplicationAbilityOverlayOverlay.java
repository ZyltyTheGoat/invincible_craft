
package net.mcreator.invincible_craft.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.procedures.ReturnValueCooldown4Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown3Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown2Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown1Procedure;
import net.mcreator.invincible_craft.procedures.DuplicationAbilityOverlayDisplayOverlayInGameProcedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility4Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility3Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility2Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility1Procedure;
import net.mcreator.invincible_craft.procedures.DisplayCloneProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class DuplicationAbilityOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		if (DuplicationAbilityOverlayDisplayOverlayInGameProcedure.execute(entity)) {
			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_duplication_clone.png"), 4, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_duplication_displacement.png"), 37, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_duplication_merge.png"), 70, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_duplication_exchange.png"), 103, 4, 0, 0, 32, 32, 32, 32);

			if (DisplayCloneProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/cooldown.png"), 4, 4, 0, 0, 32, 32, 32, 32);
			}
			if (DoesHaveCDAbility2Procedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/cooldown.png"), 37, 4, 0, 0, 32, 32, 32, 32);
			}
			if (DoesHaveCDAbility3Procedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/cooldown.png"), 70, 4, 0, 0, 32, 32, 32, 32);
			}
			if (DoesHaveCDAbility4Procedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/cooldown.png"), 103, 4, 0, 0, 32, 32, 32, 32);
			}
			if (DoesHaveCDAbility1Procedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						ReturnValueCooldown1Procedure.execute(entity), 13, 15, -65536, false);
			if (DoesHaveCDAbility2Procedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						ReturnValueCooldown2Procedure.execute(entity), 46, 15, -65536, false);
			if (DoesHaveCDAbility3Procedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						ReturnValueCooldown3Procedure.execute(entity), 79, 15, -65536, false);
			if (DoesHaveCDAbility4Procedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						ReturnValueCooldown4Procedure.execute(entity), 112, 15, -65536, false);
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
