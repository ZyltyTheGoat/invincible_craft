package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

public class AbilityButton1OnKeyReleasedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
			entity.getPersistentData().putBoolean("punching", false);
			if (entity.getPersistentData().getDouble("punch_followup_timer") >= 10 && entity.getPersistentData().getDouble("punch_followup_timer") < 30) {
				if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).punch_limit < 3) {
					ViltrumitePunchFollowUpProcedure.execute(world, x, y, z, entity);
					{
						double _setval = 0;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.ability_cooldown_1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).punch_limit + 1;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.punch_limit = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.PUNCH_LIMIT.get(), 35, 0, false, false));
				} else {
					{
						double _setval = 0;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.punch_limit = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			} else {
				{
					double _setval = 0;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.punch_limit = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("BattleBeast")) {
			LeapTargetSelectedProcedure.execute(world, x, y, z, entity);
		}
	}
}
