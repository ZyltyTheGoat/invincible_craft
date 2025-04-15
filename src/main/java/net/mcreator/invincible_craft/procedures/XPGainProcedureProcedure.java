package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModGameRules;
import net.mcreator.invincible_craft.entity.ViltrumiteEntity;
import net.mcreator.invincible_craft.entity.BanditEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class XPGainProcedureProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double xpReceiver = 0;
		if (!(entity instanceof TamableAnimal _tamIsTamedBy && sourceentity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)) {
			if (entity instanceof ViltrumiteEntity) {
				xpReceiver = Math.round(1000 + (entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_age) : 0) / 10)
						* ((world.getLevelData().getGameRules().getInt(InvincibleCraftModGameRules.INVINCIBLE_CRAFT_XP_MULTIPLIER)) / 10);
			} else if (entity instanceof BanditEntity) {
				xpReceiver = 5 * (world.getLevelData().getGameRules().getInt(InvincibleCraftModGameRules.INVINCIBLE_CRAFT_XP_MULTIPLIER));
			} else {
				if (entity instanceof Mob) {
					xpReceiver = Math.round(1 + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 20) * ((world.getLevelData().getGameRules().getInt(InvincibleCraftModGameRules.INVINCIBLE_CRAFT_XP_MULTIPLIER)) / 10);
				}
			}
			if (!(sourceentity instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false)) {
				{
					double _setval = (sourceentity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).xp + xpReceiver;
					sourceentity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.xp = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				if (sourceentity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("XP received: " + new java.text.DecimalFormat("##").format(xpReceiver))), true);
			} else {
				if (!((sourceentity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) == null)) {
					{
						double _setval = ((sourceentity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new InvincibleCraftModVariables.PlayerVariables())).xp + xpReceiver;
						(sourceentity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.xp = _setval;
							capability.syncPlayerVariables((sourceentity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null));
						});
					}
					if ((sourceentity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("XP received: " + new java.text.DecimalFormat("##").format(xpReceiver))), true);
				}
			}
		}
	}
}
