package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;

public class InfiniteDesertPlayerEntersDimensionProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double new_x = 0;
		double new_z = 0;
		double new_y = 0;
		new_x = entity.getX();
		new_y = entity.getY();
		new_z = entity.getZ();
		while (true) {
			if (!world.isEmptyBlock(BlockPos.containing(new_x, new_y, new_z))) {
				new_y = new_y + 1;
				continue;
			} else {
				{
					Entity _ent = entity;
					_ent.teleportTo(new_x, new_y, new_z);
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(new_x, new_y, new_z, _ent.getYRot(), _ent.getXRot());
				}
				break;
			}
		}
	}
}
