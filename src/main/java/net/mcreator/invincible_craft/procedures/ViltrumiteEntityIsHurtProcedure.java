package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

public class ViltrumiteEntityIsHurtProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_recovery) : 0) <= 0) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_recovery, 10);
		}
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_HitMeterRecovery) : 0) <= 0) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_SlamMeter, (int) ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_SlamMeter) : 0) + 1));
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_HitMeterRecovery, 20);
		}
	}
}
