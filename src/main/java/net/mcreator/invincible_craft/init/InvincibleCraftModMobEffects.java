
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.invincible_craft.potion.TimedDestructionMobEffect;
import net.mcreator.invincible_craft.potion.TargetMobEffect;
import net.mcreator.invincible_craft.potion.StunMobEffect;
import net.mcreator.invincible_craft.potion.ScreenShakeMobEffect;
import net.mcreator.invincible_craft.potion.PunchLimitMobEffect;
import net.mcreator.invincible_craft.potion.LeapContinueMobEffect;
import net.mcreator.invincible_craft.potion.FlightSlownessMobEffect;
import net.mcreator.invincible_craft.potion.DenyMobEffect;
import net.mcreator.invincible_craft.potion.CanDownslamMobEffect;
import net.mcreator.invincible_craft.potion.BleedMobEffect;
import net.mcreator.invincible_craft.InvincibleCraftMod;

public class InvincibleCraftModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, InvincibleCraftMod.MODID);
	public static final RegistryObject<MobEffect> TARGET = REGISTRY.register("target", () -> new TargetMobEffect());
	public static final RegistryObject<MobEffect> SCREEN_SHAKE = REGISTRY.register("screen_shake", () -> new ScreenShakeMobEffect());
	public static final RegistryObject<MobEffect> PUNCH_LIMIT = REGISTRY.register("punch_limit", () -> new PunchLimitMobEffect());
	public static final RegistryObject<MobEffect> STUN = REGISTRY.register("stun", () -> new StunMobEffect());
	public static final RegistryObject<MobEffect> FLIGHT_SLOWNESS = REGISTRY.register("flight_slowness", () -> new FlightSlownessMobEffect());
	public static final RegistryObject<MobEffect> CAN_DOWNSLAM = REGISTRY.register("can_downslam", () -> new CanDownslamMobEffect());
	public static final RegistryObject<MobEffect> LEAP_CONTINUE = REGISTRY.register("leap_continue", () -> new LeapContinueMobEffect());
	public static final RegistryObject<MobEffect> BLEED = REGISTRY.register("bleed", () -> new BleedMobEffect());
	public static final RegistryObject<MobEffect> DENY = REGISTRY.register("deny", () -> new DenyMobEffect());
	public static final RegistryObject<MobEffect> TIMED_DESTRUCTION = REGISTRY.register("timed_destruction", () -> new TimedDestructionMobEffect());
}
