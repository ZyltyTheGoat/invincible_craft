package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.entity.KreggEntity;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

public class KreggOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean canAttack = false;
		Entity target = null;
		List<Object> availableAttacks = new ArrayList<>();
		double global_cooldown = 0;
		double distance = 0;
		double dx = 0;
		double barrageCooldown = 0;
		double dy = 0;
		double upslamCooldown = 0;
		double dz = 0;
		double meleeCooldown = 0;
		double downslamCooldown = 0;
		meleeCooldown = 10;
		barrageCooldown = 60;
		upslamCooldown = 100;
		downslamCooldown = 100;
		global_cooldown = 20;
		canAttack = !(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(InvincibleCraftModMobEffects.DENY.get())) && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(InvincibleCraftModMobEffects.STUN.get()))
				&& !(entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get()));
		if (canAttack) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
				distance = Math.sqrt(Math.pow(entity.getX() - target.getX(), 2) + Math.pow(entity.getY() - target.getY(), 2) + Math.pow(entity.getZ() - target.getZ(), 2));
				if (entity instanceof Mob _mob && _mob.getTarget() != null) {
					LivingEntity ent = _mob.getTarget();
					double deltaX = ent.getX() - entity.getX();
					double deltaZ = ent.getZ() - entity.getZ();
					float targetYaw = (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX))) - 90.0F;
					entity.setYRot(targetYaw);
					entity.yRotO = targetYaw;
					if (entity instanceof LivingEntity _livingEntity) {
						_livingEntity.yBodyRot = targetYaw;
						_livingEntity.yHeadRot = targetYaw;
					}
				}
				if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("IDLE")) {
					if (entity instanceof KreggEntity _datEntSetS)
						_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "TARGETING");
				}
				if (entity instanceof KreggEntity _datEntSetI)
					_datEntSetI.getEntityData().set(KreggEntity.DATA_GlobalAttackCooldown, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_GlobalAttackCooldown) : 0) - 1));
				if (entity instanceof KreggEntity _datEntSetI)
					_datEntSetI.getEntityData().set(KreggEntity.DATA_UpslamCooldown, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_UpslamCooldown) : 0) - 1));
				if (entity instanceof KreggEntity _datEntSetI)
					_datEntSetI.getEntityData().set(KreggEntity.DATA_BarrageCooldown, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_BarrageCooldown) : 0) - 1));
				if (entity instanceof KreggEntity _datEntSetI)
					_datEntSetI.getEntityData().set(KreggEntity.DATA_MeleeCooldown, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_MeleeCooldown) : 0) - 1));
				if (entity instanceof KreggEntity _datEntSetI)
					_datEntSetI.getEntityData().set(KreggEntity.DATA_DownslamCooldown, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_DownslamCooldown) : 0) - 1));
				if (entity instanceof KreggEntity _datEntSetL)
					_datEntSetL.getEntityData().set(KreggEntity.DATA_Flying, true);
				entity.setNoGravity(true);
				if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("TARGETING")) {
					if (entity instanceof KreggEntity _datEntL29 && _datEntL29.getEntityData().get(KreggEntity.DATA_Flying)) {
						if (entity instanceof LivingEntity _livEnt30 && _livEnt30.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
							entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 0.1), ((target.getY() - entity.getY()) * (1 / distance) * 0.1), ((target.getZ() - entity.getZ()) * (1 / distance) * 0.1)));
						} else {
							entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 3), ((target.getY() - entity.getY()) * (1 / distance) * 3), ((target.getZ() - entity.getZ()) * (1 / distance) * 3)));
						}
					}
					if (distance <= 3 && (entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_MeleeCooldown) : 0) <= 0) {
						if (entity instanceof KreggEntity _datEntSetS)
							_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "MELEE");
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, 0);
					}
					if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("TARGETING")) {
						if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_GlobalAttackCooldown) : 0) <= 0) {
							if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_BarrageCooldown) : 0) <= 0) {
								availableAttacks.add("BARRAGE");
							}
							if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_UpslamCooldown) : 0) <= 0) {
								availableAttacks.add("UPSLAM");
							}
							if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_DownslamCooldown) : 0) <= 0) {
								availableAttacks.add("DOWNSLAM");
							}
							if (!availableAttacks.isEmpty()) {
								if (entity instanceof KreggEntity _datEntSetS)
									_datEntSetS.getEntityData().set(KreggEntity.DATA_State, (availableAttacks.get(Mth.nextInt(RandomSource.create(), 0, (int) (availableAttacks.size() - 1))) instanceof String _s ? _s : ""));
								if (entity instanceof KreggEntity _datEntSetI)
									_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, 0);
								if (entity instanceof KreggEntity _datEntSetI)
									_datEntSetI.getEntityData().set(KreggEntity.DATA_GlobalAttackCooldown, (int) global_cooldown);
								availableAttacks.clear();
							}
						}
					}
				}
				if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("MELEE")) {
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					entity.setDeltaMovement(new Vec3(0, 0, 0));
					if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 1) {
						entity.setDeltaMovement(new Vec3(0, 0, 0));
						if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
							if (entity instanceof KreggEntity) {
								((KreggEntity) entity).setAnimation("punch_right");
							}
						} else {
							if (entity instanceof KreggEntity) {
								((KreggEntity) entity).setAnimation("punch_left");
							}
						}
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 2) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 1, 0, 0, 0, 0);
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 45, 0.25, 0.25, 0.25, 0.25);
						target.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (2 / Math.sqrt(Math.pow(target.getX() - entity.getX(), 2) + Math.pow(target.getY() - entity.getY(), 2) + Math.pow(target.getZ() - entity.getZ(), 2)))), 0.5,
								((target.getZ() - entity.getZ()) * (2 / Math.sqrt(Math.pow(target.getX() - entity.getX(), 2) + Math.pow(target.getY() - entity.getY(), 2) + Math.pow(target.getZ() - entity.getZ(), 2))))));
						target.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
								(float) (entity instanceof LivingEntity _livingEntity107 && _livingEntity107.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity107.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 15, 0, false, false));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get(), 5, 3, false, false));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.MOTION.get(), 10, 0, false, false));
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) >= 4) {
						if (entity instanceof KreggEntity _datEntSetS)
							_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "IDLE");
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_MeleeCooldown, (int) meleeCooldown);
					}
				}
				if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("DOWNSLAM")) {
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_GlobalAttackCooldown, (int) global_cooldown);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 1) {
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 5, 0, false, false));
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX()), (target.getY() + 1.4), (target.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX()), (target.getY() + 1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
						}
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 4) {
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
						KreggDownslamProcedure.execute(world, x, y, z, entity);
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) >= 5) {
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 5, 0, false, false));
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX()), (target.getY() + 1.4), (target.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX()), (target.getY() + 1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
						}
						if (entity instanceof KreggEntity _datEntSetS)
							_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "IDLE");
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_DownslamCooldown, (int) downslamCooldown);
					}
				}
				if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("UPSLAM")) {
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_GlobalAttackCooldown, (int) global_cooldown);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 1) {
						if (entity instanceof KreggEntity) {
							((KreggEntity) entity).setAnimation("upslam");
						}
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 5, 0, false, false));
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX()), (target.getY() - 1.4), (target.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX()), (target.getY() - 1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
						}
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 4) {
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
						KreggUpslamProcedure.execute(world, x, y, z, entity);
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) >= 5) {
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
						if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 5, 0, false, false));
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX()), (target.getY() + 1.4), (target.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX()), (target.getY() + 1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
						}
						if (entity instanceof KreggEntity _datEntSetS)
							_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "IDLE");
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_UpslamCooldown, (int) upslamCooldown);
					}
				}
				if ((entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("BARRAGE")) {
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, (int) ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof KreggEntity _datEntSetI)
						_datEntSetI.getEntityData().set(KreggEntity.DATA_GlobalAttackCooldown, (int) global_cooldown);
					{
						Entity _ent = entity;
						_ent.teleportTo((target.getX() + 1 * target.getLookAngle().x), (target.getY()), (target.getZ() + 1.5 * target.getLookAngle().z));
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport((target.getX() + 1 * target.getLookAngle().x), (target.getY()), (target.getZ() + 1.5 * target.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
					}
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
					if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) == 1) {
						if (entity instanceof KreggEntity) {
							((KreggEntity) entity).setAnimation("barrage");
						}
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) <= 60
							&& (entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) % 4 == 0) {
						dx = entity.getX() + 2 * entity.getLookAngle().x;
						dy = entity.getY() + 2 * entity.getLookAngle().y;
						dz = entity.getZ() + 2 * entity.getLookAngle().z;
						{
							final Vec3 _center = new Vec3(dx, dy, dz);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3.5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof LivingEntity && !(entityiterator == entity)) {
									entityiterator.hurt(
											new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage"))), entity), 3);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get()), (entityiterator.getX() + Mth.nextDouble(RandomSource.create(), -1, 1)),
												(entityiterator.getY() + entityiterator.getBbHeight() / 2 + Mth.nextDouble(RandomSource.create(), -1, 1)), (entityiterator.getZ() + Mth.nextDouble(RandomSource.create(), -1, 1)), 1, 0, 0, 0, 0);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45,
												0.25, 0.25, 0.25, 0.25);
									if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 5, 0, false, false));
									entityiterator.setDeltaMovement(new Vec3(0, 0.1, 0));
									entity.invulnerableTime = 0;
								}
							}
						}
					} else if ((entity instanceof KreggEntity _datEntI ? _datEntI.getEntityData().get(KreggEntity.DATA_AttackDuration) : 0) >= 50) {
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
						if (entity instanceof KreggEntity _datEntSetS)
							_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "MELEE");
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_BarrageCooldown, (int) barrageCooldown);
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_AttackDuration, 0);
						if (entity instanceof KreggEntity _datEntSetI)
							_datEntSetI.getEntityData().set(KreggEntity.DATA_GlobalAttackCooldown, (int) global_cooldown);
					}
				}
			} else {
				if (entity instanceof KreggEntity _datEntSetS)
					_datEntSetS.getEntityData().set(KreggEntity.DATA_State, "IDLE");
			}
		}
	}
}
