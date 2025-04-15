package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.invincible_craft.entity.BanditEntity;

public class BanditOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_base, Mth.nextInt(RandomSource.create(), 1, 3));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_hair, Mth.nextInt(RandomSource.create(), 1, 7));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_top, Mth.nextInt(RandomSource.create(), 1, 7));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_bottom, Mth.nextInt(RandomSource.create(), 1, 4));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_mask, Mth.nextInt(RandomSource.create(), 1, 10));
	}
}
