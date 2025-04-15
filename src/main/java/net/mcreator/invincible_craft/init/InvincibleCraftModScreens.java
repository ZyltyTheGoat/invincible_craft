
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.invincible_craft.client.gui.TestScreen;
import net.mcreator.invincible_craft.client.gui.StatPanelGUIScreen;
import net.mcreator.invincible_craft.client.gui.QuickTravelGUIScreen;
import net.mcreator.invincible_craft.client.gui.PowerPickerGUIScreen;
import net.mcreator.invincible_craft.client.gui.MoveToSpaceScreen;
import net.mcreator.invincible_craft.client.gui.ArtRosenbaumGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvincibleCraftModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(InvincibleCraftModMenus.QUICK_TRAVEL_GUI.get(), QuickTravelGUIScreen::new);
			MenuScreens.register(InvincibleCraftModMenus.POWER_PICKER_GUI.get(), PowerPickerGUIScreen::new);
			MenuScreens.register(InvincibleCraftModMenus.ART_ROSENBAUM_GUI.get(), ArtRosenbaumGUIScreen::new);
			MenuScreens.register(InvincibleCraftModMenus.STAT_PANEL_GUI.get(), StatPanelGUIScreen::new);
			MenuScreens.register(InvincibleCraftModMenus.TEST.get(), TestScreen::new);
			MenuScreens.register(InvincibleCraftModMenus.MOVE_TO_SPACE.get(), MoveToSpaceScreen::new);
		});
	}
}
