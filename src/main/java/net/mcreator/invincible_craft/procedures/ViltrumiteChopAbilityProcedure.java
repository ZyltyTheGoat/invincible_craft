package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

import java.util.List;
import java.util.Comparator;

public class ViltrumiteChopAbilityProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
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
		double delay = 0;
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
			hei = -2;
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
				hei = hei + 0.133;
				if (world.getBlockState(BlockPos.containing(x_pos, y_pos + 1.6, z_pos)).getDestroySpeed(world, BlockPos.containing(x_pos, y_pos + 1.6, z_pos)) != -1) {
					world.setBlock(BlockPos.containing(x_pos, y_pos + 1.6, z_pos), Blocks.AIR.defaultBlockState(), 3);
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CLOUD, x_pos, (y_pos + 1.6), z_pos, 1, 0, 0, 0, 0);
				{
					final Vec3 _center = new Vec3(x_pos, (y_pos + 1.8), z_pos);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator) && entityiterator instanceof LivingEntity && !(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
							entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:chop"))), entity),
									(float) (11 + (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_strength * 0.3));
						}
					}
				}
			}
		} else if (rand == 2) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			radius = 2.3;
			hei = 2;
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
									(float) ((entity instanceof LivingEntity _livingEntity34 && _livingEntity34.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity34.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) * 2));
						}
					}
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			radius = 2.3;
			hei = 0;
			speed = 5;
			particleNum = 30;
			arcAngle = 180;
			radYaw = Math.toRadians(entity.getYRot() + 90);
			radPitch = Math.toRadians((entity.getXRot() + 90) * (-1));
			for (int index2 = 0; index2 < (int) particleNum; index2++) {
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
									(float) ((entity instanceof LivingEntity _livingEntity51 && _livingEntity51.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity51.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) * 2));
						}
					}
				}
			}
		}
	}
}
