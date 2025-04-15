package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.entity.DuplicationCloneEntity;

import javax.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class DuplicationRevivalProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		List<Object> clones = new ArrayList<>();
		double random_clone = 0;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Duplication")) {
			if (world instanceof ServerLevel) {
				for (Entity entityiterator : ((ServerLevel) world).getAllEntities()) {
					if (entityiterator == null)
						continue;
					if ((entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) && entityiterator instanceof DuplicationCloneEntity) {
						if ((entityiterator.level().dimension()) == (entity.level().dimension())) {
							clones.add(entityiterator);
						}
					}
				}
			}
			if (clones.size() > 0) {
				random_clone = Mth.nextInt(RandomSource.create(), 1, (int) clones.size());
				{
					double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).clone_number - 1;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.clone_number = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null) instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1);
				{
					Entity _ent = entity;
					_ent.teleportTo(((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).getX()), ((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).getY()),
							((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).getZ()));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).getX()), ((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).getY()),
								((clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).getZ()), _ent.getYRot(), _ent.getXRot());
				}
				if (!(clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).level().isClientSide())
					(clones.get((int) (random_clone - 1)) instanceof Entity _e ? _e : null).discard();
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, (y + 1), z, 5, 0.5, 1, 0.5, 0.1);
			}
		}
	}
}
