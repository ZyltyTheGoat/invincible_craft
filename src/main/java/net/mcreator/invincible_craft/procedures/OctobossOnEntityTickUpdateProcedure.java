package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.entity.OctobossEntity;
import net.mcreator.invincible_craft.entity.FireballGlobalEntity;

public class OctobossOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double distance = 0;
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
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
			distance = Math.sqrt(Math.pow(entity.getX() - (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX(), 2) + Math.pow(entity.getY() - (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY(), 2)
					+ Math.pow(entity.getZ() - (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ(), 2));
			if (distance <= 5) {
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Melee, (int) ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Melee) : 0) + 1));
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Ranged, 0);
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 0, false, false));
			} else if (distance >= 15) {
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Ranged, (int) ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Ranged) : 0) + 1));
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Melee, 0);
			} else {
				if (entity instanceof Mob _entity)
					_entity.getNavigation().moveTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()), 1);
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Melee, 0);
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Ranged, 0);
			}
			if (entity.getPersistentData().getDouble("comboTimer") > 0) {
				entity.getPersistentData().putDouble("comboTimer", (entity.getPersistentData().getDouble("comboTimer") + 1));
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Ranged, 0);
			}
			if ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Melee) : 0) == 2) {
				if (entity instanceof OctobossEntity) {
					((OctobossEntity) entity).setAnimation("attack_right");
				}
			} else if ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Melee) : 0) == 13) {
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Melee, (int) ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Melee) : 0) + 1));
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
				if (Math.random() < (1) / ((float) 3)) {
					entity.getPersistentData().putBoolean("combo", true);
					entity.getPersistentData().putDouble("comboTimer", 1);
				}
				(entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)
						.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:mob_attack"))), entity), 6);
			} else if ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Melee) : 0) >= 40) {
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Melee, 0);
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			}
			if ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Ranged) : 0) == 2 || entity.getPersistentData().getDouble("comboTimer") == 2) {
				if (entity instanceof OctobossEntity) {
					((OctobossEntity) entity).setAnimation("attack_shoot");
				}
			} else if ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Ranged) : 0) == 12 || entity.getPersistentData().getDouble("comboTimer") == 12) {
				entity.getPersistentData().putDouble("comboTimer", 0);
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Ranged, (int) ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Ranged) : 0) + 1));
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1.6),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
				{
					Entity _shootFrom = entity;
					Level projectileLevel = _shootFrom.level();
					if (!projectileLevel.isClientSide()) {
						Projectile _entityToSpawn = new Object() {
							public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
								AbstractArrow entityToSpawn = new FireballGlobalEntity(InvincibleCraftModEntities.FIREBALL_GLOBAL.get(), level);
								entityToSpawn.setOwner(shooter);
								entityToSpawn.setBaseDamage(damage);
								entityToSpawn.setKnockback(knockback);
								entityToSpawn.setSilent(true);
								entityToSpawn.setSecondsOnFire(100);
								return entityToSpawn;
							}
						}.getArrow(projectileLevel, entity, (float) 3.33333333, 1);
						_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
						_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
						projectileLevel.addFreshEntity(_entityToSpawn);
					}
				}
			} else if ((entity instanceof OctobossEntity _datEntI ? _datEntI.getEntityData().get(OctobossEntity.DATA_Ranged) : 0) >= 80) {
				if (entity instanceof OctobossEntity _datEntSetI)
					_datEntSetI.getEntityData().set(OctobossEntity.DATA_Ranged, 0);
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			}
		}
	}
}
