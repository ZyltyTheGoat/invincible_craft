
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.invincible_craft.potion.TargetMobEffect;
import net.mcreator.invincible_craft.potion.StunMobEffect;
import net.mcreator.invincible_craft.potion.ScreenShakeMobEffect;
import net.mcreator.invincible_craft.potion.PunchLimitMobEffect;
import net.mcreator.invincible_craft.potion.FlightSlownessMobEffect;
import net.mcreator.invincible_craft.InvincibleCraftMod;

public class InvincibleCraftModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, InvincibleCraftMod.MODID);
	public static final RegistryObject<MobEffect> TARGET = REGISTRY.register("target", () -> new TargetMobEffect());
	public static final RegistryObject<MobEffect> SCREEN_SHAKE = REGISTRY.register("screen_shake", () -> new ScreenShakeMobEffect());
	public static final RegistryObject<MobEffect> PUNCH_LIMIT = REGISTRY.register("punch_limit", () -> new PunchLimitMobEffect());
	public static final RegistryObject<MobEffect> STUN = REGISTRY.register("stun", () -> new StunMobEffect());
	public static final RegistryObject<MobEffect> FLIGHT_SLOWNESS = REGISTRY.register("flight_slowness", () -> new FlightSlownessMobEffect());
}
