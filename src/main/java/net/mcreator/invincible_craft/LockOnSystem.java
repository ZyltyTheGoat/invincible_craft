package net.mcreator.invincible_craft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

@Mod.EventBusSubscriber
public class LockOnSystem {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Player player = event.player;
			System.out.println("DEBUG: LockOnSystem tick for player " + player.getName().getString());

			if (player.level() instanceof ServerLevel serverLevel) {
				String targetUUID = player.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new InvincibleCraftModVariables.PlayerVariables()).lock_on_target;

				System.out.println("DEBUG: Target UUID: " + targetUUID);

				if (!targetUUID.isEmpty()) {
					Entity target = serverLevel.getEntity(java.util.UUID.fromString(targetUUID));

					if (target instanceof LivingEntity livingTarget) {
						System.out.println("DEBUG: Found target: " + livingTarget.getName().getString());
						forceLookAt(player, livingTarget);
					} else {
						System.out.println("DEBUG: Target not found or not living");
					}
				}
			}
		}
	}

	private static void forceLookAt(Player player, LivingEntity target) {
		// Directly set rotation to look at target
		player.lookAt(EntityAnchorArgument.Anchor.EYES, target.getEyePosition());

		// Force all rotation updates
		player.yHeadRot = player.getYRot();
		player.yHeadRotO = player.getYRot();
		player.yRotO = player.getYRot();
		player.xRotO = player.getXRot();

		System.out.println("DEBUG: New rotation - Yaw: " + player.getYRot() + " Pitch: " + player.getXRot());
	}
}
