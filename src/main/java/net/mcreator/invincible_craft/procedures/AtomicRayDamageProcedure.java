package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

import javax.annotation.Nullable;

import java.util.Comparator;

@Mod.EventBusSubscriber
public class AtomicRayDamageProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double raytrace_distance = 0;
		Entity ent = null;
		boolean entity_found = false;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_ray) {
			raytrace_distance = 0;
			entity_found = false;
			for (int index0 = 0; index0 < 50; index0++) {
				if (!((world
						.getBlockState(BlockPos.containing(entity.getX() + raytrace_distance * entity.getLookAngle().x, entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y, entity.getZ() + raytrace_distance * entity.getLookAngle().z)))
						.getBlock() == Blocks.AIR)) {
					if (Math.random() < (1) / ((float) 5)) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL,
													new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y),
															(entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
													Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											"particle dust{color:[1.0,0.65,0.66],scale:1} ~ ~ ~ 0.5 0.5 0.5 0.2 5");
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SMOKE.get()), (entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y),
									(entity.getZ() + raytrace_distance * entity.getLookAngle().z), 2, 0.2, 0.2, 0.2, 0.05);
						break;
					}
				}
				if (!world
						.getEntitiesOfClass(LivingEntity.class,
								AABB.ofSize(new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
										1, 1, 1),
								e -> true)
						.isEmpty()
						&& !(((Entity) world.getEntitiesOfClass(LivingEntity.class,
								AABB.ofSize(new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.6 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
										1, 1, 1),
								e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.6 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)))
								.findFirst().orElse(null)) == entity)) {
					entity_found = true;
					if (!(((Entity) world.getEntitiesOfClass(LivingEntity.class, AABB
							.ofSize(new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)), 1, 1, 1),
							e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)))
							.findFirst().orElse(null)) == (null))) {
						if (!(((Entity) world.getEntitiesOfClass(LivingEntity.class,
								AABB.ofSize(new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
										1, 1, 1),
								e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)))
								.findFirst().orElse(null)) == entity)) {
							ent = (Entity) world.getEntitiesOfClass(LivingEntity.class,
									AABB.ofSize(
											new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
											1, 1, 1),
									e -> true).stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
										}
									}.compareDistOf((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z)))
									.findFirst().orElse(null);
						}
						if (!(ent == null)) {
							if (ent instanceof LivingEntity livingEntity) {
								livingEntity.invulnerableTime = 2; // Reset hurt timer
							}
							ent.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
									(float) (0.5 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_intelligence));
							if (Math.random() < (1) / ((float) 5)) {
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL,
													new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y),
															(entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
													Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											"particle dust{color:[1.0,0.65,0.66],scale:1} ~ ~ ~ 0.5 0.5 0.5 0.2 5");
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SMOKE.get()), (entity.getX() + raytrace_distance * entity.getLookAngle().x),
											(entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y), (entity.getZ() + raytrace_distance * entity.getLookAngle().z), 2, 0.2, 0.2, 0.2, 0.05);
								break;
							}
						}
					}
				} else {
					entity_found = false;
					raytrace_distance = raytrace_distance + 0.3;
					if (Math.random() < (1) / ((float) 7)) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL,
													new Vec3((entity.getX() + raytrace_distance * entity.getLookAngle().x), (entity.getY() + 1.4 + raytrace_distance * entity.getLookAngle().y),
															(entity.getZ() + raytrace_distance * entity.getLookAngle().z)),
													Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											"particle dust{color:[1.0,0.65,0.66],scale:1} ~ ~ ~ 0.1 0.1 0.1 0.1 2");
					}
				}
			}
		}
	}
}
