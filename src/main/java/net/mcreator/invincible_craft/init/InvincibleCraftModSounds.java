
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.InvincibleCraftMod;

public class InvincibleCraftModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, InvincibleCraftMod.MODID);
	public static final RegistryObject<SoundEvent> FOLLOWUP = REGISTRY.register("followup", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "followup")));
	public static final RegistryObject<SoundEvent> PUNCH = REGISTRY.register("punch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "punch")));
	public static final RegistryObject<SoundEvent> CRUSH = REGISTRY.register("crush", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "crush")));
	public static final RegistryObject<SoundEvent> BLOOD_SOUND_1 = REGISTRY.register("blood_sound_1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "blood_sound_1")));
	public static final RegistryObject<SoundEvent> BLOOD_SOUND_2 = REGISTRY.register("blood_sound_2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "blood_sound_2")));
	public static final RegistryObject<SoundEvent> BLOOD_SOUND_3 = REGISTRY.register("blood_sound_3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "blood_sound_3")));
	public static final RegistryObject<SoundEvent> SKULL_CRUSH = REGISTRY.register("skull_crush", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "skull_crush")));
	public static final RegistryObject<SoundEvent> VILTRUMITE_CHOP = REGISTRY.register("viltrumite_chop", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "viltrumite_chop")));
	public static final RegistryObject<SoundEvent> VILTRUMITE_PIERCE = REGISTRY.register("viltrumite_pierce", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "viltrumite_pierce")));
	public static final RegistryObject<SoundEvent> GROWL1 = REGISTRY.register("growl1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "growl1")));
	public static final RegistryObject<SoundEvent> GROWL2 = REGISTRY.register("growl2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "growl2")));
	public static final RegistryObject<SoundEvent> GROWL3 = REGISTRY.register("growl3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "growl3")));
	public static final RegistryObject<SoundEvent> BATTLE_BEAST_ROAR = REGISTRY.register("battle_beast_roar", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("invincible_craft", "battle_beast_roar")));
}
