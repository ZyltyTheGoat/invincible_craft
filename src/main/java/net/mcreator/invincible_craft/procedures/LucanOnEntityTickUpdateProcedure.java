package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.entity.LucanEntity;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

public class LucanOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		List<Object> availableAttacks = new ArrayList<>();
		Entity target = null;
		boolean canAttack = false;
		double downslamCooldown = 0;
		double distance = 0;
		double dx = 0;
		double barrageCooldown = 0;
		double dy = 0;
		double dz = 0;
		double sonicClapCooldown = 0;
		double meleeCooldown = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double upslamCooldown = 0;
		meleeCooldown = 15;
		sonicClapCooldown = 120;
		downslamCooldown = 180;
		barrageCooldown = 120;
		upslamCooldown = 100;
		canAttack = !(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(InvincibleCraftModMobEffects.DENY.get())) && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get()));
		if (canAttack) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
				distance = Math.sqrt(Math.pow(entity.getX() - target.getX(), 2) + Math.pow(entity.getY() - target.getY(), 2) + Math.pow(entity.getZ() - target.getZ(), 2));
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("IDLE")) {
					if (entity instanceof LucanEntity _datEntSetS)
						_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "TARGETING");
				}
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
				entity.setNoGravity(true);
				if (entity instanceof LucanEntity _datEntSetL)
					_datEntSetL.getEntityData().set(LucanEntity.DATA_Flying, true);
				if (entity instanceof LucanEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LucanEntity.DATA_GlobalAttackCooldown, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_GlobalAttackCooldown) : 0) - 1));
				if (entity instanceof LucanEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LucanEntity.DATA_MeleeCooldown, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_MeleeCooldown) : 0) - 1));
				if (entity instanceof LucanEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LucanEntity.DATA_SonicClapCooldown, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_SonicClapCooldown) : 0) - 1));
				if (entity instanceof LucanEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LucanEntity.DATA_DownslamCooldown, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_DownslamCooldown) : 0) - 1));
				if (entity instanceof LucanEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LucanEntity.DATA_BarrageCooldown, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_BarrageCooldown) : 0) - 1));
				if (entity instanceof LucanEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LucanEntity.DATA_UpslamCooldown, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_UpslamCooldown) : 0) - 1));
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("TARGETING")) {
					if (entity instanceof LucanEntity _datEntL33 && _datEntL33.getEntityData().get(LucanEntity.DATA_Flying)) {
						if (entity instanceof LivingEntity _livEnt34 && _livEnt34.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
							entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 0.1), ((target.getY() - entity.getY()) * (1 / distance) * 0.1), ((target.getZ() - entity.getZ()) * (1 / distance) * 0.1)));
						} else {
							entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance)), ((target.getY() - entity.getY()) * (1 / distance)), ((target.getZ() - entity.getZ()) * (1 / distance))));
						}
					}
					if (distance <= 2 && (entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_MeleeCooldown) : 0) <= 0) {
						if (entity instanceof LucanEntity _datEntSetS)
							_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "MELEE");
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, 0);
					}
					if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("TARGETING")) {
						if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_GlobalAttackCooldown) : 0) <= 0) {
							if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_SonicClapCooldown) : 0) <= 0) {
								availableAttacks.add("SONIC_CLAP");
							}
							if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_DownslamCooldown) : 0) <= 0) {
								availableAttacks.add("DOWNSLAM");
							}
							if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_BarrageCooldown) : 0) <= 0) {
								availableAttacks.add("BARRAGE");
							}
							if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_UpslamCooldown) : 0) <= 0) {
								availableAttacks.add("UPSLAM");
							}
							if (!availableAttacks.isEmpty()) {
								if (entity instanceof LucanEntity _datEntSetS)
									_datEntSetS.getEntityData().set(LucanEntity.DATA_State, (availableAttacks.get(Mth.nextInt(RandomSource.create(), 0, (int) (availableAttacks.size() - 1))) instanceof String _s ? _s : ""));
								if (entity instanceof LucanEntity _datEntSetI)
									_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, 0);
								if (entity instanceof LucanEntity _datEntSetI)
									_datEntSetI.getEntityData().set(LucanEntity.DATA_GlobalAttackCooldown, 60);
								availableAttacks.clear();
							}
						}
					}
				}
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("MELEE")) {
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 1) {
						if ((entity.getPersistentData().getString("lastPunch")).equals("") || (entity.getPersistentData().getString("lastPunch")).equals("left")) {
							if (entity instanceof LucanEntity) {
								((LucanEntity) entity).setAnimation("punch_right");
							}
							entity.getPersistentData().putString("lastPunch", "right");
						} else {
							if (entity instanceof LucanEntity) {
								((LucanEntity) entity).setAnimation("punch_left");
							}
							entity.getPersistentData().putString("lastPunch", "left");
						}
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 2) {
						sx = -3;
						for (int index0 = 0; index0 < 6; index0++) {
							sy = -3;
							for (int index1 = 0; index1 < 6; index1++) {
								sz = -3;
								for (int index2 = 0; index2 < 6; index2++) {
									if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(new ResourceLocation("minecraft:glass")))) {
										world.destroyBlock(BlockPos.containing(x + sx, y + sy, z + sz), false);
									}
									sz = sz + 1;
								}
								sy = sy + 1;
							}
							sx = sx + 1;
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.PLAYERS, 1, (float) 1.5);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.PLAYERS, 1, (float) 1.5, false);
							}
						}
						{
							final Vec3 _center = new Vec3((entity.getX() + entity.getLookAngle().x), (entity.getY() + entity.getLookAngle().y), (entity.getZ() + entity.getLookAngle().z));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof LivingEntity && !(entityiterator == entity)
										&& !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1,
												0, 0, 0, 0);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45,
												0.25, 0.25, 0.25, 0.25);
									if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get(), 13, 3, false, false));
									entityiterator.hurt(
											new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:mob_attack"))), entity),
											15);
								}
							}
						}
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) >= 10) {
						if (entity instanceof LucanEntity _datEntSetS)
							_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "IDLE");
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_MeleeCooldown, (int) meleeCooldown);
					}
				}
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("SONIC_CLAP")) {
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_GlobalAttackCooldown, 60);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 1) {
						if (entity instanceof LucanEntity) {
							((LucanEntity) entity).setAnimation("sonic_clap");
						}
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 9) {
						SonicClapAttackProcedure.execute(world, entity);
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) >= 15) {
						if (entity instanceof LucanEntity _datEntSetS)
							_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "IDLE");
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_SonicClapCooldown, (int) sonicClapCooldown);
					}
				}
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("DOWNSLAM")) {
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_GlobalAttackCooldown, 60);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 1) {
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX()), (target.getY() + 1.4), (target.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX()), (target.getY() + 1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
						}
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
						ViltrumiteDownslamProcedure.execute(world, x, y, z, entity);
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) >= 5) {
						if (entity instanceof LucanEntity _datEntSetS)
							_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "IDLE");
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_DownslamCooldown, (int) downslamCooldown);
					}
				}
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("UPSLAM")) {
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_GlobalAttackCooldown, 60);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
					if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 1) {
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX()), (target.getY()), (target.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX()), (target.getY()), (target.getZ()), _ent.getYRot(), _ent.getXRot());
						}
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
						ViltrumiteUpslamProcedure.execute(world, x, y, z, entity);
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) >= 5) {
						if (entity instanceof LucanEntity _datEntSetS)
							_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "IDLE");
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_UpslamCooldown, (int) upslamCooldown);
					}
				}
				if ((entity instanceof LucanEntity _datEntS ? _datEntS.getEntityData().get(LucanEntity.DATA_State) : "").equals("BARRAGE")) {
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, (int) ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) + 1));
					if (entity instanceof LucanEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LucanEntity.DATA_GlobalAttackCooldown, 60);
					if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) == 1) {
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() + 1.5 * target.getLookAngle().y), (target.getZ() + 1.5 * target.getLookAngle().z));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() + 1.5 * target.getLookAngle().y), (target.getZ() + 1.5 * target.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
						}
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
						if (entity instanceof LucanEntity) {
							((LucanEntity) entity).setAnimation("barrage");
						}
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) <= 60
							&& (entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) % 4 == 0) {
						{
							Entity _ent = entity;
							_ent.teleportTo((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() + 1.5 * target.getLookAngle().y), (target.getZ() + 1.5 * target.getLookAngle().z));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() + 1.5 * target.getLookAngle().y), (target.getZ() + 1.5 * target.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
						}
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
						dx = entity.getX() + 1.5 * entity.getLookAngle().x;
						dy = entity.getY() + 1.5 * entity.getLookAngle().y;
						dz = entity.getZ() + 1.5 * entity.getLookAngle().z;
						{
							final Vec3 _center = new Vec3(dx, dy, dz);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3.5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof LivingEntity && !(entityiterator == entity)) {
									entityiterator.hurt(
											new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage"))), entity), 3);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entityiterator.getX() + Mth.nextDouble(RandomSource.create(), -1, 1)),
												(entityiterator.getY() + entityiterator.getBbHeight() / 2 + Mth.nextDouble(RandomSource.create(), -1, 1)), (entityiterator.getZ() + Mth.nextDouble(RandomSource.create(), -1, 1)), 1, 0, 0, 0, 0);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45,
												0.25, 0.25, 0.25, 0.25);
									entityiterator.setDeltaMovement(new Vec3(0, 0.1, 0));
									entity.invulnerableTime = 0;
								}
							}
						}
					} else if ((entity instanceof LucanEntity _datEntI ? _datEntI.getEntityData().get(LucanEntity.DATA_AttackDuration) : 0) >= 50) {
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
						ViltrumitePunchAbilityProcedure.execute(world, x, y, z, entity);
						if (entity instanceof LucanEntity _datEntSetS)
							_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "IDLE");
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_BarrageCooldown, (int) barrageCooldown);
						if (entity instanceof LucanEntity _datEntSetI)
							_datEntSetI.getEntityData().set(LucanEntity.DATA_AttackDuration, 60);
					}
				}
			} else {
				if (entity instanceof LucanEntity _datEntSetS)
					_datEntSetS.getEntityData().set(LucanEntity.DATA_State, "IDLE");
			}
		}
	}
}
