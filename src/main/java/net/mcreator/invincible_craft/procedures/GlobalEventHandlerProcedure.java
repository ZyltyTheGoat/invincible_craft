package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

import javax.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class GlobalEventHandlerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level());
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		String name = "";
		List<Object> player = new ArrayList<>();
		double CordZ = 0;
		double CordX = 0;
		double randomNum = 0;
		double CordY = 0;
		double random_event = 0;
		if (world.dayTime() % 24000 == 0) {
			player.clear();
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				player.add(entityiterator);
			}
			randomNum = Mth.nextInt(RandomSource.create(), 1, (int) player.size());
			name = (player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getDisplayName().getString();
			random_event = Mth.nextInt(RandomSource.create(), 1, 3);
			if (random_event == 1) {
				CordX = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getX())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -50, 50);
				CordZ = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getZ())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -50, 50);
				CordY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(CordX), Mth.floor(CordZ));
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(
							("\u00A7cA group of bandits has been spotted at " + new java.text.DecimalFormat("##").format(CordX) + " " + new java.text.DecimalFormat("##").format(CordY) + " " + new java.text.DecimalFormat("##").format(CordZ) + ".")),
							false);
				if (!(CordY <= -50)) {
					for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 4, 8); index0++) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = InvincibleCraftModEntities.BANDIT.get().spawn(_level, BlockPos.containing(CordX, CordY, CordZ), MobSpawnType.MOB_SUMMONED);
							if (entityToSpawn != null) {
							}
						}
					}
				}
			} else if (random_event == 2) {
				CordX = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getX())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -75, 75);
				CordZ = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getZ())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -75, 75);
				CordY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(CordX), Mth.floor(CordZ));
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList()
							.broadcastSystemMessage(Component.literal(
									("\u00A7cA Viltrumite has been spotted at" + new java.text.DecimalFormat("##").format(CordX) + " " + new java.text.DecimalFormat("##").format(CordY) + " " + new java.text.DecimalFormat("##").format(CordZ) + ".")),
									false);
				if (!(CordY <= -50)) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = InvincibleCraftModEntities.VILTRUMITE.get().spawn(_level, BlockPos.containing(CordX, CordY, CordZ), MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
						}
					}
				}
			} else if (random_event == 2) {
				CordX = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getX())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -75, 75);
				CordZ = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getZ())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -75, 75);
				CordY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(CordX), Mth.floor(CordZ));
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList()
							.broadcastSystemMessage(Component.literal(
									("\u00A7cA Giant has been spotted at" + new java.text.DecimalFormat("##").format(CordX) + " " + new java.text.DecimalFormat("##").format(CordY) + " " + new java.text.DecimalFormat("##").format(CordZ) + ".")),
									false);
				if (!(CordY <= -50)) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = InvincibleCraftModEntities.THE_GIANT.get().spawn(_level, BlockPos.containing(CordX, CordY, CordZ), MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
						}
					}
				}
			}
		}
	}
}
