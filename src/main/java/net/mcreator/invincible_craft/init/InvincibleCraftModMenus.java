
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.invincible_craft.world.inventory.TestMenu;
import net.mcreator.invincible_craft.world.inventory.StatPanelGUIMenu;
import net.mcreator.invincible_craft.world.inventory.QuickTravelGUIMenu;
import net.mcreator.invincible_craft.world.inventory.PowerPickerGUIMenu;
import net.mcreator.invincible_craft.world.inventory.MoveToSpaceMenu;
import net.mcreator.invincible_craft.world.inventory.ArtRosenbaumGUIMenu;
import net.mcreator.invincible_craft.InvincibleCraftMod;

public class InvincibleCraftModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, InvincibleCraftMod.MODID);
	public static final RegistryObject<MenuType<QuickTravelGUIMenu>> QUICK_TRAVEL_GUI = REGISTRY.register("quick_travel_gui", () -> IForgeMenuType.create(QuickTravelGUIMenu::new));
	public static final RegistryObject<MenuType<PowerPickerGUIMenu>> POWER_PICKER_GUI = REGISTRY.register("power_picker_gui", () -> IForgeMenuType.create(PowerPickerGUIMenu::new));
	public static final RegistryObject<MenuType<ArtRosenbaumGUIMenu>> ART_ROSENBAUM_GUI = REGISTRY.register("art_rosenbaum_gui", () -> IForgeMenuType.create(ArtRosenbaumGUIMenu::new));
	public static final RegistryObject<MenuType<StatPanelGUIMenu>> STAT_PANEL_GUI = REGISTRY.register("stat_panel_gui", () -> IForgeMenuType.create(StatPanelGUIMenu::new));
	public static final RegistryObject<MenuType<TestMenu>> TEST = REGISTRY.register("test", () -> IForgeMenuType.create(TestMenu::new));
	public static final RegistryObject<MenuType<MoveToSpaceMenu>> MOVE_TO_SPACE = REGISTRY.register("move_to_space", () -> IForgeMenuType.create(MoveToSpaceMenu::new));
}
