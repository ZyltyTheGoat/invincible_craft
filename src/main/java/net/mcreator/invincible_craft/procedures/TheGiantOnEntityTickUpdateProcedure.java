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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.invincible_craft.entity.TheGiantEntity;

import java.util.List;
import java.util.Comparator;

public class TheGiantOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double rand = 0;
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
			distance = Math.sqrt(Math.pow(entity.getX() - (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX(), 2) + Math.pow(entity.getZ() - (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ(), 2));
			if (distance <= 14) {
				if (entity instanceof TheGiantEntity _datEntSetI)
					_datEntSetI.getEntityData().set(TheGiantEntity.DATA_IA, (int) ((entity instanceof TheGiantEntity _datEntI ? _datEntI.getEntityData().get(TheGiantEntity.DATA_IA) : 0) + 1));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, false, false));
			} else {
				if (entity instanceof Mob _entity)
					_entity.getNavigation().moveTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()), 1);
			}
			if ((entity instanceof TheGiantEntity _datEntI ? _datEntI.getEntityData().get(TheGiantEntity.DATA_IA) : 0) == 2) {
				rand = Mth.nextInt(RandomSource.create(), 1, 2);
				if (rand == 1) {
					if (entity instanceof TheGiantEntity) {
						((TheGiantEntity) entity).setAnimation("attack_left");
					}
				} else {
					if (entity instanceof TheGiantEntity) {
						((TheGiantEntity) entity).setAnimation("attack_right");
					}
				}
			}
			if ((entity instanceof TheGiantEntity _datEntI ? _datEntI.getEntityData().get(TheGiantEntity.DATA_IA) : 0) == 21) {
				{
					final Vec3 _center = new Vec3((entity.getX() + 5 * entity.getLookAngle().x), (entity.getY() + 6), (entity.getZ() + 5 * entity.getLookAngle().z));
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(22 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == entity) && entityiterator instanceof LivingEntity) {
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:impact"))), entity),
									(float) (entity instanceof LivingEntity _livingEntity30 && _livingEntity30.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity30.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0));
						}
					}
				}
			}
			if ((entity instanceof TheGiantEntity _datEntI ? _datEntI.getEntityData().get(TheGiantEntity.DATA_IA) : 0) == 80) {
				if (entity instanceof TheGiantEntity _datEntSetI)
					_datEntSetI.getEntityData().set(TheGiantEntity.DATA_IA, 0);
			}
		}
	}
}
