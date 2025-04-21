
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.invincible_craft.client.particle.SparkParticle;
import net.mcreator.invincible_craft.client.particle.SonicClapParticle;
import net.mcreator.invincible_craft.client.particle.SmokeParticle;
import net.mcreator.invincible_craft.client.particle.SmallGustParticle;
import net.mcreator.invincible_craft.client.particle.SlashParticle;
import net.mcreator.invincible_craft.client.particle.PunchImpact3Particle;
import net.mcreator.invincible_craft.client.particle.PunchImpact2Particle;
import net.mcreator.invincible_craft.client.particle.PunchImpact1Particle;
import net.mcreator.invincible_craft.client.particle.GlintParticle;
import net.mcreator.invincible_craft.client.particle.ExplosionParticle;
import net.mcreator.invincible_craft.client.particle.ClawStrikeParticle;
import net.mcreator.invincible_craft.client.particle.BloodSplashParticle;
import net.mcreator.invincible_craft.client.particle.BloodSplash4Particle;
import net.mcreator.invincible_craft.client.particle.BloodSplash3Particle;
import net.mcreator.invincible_craft.client.particle.BloodSplash2Particle;
import net.mcreator.invincible_craft.client.particle.BloodLandParticle;
import net.mcreator.invincible_craft.client.particle.BloodFallParticle;
import net.mcreator.invincible_craft.client.particle.BiteParticle;
import net.mcreator.invincible_craft.client.particle.BattleBeastRoar5Particle;
import net.mcreator.invincible_craft.client.particle.BattleBeastRoar4Particle;
import net.mcreator.invincible_craft.client.particle.BattleBeastRoar3Particle;
import net.mcreator.invincible_craft.client.particle.BattleBeastRoar2Particle;
import net.mcreator.invincible_craft.client.particle.BattleBeastRoar1Particle;
import net.mcreator.invincible_craft.client.particle.AtomicBlastShockwaveParticle;
import net.mcreator.invincible_craft.client.particle.AtomicBlastChargeParticle;
import net.mcreator.invincible_craft.client.particle.AtomEveCloudParticle;
import net.mcreator.invincible_craft.client.particle.AtomEveBubbleBreakParticle;
import net.mcreator.invincible_craft.client.particle.AtomEveBubbleBreak4Particle;
import net.mcreator.invincible_craft.client.particle.AtomEveBubbleBreak3Particle;
import net.mcreator.invincible_craft.client.particle.AtomEveBubbleBreak2Particle;
import net.mcreator.invincible_craft.client.particle.AtomEveBigBlastParticle;
import net.mcreator.invincible_craft.client.particle.AtomEveAwakeningCloudParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvincibleCraftModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get(), PunchImpact1Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get(), PunchImpact2Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.PUNCH_IMPACT_3.get(), PunchImpact3Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.SMALL_GUST.get(), SmallGustParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.SONIC_CLAP.get(), SonicClapParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BLOOD_FALL.get(), BloodFallParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BLOOD_LAND.get(), BloodLandParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BLOOD_SPLASH.get(), BloodSplashParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BLOOD_SPLASH_2.get(), BloodSplash2Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BLOOD_SPLASH_3.get(), BloodSplash3Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BLOOD_SPLASH_4.get(), BloodSplash4Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.SPARK.get(), SparkParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.GLINT.get(), GlintParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.EXPLOSION.get(), ExplosionParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.SMOKE.get(), SmokeParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.CLAW_STRIKE.get(), ClawStrikeParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK.get(), AtomEveBubbleBreakParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK_2.get(), AtomEveBubbleBreak2Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK_3.get(), AtomEveBubbleBreak3Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK_4.get(), AtomEveBubbleBreak4Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_1.get(), BattleBeastRoar1Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_2.get(), BattleBeastRoar2Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_3.get(), BattleBeastRoar3Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_4.get(), BattleBeastRoar4Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_5.get(), BattleBeastRoar5Particle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.SLASH.get(), SlashParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.BITE.get(), BiteParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get(), AtomEveCloudParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOMIC_BLAST_CHARGE.get(), AtomicBlastChargeParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOMIC_BLAST_SHOCKWAVE.get(), AtomicBlastShockwaveParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_BIG_BLAST.get(), AtomEveBigBlastParticle::provider);
		event.registerSpriteSet(InvincibleCraftModParticleTypes.ATOM_EVE_AWAKENING_CLOUD.get(), AtomEveAwakeningCloudParticle::provider);
	}
}
