package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.entity.DuplicationCloneEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

@Mod.EventBusSubscriber
public class DisplacementTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double raytrace_distance = 0;
		boolean entity_found = false;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).displacemet_targetting) {
			raytrace_distance = 0;
			entity_found = false;
			for (int index0 = 0; index0 < 30; index0++) {
				if (!world
						.getEntitiesOfClass(DuplicationCloneEntity.class,
								AABB.ofSize(
										new Vec3(
												(entity.level()
														.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
														.getBlockPos().getX()),
												(entity.level()
														.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																entity))
														.getBlockPos().getY()),
												(entity.level()
														.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																entity))
														.getBlockPos().getZ())),
										1, 1, 1),
								e -> true)
						.isEmpty()
						&& !(((Entity) world
								.getEntitiesOfClass(DuplicationCloneEntity.class,
										AABB.ofSize(
												new Vec3(
														(entity.level()
																.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																		entity))
																.getBlockPos().getX()),
														(entity.level()
																.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																		entity))
																.getBlockPos().getY()),
														(entity.level().clip(
																new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
																.getBlockPos().getZ())),
												1, 1, 1),
										e -> true)
								.stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
												.getBlockPos().getX()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
												.getBlockPos().getY()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
												.getBlockPos().getZ())))
								.findFirst().orElse(null)) == entity)) {
					if (((Entity) world
							.getEntitiesOfClass(DuplicationCloneEntity.class,
									AABB.ofSize(
											new Vec3(
													(entity.level()
															.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																	entity))
															.getBlockPos().getX()),
													(entity.level()
															.clip(new ClipContext(
																	entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getY()),
													(entity.level().clip(
															new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getZ())),
											1, 1, 1),
									e -> true)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
											.getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
											.getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
											.getBlockPos().getZ())))
							.findFirst().orElse(null)).isAlive()) {
						if (((Entity) world
								.getEntitiesOfClass(DuplicationCloneEntity.class,
										AABB.ofSize(
												new Vec3(
														(entity.level()
																.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																		entity))
																.getBlockPos().getX()),
														(entity.level()
																.clip(new ClipContext(
																		entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
																.getBlockPos().getY()),
														(entity.level().clip(
																new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
																.getBlockPos().getZ())),
												1, 1, 1),
										e -> true)
								.stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
												.getBlockPos().getX()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
												.getBlockPos().getY()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
												.getBlockPos().getZ())))
								.findFirst().orElse(null)) instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) {
							entity_found = true;
						}
					}
				} else {
					entity_found = false;
					raytrace_distance = raytrace_distance + 1;
				}
			}
			if (entity_found) {
				{
					String _setval = ((Entity) world
							.getEntitiesOfClass(DuplicationCloneEntity.class,
									AABB.ofSize(
											new Vec3(
													(entity.level()
															.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																	entity))
															.getBlockPos().getX()),
													(entity.level()
															.clip(new ClipContext(
																	entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getY()),
													(entity.level().clip(
															new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
															.getBlockPos().getZ())),
											1, 1, 1),
									e -> true)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
											.getBlockPos().getX()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
											.getBlockPos().getY()),
									(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity))
											.getBlockPos().getZ())))
							.findFirst().orElse(null)).getStringUUID();
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.displacement_target = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				{
					String _setval = "";
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.displacement_target = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
