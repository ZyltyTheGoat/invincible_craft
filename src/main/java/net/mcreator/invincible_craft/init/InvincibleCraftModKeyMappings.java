
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.invincible_craft.network.StatPanelMessage;
import net.mcreator.invincible_craft.network.LeavePlanetMessage;
import net.mcreator.invincible_craft.network.FlyMessage;
import net.mcreator.invincible_craft.network.AbilityButton5Message;
import net.mcreator.invincible_craft.network.AbilityButton4Message;
import net.mcreator.invincible_craft.network.AbilityButton3Message;
import net.mcreator.invincible_craft.network.AbilityButton2Message;
import net.mcreator.invincible_craft.network.AbilityButton1Message;
import net.mcreator.invincible_craft.InvincibleCraftMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class InvincibleCraftModKeyMappings {
	public static final KeyMapping ABILITY_BUTTON_1 = new KeyMapping("key.invincible_craft.ability_button_1", GLFW.GLFW_KEY_Y, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton1Message(0, 0));
				AbilityButton1Message.pressAction(Minecraft.getInstance().player, 0, 0);
				ABILITY_BUTTON_1_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - ABILITY_BUTTON_1_LASTPRESS);
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton1Message(1, dt));
				AbilityButton1Message.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping ABILITY_BUTTON_2 = new KeyMapping("key.invincible_craft.ability_button_2", GLFW.GLFW_KEY_X, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton2Message(0, 0));
				AbilityButton2Message.pressAction(Minecraft.getInstance().player, 0, 0);
				ABILITY_BUTTON_2_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - ABILITY_BUTTON_2_LASTPRESS);
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton2Message(1, dt));
				AbilityButton2Message.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping ABILITY_BUTTON_3 = new KeyMapping("key.invincible_craft.ability_button_3", GLFW.GLFW_KEY_C, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton3Message(0, 0));
				AbilityButton3Message.pressAction(Minecraft.getInstance().player, 0, 0);
				ABILITY_BUTTON_3_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - ABILITY_BUTTON_3_LASTPRESS);
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton3Message(1, dt));
				AbilityButton3Message.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping ABILITY_BUTTON_4 = new KeyMapping("key.invincible_craft.ability_button_4", GLFW.GLFW_KEY_V, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton4Message(0, 0));
				AbilityButton4Message.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping FLY = new KeyMapping("key.invincible_craft.fly", GLFW.GLFW_KEY_SPACE, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new FlyMessage(0, 0));
				FlyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping ABILITY_BUTTON_5 = new KeyMapping("key.invincible_craft.ability_button_5", GLFW.GLFW_KEY_B, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new AbilityButton5Message(0, 0));
				AbilityButton5Message.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping STAT_PANEL = new KeyMapping("key.invincible_craft.stat_panel", GLFW.GLFW_KEY_N, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new StatPanelMessage(0, 0));
				StatPanelMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping LEAVE_PLANET = new KeyMapping("key.invincible_craft.leave_planet", GLFW.GLFW_KEY_K, "key.categories.invincible_craft") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				InvincibleCraftMod.PACKET_HANDLER.sendToServer(new LeavePlanetMessage(0, 0));
				LeavePlanetMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	private static long ABILITY_BUTTON_1_LASTPRESS = 0;
	private static long ABILITY_BUTTON_2_LASTPRESS = 0;
	private static long ABILITY_BUTTON_3_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(ABILITY_BUTTON_1);
		event.register(ABILITY_BUTTON_2);
		event.register(ABILITY_BUTTON_3);
		event.register(ABILITY_BUTTON_4);
		event.register(FLY);
		event.register(ABILITY_BUTTON_5);
		event.register(STAT_PANEL);
		event.register(LEAVE_PLANET);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				ABILITY_BUTTON_1.consumeClick();
				ABILITY_BUTTON_2.consumeClick();
				ABILITY_BUTTON_3.consumeClick();
				ABILITY_BUTTON_4.consumeClick();
				FLY.consumeClick();
				ABILITY_BUTTON_5.consumeClick();
				STAT_PANEL.consumeClick();
				LEAVE_PLANET.consumeClick();
			}
		}
	}
}
