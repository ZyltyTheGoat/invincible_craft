
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.invincible_craft.item.ViltrumiteSuitItem;
import net.mcreator.invincible_craft.item.SuitFabricItem;
import net.mcreator.invincible_craft.item.PowerPickerItem;
import net.mcreator.invincible_craft.item.OmniManSuitItem;
import net.mcreator.invincible_craft.item.InvincibleSuitSinisterItem;
import net.mcreator.invincible_craft.item.InvincibleSuitMohawkItem;
import net.mcreator.invincible_craft.item.InvincibleSuitItem;
import net.mcreator.invincible_craft.item.InvincibleSuitBlueShowItem;
import net.mcreator.invincible_craft.item.InvincibleSuitBlueComicItem;
import net.mcreator.invincible_craft.item.DupliKateSuitItem;
import net.mcreator.invincible_craft.item.BattleBeastMaceItem;
import net.mcreator.invincible_craft.item.AstronautHelmetItem;
import net.mcreator.invincible_craft.InvincibleCraftMod;

public class InvincibleCraftModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, InvincibleCraftMod.MODID);
	public static final RegistryObject<Item> OMNI_MAN_SUIT_CHESTPLATE = REGISTRY.register("omni_man_suit_chestplate", () -> new OmniManSuitItem.Chestplate());
	public static final RegistryObject<Item> OMNI_MAN_SUIT_LEGGINGS = REGISTRY.register("omni_man_suit_leggings", () -> new OmniManSuitItem.Leggings());
	public static final RegistryObject<Item> OMNI_MAN_SUIT_BOOTS = REGISTRY.register("omni_man_suit_boots", () -> new OmniManSuitItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_HELMET = REGISTRY.register("invincible_suit_helmet", () -> new InvincibleSuitItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_CHESTPLATE = REGISTRY.register("invincible_suit_chestplate", () -> new InvincibleSuitItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_LEGGINGS = REGISTRY.register("invincible_suit_leggings", () -> new InvincibleSuitItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BOOTS = REGISTRY.register("invincible_suit_boots", () -> new InvincibleSuitItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_SHOW_HELMET = REGISTRY.register("invincible_suit_blue_show_helmet", () -> new InvincibleSuitBlueShowItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_SHOW_CHESTPLATE = REGISTRY.register("invincible_suit_blue_show_chestplate", () -> new InvincibleSuitBlueShowItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_SHOW_LEGGINGS = REGISTRY.register("invincible_suit_blue_show_leggings", () -> new InvincibleSuitBlueShowItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_SHOW_BOOTS = REGISTRY.register("invincible_suit_blue_show_boots", () -> new InvincibleSuitBlueShowItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_COMIC_HELMET = REGISTRY.register("invincible_suit_blue_comic_helmet", () -> new InvincibleSuitBlueComicItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_COMIC_CHESTPLATE = REGISTRY.register("invincible_suit_blue_comic_chestplate", () -> new InvincibleSuitBlueComicItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_COMIC_LEGGINGS = REGISTRY.register("invincible_suit_blue_comic_leggings", () -> new InvincibleSuitBlueComicItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_BLUE_COMIC_BOOTS = REGISTRY.register("invincible_suit_blue_comic_boots", () -> new InvincibleSuitBlueComicItem.Boots());
	public static final RegistryObject<Item> VILTRUMITE_SUIT_CHESTPLATE = REGISTRY.register("viltrumite_suit_chestplate", () -> new ViltrumiteSuitItem.Chestplate());
	public static final RegistryObject<Item> VILTRUMITE_SUIT_LEGGINGS = REGISTRY.register("viltrumite_suit_leggings", () -> new ViltrumiteSuitItem.Leggings());
	public static final RegistryObject<Item> VILTRUMITE_SUIT_BOOTS = REGISTRY.register("viltrumite_suit_boots", () -> new ViltrumiteSuitItem.Boots());
	public static final RegistryObject<Item> MOON_STONE = block(InvincibleCraftModBlocks.MOON_STONE);
	public static final RegistryObject<Item> POWER_PICKER = REGISTRY.register("power_picker", () -> new PowerPickerItem());
	public static final RegistryObject<Item> VILTRUMITE_SPAWN_EGG = REGISTRY.register("viltrumite_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.VILTRUMITE, -769, -1448211, new Item.Properties()));
	public static final RegistryObject<Item> ART_ROSENBAUM_SPAWN_EGG = REGISTRY.register("art_rosenbaum_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.ART_ROSENBAUM, -77652, -6722757, new Item.Properties()));
	public static final RegistryObject<Item> SUIT_FABRIC = REGISTRY.register("suit_fabric", () -> new SuitFabricItem());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_HELMET = REGISTRY.register("invincible_suit_sinister_helmet", () -> new InvincibleSuitSinisterItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_CHESTPLATE = REGISTRY.register("invincible_suit_sinister_chestplate", () -> new InvincibleSuitSinisterItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_LEGGINGS = REGISTRY.register("invincible_suit_sinister_leggings", () -> new InvincibleSuitSinisterItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_BOOTS = REGISTRY.register("invincible_suit_sinister_boots", () -> new InvincibleSuitSinisterItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_MOHAWK_CHESTPLATE = REGISTRY.register("invincible_suit_mohawk_chestplate", () -> new InvincibleSuitMohawkItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_MOHAWK_LEGGINGS = REGISTRY.register("invincible_suit_mohawk_leggings", () -> new InvincibleSuitMohawkItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_MOHAWK_BOOTS = REGISTRY.register("invincible_suit_mohawk_boots", () -> new InvincibleSuitMohawkItem.Boots());
	public static final RegistryObject<Item> BANDIT_SPAWN_EGG = REGISTRY.register("bandit_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.BANDIT, -13421773, -1, new Item.Properties()));
	public static final RegistryObject<Item> ASTRONAUT_HELMET_HELMET = REGISTRY.register("astronaut_helmet_helmet", () -> new AstronautHelmetItem.Helmet());
	public static final RegistryObject<Item> DUPLI_KATE_SUIT_CHESTPLATE = REGISTRY.register("dupli_kate_suit_chestplate", () -> new DupliKateSuitItem.Chestplate());
	public static final RegistryObject<Item> DUPLI_KATE_SUIT_LEGGINGS = REGISTRY.register("dupli_kate_suit_leggings", () -> new DupliKateSuitItem.Leggings());
	public static final RegistryObject<Item> DUPLI_KATE_SUIT_BOOTS = REGISTRY.register("dupli_kate_suit_boots", () -> new DupliKateSuitItem.Boots());
	public static final RegistryObject<Item> THE_GIANT_SPAWN_EGG = REGISTRY.register("the_giant_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.THE_GIANT, -4637696, -4665911, new Item.Properties()));
	public static final RegistryObject<Item> BATTLE_BEAST_MACE = REGISTRY.register("battle_beast_mace", () -> new BattleBeastMaceItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
