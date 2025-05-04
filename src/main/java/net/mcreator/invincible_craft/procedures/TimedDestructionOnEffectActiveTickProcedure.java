package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

import javax.annotation.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class TimedDestructionOnEffectActiveTickProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double amplifier = 0;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get())) {
			amplifier = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get()) ? _livEnt.getEffect(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get()).getAmplifier() : 0;
			if (entity.level().isClientSide) {
				return; // Server-side only
			}
			Vec3 lookDirection = entity.getLookAngle();
			BlockPos centerPos = BlockPos.containing(entity.getX() + lookDirection.x * 2, entity.getY() + lookDirection.y * 2 + 1, entity.getZ() + lookDirection.z * 2);
			int radius = (int) amplifier;
			if (radius > 2) {
				radius = 2;
			}
			Random random = new Random();
			boolean blocksBroken = false; // Track if any blocks were broken
			for (int i = -radius; i <= radius; i++) {
				for (int j = -radius; j <= radius; j++) {
					for (int k = -radius; k <= radius; k++) {
						BlockPos targetPos = centerPos.offset(i, j, k);
						double distanceSquared = i * i + j * j + k * k;
						if (distanceSquared > radius * radius)
							continue;
						BlockState blockState = entity.level().getBlockState(targetPos);
						if (blockState.isAir() || blockState.getDestroySpeed(entity.level(), targetPos) < 0) {
							continue;
						}
						// SILENTLY replace block with air
						entity.level().setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
						blocksBroken = true;
						// 25% chance to spawn particles
						if (random.nextFloat() < 0.1f && entity.level() instanceof ServerLevel _level) {
							double spread = 0.5;
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SMOKE.get()), targetPos.getX() + 0.5 + random.nextGaussian() * spread, targetPos.getY() + 0.5 + random.nextGaussian() * spread,
									targetPos.getZ() + 0.5 + random.nextGaussian() * spread, 1, 0, 0, 0, 0.1);
							_level.sendParticles(ParticleTypes.CLOUD, targetPos.getX() + 0.5 + random.nextGaussian() * spread, targetPos.getY() + 0.5 + random.nextGaussian() * spread + 0.2, targetPos.getZ() + 0.5 + random.nextGaussian() * spread, 1,
									0, 0, 0, 0.15);
						}
					}
				}
			}
			// Apply screen shake if any blocks were broken (without particles)
			if (blocksBroken && entity instanceof ServerPlayer player) {
				// Create effect instance without particles
				MobEffectInstance shakeEffect = new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 3, // 3 ticks duration
						0, // amplifier
						false, // ambient
						false, // visible
						false // show particles
				);
				player.addEffect(shakeEffect);
			}
		}
	}
}
