package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import net.mcreator.invincible_craft.entity.ThraggExiledEntity;

public class ThraggMeleeProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity target) {
		if (entity == null || target == null)
			return;
		if (entity instanceof ThraggExiledEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_AttackDuration, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) + 1));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
		entity.setDeltaMovement(new Vec3(0, 0, 0));
		entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
		if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) == 1) {
			if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
				if (entity instanceof ThraggExiledEntity) {
					((ThraggExiledEntity) entity).setAnimation("punch_right");
				}
			} else {
				if (entity instanceof ThraggExiledEntity) {
					((ThraggExiledEntity) entity).setAnimation("punch_left");
				}
			}
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) == 2) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 1, 0, 0, 0, 0);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 45, 0.25, 0.25, 0.25, 0.25);
			target.setDeltaMovement(new Vec3(((target.getX() - entity.getX()) * (6 / Math.sqrt(Math.pow(target.getX() - entity.getX(), 2) + Math.pow(target.getY() - entity.getY(), 2) + Math.pow(target.getZ() - entity.getZ(), 2)))), 0.5,
					((target.getZ() - entity.getZ()) * (6 / Math.sqrt(Math.pow(target.getX() - entity.getX(), 2) + Math.pow(target.getY() - entity.getY(), 2) + Math.pow(target.getZ() - entity.getZ(), 2))))));
			target.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
					(float) (entity instanceof LivingEntity _livingEntity41 && _livingEntity41.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity41.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0));
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 15, 0, false, false));
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get(), 5, 3, false, false));
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) >= 6) {
			if (entity instanceof ThraggExiledEntity _datEntSetS)
				_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, "IDLE");
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_MeleeCooldown, 15);
			{
				Entity _ent = entity;
				_ent.teleportTo((target.getX()), (target.getY()), (target.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((target.getX()), (target.getY()), (target.getZ()), _ent.getYRot(), _ent.getXRot());
			}
		}
	}
}
