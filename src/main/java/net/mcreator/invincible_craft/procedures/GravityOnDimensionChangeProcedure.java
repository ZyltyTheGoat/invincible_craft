package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GravityOnDimensionChangeProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double gravity = 0;
		if ((entity.level().dimension()) == Level.OVERWORLD) {
			gravity = 0.08;
		}
		if ((entity.level().dimension()) == Level.NETHER) {
			gravity = 0.08;
		}
		if ((entity.level().dimension()) == Level.END) {
			gravity = 0.08;
		}
		if ((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, new ResourceLocation("invincible_craft:moon"))) {
			gravity = 0.0132;
		}
		if (entity instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttributes().hasAttribute(ForgeMod.ENTITY_GRAVITY.get()))
			_livingEntity12.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).setBaseValue(gravity);
	}
}
