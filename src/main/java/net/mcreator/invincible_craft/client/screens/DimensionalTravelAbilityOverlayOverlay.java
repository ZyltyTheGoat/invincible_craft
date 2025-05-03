
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

import net.mcreator.invincible_craft.procedures.ReturnValueCooldown5Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown4Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown3Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown2Procedure;
import net.mcreator.invincible_craft.procedures.ReturnValueCooldown1Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility5Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility4Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility3Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility2Procedure;
import net.mcreator.invincible_craft.procedures.DoesHaveCDAbility1Procedure;
import net.mcreator.invincible_craft.procedures.DimensionalTravelAbilityOverlayDisplayOverlayIngameProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class DimensionalTravelAbilityOverlayOverlay {
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
		if (DimensionalTravelAbilityOverlayDisplayOverlayIngameProcedure.execute(entity)) {
			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_dimensional_travel_quick_travel.png"), 4, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_dimensional_travel_banishment.png"), 37, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_dimensional_traveldash_portal.png"), 70, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_dimensional_travel_spy_drone.png"), 103, 4, 0, 0, 32, 32, 32, 32);

			event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/ability_dimensional_travel_refuge.png"), 136, 4, 0, 0, 32, 32, 32, 32);

			if (DoesHaveCDAbility1Procedure.execute(entity)) {
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
			if (DoesHaveCDAbility5Procedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("invincible_craft:textures/screens/cooldown.png"), 136, 4, 0, 0, 32, 32, 32, 32);
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
			if (DoesHaveCDAbility5Procedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						ReturnValueCooldown5Procedure.execute(entity), 145, 15, -3407872, false);
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
