
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.invincible_craft.InvincibleCraftMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvincibleCraftModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InvincibleCraftMod.MODID);
	public static final RegistryObject<CreativeModeTab> INVINCIBLE_CRAFT_ARMOR = REGISTRY.register("invincible_craft_armor", () -> CreativeModeTab.builder().title(Component.translatable("item_group.invincible_craft.invincible_craft_armor"))
			.icon(() -> new ItemStack(InvincibleCraftModItems.INVINCIBLE_SUIT_CHESTPLATE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(InvincibleCraftModItems.OMNI_MAN_SUIT_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.OMNI_MAN_SUIT_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.OMNI_MAN_SUIT_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.VILTRUMITE_SUIT_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.VILTRUMITE_SUIT_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.VILTRUMITE_SUIT_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_HELMET.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_SHOW_HELMET.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_SHOW_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_SHOW_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_SHOW_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_COMIC_HELMET.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_COMIC_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_COMIC_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_BLUE_COMIC_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_SINISTER_HELMET.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_SINISTER_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_SINISTER_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_SINISTER_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_MOHAWK_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_MOHAWK_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.INVINCIBLE_SUIT_MOHAWK_BOOTS.get());
				tabData.accept(InvincibleCraftModItems.DUPLI_KATE_SUIT_CHESTPLATE.get());
				tabData.accept(InvincibleCraftModItems.DUPLI_KATE_SUIT_LEGGINGS.get());
				tabData.accept(InvincibleCraftModItems.DUPLI_KATE_SUIT_BOOTS.get());
			}).withSearchBar().build());
	public static final RegistryObject<CreativeModeTab> INVINCIBLE_CRAFT_CREATIVE_TAB = REGISTRY.register("invincible_craft_creative_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.invincible_craft.invincible_craft_creative_tab")).icon(() -> new ItemStack(InvincibleCraftModItems.POWER_PICKER.get())).displayItems((parameters, tabData) -> {
				tabData.accept(InvincibleCraftModItems.POWER_PICKER.get());
				tabData.accept(InvincibleCraftModItems.SUIT_FABRIC.get());
				tabData.accept(InvincibleCraftModItems.ART_ROSENBAUM_SPAWN_EGG.get());
				tabData.accept(InvincibleCraftModItems.BANDIT_SPAWN_EGG.get());
				tabData.accept(InvincibleCraftModItems.VILTRUMITE_SPAWN_EGG.get());
				tabData.accept(InvincibleCraftModItems.THE_GIANT_SPAWN_EGG.get());
				tabData.accept(InvincibleCraftModItems.ASTRONAUT_HELMET_HELMET.get());
				tabData.accept(InvincibleCraftModItems.BATTLE_BEAST_MACE.get());
				tabData.accept(InvincibleCraftModItems.WOODEN_BAT.get());
				tabData.accept(InvincibleCraftModItems.METAL_BAT.get());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {

			tabData.accept(InvincibleCraftModItems.BATTLE_BEAST_MACE.get());
			tabData.accept(InvincibleCraftModItems.WOODEN_BAT.get());
			tabData.accept(InvincibleCraftModItems.METAL_BAT.get());

		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {

			tabData.accept(InvincibleCraftModItems.VILTRUMITE_SPAWN_EGG.get());
			tabData.accept(InvincibleCraftModItems.ART_ROSENBAUM_SPAWN_EGG.get());
			tabData.accept(InvincibleCraftModItems.BANDIT_SPAWN_EGG.get());
			tabData.accept(InvincibleCraftModItems.THE_GIANT_SPAWN_EGG.get());

		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {

			tabData.accept(InvincibleCraftModBlocks.MOON_STONE.get().asItem());

		}
	}
}
