package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

public class ReturnSPProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Stat Points: " + new java.text.DecimalFormat("##").format((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_points);
	}
}
