package net.mcreator.invincible_craft;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WaveEffect {
	public static void createShockwave(Level world, BlockPos center, int maxRadius, int damage) {
		for (int radius = 2; radius <= maxRadius; radius++) {
			for (BlockPos pos : getRing(center, radius)) {
				//Replace "YourMod" with the actual Mod class, in my case it's "BoltzyBreezeMod"
				InvincibleCraftMod.queueServerWork(radius * 2, () -> {
					//Some Particles
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(pos.getX() + 0.5, (pos.getY() + 1.1), pos.getZ() + 0.5), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("/particle sweep_attack ~ ~ ~ 0 0 0 1 0 normal"));
					// Displace the block at the current position
					if (world.getBlockState(pos).getBlock() != Blocks.AIR) {
						Block block = world.getBlockState(pos).getBlock();
						float blockHardness = block.defaultBlockState().getDestroySpeed(world, pos); // Get the block's hardness
						// Check if the block is breakable (hardness >= 0.0F and not unbreakable like bedrock)
						if (blockHardness >= 0.0F && blockHardness != -1.0F) {
							if (!(block == Blocks.WATER) && !world.isEmptyBlock(pos)) {
								if (world instanceof ServerLevel _level) {
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(pos.getX() + 0.5, (pos.getY()), pos.getZ() + 0.5), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											("/summon falling_block ~ ~1 ~ {BlockState:{Name:\"" + "" + ForgeRegistries.BLOCKS.getKey(block).toString() + "\"},Time:0.1,Motion:[" + "0.0," + new java.text.DecimalFormat("##.##").format(0.2) + ",0.0"
													+ "]}"));
								}
								if (world instanceof ServerLevel _level) {
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(pos.getX() + 0.5, (pos.getY()), pos.getZ() + 0.5), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											("/fill ~ ~ ~ ~ ~ ~ air replace"));
								}
							}
						}
					}
					// Damage entities within the current position
					AABB aabb = new AABB(pos).inflate(0.5);
					for (Entity entity : world.getEntitiesOfClass(LivingEntity.class, aabb)) {
						if (entity instanceof Player) {
							continue;
						}
						entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FALL)), damage);
						entity.setDeltaMovement(entity.getDeltaMovement().x(), 0.5, entity.getDeltaMovement().z);
					}
				});
			}
		}
	}

	// Helper method to get a ring of blocks at a specific radius
	private static Iterable<BlockPos> getRing(BlockPos center, int radius) {
		List<BlockPos> positions = new ArrayList<>();
		for (int dx = -radius; dx <= radius; dx++) {
			for (int dz = -radius; dz <= radius; dz++) {
				if (Math.sqrt(dx * dx + dz * dz) >= radius - 0.5 && Math.sqrt(dx * dx + dz * dz) <= radius + 0.5) {
					positions.add(center.offset(dx, 0, dz));
				}
			}
		}
		return positions;
	}
}
