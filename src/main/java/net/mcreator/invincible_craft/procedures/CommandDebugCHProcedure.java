package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.ModList;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class CommandDebugCHProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (ModList.get().isLoaded("cosmos")) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(" \t\u00A7aCosmic Horizons mod is loaded!"), false);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cCosmic Horizons mod is NOT loaded!"), false);
		}
	}
}
