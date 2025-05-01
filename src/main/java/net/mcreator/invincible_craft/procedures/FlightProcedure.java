package net.mcreator.invincible_craft.procedures;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

import javax.annotation.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class FlightProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double pitch_new = 0;
		double pitch = 0;
		double yaw = 0;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying) {
			if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get()))) {
				if (!Minecraft.getInstance().isPaused()) {
					if (entity.onGround()) {
						entity.setDeltaMovement(new Vec3(0, 0, 0));
						if (entity instanceof Player _plr && _plr.isFallFlying()) {
							_plr.stopFallFlying();
						}
						{
							boolean _setval = false;
							entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.flying = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						entity.setNoGravity(false);
						{
							double _setval = -1;
							entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.boost_timer = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = -1;
							entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.boost_cooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof LivingEntity _livEnt6 && _livEnt6.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
							{
								double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying_speed_changed * 0.1;
								entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.current_speed = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						} else {
							{
								double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying_speed_changed;
								entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.current_speed = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
						entity.noPhysics = false;
					} else {
						if (!(entity instanceof LivingEntity _livEnt7 && _livEnt7.hasEffect(InvincibleCraftModMobEffects.STUN.get()))) {
							if (entity.isSprinting() && GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
								if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).boost_timer <= 0
										&& (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).boost_cooldown <= 0 && new Object() {
											public boolean getValue() {
												boolean retBool = Minecraft.getInstance().options.keyJump.isDown();
												if (retBool) {
													if (Minecraft.getInstance().options.keyJump.getKeyModifier().toString().equals("SHIFT")) {
														retBool = Screen.hasShiftDown();
													} else if (Minecraft.getInstance().options.keyJump.getKeyModifier().toString().equals("CONTROL")) {
														retBool = Screen.hasControlDown();
													} else if (Minecraft.getInstance().options.keyJump.getKeyModifier().toString().equals("ALT")) {
														retBool = Screen.hasAltDown();
													}
												}
												return retBool;
											}
										}.getValue()) {
									if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
										_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 5, 0, false, false));
									{
										double _setval = 15;
										entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.boost_cooldown = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										double _setval = 10;
										entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.boost_timer = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_3.get()), x, y, z, 1, 0, 0, 0, 0);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SMALL_GUST.get()), x, y, z, 5, 1, 1, 1, 0);
								}
								if (!(entity instanceof LivingEntity _livEnt14 && _livEnt14.isFallFlying())) {
									if (entity instanceof Player _plr && !(_plr.isFallFlying())) {
										_plr.startFallFlying();
									}
								}
								entity.setNoGravity(true);
								entity.setDeltaMovement(
										new Vec3((entity.getLookAngle().x * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed),
												(entity.getLookAngle().y * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed),
												(entity.getLookAngle().z * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed)));
								if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).boost_cooldown >= 0) {
									{
										double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).boost_cooldown - 1;
										entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.boost_cooldown = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
								{
									double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying_speed_changed * 2;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.current_speed = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).boost_timer - 1;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.boost_timer = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
									if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
										if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed >= 2) {
											if (entity.level().isClientSide) {
												return; // Ensure this only runs on the server
											}
											Vec3 lookDirection = entity.getLookAngle();
											// Block 2 unit in front of where the entity is looking
											BlockPos centerPos = BlockPos.containing(entity.getX() + lookDirection.x * 2, entity.getY() + lookDirection.y * 2, entity.getZ() + lookDirection.z * 2);
											int radius = 5;
											double radiusSquared = radius * radius;
											Random random = new Random();
											for (int i = -radius; i <= radius; i++) {
												for (int j = -radius; j <= radius; j++) {
													for (int k = -radius; k <= radius; k++) {
														BlockPos targetPos = centerPos.offset(i, j, k);
														double distanceSquared = i * i + j * j + k * k;
														if (distanceSquared > radiusSquared)
															continue;
														// Outer layer variation: randomly skip ~25% of blocks
														if (distanceSquared > (radius - 2) * (radius - 1)) {
															if (random.nextFloat() < 0.25f)
																continue;
														}
														BlockState blockState = entity.level().getBlockState(targetPos);
														if (blockState.isAir() || blockState.getDestroySpeed(entity.level(), targetPos) < 0) {
															continue;
														}
														// Break the block with visual/sound effects
														entity.level().levelEvent(500, targetPos, Block.getId(blockState));
														entity.level().setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
													}
												}
											}
										}
									}
								} else {
									if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed < (entity
											.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying_speed_changed) {
										{
											double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed + 0.025;
											entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
												capability.current_speed = _setval;
												capability.syncPlayerVariables(entity);
											});
										}
									} else {
										{
											double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying_speed_changed;
											entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
												capability.current_speed = _setval;
												capability.syncPlayerVariables(entity);
											});
										}
									}
								}
							} else {
								{
									double _setval = 0;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.boost_timer = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								if (entity instanceof Player _plr && _plr.isFallFlying()) {
									_plr.stopFallFlying();
								}
								entity.setNoGravity(true);
								if (entity.getXRot() > 1) {
									pitch_new = 1;
								}
								if (entity.getXRot() < -1) {
									pitch_new = -1;
								}
								if (entity.getXRot() < 1 && entity.getXRot() > -1) {
									pitch_new = entity.getXRot();
								}
								if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
									if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS && GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 4;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
										yaw = entity.getYRot() * 0.0174533 + (3 * Math.PI) / 4;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									} else {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									}
								} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
									if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS && GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
										yaw = entity.getYRot() * 0.0174533 - Math.PI / 2;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
										yaw = entity.getYRot() * 0.0174533 - Math.PI / 4;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
										yaw = entity.getYRot() * 0.0174533 - (3 * Math.PI) / 4;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									} else {
										yaw = entity.getYRot() * 0.0174533 - Math.PI / 2;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									}
								} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
									if (!(GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS)) {
										yaw = entity.getYRot() * 0.0174533 + Math.PI;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									}
								} else if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
									if (!(GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS)) {
										yaw = entity.getYRot() * 0.0174533;
										pitch = pitch_new * 0.0174533;
										entity.setDeltaMovement(new Vec3((0.5 * Math.cos(yaw) * Math.cos(pitch)), 0, (0.5 * Math.sin(yaw) * Math.cos(pitch))));
									}
								}
								if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
									if (!entity.isShiftKeyDown()) {
										entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.5, (entity.getDeltaMovement().z())));
									}
								} else if (entity.isShiftKeyDown()) {
									if (!(GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS)) {
										entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (-0.5), (entity.getDeltaMovement().z())));
									}
								} else {
									entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0, (entity.getDeltaMovement().z())));
								}
							}
						}
					}
				}
			}
		}
	}
}
