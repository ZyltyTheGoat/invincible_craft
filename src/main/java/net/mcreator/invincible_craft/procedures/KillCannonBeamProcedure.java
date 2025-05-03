package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

import java.util.List;
import java.util.Comparator;

public class KillCannonBeamProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double yaw = 0;
		double pitch = 0;
		double delay = 0;
		double sx = 0;
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
					((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
			entity.getPersistentData().putDouble("KillCannonRepeat", 0);
			entity.getPersistentData().putDouble("BeamX", (entity.getLookAngle().x));
			entity.getPersistentData().putDouble("BeamY", (entity.getLookAngle().y));
			entity.getPersistentData().putDouble("BeamZ", (entity.getLookAngle().z));
			for (int index0 = 0; index0 < 60; index0++) {
				entity.getPersistentData().putDouble("KillCannonRepeat", (entity.getPersistentData().getDouble("KillCannonRepeat") + 1));
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.KILL_CANNON_CLOUD.get()), (entity.getX() + entity.getPersistentData().getDouble("KillCannonRepeat") * entity.getPersistentData().getDouble("BeamX")),
							(entity.getY() + 1.6 + entity.getPersistentData().getDouble("KillCannonRepeat") * entity.getPersistentData().getDouble("BeamY")),
							(entity.getZ() + entity.getPersistentData().getDouble("KillCannonRepeat") * entity.getPersistentData().getDouble("BeamZ")), 5, 0.1, 0.1, 0.1, 0.01);
				{
					final Vec3 _center = new Vec3((entity.getX() + entity.getPersistentData().getDouble("KillCannonRepeat") * entity.getPersistentData().getDouble("BeamX")),
							(entity.getY() + 1.6 + entity.getPersistentData().getDouble("KillCannonRepeat") * entity.getPersistentData().getDouble("BeamY")),
							(entity.getZ() + entity.getPersistentData().getDouble("KillCannonRepeat") * entity.getPersistentData().getDouble("BeamZ")));
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator) && !(entityiterator instanceof ExperienceOrb) && !(entityiterator instanceof ItemEntity)) {
							entityiterator.hurt(
									new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:sonic_clap_damage"))), entity), 8);
						}
					}
				}
				sx = -3;
			}
		}
	}
}
