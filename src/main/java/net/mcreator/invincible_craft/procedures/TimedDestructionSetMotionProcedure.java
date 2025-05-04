package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TimedDestructionSetMotionProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get())) {
			entity.getPersistentData().putDouble("destructionX", (sourceentity.getLookAngle().x));
			entity.getPersistentData().putDouble("destructionY", (sourceentity.getLookAngle().y));
			entity.getPersistentData().putDouble("destructionZ", (sourceentity.getLookAngle().z));
		}
	}
}
