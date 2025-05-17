package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.entity.ThraggExiledEntity;

import java.util.List;
import java.util.ArrayList;

public class ThraggOnEntityTickUpdateProcedure {
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
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("IDLE")) {
				if (entity instanceof ThraggExiledEntity _datEntSetS)
					_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, "TARGETING");
			}
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_GlobalAttackCooldown, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_GlobalAttackCooldown) : 0) - 1));
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_MeleeCooldown, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_MeleeCooldown) : 0) - 1));
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_SonicClapCooldown, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_SonicClapCooldown) : 0) - 1));
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_UpslamCooldown, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_UpslamCooldown) : 0) - 1));
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_ChopCooldown, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_ChopCooldown) : 0) - 1));
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_ImpaleCooldown, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_ImpaleCooldown) : 0) - 1));
			if (entity instanceof ThraggExiledEntity _datEntSetL)
				_datEntSetL.getEntityData().set(ThraggExiledEntity.DATA_Flying, true);
			entity.setNoGravity(true);
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("TARGETING")) {
				if (entity instanceof ThraggExiledEntity _datEntL28 && _datEntL28.getEntityData().get(ThraggExiledEntity.DATA_Flying)) {
					if (entity instanceof LivingEntity _livEnt29 && _livEnt29.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
						entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 0.1), ((target.getY() - entity.getY()) * (1 / distance) * 0.1), ((target.getZ() - entity.getZ()) * (1 / distance) * 0.1)));
					} else {
						entity.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (1 / distance) * 2), ((target.getY() - entity.getY()) * (1 / distance) * 2), ((target.getZ() - entity.getZ()) * (1 / distance) * 2)));
					}
				}
				if (distance <= 3 && (entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_MeleeCooldown) : 0) <= 0
						&& (entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("TARGETING")) {
					if (entity instanceof ThraggExiledEntity _datEntSetS)
						_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, "MELEE");
					if (entity instanceof ThraggExiledEntity _datEntSetI)
						_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_AttackDuration, 0);
				}
				if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("TARGETING")) {
					if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_GlobalAttackCooldown) : 0) <= 0) {
						if (distance <= 12.5 && (entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_SonicClapCooldown) : 0) <= 0) {
							availableAttacks.add("SONIC_CLAP");
						}
						if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_UpslamCooldown) : 0) <= 0) {
							availableAttacks.add("UPSLAM");
						}
						if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_ChopCooldown) : 0) <= 0) {
							availableAttacks.add("CHOP");
						}
						if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_ImpaleCooldown) : 0) <= 0) {
							availableAttacks.add("IMPALE");
						}
						if (!availableAttacks.isEmpty()) {
							if (entity instanceof ThraggExiledEntity _datEntSetS)
								_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, (availableAttacks.get(Mth.nextInt(RandomSource.create(), 0, (int) (availableAttacks.size() - 1))) instanceof String _s ? _s : ""));
							if (entity instanceof ThraggExiledEntity _datEntSetI)
								_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_AttackDuration, 0);
							if (entity instanceof ThraggExiledEntity _datEntSetI)
								_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_GlobalAttackCooldown, 20);
							availableAttacks.clear();
						}
					}
				}
			}
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("MELEE")) {
				ThraggMeleeProcedure.execute(world, entity, target);
			}
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("SONIC_CLAP")) {
				ThraggSonicClapProcedure.execute(world, entity, target);
			}
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("UPSLAM")) {
				ThraggUpslamProcedure.execute(world, x, y, z, entity, target);
			}
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("CHOP")) {
				ThraggChopProcedure.execute(world, entity, target);
			}
			if ((entity instanceof ThraggExiledEntity _datEntS ? _datEntS.getEntityData().get(ThraggExiledEntity.DATA_State) : "").equals("IMPALE")) {
				ThraggImpaleProcedure.execute(world, entity, target);
			}
		} else {
			if (entity instanceof ThraggExiledEntity _datEntSetS)
				_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, "IDLE");
		}
	}
}
