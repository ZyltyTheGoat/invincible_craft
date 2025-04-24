package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class LeapTickProcedure {
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
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).battle_beast_leaping) {
			if (entity.getXRot() >= 5) {
				entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 4), (-1 + entity.getLookAngle().y * 4), (entity.getLookAngle().z * 4)));
			}
			if (entity.onGround()) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CLOUD, x, y, z, 250, 3, 0.5, 3, 0.1);
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 2, 2, 0.5, 2, 0.1);
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_3.get()), x, y, z, 2, 0, 0, 0, 0.1);
				{
					boolean _setval = false;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.battle_beast_leaping = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				int horizontalRadiusHemiBot = (int) (2 + 0.1 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_strength) - 1;
				int verticalRadiusHemiBot = (int) (2 + 0.1 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_strength);
				int yIterationsHemiBot = verticalRadiusHemiBot;
				for (int i = -yIterationsHemiBot; i <= 0; i++) {
					if (i == -verticalRadiusHemiBot) {
						continue;
					}
					for (int xi = -horizontalRadiusHemiBot; xi <= horizontalRadiusHemiBot; xi++) {
						for (int zi = -horizontalRadiusHemiBot; zi <= horizontalRadiusHemiBot; zi++) {
							double distanceSq = (xi * xi) / (double) (horizontalRadiusHemiBot * horizontalRadiusHemiBot) + (i * i) / (double) (verticalRadiusHemiBot * verticalRadiusHemiBot)
									+ (zi * zi) / (double) (horizontalRadiusHemiBot * horizontalRadiusHemiBot);
							if (distanceSq <= 1.0) {
								if (Math.random() < (3) / ((float) 5)) {
									if (world instanceof ServerLevel _serverLevel) {
										Entity entityinstance = EntityType.FALLING_BLOCK.create(_serverLevel);
										if (entityinstance != null) {
											CompoundTag _compoundTag = entityinstance.saveWithoutId(new CompoundTag());
											_compoundTag.put("BlockState", NbtUtils.writeBlockState((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi)))));
											entityinstance.load(_compoundTag);
											entityinstance.setPos(x + xi, (y + i + 0.5), z + zi);
											entityinstance.setDeltaMovement(new Vec3(
													((entityinstance.getX() - entity.getX())
															* (2 / Math.sqrt(Math.pow(entityinstance.getX() - entity.getX(), 2) + Math.pow(entityinstance.getY() - entity.getY(), 2) + Math.pow(entityinstance.getZ() - entity.getZ(), 2)))),
													(Mth.nextDouble(RandomSource.create(), 0.5, 1)), ((entityinstance.getZ() - entity.getZ())
															* (2 / Math.sqrt(Math.pow(entityinstance.getX() - entity.getX(), 2) + Math.pow(entityinstance.getY() - entity.getY(), 2) + Math.pow(entityinstance.getZ() - entity.getZ(), 2))))));
											_serverLevel.addFreshEntity(entityinstance);
										}
									}
								}
								world.setBlock(BlockPos.containing(x + xi, y + i, z + zi), Blocks.AIR.defaultBlockState(), 3);
							}
						}
					}
				}
			}
		}
	}
}
