package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import javax.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class EventHandlerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event);
		}
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		String name = "";
		List<Object> player = new ArrayList<>();
		double CordZ = 0;
		double CordX = 0;
		double randomNum = 0;
		double CordY = 0;
		double random_event = 0;
	}
}
