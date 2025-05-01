package net.mcreator.invincible_craft.procedures;

import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.api.ScaleOperations;

import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.entity.AtomicbBlastEntity;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class AbilityButton3OnKeyReleasedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double sy = 0;
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
			if (!DoesHaveCDAbility3Procedure.execute(entity)) {
				if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_awakened_timer > 0) {
					{
						boolean _setval = false;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.atom_eve_atomic_ray = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "base_animation", true);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										InvincibleCraftMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.InvincibleCraftModAnimationMessage(Component.literal("base_animation"), entity.getId(), true), connection, NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
					{
						double _setval = 15;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.ability_cooldown_3 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					{
						double _setval = 5;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.ability_cooldown_3 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = false;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.atom_eve_atomic_blast_holding = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity.isShiftKeyDown()) {
						sy = entity.getY() + 1.2;
					} else {
						sy = entity.getY() + 1.6;
					}
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
											_ent.level().getServer(), _ent),
									("summon invincible_craft:atomicb_blast " + (entity.getX() + 0.5 * entity.getLookAngle().x) + " " + sy + " " + (entity.getZ() + 0.5 * entity.getLookAngle().z) + " {Rotation:[" + entity.getYRot() + "f,0f]}"));
						}
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(15 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof AtomicbBlastEntity && !(entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false)) {
								entityiterator.getPersistentData().putDouble("dx", (entity.getLookAngle().x));
								entityiterator.getPersistentData().putDouble("dy", (entity.getLookAngle().y));
								entityiterator.getPersistentData().putDouble("dz", (entity.getLookAngle().z));
								ScaleTypes.WIDTH.getScaleData(entityiterator).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.WIDTH.getScaleData(entityiterator).getTargetScale(),
										(1 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
								ScaleTypes.HITBOX_WIDTH.getScaleData(entityiterator).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_WIDTH.getScaleData(entityiterator).getTargetScale(),
										(0.5 + 0.01 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
								ScaleTypes.HEIGHT.getScaleData(entityiterator).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HEIGHT.getScaleData(entityiterator).getTargetScale(),
										(1 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
								ScaleTypes.HITBOX_HEIGHT.getScaleData(entityiterator).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_HEIGHT.getScaleData(entityiterator).getTargetScale(),
										(0.5 + 0.01 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale)));
								entityiterator.getPersistentData().putDouble("scale",
										(1 + 0.09 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_blast_scale));
								{
									double _setval = 0;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.atom_eve_atomic_blast_scale = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								if (entityiterator instanceof TamableAnimal _toTame && entity instanceof Player _owner)
									_toTame.tame(_owner);
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOMIC_BLAST_SHOCKWAVE.get()), (entity.getX() + 0.5 * entity.getLookAngle().x), (sy + 0.5 * entity.getLookAngle().y),
											(entity.getZ() + 0.5 * entity.getLookAngle().z), 1, 0, 0, 0, 0);
								if (entityiterator instanceof TamableAnimal _toTame && entity instanceof Player _owner)
									_toTame.tame(_owner);
							}
						}
					}
					if (world.isClientSide()) {
						SetupAnimationsProcedure.setAnimationClientside((Player) entity, "atomic_blast_shoot", true);
					}
					if (!world.isClientSide()) {
						if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
							List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
							synchronized (connections) {
								Iterator<Connection> iterator = connections.iterator();
								while (iterator.hasNext()) {
									Connection connection = iterator.next();
									if (!connection.isConnecting() && connection.isConnected())
										InvincibleCraftMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.InvincibleCraftModAnimationMessage(Component.literal("atomic_blast_shoot"), entity.getId(), true), connection,
												NetworkDirection.PLAY_TO_CLIENT);
								}
							}
						}
					}
				}
			}
		}
	}
}
