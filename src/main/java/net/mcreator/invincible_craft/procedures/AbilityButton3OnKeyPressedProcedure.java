package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class AbilityButton3OnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!DoesHaveCDAbility3Procedure.execute(entity)) {
			if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
				if (!(entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding) {
					entity.getPersistentData().putBoolean("barrage", true);
					entity.getPersistentData().putDouble("barrage_timer", 0);
				} else {
					if (!((Minecraft.getInstance().hitResult instanceof EntityHitResult _entityHitResult ? _entityHitResult.getEntity() : (Entity) null) == null)) {
						if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).holding_entity)
								.equals((Minecraft.getInstance().hitResult instanceof EntityHitResult _entityHitResult ? _entityHitResult.getEntity() : (Entity) null).getStringUUID())) {
							entity.getPersistentData().putBoolean("crush", true);
							entity.getPersistentData().putDouble("crush_max_timer",
									(((Minecraft.getInstance().hitResult instanceof EntityHitResult _entityHitResult ? _entityHitResult.getEntity() : (Entity) null) instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) * 3));
							entity.getPersistentData().putDouble("crush_timer", 0);
						}
					} else {
						entity.getPersistentData().putBoolean("crush", false);
						entity.getPersistentData().putDouble("crush_timer", 0);
					}
				}
			} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("DimensionalTravel")) {
				entity.getPersistentData().putBoolean("riftDash", true);
				entity.getPersistentData().putDouble("riftDashTimer", 0);
				if (world instanceof ServerLevel _serverLevel) {
					Entity entityinstance = InvincibleCraftModEntities.PORTAL_DASH_PORTAL.get().create(_serverLevel, null, null,
							BlockPos.containing(entity.getX() + (-2) * entity.getLookAngle().x, entity.getY(), entity.getZ() + (-2) * entity.getLookAngle().z), MobSpawnType.MOB_SUMMONED, false, false);
					if (entityinstance != null) {
						entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
						if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
							_toTame.tame(_owner);
						{
							Entity _ent = entityinstance;
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
						_serverLevel.addFreshEntity(entityinstance);
					}
				}
			} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Duplication")) {
				DuplicationMergeProcedure.execute(world, entity);
			} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("BattleBeast")) {
				AbilityUppercutProcedure.execute(world, x, y, z, entity);
			} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("AtomEve")) {
				if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_awakened_timer > 0) {
					{
						boolean _setval = true;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.atom_eve_atomic_ray = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					{
						boolean _setval = true;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.atom_eve_atomic_blast_holding = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
