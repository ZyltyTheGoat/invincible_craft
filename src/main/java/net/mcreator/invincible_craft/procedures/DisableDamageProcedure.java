package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.RefugePortalEntity;
import net.mcreator.invincible_craft.entity.QuickPortalEntity;
import net.mcreator.invincible_craft.entity.PortalDashPortalEntity;
import net.mcreator.invincible_craft.entity.OrbsSpawnerPortalEntity;
import net.mcreator.invincible_craft.entity.BanishmentPortalEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DisableDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof QuickPortalEntity || entity instanceof BanishmentPortalEntity || entity instanceof OrbsSpawnerPortalEntity || entity instanceof RefugePortalEntity || entity instanceof PortalDashPortalEntity) {
			if (event != null && event.isCancelable()) {
				event.setCanceled(true);
			}
		}
	}
}
