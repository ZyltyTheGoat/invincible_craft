package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

public class ViltrumiteOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity ent = null;
		double distance = 0;
		double combo_rand = 0;
		{
			String _setval = "Viltrumite";
			entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.power = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (!CanViltrumiteFlyingAttackProcedure.execute(entity)) {
			entity.setNoGravity(false);
		} else {
			entity.setNoGravity(true);
		}
		if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(InvincibleCraftModMobEffects.DENY.get())) {
			if (entity instanceof ViltrumiteEntity _datEntSetL)
				_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_denied, true);
		} else {
			if (entity instanceof ViltrumiteEntity _datEntSetL)
				_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_denied, false);
		}
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_HitMeterRecovery) : 0) > 0) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_HitMeterRecovery, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_HitMeterRecovery) : 0) - 1));
		}
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_recovery) : 0) > 0) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_recovery) : 0) - 1));
		} else {
			if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ClapTimer) : 0) < 200) {
				if (entity instanceof ViltrumiteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ClapTimer, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ClapTimer) : 0) + 1));
			}
			if (!(entity instanceof LivingEntity _livEnt16 && _livEnt16.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get()))) {
				if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboTimer) : 0) < 150) {
					if (entity instanceof ViltrumiteEntity _datEntSetI)
						_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboTimer, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboTimer) : 0) + 1));
				}
			}
			if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ChopTimer) : 0) <= 200) {
				if (entity instanceof ViltrumiteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ChopTimer, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ChopTimer) : 0) + 1));
			}
			if (entity instanceof ViltrumiteEntity _datEntL24 && _datEntL24.getEntityData().get(ViltrumiteEntity.DATA_Slamming)) {
				if (entity instanceof ViltrumiteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_SlamIA, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_SlamIA) : 0) + 1));
				if (entity instanceof ViltrumiteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 60);
				if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_SlamIA) : 0) == 20) {
					entity.setDeltaMovement(new Vec3(0, 10, 0));
					if (entity instanceof ViltrumiteEntity _datEntSetL)
						_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_SlamLogic, true);
				}
				if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_SlamIA) : 0) == 20) {
					entity.push(0, (-30), 0);
				}
				if (entity instanceof ViltrumiteEntity _datEntL33 && _datEntL33.getEntityData().get(ViltrumiteEntity.DATA_SlamLogic)) {
					if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_SlamIA) : 0) > 20) {
						if (entity.onGround()) {
							if (entity instanceof ViltrumiteEntity _datEntSetL)
								_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_Slamming, false);
							if (entity instanceof ViltrumiteEntity _datEntSetL)
								_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_SlamLogic, false);
						}
					}
				}
			} else {
				if (entity instanceof ViltrumiteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_SlamIA, 0);
				if (entity instanceof ViltrumiteEntity _datEntSetL)
					_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_Slamming, false);
			}
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			ent = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
			distance = Math.sqrt(Math.pow(entity.getX() - ent.getX(), 2) + Math.pow(entity.getY() - ent.getY(), 2) + Math.pow(entity.getZ() - ent.getZ(), 2));
			if (entity instanceof Mob _mob && _mob.getTarget() != null) {
				LivingEntity target = _mob.getTarget();
				double deltaX = target.getX() - entity.getX();
				double deltaZ = target.getZ() - entity.getZ();
				float targetYaw = (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX))) - 90.0F;
				entity.setYRot(targetYaw);
				entity.yRotO = targetYaw;
				if (entity instanceof LivingEntity _livingEntity) {
					_livingEntity.yBodyRot = targetYaw;
					_livingEntity.yHeadRot = targetYaw;
				}
			}
			if (!(ent.getPersistentData().getString("Holding_Entity")).equals(entity.getStringUUID())) {
				if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_recovery) : 0) <= 0) {
					if (CanViltrumiteFlyingAttackProcedure.execute(entity)) {
						if (entity instanceof LivingEntity _livEnt55 && _livEnt55.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
							entity.setDeltaMovement(new Vec3(((ent.getX() - entity.getX()) * (1 / distance) * 0.1), ((ent.getY() - entity.getY()) * (1 / distance) * 0.1), ((ent.getZ() - entity.getZ()) * (1 / distance) * 0.1)));
						} else {
							entity.setDeltaMovement(new Vec3(((ent.getX() - entity.getX()) * (1 / distance)), ((ent.getY() - entity.getY()) * (1 / distance)), ((ent.getZ() - entity.getZ()) * (1 / distance))));
						}
					}
					if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ClapTimer) : 0) >= 200) {
						if (entity instanceof ViltrumiteEntity _datEntSetI)
							_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 40);
						if (entity instanceof ViltrumiteEntity _datEntSetI)
							_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ClapTimer, 0);
						SonicClapAttackProcedure.execute(world, entity);
					}
					if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboTimer) : 0) >= 150) {
						if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) <= 3) {
							if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) < 3) {
								combo_rand = Mth.nextInt(RandomSource.create(), 1, 3);
								if (combo_rand >= 2) {
									{
										Entity _ent = entity;
										_ent.teleportTo((ent.getX() + (-1.5) * ent.getLookAngle().x), (ent.getY() + (-1.5) * ent.getLookAngle().y), (ent.getZ() + (-1.5) * ent.getLookAngle().z));
										if (_ent instanceof ServerPlayer _serverPlayer)
											_serverPlayer.connection.teleport((ent.getX() + (-1.5) * ent.getLookAngle().x), (ent.getY() + (-1.5) * ent.getLookAngle().y), (ent.getZ() + (-1.5) * ent.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
									}
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((ent.getX()), (ent.getY() + 1.4), (ent.getZ())));
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 20);
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboAmount, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) + 1));
									ViltrumiteUpslamProcedure.execute(world, x, y, z, entity);
								} else {
									{
										Entity _ent = entity;
										_ent.teleportTo((ent.getX() + ent.getLookAngle().x), (ent.getY() + 2 + ent.getLookAngle().y), (ent.getZ() + ent.getLookAngle().z));
										if (_ent instanceof ServerPlayer _serverPlayer)
											_serverPlayer.connection.teleport((ent.getX() + ent.getLookAngle().x), (ent.getY() + 2 + ent.getLookAngle().y), (ent.getZ() + ent.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
									}
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((ent.getX()), (ent.getY() + 1.4), (ent.getZ())));
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 20);
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboAmount, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) + 1));
									ViltrumiteComboPunchProcedure.execute(world, x, y, z, entity);
								}
							}
							if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) == 3) {
								combo_rand = Mth.nextInt(RandomSource.create(), 1, 3);
								if (combo_rand >= 2) {
									{
										Entity _ent = entity;
										_ent.teleportTo((ent.getX() + (-1.5) * ent.getLookAngle().x), (ent.getY() + (-1.5) * ent.getLookAngle().y), (ent.getZ() + (-1.5) * ent.getLookAngle().z));
										if (_ent instanceof ServerPlayer _serverPlayer)
											_serverPlayer.connection.teleport((ent.getX() + (-1.5) * ent.getLookAngle().x), (ent.getY() + (-1.5) * ent.getLookAngle().y), (ent.getZ() + (-1.5) * ent.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
									}
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((ent.getX()), (ent.getY() + 1.4), (ent.getZ())));
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 20);
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboAmount, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) + 1));
									ViltrumiteDownslamProcedure.execute(world, x, y, z, entity);
								} else {
									{
										Entity _ent = entity;
										_ent.teleportTo((ent.getX() + ent.getLookAngle().x), (ent.getY() + 2 + ent.getLookAngle().y), (ent.getZ() + ent.getLookAngle().z));
										if (_ent instanceof ServerPlayer _serverPlayer)
											_serverPlayer.connection.teleport((ent.getX() + ent.getLookAngle().x), (ent.getY() + 2 + ent.getLookAngle().y), (ent.getZ() + ent.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
									}
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((ent.getX()), (ent.getY() + 1.4), (ent.getZ())));
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 20);
									if (entity instanceof ViltrumiteEntity _datEntSetI)
										_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboAmount, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) + 1));
									ViltrumiteComboPunchProcedure.execute(world, x, y, z, entity);
								}
							} else {
								{
									Entity _ent = entity;
									_ent.teleportTo((ent.getX() + (-1.5) * ent.getLookAngle().x), (ent.getY() + (-1.5) * ent.getLookAngle().y), (ent.getZ() + (-1.5) * ent.getLookAngle().z));
									if (_ent instanceof ServerPlayer _serverPlayer)
										_serverPlayer.connection.teleport((ent.getX() + (-1.5) * ent.getLookAngle().x), (ent.getY() + (-1.5) * ent.getLookAngle().y), (ent.getZ() + (-1.5) * ent.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
								}
								entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((ent.getX()), (ent.getY() + 1.4), (ent.getZ())));
								if (entity instanceof ViltrumiteEntity _datEntSetI)
									_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 20);
								if (entity instanceof ViltrumiteEntity _datEntSetI)
									_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboAmount, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_ComboAmount) : 0) + 1));
								ViltrumiteComboPunchProcedure.execute(world, x, y, z, entity);
							}
						} else {
							if (entity instanceof ViltrumiteEntity _datEntSetI)
								_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 40);
							if (entity instanceof ViltrumiteEntity _datEntSetI)
								_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboAmount, 0);
							if (entity instanceof ViltrumiteEntity _datEntSetI)
								_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_ComboTimer, 0);
						}
					}
				} else {
					if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_SlamMeter) : 0) > 10) {
						if (entity instanceof ViltrumiteEntity _datEntSetI)
							_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_SlamMeter, 0);
					}
				}
				if (world.canSeeSkyFromBelowWater(BlockPos.containing(ent.getX(), ent.getY(), ent.getZ()))) {
					if (ent instanceof Player) {
						if ((ent.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying) {
							if (entity instanceof ViltrumiteEntity _datEntSetL)
								_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_flying, true);
						}
					}
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) < 0.4) {
						if (entity instanceof ViltrumiteEntity _datEntSetL)
							_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_flying, true);
					}
				} else {
					if (entity instanceof ViltrumiteEntity _datEntSetL)
						_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_flying, false);
				}
			} else {
				if (entity instanceof ViltrumiteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 5);
			}
		} else {
			if (entity instanceof ViltrumiteEntity _datEntSetL)
				_datEntSetL.getEntityData().set(ViltrumiteEntity.DATA_flying, false);
		}
	}
}
