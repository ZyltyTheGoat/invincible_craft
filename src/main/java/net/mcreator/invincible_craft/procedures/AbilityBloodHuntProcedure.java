package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.init.InvincibleCraftModItems;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class AbilityBloodHuntProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double duration = 0;
		double tx = 0;
		double t = 0;
		double sx = 0;
		double ty = 0;
		double sy = 0;
		double tz = 0;
		double sz = 0;
		double easedT = 0;
		double height = 0;
		double ran = 0;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).battle_beast_blood_hunt > 0) {
			{
				double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).battle_beast_blood_hunt - 1;
				entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.battle_beast_blood_hunt = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).battle_beast_blood_hunt <= 0) {
				{
					double _setval = 10;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ability_cooldown_2 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()))
				_livingEntity0.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(1);
			if (world.dayTime() % 3 == 0) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 3, 0, false, false));
				if (entity.onGround()) {
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 2), (entity.getDeltaMovement().y()), (entity.getLookAngle().z * 2)));
				} else {
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 2), (entity.getLookAngle().y * 2), (entity.getLookAngle().z * 2)));
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == InvincibleCraftModItems.BATTLE_BEAST_MACE.get()) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 5, 1.5, 1.5, 1.5, 0);
				} else {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SLASH.get()), x, y, z, 5, 1.5, 1.5, 1.5, 0);
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.CLAW_STRIKE.get()), x, y, z, 5, 1.5, 1.5, 1.5, 0);
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CLOUD, (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 5, 0.5, 1, 0.5, 0.25);
				if (entity.onGround()) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 3, 0.1, 0.1, 0.1, 0.15);
				}
				if (Math.random() < (1) / ((float) 5)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:growl1")), SoundSource.PLAYERS, (float) 0.5, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:growl1")), SoundSource.PLAYERS, (float) 0.5, 1, false);
						}
					}
				} else if (Math.random() < (1) / ((float) 5)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:growl2")), SoundSource.PLAYERS, (float) 0.5, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:growl2")), SoundSource.PLAYERS, (float) 0.5, 1, false);
						}
					}
				} else if (Math.random() < (1) / ((float) 5)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:growl3")), SoundSource.PLAYERS, (float) 0.5, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:growl3")), SoundSource.PLAYERS, (float) 0.5, 1, false);
						}
					}
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) && !(entityiterator == entity) && entityiterator instanceof LivingEntity) {
							entityiterator.hurt(
									new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:blood_hunt"))), entity), (float) (3
											+ (entity instanceof LivingEntity _livingEntity34 && _livingEntity34.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity34.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) * 1.5));
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 60, 0.25,
										0.25, 0.25, 0.25);
							entityiterator.invulnerableTime = 1;
							ran = Mth.nextInt(RandomSource.create(), 1, 3);
							if (Math.random() < (1) / ((float) 3)) {
								if (ran == 1) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_1")), SoundSource.NEUTRAL, (float) 0.5,
													(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2));
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_1")), SoundSource.NEUTRAL, (float) 0.5,
													(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2), false);
										}
									}
								} else if (ran == 1) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_2")), SoundSource.NEUTRAL, (float) 0.5,
													(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2));
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_2")), SoundSource.NEUTRAL, (float) 0.5,
													(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2), false);
										}
									}
								} else {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_3")), SoundSource.NEUTRAL, (float) 0.5,
													(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2));
										} else {
											_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:blood_sound_3")), SoundSource.NEUTRAL, (float) 0.5,
													(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2), false);
										}
									}
								}
							} else if (Math.random() < (1) / ((float) 3)) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:viltrumite_chop")), SoundSource.NEUTRAL, (float) 0.5,
												(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2));
									} else {
										_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("invincible_craft:viltrumite_chop")), SoundSource.NEUTRAL, (float) 0.5,
												(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.2), false);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
