package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class RoarTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double delay = 0;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).battle_beast_roar > 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 3, 0, false, false));
			{
				double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).battle_beast_roar - 1;
				entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.battle_beast_roar = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world.dayTime() % 5 == 0) {
				entity.getPersistentData().putDouble("BattleBeastRoarRepeat", 0);
				entity.getPersistentData().putDouble("BattleBeastRoarX", (entity.getLookAngle().x));
				entity.getPersistentData().putDouble("BattleBeastRoarY", (entity.getLookAngle().y));
				entity.getPersistentData().putDouble("BattleBeastRoarZ", (entity.getLookAngle().z));
				for (int index0 = 0; index0 < 5; index0++) {
					entity.getPersistentData().putDouble("BattleBeastRoarRepeat", (entity.getPersistentData().getDouble("BattleBeastRoarRepeat") + 4));
					if (entity.getPersistentData().getDouble("BattleBeastRoarRepeat") == 4) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_1.get()),
									(entity.getX() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarX")),
									(entity.getY() + 1.6 + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarY")),
									(entity.getZ() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarZ")), 1, 0, 0, 0, 0);
					} else if (entity.getPersistentData().getDouble("BattleBeastRoarRepeat") == 8) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_2.get()),
									(entity.getX() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarX")),
									(entity.getY() + 1.6 + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarY")),
									(entity.getZ() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarZ")), 1, 0, 0, 0, 0);
					} else if (entity.getPersistentData().getDouble("BattleBeastRoarRepeat") == 12) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_3.get()),
									(entity.getX() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarX")),
									(entity.getY() + 1.6 + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarY")),
									(entity.getZ() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarZ")), 1, 0, 0, 0, 0);
					} else if (entity.getPersistentData().getDouble("BattleBeastRoarRepeat") == 16) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_4.get()),
									(entity.getX() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarX")),
									(entity.getY() + 1.6 + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarY")),
									(entity.getZ() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarZ")), 1, 0, 0, 0, 0);
					} else if (entity.getPersistentData().getDouble("BattleBeastRoarRepeat") == 20) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BATTLE_BEAST_ROAR_5.get()),
									(entity.getX() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarX")),
									(entity.getY() + 1.6 + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarY")),
									(entity.getZ() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarZ")), 1, 0, 0, 0, 0);
					}
					{
						final Vec3 _center = new Vec3((entity.getX() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarX")),
								(entity.getY() + 1.6 + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarY")),
								(entity.getZ() + entity.getPersistentData().getDouble("BattleBeastRoarRepeat") * entity.getPersistentData().getDouble("BattleBeastRoarZ")));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entity == entityiterator) && !(entityiterator instanceof ExperienceOrb) && !(entityiterator instanceof ItemEntity)) {
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:sonic_clap_damage"))), entity),
										15);
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 20, 0, false, false));
								if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 35, 0));
							}
						}
					}
				}
			}
		}
	}
}
