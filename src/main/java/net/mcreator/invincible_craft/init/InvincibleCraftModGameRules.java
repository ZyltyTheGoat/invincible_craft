
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvincibleCraftModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> RANDOM_POWER_ON_SPAWN = GameRules.register("randomPowerOnSpawn", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.IntegerValue> INVINCIBLE_CRAFT_CLONE_LIMIT = GameRules.register("invincibleCraftCloneLimit", GameRules.Category.PLAYER, GameRules.IntegerValue.create(10));
	public static final GameRules.Key<GameRules.BooleanValue> INVINCIBLE_CRAFT_ENABLE_IMPACT_FRAMES = GameRules.register("invincibleCraftEnableImpactFrames", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
	public static final GameRules.Key<GameRules.BooleanValue> INVINCIBLE_CRAFT_ENABLE_MOON_TRAVEL = GameRules.register("invincibleCraftEnableMoonTravel", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
	public static final GameRules.Key<GameRules.IntegerValue> INVINCIBLE_CRAFT_XP_MULTIPLIER = GameRules.register("invincibleCraftXPMultiplier", GameRules.Category.PLAYER, GameRules.IntegerValue.create(10));
}
