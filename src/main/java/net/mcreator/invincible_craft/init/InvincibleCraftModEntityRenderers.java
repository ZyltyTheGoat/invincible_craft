
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.invincible_craft.client.renderer.ViltrumiteRenderer;
import net.mcreator.invincible_craft.client.renderer.TheGiantRenderer;
import net.mcreator.invincible_craft.client.renderer.SpyDroneOrbRenderer;
import net.mcreator.invincible_craft.client.renderer.SpikeBallEntityRenderer;
import net.mcreator.invincible_craft.client.renderer.RefugePortalRenderer;
import net.mcreator.invincible_craft.client.renderer.QuickPortalRenderer;
import net.mcreator.invincible_craft.client.renderer.PortalDashPortalRenderer;
import net.mcreator.invincible_craft.client.renderer.OrbsSpawnerPortalRenderer;
import net.mcreator.invincible_craft.client.renderer.LeapGuideRenderer;
import net.mcreator.invincible_craft.client.renderer.ExchangeCloneRenderer;
import net.mcreator.invincible_craft.client.renderer.DuplicationCloneRenderer;
import net.mcreator.invincible_craft.client.renderer.BanishmentPortalRenderer;
import net.mcreator.invincible_craft.client.renderer.BanditRenderer;
import net.mcreator.invincible_craft.client.renderer.AtomicbBlastRenderer;
import net.mcreator.invincible_craft.client.renderer.AtomEveConstructTridentProjectileRenderer;
import net.mcreator.invincible_craft.client.renderer.AtomEveAwakeningRenderer;
import net.mcreator.invincible_craft.client.renderer.ArtRosenbaumRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvincibleCraftModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(InvincibleCraftModEntities.QUICK_PORTAL.get(), QuickPortalRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.BANISHMENT_PORTAL.get(), BanishmentPortalRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.PORTAL_DASH_PORTAL.get(), PortalDashPortalRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.REFUGE_PORTAL.get(), RefugePortalRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.VILTRUMITE.get(), ViltrumiteRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.ORBS_SPAWNER_PORTAL.get(), OrbsSpawnerPortalRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.SPY_DRONE_ORB.get(), SpyDroneOrbRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.ART_ROSENBAUM.get(), ArtRosenbaumRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.BANDIT.get(), BanditRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.DUPLICATION_CLONE.get(), DuplicationCloneRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.EXCHANGE_CLONE.get(), ExchangeCloneRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.THE_GIANT.get(), TheGiantRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.LEAP_GUIDE.get(), LeapGuideRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.SPIKE_BALL_ENTITY.get(), SpikeBallEntityRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.ATOM_EVE_CONSTRUCT_TRIDENT_PROJECTILE.get(), AtomEveConstructTridentProjectileRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.ATOMICB_BLAST.get(), AtomicbBlastRenderer::new);
		event.registerEntityRenderer(InvincibleCraftModEntities.ATOM_EVE_AWAKENING.get(), AtomEveAwakeningRenderer::new);
	}
}
