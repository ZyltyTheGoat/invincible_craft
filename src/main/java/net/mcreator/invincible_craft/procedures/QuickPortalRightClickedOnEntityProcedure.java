package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
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

public class QuickPortalRightClickedOnEntityProcedure {
	public static void execute(Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof TamableAnimal _tamIsTamedBy && sourceentity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) {
			if (sourceentity.isShiftKeyDown()) {
				if (!entity.level().isClientSide())
					entity.discard();
			} else {
				if ((sourceentity.getPersistentData().getString("selectedDim")).equals("Earth")) {
					if (!((sourceentity.level().dimension()) == Level.OVERWORLD)) {
						if (sourceentity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
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
				} else if ((sourceentity.getPersistentData().getString("selectedDim")).equals("Moon")) {
					if (!((sourceentity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, new ResourceLocation("invincible_craft:moon")))) {
						if (sourceentity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
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
					Entity _ent = sourceentity;
					_ent.teleportTo((entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_x) : 0),
							(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_y) : 0),
							(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_z) : 0));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport((entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_x) : 0),
								(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_y) : 0),
								(entity instanceof QuickPortalEntity _datEntI ? _datEntI.getEntityData().get(QuickPortalEntity.DATA_travel_z) : 0), _ent.getYRot(), _ent.getXRot());
				}
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}
