package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.entity.KreggEntity;
import net.mcreator.invincible_craft.entity.AnissaEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BarrageClashProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((entity.getPersistentData().getBoolean("barrage") || (entity instanceof AnissaEntity _datEntS ? _datEntS.getEntityData().get(AnissaEntity.DATA_State) : "").equals("BARRAGE")
				|| (entity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("BARRAGE"))
				&& (sourceentity.getPersistentData().getBoolean("barrage") || (sourceentity instanceof AnissaEntity _datEntS ? _datEntS.getEntityData().get(AnissaEntity.DATA_State) : "").equals("BARRAGE")
						|| (sourceentity instanceof KreggEntity _datEntS ? _datEntS.getEntityData().get(KreggEntity.DATA_State) : "").equals("BARRAGE"))) {
			if (event != null && event.isCancelable()) {
				event.setCanceled(true);
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.CLOUD, x, y, z, 10, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SPARK.get()), x, y, z, 10, 1, 1, 1, 1);
		}
	}
}
