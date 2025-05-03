package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

import java.util.List;
import java.util.Comparator;

public class ViltrumitePunchFollowUpProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double rand = 0;
		double rand2 = 0;
		double rand3 = 0;
		double fov = 0;
		double i = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator == entity)) {
					if ((entity.getPersistentData().getString("track")).equals(entityiterator.getStringUUID())) {
						if (entityiterator.isAlive()) {
							if (!entityiterator.onGround()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:followup")),
												SoundSource.AMBIENT, 80, 1);
									} else {
										_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:followup")), SoundSource.AMBIENT,
												80, 1, false);
									}
								}
								entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
								entity.setDeltaMovement(new Vec3(0, 0, 0));
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 2, 0, false, false));
								{
									Entity _ent = entity;
									_ent.teleportTo((entityiterator.getX() + (-1.5) * entity.getLookAngle().x), (entityiterator.getY() + (-1.5) * entity.getLookAngle().y), (entityiterator.getZ() + (-1.5) * entity.getLookAngle().z));
									if (_ent instanceof ServerPlayer _serverPlayer)
										_serverPlayer.connection.teleport((entityiterator.getX() + (-1.5) * entity.getLookAngle().x), (entityiterator.getY() + (-1.5) * entity.getLookAngle().y),
												(entityiterator.getZ() + (-1.5) * entity.getLookAngle().z), _ent.getYRot(), _ent.getXRot());
								}
								entity.getPersistentData().putString("track", "");
								{
									boolean _setval = true;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.flying = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								entityiterator.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entity.getX()), (entity.getY() + (entity.getBbHeight() * 2) / 3), (entity.getZ())));
							} else {
								entity.getPersistentData().putString("track", "");
							}
						}
					}
				}
			}
		}
	}
}
