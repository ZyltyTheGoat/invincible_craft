package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
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
public class ViltrumiteEventHandlerProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
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
		Entity ent = null;
		if (world.dayTime() % 24000 == 0) {
			player.clear();
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if ((entityiterator.level().dimension()) == Level.OVERWORLD) {
					player.add(entityiterator);
				}
			}
			if (player.size() > 0) {
				if (Math.random() < (1) / ((float) 2)) {
					randomNum = Mth.nextInt(RandomSource.create(), 1, (int) player.size());
					name = (player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getDisplayName().getString();
					ent = player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null;
					random_event = Mth.nextInt(RandomSource.create(), 1, 1);
					if (random_event == 1) {
						CordX = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getX())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -100, 100);
						CordZ = Math.round(Math.pow(10, 2) * ((player.get((int) (randomNum - 1)) instanceof Entity _e ? _e : null).getZ())) / Math.pow(10, 2) + Mth.nextInt(RandomSource.create(), -100, 100);
						CordY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(CordX), Mth.floor(CordZ));
						if (!(CordY <= -50)) {
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("\u00A7l\u00A74 A strange warrior in white has been spotted at " + new java.text.DecimalFormat("##").format(CordX) + " "
										+ new java.text.DecimalFormat("##").format(CordY) + " " + new java.text.DecimalFormat("##").format(CordZ))), false);
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = InvincibleCraftModEntities.VILTRUMITE.get().spawn(_level, BlockPos.containing(CordX, CordY, CordZ), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
								}
							}
						} else {
							EventHandlerProcedure.execute();
						}
					}
				}
			}
		}
	}
}
