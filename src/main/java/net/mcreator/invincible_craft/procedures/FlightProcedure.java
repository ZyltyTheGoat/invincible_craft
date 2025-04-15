package net.mcreator.invincible_craft.procedures;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.WaveEffect;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

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
			if (!Minecraft.getInstance().isPaused()) {
				if (entity.onGround()) {
					if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).boost_timer > 0) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, (float) 1.6);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, (float) 1.6, false);
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 7, 0, false, false));
						BlockPos center = new BlockPos((int) x, (int) y - 1, (int) z);
						WaveEffect.createShockwave((Level) world, center, 8, 0);
						{
							final Vec3 _center = new Vec3((entity.getX()), (entity.getY()), (entity.getZ()));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (!(entity == entityiterator) && !(entityiterator instanceof ExperienceOrb) && !(entityiterator instanceof ItemEntity)) {
									entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), entity),
											(float) ((entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE)
													? _livingEntity10.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue()
													: 0) * 3));
								}
							}
						}
					}
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
					if (entity instanceof LivingEntity _livEnt17 && _livEnt17.hasEffect(InvincibleCraftModMobEffects.FLIGHT_SLOWNESS.get())) {
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
					if (!(entity instanceof LivingEntity _livEnt18 && _livEnt18.hasEffect(InvincibleCraftModMobEffects.STUN.get()))) {
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
							if (!(entity instanceof LivingEntity _livEnt25 && _livEnt25.isFallFlying())) {
								if (entity instanceof Player _plr && !(_plr.isFallFlying())) {
									_plr.startFallFlying();
								}
							}
							entity.setNoGravity(true);
							entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).current_speed),
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
										// Check if the player is looking down at 30 degrees or more
										if (lookDirection.y <= -0.5) {
											return; // If the player is looking down steeply, don't break blocks
										}
										BlockPos centerPos = BlockPos.containing(entity.getX() + lookDirection.x * 1, entity.getY() + lookDirection.y * 1, entity.getZ() + lookDirection.z * 1);
										int radius = 4;
										double radiusSquared = radius * radius;
										for (int i = -radius; i <= radius; i++) {
											for (int j = -radius; j <= radius; j++) {
												for (int k = -radius; k <= radius; k++) {
													BlockPos targetPos = centerPos.offset(i, j, k);
													double distanceSquared = i * i + j * j + k * k;
													if (distanceSquared > radiusSquared) {
														continue;
													}
													BlockState blockState = entity.level().getBlockState(targetPos);
													if (blockState.isAir() || blockState.getDestroySpeed(entity.level(), targetPos) < 0) {
														continue;
													}
													// Play break sound & particles
													entity.level().levelEvent(500, targetPos, Block.getId(blockState));
													// Ensure the block is set to air and updated properly
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
