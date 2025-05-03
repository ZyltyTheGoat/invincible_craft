package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

public class AtomicBubbleDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_bubble
				&& Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON) {
			return true;
		}
		return false;
	}
}
