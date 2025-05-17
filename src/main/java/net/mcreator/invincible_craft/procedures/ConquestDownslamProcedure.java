package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
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
import net.mcreator.invincible_craft.entity.ConquestEntity;

public class ConquestDownslamProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity target) {
		if (entity == null || target == null)
			return;
		double knockbackres = 0;
		if (entity instanceof ConquestEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ConquestEntity.DATA_AttackDuration, (int) ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_AttackDuration) : 0) + 1));
		if (entity instanceof ConquestEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ConquestEntity.DATA_GlobalAttackCooldown, 20);
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
		if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_AttackDuration) : 0) == 1) {
			{
				Entity _ent = entity;
				_ent.teleportTo((target.getX()), (target.getY() + -1.4), (target.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((target.getX()), (target.getY() + -1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
			}
		} else if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_AttackDuration) : 0) == 3) {
			entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_2.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 1, 0, 0, 0, 0);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (target.getX()), (target.getY() + target.getBbHeight() / 2), (target.getZ()), 45, 0.25, 0.25, 0.25, 0.25);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
				}
			}
			target.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity), 25);
			knockbackres = Math.min(1,
					Math.max(target instanceof LivingEntity _livingEntity28 && _livingEntity28.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE) ? _livingEntity28.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() : 0, 0));
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 25, 0, false, false));
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 20, 0, false, false));
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get(), 10, 3, false, false));
			if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.DOWNSLAM.get(), 30, 0, false, false));
		} else if ((entity instanceof ConquestEntity _datEntI ? _datEntI.getEntityData().get(ConquestEntity.DATA_AttackDuration) : 0) >= 5) {
			{
				Entity _ent = entity;
				_ent.teleportTo((target.getX()), (target.getY() + -1.4), (target.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((target.getX()), (target.getY() + -1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
			}
			if (entity instanceof ConquestEntity _datEntSetS)
				_datEntSetS.getEntityData().set(ConquestEntity.DATA_State, "IDLE");
			if (entity instanceof ConquestEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ConquestEntity.DATA_DownslamCooldown, 120);
		}
	}
}
