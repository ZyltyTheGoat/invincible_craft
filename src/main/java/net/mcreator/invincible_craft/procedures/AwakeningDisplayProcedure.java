package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AwakeningDisplayProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double radius = 0;
		double rotation_angle = 0;
		double tiltedY = 0;
		double tiltedZ = 0;
		double rotationAngle = 0;
		double rotation_speed = 0;
		double tiltAngle = 0;
		double theta = 0;
		double particleAmount = 0;
		double rotated_theta = 0;
		double circleY = 0;
		double circleX = 0;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("AtomEve")) {
			if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_awakened_timer > 0) {
				{
					double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_awakened_timer - 1;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.atom_eve_awakened_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_awakened_timer > 820) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "");
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "");
						}
					} // Configuration
					int particlesPerRing = 50; // Half the particles for each ring
					double ringRadius = 2.0;
					double verticalOffset = 1.0;
					// First circle: 45 degree upward tilt
					for (int i = 0; i < particlesPerRing; i++) {
						double angle = (Math.PI * 2 * i) / particlesPerRing;
						double baseX = Math.cos(angle) * ringRadius;
						double baseY = Math.sin(angle) * ringRadius;
						// Rotate +45 degrees around X-axis
						double transformedY = baseY * Math.cos(Math.PI / 4) - 0 * Math.sin(Math.PI / 4);
						double transformedZ = baseY * Math.sin(Math.PI / 4) + 0 * Math.cos(Math.PI / 4);
						if (world instanceof ServerLevel serverLevel) {
							serverLevel.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), x + baseX, y + transformedY + verticalOffset, z + transformedZ, 1, 0, 0, 0, 0);
						}
					}
					// Second circle: 45 degree downward tilt
					for (int i = 0; i < particlesPerRing; i++) {
						double angle = (Math.PI * 2 * i) / particlesPerRing;
						double baseX = Math.cos(angle) * ringRadius;
						double baseY = Math.sin(angle) * ringRadius;
						// Rotate -45 degrees around X-axis
						double transformedY = baseY * Math.cos(-Math.PI / 4) - 0 * Math.sin(-Math.PI / 4);
						double transformedZ = baseY * Math.sin(-Math.PI / 4) + 0 * Math.cos(-Math.PI / 4);
						if (world instanceof ServerLevel serverLevel) {
							serverLevel.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_CLOUD.get()), x + baseX, y + transformedY + verticalOffset, z + transformedZ, 1, 0, 0, 0, 0);
						}
					}
				}
			}
		}
	}
}
