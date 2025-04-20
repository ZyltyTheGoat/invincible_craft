package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AtomEveFlightFlareProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double pitch = 0;
		double yaw = 0;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("AtomEve")
				&& (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).flying) {
			yaw = entity.getYRot() * 0.0174533 + Math.PI;
			pitch = 0;
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), (entity.getX() + 0.15 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY()), (entity.getZ() + 0.15 * Math.sin(yaw) * Math.cos(pitch)), 2,
						0.01, 0.01, 0.01, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), (entity.getX() + 0.3 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.75),
						(entity.getZ() + 0.3 * Math.sin(yaw) * Math.cos(pitch)), 2, 0.01, 0.01, 0.01, 0.01);
			yaw = entity.getYRot() * 0.0174533;
			pitch = 0;
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), (entity.getX() + 0.15 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY()), (entity.getZ() + 0.15 * Math.sin(yaw) * Math.cos(pitch)), 2,
						0.01, 0.01, 0.01, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), (entity.getX() + 0.3 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + 0.75),
						(entity.getZ() + 0.3 * Math.sin(yaw) * Math.cos(pitch)), 2, 0.01, 0.01, 0.01, 0.01);
		}
	}
}
