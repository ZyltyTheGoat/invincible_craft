package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.Map;
import java.util.List;
import java.util.Comparator;

public class SonicClapAttackProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double yaw = 0;
		double pitch = 0;
		double delay = 0;
		double sx = 0;
		if (entity instanceof LivingEntity _entity)
			_entity.swing(InteractionHand.MAIN_HAND, true);
		entity.getPersistentData().putDouble("SonicClapRepeat", 0);
		entity.getPersistentData().putDouble("SonicClapX", (entity.getLookAngle().x));
		entity.getPersistentData().putDouble("SonicClapY", (entity.getLookAngle().y));
		entity.getPersistentData().putDouble("SonicClapZ", (entity.getLookAngle().z));
		for (int index0 = 0; index0 < 20; index0++) {
			InvincibleCraftMod.queueServerWork((int) delay, () -> {
				entity.getPersistentData().putDouble("SonicClapRepeat", (entity.getPersistentData().getDouble("SonicClapRepeat") + 2));
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SONIC_CLAP.get()), (entity.getX() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapX")),
							(entity.getY() + 1.6 + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapY")),
							(entity.getZ() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapZ")), 1, 0, 0, 0, 0);
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.EXPLOSION, (entity.getX() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapX")),
							(entity.getY() + 1.6 + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapY")),
							(entity.getZ() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapZ")), 2, 1, 1, 1, 0);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null,
								BlockPos.containing(entity.getX() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapX"),
										entity.getY() + 1.6 + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapY"),
										entity.getZ() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapZ")),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, (float) 1.6);
					} else {
						_level.playLocalSound((entity.getX() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapX")),
								(entity.getY() + 1.6 + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapY")),
								(entity.getZ() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapZ")), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")),
								SoundSource.NEUTRAL, 1, (float) 1.6, false);
					}
				}
				// Calculate center position
				BlockPos centerPos = BlockPos.containing(entity.getX() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapX"),
						entity.getY() + 1.6 + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapY"),
						entity.getZ() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapZ"));
				// Check all blocks in 6-block radius sphere
				int radius = 6;
				RandomSource random = RandomSource.create(); // Create a random instance
				for (int x = -radius; x <= radius; x++) {
					for (int y = -radius; y <= radius; y++) {
						for (int z = -radius; z <= radius; z++) {
							// Only process blocks within spherical radius
							if (x * x + y * y + z * z <= radius * radius) {
								BlockPos checkPos = centerPos.offset(x, y, z);
								BlockState blockState = world.getBlockState(checkPos);
								Block block = blockState.getBlock();
								// Destroy leaves and glass (replace with air)
								if (block instanceof LeavesBlock || block instanceof AbstractGlassBlock) {
									// Play breaking sound
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, checkPos, blockState.getSoundType().getBreakSound(), SoundSource.BLOCKS, 1.0F, 0.8F + random.nextFloat() * 0.4F); // Use local random
										}
									}
									// Show breaking particles
									if (world instanceof ServerLevel _level) {
										_level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), checkPos.getX() + 0.5, checkPos.getY() + 0.5, checkPos.getZ() + 0.5, 10, 0.5, 0.5, 0.5, 0.05);
									}
									world.setBlock(checkPos, Blocks.AIR.defaultBlockState(), 3);
								}
								// Replace grass blocks with dirt
								else if (block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH || block == Blocks.MYCELIUM || block == Blocks.PODZOL) {
									// Play digging sound
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, checkPos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.7F, 0.8F + random.nextFloat() * 0.4F); // Use local random
										}
									}
									BlockState _bs = Blocks.DIRT.defaultBlockState();
									// Preserve block properties
									BlockState _bso = world.getBlockState(checkPos);
									for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
										Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
										if (_property != null && _bs.getValue(_property) != null) {
											try {
												_bs = _bs.setValue(_property, (Comparable) entry.getValue());
											} catch (Exception e) {
												// Silent catch
											}
										}
									}
									world.setBlock(checkPos, _bs, 3);
								}
							}
						}
					}
				}
				{
					final Vec3 _center = new Vec3((entity.getX() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapX")),
							(entity.getY() + 1.6 + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapY")),
							(entity.getZ() + entity.getPersistentData().getDouble("SonicClapRepeat") * entity.getPersistentData().getDouble("SonicClapZ")));
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator) && !(entityiterator instanceof ExperienceOrb) && !(entityiterator instanceof ItemEntity)) {
							entityiterator.hurt(
									new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:sonic_clap_damage"))), entity),
									(float) (6 + (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_strength * 0.2));
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 20, 1, false, false));
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 20, 0, false, false));
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get(), 2, 3, false, false));
						}
					}
				}
			});
			delay = delay + 0.5;
			sx = -3;
		}
	}
}
