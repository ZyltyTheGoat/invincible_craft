package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.entity.KillCannonBlastEntity;

import java.util.List;
import java.util.Comparator;

public class KillCannonBlastOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof KillCannonBlastEntity _datEntSetI)
			_datEntSetI.getEntityData().set(KillCannonBlastEntity.DATA_life, (int) ((entity instanceof KillCannonBlastEntity _datEntI ? _datEntI.getEntityData().get(KillCannonBlastEntity.DATA_life) : 0) + 1));
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.KILL_CANNON_CLOUD.get()), x, y, z, 10, 0.4, 0.4, 0.4, 0);
		entity.setDeltaMovement(new Vec3((entity.getPersistentData().getDouble("dx") * 0.275), (entity.getPersistentData().getDouble("dy") * 0.275), (entity.getPersistentData().getDouble("dz") * 0.275)));
		if ((entity instanceof KillCannonBlastEntity _datEntI ? _datEntI.getEntityData().get(KillCannonBlastEntity.DATA_life) : 0) >= 30) {
			if (!entity.level().isClientSide())
				entity.discard();
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, 3, Level.ExplosionInteraction.NONE);
		}
		if ((entity instanceof KillCannonBlastEntity _datEntS ? _datEntS.getEntityData().get(KillCannonBlastEntity.DATA_owner) : "").equals("")) {
			if ((entity instanceof KillCannonBlastEntity _datEntI ? _datEntI.getEntityData().get(KillCannonBlastEntity.DATA_life) : 0) > 1) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		} else {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (!(entity == entityiterator) && entityiterator instanceof LivingEntity
							&& !((entity instanceof KillCannonBlastEntity _datEntS ? _datEntS.getEntityData().get(KillCannonBlastEntity.DATA_owner) : "").equals(entityiterator.getStringUUID()))) {
						entityiterator.hurt(
								new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null), entity), 10);
						if (!entity.level().isClientSide())
							entity.discard();
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.KILL_CANNON_SHOCKWAVE.get()), x, y, z, 1, 0, 0, 0, 0);
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.KILL_CANNON_CLOUD.get()), x, y, z, 15, 0.2, 0.2, 0.2, 0.1);
					}
				}
			}
		}
	}
}
