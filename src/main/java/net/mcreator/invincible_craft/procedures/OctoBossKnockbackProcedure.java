package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.eventbus.api.Event;

@Mod.EventBusSubscriber
public class OctoBossKnockbackProcedure {
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
		if (sourceentity instanceof OctobossEntity && damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:mob_attack")))) {
			if (sourceentity.getPersistentData().getBoolean("combo")) {
				entity.setDeltaMovement(
						new Vec3(((entity.getX() - sourceentity.getX()) * (9 / Math.sqrt(Math.pow(entity.getX() - sourceentity.getX(), 2) + Math.pow(entity.getY() - sourceentity.getY(), 2) + Math.pow(entity.getZ() - sourceentity.getZ(), 2)))), 0.5,
								((entity.getZ() - sourceentity.getZ()) * (9 / Math.sqrt(Math.pow(entity.getX() - sourceentity.getX(), 2) + Math.pow(entity.getY() - sourceentity.getY(), 2) + Math.pow(entity.getZ() - sourceentity.getZ(), 2))))));
			} else {
				entity.setDeltaMovement(new Vec3(0, 0, 0));
			}
			sourceentity.getPersistentData().putBoolean("combo", false);
		}
	}
}
