package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.init.InvincibleCraftModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BattleBeastMaceCriticalProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getTarget(), event.getEntity(), event.isVanillaCritical());
	}

	public static void execute(Entity entity, Entity sourceentity, boolean isvanillacritical) {
		execute(null, entity, sourceentity, isvanillacritical);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity, boolean isvanillacritical) {
		if (entity == null || sourceentity == null)
			return;
		double knockbackres = 0;
		if (isvanillacritical) {
			if ((sourceentity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_strength > 20) {
				knockbackres = Math.min(1,
						Math.max(entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE) ? _livingEntity0.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() : 0, 0));
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == InvincibleCraftModItems.BATTLE_BEAST_MACE.get()) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.TIMED_DESTRUCTION.get(), 5, 2, false, false));
					entity.setDeltaMovement(new Vec3((0.5 * sourceentity.getLookAngle().x * (1 - knockbackres / 1)), (-2), (0.5 * sourceentity.getLookAngle().z * (1 - knockbackres / 1))));
				}
			}
		}
	}
}
