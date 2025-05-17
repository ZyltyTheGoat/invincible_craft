package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.entity.ThraggExiledEntity;

import java.util.List;
import java.util.Comparator;

public class ThraggChopProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity target) {
		if (entity == null || target == null)
			return;
		double knockbackres = 0;
		double particleNum = 0;
		double vX = 0;
		double vY = 0;
		double vZ = 0;
		double i = 0;
		double x_pos = 0;
		double z_pos = 0;
		double hei = 0;
		double speed = 0;
		double arcAngle = 0;
		double rand = 0;
		double radAngle = 0;
		double radYaw = 0;
		double radPitch = 0;
		double angle = 0;
		double y_pos = 0;
		double radius = 0;
		if (entity instanceof ThraggExiledEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_AttackDuration, (int) ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) + 1));
		if (entity instanceof ThraggExiledEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_GlobalAttackCooldown, 20);
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
		if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 255, false, false));
		if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get(), 5, 0, false, false));
		if (target instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 5, 0, false, false));
		entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + 1.4), (target.getZ())));
		{
			Entity _ent = entity;
			_ent.teleportTo((target.getX()), (target.getY()), (target.getZ()));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport((target.getX()), (target.getY()), (target.getZ()), _ent.getYRot(), _ent.getXRot());
		}
		entity.setDeltaMovement(new Vec3(0, 0, 0));
		target.setDeltaMovement(new Vec3(0, 0, 0));
		if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) == 7) {
			rand = Mth.nextInt(RandomSource.create(), 1, 3);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:viltrumite_chop")), SoundSource.NEUTRAL, (float) 0.5,
							(float) Mth.nextDouble(RandomSource.create(), 0.75, 1.25));
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:viltrumite_chop")), SoundSource.NEUTRAL, (float) 0.5,
							(float) Mth.nextDouble(RandomSource.create(), 0.75, 1.25), false);
				}
			}
			if (rand == 1) {
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				radius = 2.3;
				hei = 2;
				speed = 5;
				particleNum = 30;
				arcAngle = 180;
				radYaw = Math.toRadians(entity.getYRot() + 90);
				radPitch = Math.toRadians((entity.getXRot() + 90) * (-1));
				for (int index0 = 0; index0 < (int) particleNum; index0++) {
					angle = i * (arcAngle / particleNum);
					radAngle = Math.toRadians(angle);
					vX = (Math.sin(radAngle) * Math.sin(radPitch) * Math.cos(radYaw) + Math.cos(radAngle) * Math.sin(radYaw)) * (-1);
					vY = Math.sin(radAngle) * Math.cos(radPitch);
					vZ = Math.sin(radAngle) * Math.sin(radPitch) * Math.sin(radYaw) * (-1) + Math.cos(radAngle) * Math.cos(radYaw);
					x_pos = entity.getX() + radius * vX;
					y_pos = entity.getY() + hei + radius * vY;
					z_pos = entity.getZ() + radius * vZ;
					i = i + 1;
					hei = hei + -0.133;
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CLOUD, x_pos, (y_pos + 1.8), z_pos, 1, 0, 0, 0, 0);
					if (world.getBlockState(BlockPos.containing(x_pos, y_pos + 1.6, z_pos)).getDestroySpeed(world, BlockPos.containing(x_pos, y_pos + 1.6, z_pos)) != -1) {
						world.setBlock(BlockPos.containing(x_pos, y_pos + 1.6, z_pos), Blocks.AIR.defaultBlockState(), 3);
					}
					{
						final Vec3 _center = new Vec3(x_pos, (y_pos + 1.6), z_pos);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entity == entityiterator) && entityiterator instanceof LivingEntity && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:chop"))), entity),
										55);
							}
						}
					}
				}
			} else if (rand == 2) {
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				radius = 2.3;
				hei = 0;
				speed = 5;
				particleNum = 30;
				arcAngle = 180;
				radYaw = Math.toRadians(entity.getYRot() + 90);
				radPitch = Math.toRadians((entity.getXRot() + 90) * (-1));
				for (int index1 = 0; index1 < (int) particleNum; index1++) {
					angle = i * (arcAngle / particleNum);
					radAngle = Math.toRadians(angle);
					vX = (Math.sin(radAngle) * Math.sin(radPitch) * Math.cos(radYaw) + Math.cos(radAngle) * Math.sin(radYaw)) * (-1);
					vY = Math.sin(radAngle) * Math.cos(radPitch);
					vZ = Math.sin(radAngle) * Math.sin(radPitch) * Math.sin(radYaw) * (-1) + Math.cos(radAngle) * Math.cos(radYaw);
					x_pos = entity.getX() + radius * vX;
					y_pos = entity.getY() + hei + radius * vY;
					z_pos = entity.getZ() + radius * vZ;
					i = i + 1;
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CLOUD, x_pos, (y_pos + 1.8), z_pos, 1, 0, 0, 0, 0);
					if (world.getBlockState(BlockPos.containing(x_pos, y_pos + 1.6, z_pos)).getDestroySpeed(world, BlockPos.containing(x_pos, y_pos + 1.6, z_pos)) != -1) {
						world.setBlock(BlockPos.containing(x_pos, y_pos + 1.6, z_pos), Blocks.AIR.defaultBlockState(), 3);
					}
					{
						final Vec3 _center = new Vec3(x_pos, (y_pos + 1.6), z_pos);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entity == entityiterator) && entityiterator instanceof LivingEntity && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:chop"))), entity),
										50);
							}
						}
					}
				}
			}
		} else if ((entity instanceof ThraggExiledEntity _datEntI ? _datEntI.getEntityData().get(ThraggExiledEntity.DATA_AttackDuration) : 0) >= 10) {
			{
				Entity _ent = entity;
				_ent.teleportTo((target.getX()), (target.getY() + 1.4), (target.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((target.getX()), (target.getY() + 1.4), (target.getZ()), _ent.getYRot(), _ent.getXRot());
			}
			if (entity instanceof ThraggExiledEntity _datEntSetS)
				_datEntSetS.getEntityData().set(ThraggExiledEntity.DATA_State, "IDLE");
			if (entity instanceof ThraggExiledEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ThraggExiledEntity.DATA_ChopCooldown, 150);
		}
	}
}
