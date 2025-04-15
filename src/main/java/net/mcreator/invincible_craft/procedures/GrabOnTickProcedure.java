package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class GrabOnTickProcedure {
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
		double pitch = 0;
		double yaw = 0;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
			if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == entity) && entityiterator instanceof LivingEntity
								&& ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding_entity).equals(entityiterator.getStringUUID())) {
							if (!entityiterator.isAlive()) {
								{
									boolean _setval = false;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.holding = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									String _setval = "";
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.holding_entity = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
							if (!(!(entityiterator == null))) {
								{
									boolean _setval = false;
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.holding = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									String _setval = "";
									entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.holding_entity = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 5, false, false));
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 5, false, false));
							if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying) {
								if (new Object() {
									public boolean getValue() {
										boolean retBool = Minecraft.getInstance().options.keyUp.isDown();
										if (retBool) {
											if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("SHIFT")) {
												retBool = Screen.hasShiftDown();
											} else if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("CONTROL")) {
												retBool = Screen.hasControlDown();
											} else if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("ALT")) {
												retBool = Screen.hasAltDown();
											}
										}
										return retBool;
									}
								}.getValue()) {
									if (new Object() {
										public boolean getValue() {
											boolean retBool = Minecraft.getInstance().options.keySprint.isDown();
											if (retBool) {
												if (Minecraft.getInstance().options.keySprint.getKeyModifier().toString().equals("SHIFT")) {
													retBool = Screen.hasShiftDown();
												} else if (Minecraft.getInstance().options.keySprint.getKeyModifier().toString().equals("CONTROL")) {
													retBool = Screen.hasControlDown();
												} else if (Minecraft.getInstance().options.keySprint.getKeyModifier().toString().equals("ALT")) {
													retBool = Screen.hasAltDown();
												}
											}
											return retBool;
										}
									}.getValue()) {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = entity.getXRot() * 0.0174533;
										{
											Entity _ent = entityiterator;
											_ent.teleportTo((entity.getX() + 12 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() - 0.4 + 12 * Math.sin((-1) * pitch)), (entity.getZ() + 12 * Math.sin(yaw) * Math.cos(pitch)));
											if (_ent instanceof ServerPlayer _serverPlayer)
												_serverPlayer.connection.teleport((entity.getX() + 12 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() - 0.4 + 12 * Math.sin((-1) * pitch)), (entity.getZ() + 12 * Math.sin(yaw) * Math.cos(pitch)),
														_ent.getYRot(), _ent.getXRot());
										}
									} else {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = entity.getXRot() * 0.0174533;
										{
											Entity _ent = entityiterator;
											_ent.teleportTo((entity.getX() + 6 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() - 0.4 + 6 * Math.sin((-1) * pitch)), (entity.getZ() + 6 * Math.sin(yaw) * Math.cos(pitch)));
											if (_ent instanceof ServerPlayer _serverPlayer)
												_serverPlayer.connection.teleport((entity.getX() + 6 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() - 0.4 + 6 * Math.sin((-1) * pitch)), (entity.getZ() + 6 * Math.sin(yaw) * Math.cos(pitch)),
														_ent.getYRot(), _ent.getXRot());
										}
									}
								} else {
									yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
									pitch = entity.getXRot() * 0.0174533;
									{
										Entity _ent = entityiterator;
										_ent.teleportTo((entity.getX() + 1 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.4), (entity.getZ() + 1 * Math.sin(yaw) * Math.cos(pitch)));
										if (_ent instanceof ServerPlayer _serverPlayer)
											_serverPlayer.connection.teleport((entity.getX() + 1 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.4), (entity.getZ() + 1 * Math.sin(yaw) * Math.cos(pitch)), _ent.getYRot(), _ent.getXRot());
									}
								}
							} else {
								if (new Object() {
									public boolean getValue() {
										boolean retBool = Minecraft.getInstance().options.keySprint.isDown();
										if (retBool) {
											if (Minecraft.getInstance().options.keySprint.getKeyModifier().toString().equals("SHIFT")) {
												retBool = Screen.hasShiftDown();
											} else if (Minecraft.getInstance().options.keySprint.getKeyModifier().toString().equals("CONTROL")) {
												retBool = Screen.hasControlDown();
											} else if (Minecraft.getInstance().options.keySprint.getKeyModifier().toString().equals("ALT")) {
												retBool = Screen.hasAltDown();
											}
										}
										return retBool;
									}
								}.getValue()) {
									if (new Object() {
										public boolean getValue() {
											boolean retBool = Minecraft.getInstance().options.keyUp.isDown();
											if (retBool) {
												if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("SHIFT")) {
													retBool = Screen.hasShiftDown();
												} else if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("CONTROL")) {
													retBool = Screen.hasControlDown();
												} else if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("ALT")) {
													retBool = Screen.hasAltDown();
												}
											}
											return retBool;
										}
									}.getValue()) {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = entity.getXRot() * 0.0174533;
										{
											Entity _ent = entityiterator;
											_ent.teleportTo((entity.getX() + 5 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.3 + 5 * Math.sin((-1) * pitch)), (entity.getZ() + 5 * Math.sin(yaw) * Math.cos(pitch)));
											if (_ent instanceof ServerPlayer _serverPlayer)
												_serverPlayer.connection.teleport((entity.getX() + 5 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.3 + 5 * Math.sin((-1) * pitch)), (entity.getZ() + 5 * Math.sin(yaw) * Math.cos(pitch)),
														_ent.getYRot(), _ent.getXRot());
										}
									}
								} else {
									if (new Object() {
										public boolean getValue() {
											boolean retBool = Minecraft.getInstance().options.keyUp.isDown();
											if (retBool) {
												if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("SHIFT")) {
													retBool = Screen.hasShiftDown();
												} else if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("CONTROL")) {
													retBool = Screen.hasControlDown();
												} else if (Minecraft.getInstance().options.keyUp.getKeyModifier().toString().equals("ALT")) {
													retBool = Screen.hasAltDown();
												}
											}
											return retBool;
										}
									}.getValue()) {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = entity.getXRot() * 0.0174533;
										{
											Entity _ent = entityiterator;
											_ent.teleportTo((entity.getX() + 4 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.3 + 4 * Math.sin((-1) * pitch)), (entity.getZ() + 4 * Math.sin(yaw) * Math.cos(pitch)));
											if (_ent instanceof ServerPlayer _serverPlayer)
												_serverPlayer.connection.teleport((entity.getX() + 4 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.3 + 4 * Math.sin((-1) * pitch)), (entity.getZ() + 4 * Math.sin(yaw) * Math.cos(pitch)),
														_ent.getYRot(), _ent.getXRot());
										}
									} else {
										yaw = entity.getYRot() * 0.0174533 + Math.PI / 2;
										pitch = entity.getXRot() * 0.0174533;
										{
											Entity _ent = entityiterator;
											_ent.teleportTo((entity.getX() + 1 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.4 + 1.2 * Math.sin((-1) * pitch)), (entity.getZ() + 1 * Math.sin(yaw) * Math.cos(pitch)));
											if (_ent instanceof ServerPlayer _serverPlayer)
												_serverPlayer.connection.teleport((entity.getX() + 1 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.4 + 1.2 * Math.sin((-1) * pitch)), (entity.getZ() + 1 * Math.sin(yaw) * Math.cos(pitch)),
														_ent.getYRot(), _ent.getXRot());
										}
									}
								}
							}
						}
						entityiterator.fallDistance = 0;
					}
				}
			}
		}
	}
}
