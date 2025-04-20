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
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.entity.AtomicbBlastEntity;

import java.util.List;
import java.util.Comparator;

public class AtomicBlastOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof AtomicbBlastEntity _datEntSetI)
			_datEntSetI.getEntityData().set(AtomicbBlastEntity.DATA_life, (int) ((entity instanceof AtomicbBlastEntity _datEntI ? _datEntI.getEntityData().get(AtomicbBlastEntity.DATA_life) : 0) + 1));
		entity.setDeltaMovement(new Vec3((entity.getPersistentData().getDouble("dx") * 2), (entity.getPersistentData().getDouble("dy") * 2), (entity.getPersistentData().getDouble("dz") * 2)));
		if ((entity instanceof AtomicbBlastEntity _datEntI ? _datEntI.getEntityData().get(AtomicbBlastEntity.DATA_life) : 0) >= 30) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
		if (!(entity instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false)) {
			if ((entity instanceof AtomicbBlastEntity _datEntI ? _datEntI.getEntityData().get(AtomicbBlastEntity.DATA_life) : 0) > 1) {
				if (entity.getPersistentData().getDouble("scale") >= 9) {
					if (world instanceof Level _level && !_level.isClientSide())
						_level.explode(null, x, y, z, 3, Level.ExplosionInteraction.NONE);
				}
				if (!entity.level().isClientSide())
					entity.discard();
			}
		} else {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((1 + 0.2 * entity.getPersistentData().getDouble("scale")) / 2d), e -> true).stream()
						.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (!(entity == entityiterator) && entityiterator instanceof LivingEntity
							&& !(entityiterator instanceof TamableAnimal _tamIsTamedBy && (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)
							&& !(entityiterator == (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null))) {
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity), 5);
						if (entity.getPersistentData().getDouble("scale") >= 9) {
							if (world instanceof Level _level && !_level.isClientSide())
								_level.explode(null, x, y, z, 3, Level.ExplosionInteraction.NONE);
						}
						if (!entity.level().isClientSide())
							entity.discard();
					}
				}
			}
			double scale = entity.getPersistentData().getDouble("scale");
			int radius = 1;
			boolean hasBlockNearby = false;
			BlockPos centerPos = BlockPos.containing(x, y, z);
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dy = -radius; dy <= radius; dy++) {
					for (int dz = -radius; dz <= radius; dz++) {
						BlockPos checkPos = centerPos.offset(dx, dy, dz);
						if (!world.getBlockState(checkPos).isAir()) {
							hasBlockNearby = true;
							break;
						}
					}
					if (hasBlockNearby)
						break;
				}
				if (hasBlockNearby)
					break;
			}
			if (hasBlockNearby) {
				if (scale >= 9 && world instanceof Level _level && !_level.isClientSide()) {
					_level.explode(null, x, y, z, 3, Level.ExplosionInteraction.NONE);
				}
				if (!entity.level().isClientSide()) {
					entity.discard();
				}
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), x, y, z, (int) (10 + 3 * entity.getPersistentData().getDouble("scale")), (0.1 + 0.1 * entity.getPersistentData().getDouble("scale")),
					(0.1 + 0.1 * entity.getPersistentData().getDouble("scale")), (0.1 + 0.1 * entity.getPersistentData().getDouble("scale")), 0.01);
	}
}
