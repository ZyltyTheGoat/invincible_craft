package net.mcreator.invincible_craft.procedures;

import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.api.ScaleOperations;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class AbilityButton3OnKeyReleasedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
			if (!(entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding) {
				if (entity.getPersistentData().getBoolean("barrage")) {
					ViltrumitePunchAbilityProcedure.execute(world, x, y, z, entity);
					{
						double _setval = 5;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.ability_cooldown_3 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
			entity.getPersistentData().putBoolean("barrage", false);
			entity.getPersistentData().putBoolean("crush", false);
		} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("DimensionalTravel")) {
			if (entity.getPersistentData().getBoolean("riftDash")) {
				entity.getPersistentData().putBoolean("riftDash", false);
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.INVISIBILITY);
				entity.setDeltaMovement(new Vec3(0, 0, 0));
				{
					boolean _setval = true;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.disable_fall = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 5;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ability_cooldown_3 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (world instanceof ServerLevel _serverLevel) {
					Entity entityinstance = InvincibleCraftModEntities.PORTAL_DASH_PORTAL.get().create(_serverLevel, null, null,
							BlockPos.containing(entity.getX() + (-2) * entity.getLookAngle().x, entity.getY(), entity.getZ() + (-2) * entity.getLookAngle().z), MobSpawnType.MOB_SUMMONED, false, false);
					if (entityinstance != null) {
						entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
						if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
							_toTame.tame(_owner);
						{
							Entity _ent = entityinstance;
							_ent.setYRot(entity.getYRot());
							_ent.setXRot(entity.getXRot());
							_ent.setYBodyRot(_ent.getYRot());
							_ent.setYHeadRot(_ent.getYRot());
							_ent.yRotO = _ent.getYRot();
							_ent.xRotO = _ent.getXRot();
							if (_ent instanceof LivingEntity _entity) {
								_entity.yBodyRotO = _entity.getYRot();
								_entity.yHeadRotO = _entity.getYRot();
							}
						}
						_serverLevel.addFreshEntity(entityinstance);
					}
				}
			}
		} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("AtomEve")) {
			{
				boolean _setval = false;
				entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.atom_eve_atomic_blast_holding = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof ServerLevel _serverLevel) {
				Entity entityinstance = InvincibleCraftModEntities.ATOMICB_BLAST.get().create(_serverLevel, null, null,
						BlockPos.containing(entity.getX() + 0.5 * entity.getLookAngle().x, entity.getY() + 1.8 + 0.5 * entity.getLookAngle().y, entity.getZ() + 0.5 * entity.getLookAngle().z), MobSpawnType.MOB_SUMMONED, false, false);
				if (entityinstance != null) {
					entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
					entityinstance.getPersistentData().putDouble("dx", (entity.getLookAngle().x));
					entityinstance.getPersistentData().putDouble("dy", (entity.getLookAngle().y));
					entityinstance.getPersistentData().putDouble("dz", (entity.getLookAngle().z));
					ScaleTypes.WIDTH.getScaleData(entityinstance).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.WIDTH.getScaleData(entityinstance).getTargetScale(),
							(1 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
					ScaleTypes.HITBOX_WIDTH.getScaleData(entityinstance).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_WIDTH.getScaleData(entityinstance).getTargetScale(),
							(0.5 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
					ScaleTypes.HEIGHT.getScaleData(entityinstance).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HEIGHT.getScaleData(entityinstance).getTargetScale(),
							(1 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
					ScaleTypes.HITBOX_HEIGHT.getScaleData(entityinstance).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_HEIGHT.getScaleData(entityinstance).getTargetScale(),
							(0.5 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
					entityinstance.getPersistentData().putDouble("scale",
							(1 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale));
					{
						double _setval = 0;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.atom_eve_atomic_blast_scale = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
						_toTame.tame(_owner);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOMIC_BLAST_SHOCKWAVE.get()), (entity.getX() + 0.5 * entity.getLookAngle().x), (entity.getY() + 1.6 + 0.5 * entity.getLookAngle().y),
								(entity.getZ() + 0.5 * entity.getLookAngle().z), 1, 0, 0, 0, 0);
					_serverLevel.addFreshEntity(entityinstance);
				}
			}
		}
	}
}
