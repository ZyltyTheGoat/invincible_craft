package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.entity.SpyDroneOrbEntity;
import net.mcreator.invincible_craft.InvincibleCraftMod;

public class OrbsSpawnerPortalOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double y, Entity entity) {
		if (entity == null)
			return;
		InvincibleCraftMod.queueServerWork(1, () -> {
			if (world instanceof ServerLevel projectileLevel) {
				Projectile _entityToSpawn = new Object() {
					public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
						AbstractArrow entityToSpawn = new SpyDroneOrbEntity(InvincibleCraftModEntities.SPY_DRONE_ORB.get(), level);
						entityToSpawn.setOwner(shooter);
						entityToSpawn.setBaseDamage(damage);
						entityToSpawn.setKnockback(knockback);
						entityToSpawn.setSilent(true);
						return entityToSpawn;
					}
				}.getArrow(projectileLevel, (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null), 5, 1);
				_entityToSpawn.setPos((entity.getX() + 0.2 * entity.getLookAngle().x), (y + 1.6), (entity.getZ() + 0.2 * entity.getLookAngle().z));
				_entityToSpawn.shoot(((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getLookAngle().x), ((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getLookAngle().y),
						((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getLookAngle().z), 4, 0);
				projectileLevel.addFreshEntity(_entityToSpawn);
			}
		});
	}
}
