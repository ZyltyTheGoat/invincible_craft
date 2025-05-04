package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.invincible_craft.entity.OctobossEntity;
import net.mcreator.invincible_craft.entity.LucanEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CancelRandomDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(DamageSource damagesource, Entity entity, Entity sourceentity) {
		execute(null, damagesource, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, DamageSource damagesource, Entity entity, Entity sourceentity) {
		if (damagesource == null || entity == null || sourceentity == null)
			return;
		double amplifier = 0;
		if (sourceentity instanceof LucanEntity) {
			if (damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:mob_attack")))) {
				entity.setDeltaMovement(new Vec3((sourceentity.getLookAngle().x * 2), Math.abs(sourceentity.getLookAngle().y), (sourceentity.getLookAngle().z * 2)));
				entity.getPersistentData().putDouble("destructionX", (sourceentity.getLookAngle().x));
				entity.getPersistentData().putDouble("destructionY", (sourceentity.getLookAngle().y));
				entity.getPersistentData().putDouble("destructionZ", (sourceentity.getLookAngle().z));
			} else if (!damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:mob_attack"))) && !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage")))
					&& !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch")))) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
		if (!damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:mob_attack"))) && !damagesource.is(DamageTypes.ARROW)) {
			if (sourceentity instanceof OctobossEntity) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
}
