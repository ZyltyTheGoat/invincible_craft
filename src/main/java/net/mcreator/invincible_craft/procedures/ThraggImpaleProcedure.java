package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
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

public class ThraggImpaleProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity target) {
		if (entity == null || target == null)
			return;
		if (entity instanceof ThraggExiledEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_AttackDuration, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) + 1));
		if (entity instanceof ThraggExiledEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_GlobalAttackCooldown, 20);
		entity.setDeltaMovement(new Vec3(0, 0, 0));
		target.setDeltaMovement(new Vec3(0, 0, 0));
		if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) == 1) {
			{
				Entity _ent = entity;
				_ent.teleportTo((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() - 0.3), (target.getZ() + 1.5 * target.getLookAngle().z));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() - 0.3), (target.getZ() + 1.5 * target.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
			}
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) == 4) {
			{
				Entity _ent = entity;
				_ent.teleportTo((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() - 0.3), (target.getZ() + 1.5 * target.getLookAngle().z));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((target.getX() + 1.5 * target.getLookAngle().x), (target.getY() - 0.3), (target.getZ() + 1.5 * target.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
			}
			entity.getPersistentData().putDouble("playerPosX", (target.getX()));
			entity.getPersistentData().putDouble("playerPosY", (target.getY()));
			entity.getPersistentData().putDouble("playerPosZ", (target.getZ()));
			if (entity instanceof ThraggExiledEntity) {
				((ThraggExiledEntity) entity).setAnimation("impale");
			}
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) == 16) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 100, 0.25, 0.25, 0.25, 0.25);
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.BLEED.get(), 200, 1));
			target.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity), 70);
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) > 4
				&& (entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) < 44) {
			{
				Entity _ent = target;
				_ent.teleportTo((entity.getPersistentData().getDouble("playerPosX")), (entity.getPersistentData().getDouble("playerPosY")), (entity.getPersistentData().getDouble("playerPosZ")));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((entity.getPersistentData().getDouble("playerPosX")), (entity.getPersistentData().getDouble("playerPosY")), (entity.getPersistentData().getDouble("playerPosZ")), _ent.getYRot(), _ent.getXRot());
			}
			target.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entity.getX()), (entity.getY() + 1.5), (entity.getZ())));
			if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) > 16) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 25, 0.25, 0.25, 0.25, 0.25);
			}
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) >= 44) {
			if (entity instanceof ThraggExiledEntity _datEntSetS)
				_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, "IDLE");
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_ImpaleCooldown, 200);
		}
	}
}
