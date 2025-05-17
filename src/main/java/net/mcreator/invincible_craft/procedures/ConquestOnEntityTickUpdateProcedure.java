package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.entity.ConquestEntity;

import java.util.List;
import java.util.ArrayList;

public class ConquestOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		List<Object> availableAttacks = new ArrayList<>();
		Entity target = null;
		boolean canAttack = false;
		double distance = 0;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		canAttack = !(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(InvincibleCraftModMobEffects.DENY.get())) && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(InvincibleCraftModMobEffects.STUN.get()));
		if (canAttack) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
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
				distance = Math.sqrt(Math.pow(entity.getX() - target.getX(), 2) + Math.pow(entity.getY() - target.getY(), 2) + Math.pow(entity.getZ() - target.getZ(), 2));
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("IDLE")) {
					if (entity instanceof ConquestEntity _datEntSetS)
						_datEntSetS.getEntityData().set(ConquestEntity.DATA_State, "TARGETING");
				}
				if (entity instanceof ConquestEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ConquestEntity.DATA_GlobalAttackCooldown, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_GlobalAttackCooldown) : 0) - 1));
				if (entity instanceof ConquestEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ConquestEntity.DATA_MeleeCooldown, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_MeleeCooldown) : 0) - 1));
				if (entity instanceof ConquestEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ConquestEntity.DATA_SonicClapCooldown, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_SonicClapCooldown) : 0) - 1));
				if (entity instanceof ConquestEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ConquestEntity.DATA_UpslamCooldown, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_UpslamCooldown) : 0) - 1));
				if (entity instanceof ConquestEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ConquestEntity.DATA_DownslamCooldown, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_DownslamCooldown) : 0) - 1));
				if (entity instanceof ConquestEntity _datEntSetI)
					_datEntSetI.getEntityData().set(ConquestEntity.DATA_ChopCooldown, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_ChopCooldown) : 0) - 1));
				if (entity instanceof ConquestEntity _datEntSetL)
					_datEntSetL.getEntityData().set(ConquestEntity.DATA_Flying, true);
				entity.setNoGravity(true);
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("TARGETING")) {
					if (entity instanceof ConquestEntity _datEntL30 && _datEntL30.getEntityData().get(ConquestEntity.DATA_Flying)) {
						if (entity instanceof LivingEntity _livEnt31 && _livEnt31.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
							entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 0.1), ((target.getY() - entity.getY()) * (1 / distance) * 0.1), ((target.getZ() - entity.getZ()) * (1 / distance) * 0.1)));
						} else {
							entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 2), ((target.getY() - entity.getY()) * (1 / distance) * 2), ((target.getZ() - entity.getZ()) * (1 / distance) * 2)));
						}
					}
					if (distance <= 3 && (entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_MeleeCooldown) : 0) <= 0
							&& (entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("TARGETING")) {
						if (entity instanceof ConquestEntity _datEntSetS)
							_datEntSetS.getEntityData().set(ConquestEntity.DATA_State, "MELEE");
						if (entity instanceof ConquestEntity _datEntSetI)
							_datEntSetI.getEntityData().set(ConquestEntity.DATA_AttackDuration, 0);
					}
					if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("TARGETING")) {
						if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_GlobalAttackCooldown) : 0) <= 0) {
							if (distance <= 12.5 && (entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_SonicClapCooldown) : 0) <= 0) {
								availableAttacks.add("SONIC_CLAP");
							}
							if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_UpslamCooldown) : 0) <= 0) {
								availableAttacks.add("UPSLAM");
							}
							if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_DownslamCooldown) : 0) <= 0) {
								availableAttacks.add("DOWNSLAM");
							}
							if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_ChopCooldown) : 0) <= 0) {
								availableAttacks.add("CHOP");
							}
							if (!availableAttacks.isEmpty()) {
								if (entity instanceof ConquestEntity _datEntSetS)
									_datEntSetS.getEntityData().set(ConquestEntity.DATA_State, (availableAttacks.get(Mth.nextInt(RandomSource.create(), 0, (int) (availableAttacks.size() - 1))) instanceof String _s ? _s : ""));
								if (entity instanceof ConquestEntity _datEntSetI)
									_datEntSetI.getEntityData().set(ConquestEntity.DATA_AttackDuration, 0);
								if (entity instanceof ConquestEntity _datEntSetI)
									_datEntSetI.getEntityData().set(ConquestEntity.DATA_GlobalAttackCooldown, 20);
								availableAttacks.clear();
							}
						}
					}
				}
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("MELEE")) {
					ConquestMeleeProcedure.execute(world, entity, target);
				}
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("SONIC_CLAP")) {
					ConquestSonicClapProcedure.execute(world, entity, target);
				}
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("DOWNSLAM")) {
					ConquestDownslamProcedure.execute(world, x, y, z, entity, target);
				}
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("UPSLAM")) {
					ConquestUpslamProcedure.execute(world, x, y, z, entity, target);
				}
				if ((entity instanceof ConquestEntity _datEntS ? _datEntS.getEntityData().get(ConquestEntity.DATA_State) : "").equals("CHOP")) {
					ConquestChopProcedure.execute(world, entity, target);
				}
			} else {
				if (entity instanceof ConquestEntity _datEntSetS)
					_datEntSetS.getEntityData().set(ConquestEntity.DATA_State, "IDLE");
			}
		}
	}
}
