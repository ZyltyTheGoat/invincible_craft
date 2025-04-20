
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

import net.mcreator.invincible_craft.item.WoodenBatItem;
import net.mcreator.invincible_craft.item.ViltrumiteSuitItem;
import net.mcreator.invincible_craft.item.SuitFabricItem;
import net.mcreator.invincible_craft.item.PowerPickerItem;
import net.mcreator.invincible_craft.item.OmniManSuitItem;
import net.mcreator.invincible_craft.item.MetalBatItem;
import net.mcreator.invincible_craft.item.InvincibleSuitUnMaskedMarkItem;
import net.mcreator.invincible_craft.item.InvincibleSuitSinisterMarkItem;
import net.mcreator.invincible_craft.item.InvincibleSuitSeason3Item;
import net.mcreator.invincible_craft.item.InvincibleSuitOmniMarkItem;
import net.mcreator.invincible_craft.item.InvincibleSuitNoGogglesMarkItem;
import net.mcreator.invincible_craft.item.InvincibleSuitMohawkMarkItem;
import net.mcreator.invincible_craft.item.InvincibleSuitItem;
import net.mcreator.invincible_craft.item.DupliKateSuitItem;
import net.mcreator.invincible_craft.item.ChainItem;
import net.mcreator.invincible_craft.item.BattleBeastMaceItem;
import net.mcreator.invincible_craft.item.AtomEveSwordConstructItem;
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
	public static final RegistryObject<Item> VILTRUMITE_SUIT_CHESTPLATE = REGISTRY.register("viltrumite_suit_chestplate", () -> new ViltrumiteSuitItem.Chestplate());
	public static final RegistryObject<Item> VILTRUMITE_SUIT_LEGGINGS = REGISTRY.register("viltrumite_suit_leggings", () -> new ViltrumiteSuitItem.Leggings());
	public static final RegistryObject<Item> VILTRUMITE_SUIT_BOOTS = REGISTRY.register("viltrumite_suit_boots", () -> new ViltrumiteSuitItem.Boots());
	public static final RegistryObject<Item> MOON_STONE = block(InvincibleCraftModBlocks.MOON_STONE);
	public static final RegistryObject<Item> POWER_PICKER = REGISTRY.register("power_picker", () -> new PowerPickerItem());
	public static final RegistryObject<Item> VILTRUMITE_SPAWN_EGG = REGISTRY.register("viltrumite_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.VILTRUMITE, -769, -1448211, new Item.Properties()));
	public static final RegistryObject<Item> ART_ROSENBAUM_SPAWN_EGG = REGISTRY.register("art_rosenbaum_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.ART_ROSENBAUM, -77652, -6722757, new Item.Properties()));
	public static final RegistryObject<Item> SUIT_FABRIC = REGISTRY.register("suit_fabric", () -> new SuitFabricItem());
	public static final RegistryObject<Item> BANDIT_SPAWN_EGG = REGISTRY.register("bandit_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.BANDIT, -13421773, -1, new Item.Properties()));
	public static final RegistryObject<Item> ASTRONAUT_HELMET_HELMET = REGISTRY.register("astronaut_helmet_helmet", () -> new AstronautHelmetItem.Helmet());
	public static final RegistryObject<Item> DUPLI_KATE_SUIT_CHESTPLATE = REGISTRY.register("dupli_kate_suit_chestplate", () -> new DupliKateSuitItem.Chestplate());
	public static final RegistryObject<Item> DUPLI_KATE_SUIT_LEGGINGS = REGISTRY.register("dupli_kate_suit_leggings", () -> new DupliKateSuitItem.Leggings());
	public static final RegistryObject<Item> DUPLI_KATE_SUIT_BOOTS = REGISTRY.register("dupli_kate_suit_boots", () -> new DupliKateSuitItem.Boots());
	public static final RegistryObject<Item> THE_GIANT_SPAWN_EGG = REGISTRY.register("the_giant_spawn_egg", () -> new ForgeSpawnEggItem(InvincibleCraftModEntities.THE_GIANT, -4637696, -4665911, new Item.Properties()));
	public static final RegistryObject<Item> BATTLE_BEAST_MACE = REGISTRY.register("battle_beast_mace", () -> new BattleBeastMaceItem());
	public static final RegistryObject<Item> WOODEN_BAT = REGISTRY.register("wooden_bat", () -> new WoodenBatItem());
	public static final RegistryObject<Item> METAL_BAT = REGISTRY.register("metal_bat", () -> new MetalBatItem());
	public static final RegistryObject<Item> CHAIN = REGISTRY.register("chain", () -> new ChainItem());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_OMNI_MARK_HELMET = REGISTRY.register("invincible_suit_omni_mark_helmet", () -> new InvincibleSuitOmniMarkItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_OMNI_MARK_CHESTPLATE = REGISTRY.register("invincible_suit_omni_mark_chestplate", () -> new InvincibleSuitOmniMarkItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_OMNI_MARK_LEGGINGS = REGISTRY.register("invincible_suit_omni_mark_leggings", () -> new InvincibleSuitOmniMarkItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_OMNI_MARK_BOOTS = REGISTRY.register("invincible_suit_omni_mark_boots", () -> new InvincibleSuitOmniMarkItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SEASON_3_HELMET = REGISTRY.register("invincible_suit_season_3_helmet", () -> new InvincibleSuitSeason3Item.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SEASON_3_CHESTPLATE = REGISTRY.register("invincible_suit_season_3_chestplate", () -> new InvincibleSuitSeason3Item.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SEASON_3_LEGGINGS = REGISTRY.register("invincible_suit_season_3_leggings", () -> new InvincibleSuitSeason3Item.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SEASON_3_BOOTS = REGISTRY.register("invincible_suit_season_3_boots", () -> new InvincibleSuitSeason3Item.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_MOHAWK_MARK_CHESTPLATE = REGISTRY.register("invincible_suit_mohawk_mark_chestplate", () -> new InvincibleSuitMohawkMarkItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_MOHAWK_MARK_LEGGINGS = REGISTRY.register("invincible_suit_mohawk_mark_leggings", () -> new InvincibleSuitMohawkMarkItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_MOHAWK_MARK_BOOTS = REGISTRY.register("invincible_suit_mohawk_mark_boots", () -> new InvincibleSuitMohawkMarkItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_MARK_HELMET = REGISTRY.register("invincible_suit_sinister_mark_helmet", () -> new InvincibleSuitSinisterMarkItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_MARK_CHESTPLATE = REGISTRY.register("invincible_suit_sinister_mark_chestplate", () -> new InvincibleSuitSinisterMarkItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_MARK_LEGGINGS = REGISTRY.register("invincible_suit_sinister_mark_leggings", () -> new InvincibleSuitSinisterMarkItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_SINISTER_MARK_BOOTS = REGISTRY.register("invincible_suit_sinister_mark_boots", () -> new InvincibleSuitSinisterMarkItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_NO_GOGGLES_MARK_HELMET = REGISTRY.register("invincible_suit_no_goggles_mark_helmet", () -> new InvincibleSuitNoGogglesMarkItem.Helmet());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_NO_GOGGLES_MARK_CHESTPLATE = REGISTRY.register("invincible_suit_no_goggles_mark_chestplate", () -> new InvincibleSuitNoGogglesMarkItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_NO_GOGGLES_MARK_LEGGINGS = REGISTRY.register("invincible_suit_no_goggles_mark_leggings", () -> new InvincibleSuitNoGogglesMarkItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_NO_GOGGLES_MARK_BOOTS = REGISTRY.register("invincible_suit_no_goggles_mark_boots", () -> new InvincibleSuitNoGogglesMarkItem.Boots());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_UN_MASKED_MARK_CHESTPLATE = REGISTRY.register("invincible_suit_un_masked_mark_chestplate", () -> new InvincibleSuitUnMaskedMarkItem.Chestplate());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_UN_MASKED_MARK_LEGGINGS = REGISTRY.register("invincible_suit_un_masked_mark_leggings", () -> new InvincibleSuitUnMaskedMarkItem.Leggings());
	public static final RegistryObject<Item> INVINCIBLE_SUIT_UN_MASKED_MARK_BOOTS = REGISTRY.register("invincible_suit_un_masked_mark_boots", () -> new InvincibleSuitUnMaskedMarkItem.Boots());
	public static final RegistryObject<Item> ATOM_EVE_SWORD_CONSTRUCT = REGISTRY.register("atom_eve_sword_construct", () -> new AtomEveSwordConstructItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
