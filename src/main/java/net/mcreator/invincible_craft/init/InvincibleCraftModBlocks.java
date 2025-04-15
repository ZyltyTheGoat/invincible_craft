
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.invincible_craft.block.MoonStoneBlock;
import net.mcreator.invincible_craft.InvincibleCraftMod;

public class InvincibleCraftModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, InvincibleCraftMod.MODID);
	public static final RegistryObject<Block> MOON_STONE = REGISTRY.register("moon_stone", () -> new MoonStoneBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
