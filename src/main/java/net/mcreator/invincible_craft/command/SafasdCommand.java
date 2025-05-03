
package net.mcreator.invincible_craft.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;

import net.minecraft.commands.Commands;

@Mod.EventBusSubscriber
public class SafasdCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("safasd")

				.then(Commands.literal("SetSelf").then(Commands.literal("Decepticons").then(Commands.literal("Megatron"))).then(Commands.literal("Autobots").then(Commands.literal("Bumblee")))));
	}
}
