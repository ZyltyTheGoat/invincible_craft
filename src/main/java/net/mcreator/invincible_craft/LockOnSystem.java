package net.mcreator.invincible_craft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

@Mod.EventBusSubscriber
public class LockOnSystem {
	private static final float ROTATION_SPEED = 10f; // Degrees per tick
	private static LivingEntity lastTarget = null;
	private static float partialTickAccumulator = 0;

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.END)
			return;

		Player player = event.player;
		partialTickAccumulator += 0.05f; // Simulate partial ticks

		if (player.level() instanceof ServerLevel serverLevel) {
			updateLockOn(player, serverLevel, partialTickAccumulator);
			if (partialTickAccumulator >= 1.0f) {
				partialTickAccumulator = 0;
			}
		}
	}

	public static void updateLockOn(Player player, ServerLevel level, float partialTicks) {
		String targetUUID = player.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new InvincibleCraftModVariables.PlayerVariables()).lock_on_target;
		if (targetUUID.isEmpty()) {
			lastTarget = null;
			return;
		}
		// Find target in the world
		LivingEntity target = (LivingEntity) level.getEntity(java.util.UUID.fromString(targetUUID));
		if (target == null || !target.isAlive()) {
			lastTarget = null;
			return;
		}
		smoothLookAt(player, target, partialTicks);
		lastTarget = target;
	}

	private static void smoothLookAt(Player player, LivingEntity target, float partialTicks) {
		Vec3 targetPos = target.getEyePosition();
		Vec3 eyePos = player.getEyePosition();
		Vec3 direction = targetPos.subtract(eyePos).normalize();
		// Calculate target angles
		double horizontalDist = Math.sqrt(direction.x * direction.x + direction.z * direction.z);
		float targetYaw = (float) Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90f;
		float targetPitch = (float) -Math.toDegrees(Math.atan2(direction.y, horizontalDist));
		// Get current angles
		float currentYaw = player.getYRot();
		float currentPitch = player.getXRot();
		// Normalize angles and find shortest path
		targetYaw = currentYaw + ((targetYaw - currentYaw + 180) % 360) - 180;

		// Calculate rotation step
		float rotationStep = ROTATION_SPEED * partialTicks;

		// Apply interpolated rotation
		float newYaw = currentYaw + Math.signum(targetYaw - currentYaw) * Math.min(rotationStep, Math.abs(targetYaw - currentYaw));
		float newPitch = currentPitch + Math.signum(targetPitch - currentPitch) * Math.min(rotationStep, Math.abs(targetPitch - currentPitch));
		player.setYRot(newYaw);
		player.setXRot(newPitch);

		// Update all rotation fields
		player.yHeadRot = newYaw;
		player.yHeadRotO = newYaw;
		player.yRotO = newYaw;
		player.xRotO = newPitch;
	}
}
