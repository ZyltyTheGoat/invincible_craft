package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.TamableAnimal;
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

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class HeavypunchProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double distance = 0;
		double dX = 0;
		double dY = 0;
		double dZ = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double random = 0;
		if (entity.getPersistentData().getBoolean("punch_charging")) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 5, 1));
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:punch")), SoundSource.NEUTRAL, 5, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:punch")), SoundSource.NEUTRAL, 5, 1, false);
				}
			}
			if (entity.getPersistentData().getDouble("punch_charge") < 20) {
				if (entity.isShiftKeyDown()) {
					ViltrumitePunchAbilityProcedure.execute(world, x, y, z, entity);
				}
			} else if (entity.getPersistentData().getDouble("punch_charge") < 40) {
				if (entity.isShiftKeyDown()) {
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
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
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:punch")), SoundSource.PLAYERS, 3, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:punch")), SoundSource.PLAYERS, 3, 1, false);
						}
					}
					{
						double _setval = 5;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.impact_frame_timer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						final Vec3 _center = new Vec3((entity.getX() + entity.getLookAngle().x), (entity.getY() + entity.getLookAngle().y), (entity.getZ() + entity.getLookAngle().z));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity) && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								if (entity.getPersistentData().getBoolean("Holding")) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if ((entity.getPersistentData().getString("Holding_Entity")).equals(entityiterator.getStringUUID())) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0,
											0, 0, 0);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
									}
								}
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
										(float) ((entity instanceof LivingEntity _livingEntity41 && _livingEntity41.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity41.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												* 1.5));
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 4, 0, false, false));
							}
						}
					}
					{
						final Vec3 _center = new Vec3((entity.getX() + 2 * entity.getLookAngle().y), (entity.getY() + 2 * entity.getLookAngle().x), (entity.getZ() + 2 * entity.getLookAngle().z));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity) && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								if (entity.getPersistentData().getBoolean("Holding")) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if ((entity.getPersistentData().getString("Holding_Entity")).equals(entityiterator.getStringUUID())) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0,
											0, 0, 0);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
									}
								}
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
										(float) ((entity instanceof LivingEntity _livingEntity74 && _livingEntity74.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity74.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												* 1.5));
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 6, 0, false, false));
							}
						}
					}
				}
			} else {
				if (entity.isShiftKeyDown()) {
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
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
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:punch")), SoundSource.PLAYERS, 3, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:punch")), SoundSource.PLAYERS, 3, 1, false);
						}
					}
					{
						double _setval = 5;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.impact_frame_timer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						final Vec3 _center = new Vec3((entity.getX() + entity.getLookAngle().x), (entity.getY() + entity.getLookAngle().y), (entity.getZ() + entity.getLookAngle().z));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity) && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								if (entity.getPersistentData().getBoolean("Holding")) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if ((entity.getPersistentData().getString("Holding_Entity")).equals(entityiterator.getStringUUID())) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0,
											0, 0, 0);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
									}
								}
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
										(float) ((entity instanceof LivingEntity _livingEntity114 && _livingEntity114.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity114.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												* 2.5));
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 4, 0, false, false));
							}
						}
					}
					{
						final Vec3 _center = new Vec3((entity.getX() + 2 * entity.getLookAngle().y), (entity.getY() + 2 * entity.getLookAngle().x), (entity.getZ() + 2 * entity.getLookAngle().z));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity) && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								if (entity.getPersistentData().getBoolean("Holding")) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if ((entity.getPersistentData().getString("Holding_Entity")).equals(entityiterator.getStringUUID())) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0,
											0, 0, 0);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
									}
								}
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
										(float) ((entity instanceof LivingEntity _livingEntity147 && _livingEntity147.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity147.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												* 2.5));
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 6, 0, false, false));
							}
						}
					}
					{
						final Vec3 _center = new Vec3((entity.getX() + 3 * entity.getLookAngle().y), (entity.getY() + 3 * entity.getLookAngle().x), (entity.getZ() + 3 * entity.getLookAngle().z));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity && !(entityiterator == entity) && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								if (entity.getPersistentData().getBoolean("Holding")) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if ((entity.getPersistentData().getString("Holding_Entity")).equals(entityiterator.getStringUUID())) {
									entity.getPersistentData().putString("Holding_Entity", "");
									entity.getPersistentData().putBoolean("Holding", false);
								}
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_3.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0,
											0, 0, 0);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25,
											0.25, 0.25, 0.25);
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
									}
								}
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
										(float) ((entity instanceof LivingEntity _livingEntity180 && _livingEntity180.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity180.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												* 2.5));
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 6, 0, false, false));
							}
						}
					}
				}
			}
			entity.getPersistentData().putDouble("punch_charge", 0);
			entity.getPersistentData().putDouble("cd_viltrumite_chargepunch", 4);
			entity.getPersistentData().putBoolean("punch_charging", false);
		}
	}
}
