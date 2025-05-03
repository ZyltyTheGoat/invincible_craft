package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class CancelRandomDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource(), event.getSource().getEntity());
		}
	}

	public static void execute(DamageSource damagesource, Entity sourceentity) {
		execute(null, damagesource, sourceentity);
	}

	private static void execute(@Nullable Event event, DamageSource damagesource, Entity sourceentity) {
		if (damagesource == null || sourceentity == null)
			return;
		if (!damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:deleted_mod_element"))) && !damagesource.is(DamageTypes.ARROW)) {
			if (sourceentity instanceof OctobossEntity) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
}
