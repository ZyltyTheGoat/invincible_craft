package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.entity.KillCannonBlastEntity;

public class KillCannonOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean targetFlying = false;
		double random = 0;
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (world.dayTime() % 100 == 0) {
				random = Mth.nextInt(RandomSource.create(), 1, 3);
				if (random <= 2) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.KILL_CANNON_CLOUD.get()), x, (y + 1.6), z, 25, 0.1, 0.3, 0.1, 0.1);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.KILL_CANNON_SHOCKWAVE.get()), x, (y + 1.6), z, 1, 0, 0, 0, 0);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					if (world instanceof ServerLevel _serverLevel) {
						Entity entityinstance = InvincibleCraftModEntities.KILL_CANNON_BLAST.get().create(_serverLevel, null, null, BlockPos.containing(x, y + 1.6, z), MobSpawnType.MOB_SUMMONED, false, false);
						if (entityinstance != null) {
							entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
							if (entityinstance instanceof KillCannonBlastEntity _datEntSetS)
								_datEntSetS.getEntityData().set(KillCannonBlastEntity.DATA_owner, (entity.getStringUUID()));
							entityinstance.getPersistentData().putDouble("dx", ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - entityinstance.getX()));
							entityinstance.getPersistentData().putDouble("dy", (((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + entity.getBbHeight() / 2) - entityinstance.getY()));
							entityinstance.getPersistentData().putDouble("dz", ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - entityinstance.getZ()));
							_serverLevel.addFreshEntity(entityinstance);
						}
					}
				} else {
					KillCannonBeamProcedure.execute(world, entity);
				}
			}
		}
	}
}
