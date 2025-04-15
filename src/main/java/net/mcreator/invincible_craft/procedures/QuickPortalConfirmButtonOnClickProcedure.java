package net.mcreator.invincible_craft.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.entity.QuickPortalEntity;

import java.util.HashMap;

public class QuickPortalConfirmButtonOnClickProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		{
			double _setval = 15;
			entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ability_cooldown_1 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, new ResourceLocation("invincible_craft:technicians_dimension"))) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Can't teleport in this dimension!"), true);
		} else {
			if (world instanceof ServerLevel _serverLevel) {
				Entity entityinstance = InvincibleCraftModEntities.QUICK_PORTAL.get().create(_serverLevel, null, null,
						BlockPos.containing(entity.getX() + 4 * entity.getLookAngle().x, entity.getY() + 1.6 + 4 * entity.getLookAngle().y, entity.getZ() + 4 * entity.getLookAngle().z), MobSpawnType.MOB_SUMMONED, false, false);
				if (entityinstance != null) {
					entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
					if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
						_toTame.tame(_owner);
					{
						Entity _ent = entity;
						_ent.setYRot(entity.getYRot());
						_ent.setXRot(entity.getXRot());
						_ent.setYBodyRot(_ent.getYRot());
						_ent.setYHeadRot(_ent.getYRot());
						_ent.yRotO = _ent.getYRot();
						_ent.xRotO = _ent.getXRot();
						if (_ent instanceof LivingEntity _entity) {
							_entity.yBodyRotO = _entity.getYRot();
							_entity.yHeadRotO = _entity.getYRot();
						}
					}
					if (entityinstance instanceof QuickPortalEntity _datEntSetI)
						_datEntSetI.getEntityData().set(QuickPortalEntity.DATA_travel_x, (int) new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(guistate.containsKey("textin:travel_x") ? (String) guistate.get("textin:travel_x") : ""));
					if (entityinstance instanceof QuickPortalEntity _datEntSetI)
						_datEntSetI.getEntityData().set(QuickPortalEntity.DATA_travel_y, (int) new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(guistate.containsKey("textin:travel_y") ? (String) guistate.get("textin:travel_y") : ""));
					if (entityinstance instanceof QuickPortalEntity _datEntSetI)
						_datEntSetI.getEntityData().set(QuickPortalEntity.DATA_travel_z, (int) new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(guistate.containsKey("textin:travel_z") ? (String) guistate.get("textin:travel_z") : ""));
					_serverLevel.addFreshEntity(entityinstance);
				}
			}
		}
	}
}
