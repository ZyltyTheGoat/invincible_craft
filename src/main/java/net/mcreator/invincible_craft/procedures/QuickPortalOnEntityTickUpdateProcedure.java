package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.entity.QuickPortalEntity;

import java.util.List;
import java.util.Comparator;

public class QuickPortalOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("Timer") < 200) {
			entity.getPersistentData().putDouble("Timer", (entity.getPersistentData().getDouble("Timer") + 1));
			if (!(entity instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false) && entity.getPersistentData().getDouble("Timer") > 2) {
				if (!entity.level().isClientSide())
					entity.discard();
			} else {
				{
					final Vec3 _center = new Vec3(x, (y + 1), z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null)) && !(entityiterator == entity)) {
							if (((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getPersistentData().getString("selectedDim")).equals("Earth")) {
								if (!((entityiterator.level().dimension()) == Level.OVERWORLD)) {
									if (entityiterator instanceof ServerPlayer _player && !_player.level().isClientSide()) {
										ResourceKey<Level> destinationType = Level.OVERWORLD;
										if (_player.level().dimension() == destinationType)
											return;
										ServerLevel nextLevel = _player.server.getLevel(destinationType);
										if (nextLevel != null) {
											_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
											_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
											_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
											for (MobEffectInstance _effectinstance : _player.getActiveEffects())
												_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
											_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
										}
									}
								}
							} else if (((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getPersistentData().getString("selectedDim")).equals("Moon")) {
								if (!((entityiterator.level().dimension()) == ResourceKey.create(Registries.DIMENSION, new ResourceLocation("invincible_craft:moon")))) {
									if (entityiterator instanceof ServerPlayer _player && !_player.level().isClientSide()) {
										ResourceKey<Level> destinationType = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("invincible_craft:moon"));
										if (_player.level().dimension() == destinationType)
											return;
										ServerLevel nextLevel = _player.server.getLevel(destinationType);
										if (nextLevel != null) {
											_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
											_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
											_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
											for (MobEffectInstance _effectinstance : _player.getActiveEffects())
												_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
											_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
										}
									}
								}
							}
							{
								Entity _ent = entityiterator;
								_ent.teleportTo((entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_x) : 0),
										(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_y) : 0),
										(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_z) : 0));
								if (_ent instanceof ServerPlayer _serverPlayer)
									_serverPlayer.connection.teleport((entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_x) : 0),
											(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_y) : 0),
											(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_z) : 0), _ent.getYRot(), _ent.getXRot());
							}
						}
					}
				}
			}
		} else {
			if (!entity.level().isClientSide())
				entity.discard();
			entity.getPersistentData().putBoolean("openedPortalBanishment", true);
		}
		entity.setDeltaMovement(new Vec3(0, 0, 0));
	}
}
