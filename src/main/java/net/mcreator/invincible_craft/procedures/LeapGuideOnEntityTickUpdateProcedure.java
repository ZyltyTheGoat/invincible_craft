package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.invincible_craft.entity.LeapGuideEntity;

import java.util.List;
import java.util.Comparator;

public class LeapGuideOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double duration = 0;
		double tx = 0;
		double t = 0;
		double sx = 0;
		double ty = 0;
		double sy = 0;
		double tz = 0;
		double sz = 0;
		double easedT = 0;
		double height = 0;
		if (entity instanceof LeapGuideEntity _datEntL0 && _datEntL0.getEntityData().get(LeapGuideEntity.DATA_IS_RETRACTING)) {
			sx = entity.getPersistentData().getDouble("sx");
			sy = entity.getPersistentData().getDouble("sy");
			sz = entity.getPersistentData().getDouble("sz");
			tx = entity.getPersistentData().getDouble("tx");
			ty = entity.getPersistentData().getDouble("ty");
			tz = entity.getPersistentData().getDouble("tz");
			duration = entity instanceof LeapGuideEntity _datEntI ? _datEntI.getEntityData().get(LeapGuideEntity.DATA_LIFE) : 0;
			height = entity instanceof LeapGuideEntity _datEntI ? _datEntI.getEntityData().get(LeapGuideEntity.DATA_MAX_HEIGHT) : 0;
			if (entity instanceof LeapGuideEntity _datEntSetI)
				_datEntSetI.getEntityData().set(LeapGuideEntity.DATA_PROGRESS, (int) ((entity instanceof LeapGuideEntity _datEntI ? _datEntI.getEntityData().get(LeapGuideEntity.DATA_PROGRESS) : 0) + 1));
			t = (entity instanceof LeapGuideEntity _datEntI ? _datEntI.getEntityData().get(LeapGuideEntity.DATA_PROGRESS) : 0) / duration;
			easedT = (t * t) * (3 - 2 * t);
			{
				Entity _ent = entity;
				_ent.teleportTo(sx + (tx - sx) * easedT, sy + (ty - sy) * easedT + (4 * height * easedT * (1 - easedT)), sz + (tz - sz) * easedT);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(sx + (tx - sx) * easedT, sy + (ty - sy) * easedT + (4 * height * easedT * (1 - easedT)), sz + (tz - sz) * easedT, _ent.getYRot(), _ent.getXRot());
			}
			if ((entity instanceof LeapGuideEntity _datEntI ? _datEntI.getEntityData().get(LeapGuideEntity.DATA_PROGRESS) : 0) >= duration) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(15 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if ((entityiterator.getStringUUID()).equals(entity instanceof LeapGuideEntity _datEntS ? _datEntS.getEntityData().get(LeapGuideEntity.DATA_LEAPER_UUID) : "")) {
						entityiterator.setDeltaMovement(new Vec3((1 * (entity.getX() - entityiterator.getX())), (1 * (entity.getY() - entityiterator.getY())), (1 * (entity.getZ() - entityiterator.getZ()))));
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CLOUD, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 1, 0, 0, 0, 0);
				}
			}
		}
	}
}
