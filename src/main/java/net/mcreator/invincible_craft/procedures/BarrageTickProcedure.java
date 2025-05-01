package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class BarrageTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double random = 0;
		if (entity.getPersistentData().getBoolean("barrage")) {
			if (entity.getPersistentData().getDouble("barrage_timer") < 40) {
				entity.getPersistentData().putDouble("barrage_timer", (entity.getPersistentData().getDouble("barrage_timer") + 1));
				dx = entity.getX() + 1.5 * entity.getLookAngle().x;
				dy = entity.getY() + 1.5 * entity.getLookAngle().y;
				dz = entity.getZ() + 1.5 * entity.getLookAngle().z;
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 3, 0, false, false));
				if (entity.getPersistentData().getDouble("barrage_timer") % 4 == 0) {
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					{
						final Vec3 _center = new Vec3(dx, dy, dz);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3.5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity)) {
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage"))), entity),
										(float) (1 + (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_strength * 0.04));
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entityiterator.getX() + Mth.nextDouble(RandomSource.create(), -1, 1)),
											(entityiterator.getY() + entityiterator.getBbHeight() / 2 + Mth.nextDouble(RandomSource.create(), -1, 1)), (entityiterator.getZ() + Mth.nextDouble(RandomSource.create(), -1, 1)), 1, 0, 0, 0, 0);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
								entityiterator.setDeltaMovement(new Vec3(0, 0.1, 0));
								entity.invulnerableTime = 0;
							}
						}
					}
				}
				if (entity.getPersistentData().getDouble("barrage_timer") % 5 == 0) {
					if ((entity.getPersistentData().getString("lastBarragePunch")).equals("") || (entity.getPersistentData().getString("lastBarragePunch")).equals("left")) {
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "punch_right", true);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											InvincibleCraftMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.InvincibleCraftModAnimationMessage(Component.literal("punch_right"), entity.getId(), true), connection,
													NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						entity.getPersistentData().putString("lastBarragePunch", "right");
					} else {
						if (world.isClientSide()) {
							SetupAnimationsProcedure.setAnimationClientside((Player) entity, "punch_left", true);
						}
						if (!world.isClientSide()) {
							if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
								List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
								synchronized (connections) {
									Iterator<Connection> iterator = connections.iterator();
									while (iterator.hasNext()) {
										Connection connection = iterator.next();
										if (!connection.isConnecting() && connection.isConnected())
											InvincibleCraftMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.InvincibleCraftModAnimationMessage(Component.literal("punch_left"), entity.getId(), true), connection, NetworkDirection.PLAY_TO_CLIENT);
									}
								}
							}
						}
						entity.getPersistentData().putString("lastBarragePunch", "left");
					}
				}
			} else {
				entity.getPersistentData().putBoolean("barrage", false);
				entity.getPersistentData().putDouble("barrage_timer", 0);
				{
					double _setval = 5;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ability_cooldown_3 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				ViltrumitePunchAbilityProcedure.execute(world, x, y, z, entity);
			}
		} else if (entity.getPersistentData().getBoolean("crush")) {
			if (entity.getPersistentData().getDouble("crush_timer") < entity.getPersistentData().getDouble("crush_max_timer")) {
				entity.getPersistentData().putDouble("crush_timer", (entity.getPersistentData().getDouble("crush_timer") + 1));
				if (entity.getPersistentData().getDouble("crush_timer") % 15 == 0) {
					random = Mth.nextInt(RandomSource.create(), 1, 3);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 3, 0, false, false));
					if (random == 1) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_1")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_1")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					} else if (random == 2) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_2")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_2")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					} else {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_3")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_3")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity)
									&& (entityiterator.getStringUUID()).equals((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding_entity)) {
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage"))), entity),
										(float) ((entity instanceof LivingEntity _livingEntity55 && _livingEntity55.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity55.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												/ 5));
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
							}
						}
					}
				}
			} else {
				entity.getPersistentData().putBoolean("crush", false);
				entity.getPersistentData().putDouble("crush_timer", 0);
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (entityiterator instanceof LivingEntity && !(entityiterator == entity)
								&& (entityiterator.getStringUUID()).equals((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding_entity)) {
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 120, 0.25,
										0.25, 0.25, 0.4);
							random = Mth.nextInt(RandomSource.create(), 1, 3);
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 7, 0, false, false));
							if (random == 1) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_1")), SoundSource.NEUTRAL, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_1")), SoundSource.NEUTRAL, 1, 1, false);
									}
								}
							} else if (random == 2) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_2")), SoundSource.NEUTRAL, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_2")), SoundSource.NEUTRAL, 1, 1, false);
									}
								}
							} else {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_3")), SoundSource.NEUTRAL, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_3")), SoundSource.NEUTRAL, 1, 1, false);
									}
								}
							}
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage"))), entity),
									entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
						}
					}
				}
			}
		}
	}
}
