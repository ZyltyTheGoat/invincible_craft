package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.BlockTags;
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

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ViltrumitePunchOnHurtProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(),
					event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, world, x, y, z, damagesource, entity, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		boolean found = false;
		double dmg = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		dmg = amount;
		if (((sourceentity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
			if (!(immediatesourceentity instanceof Projectile) && !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:barrage")))
					&& !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch")))
					&& !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:chop")))
					&& !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:sonic_clap_damage")))) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof SwordItem
						|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem
						|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof TridentItem
						|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof ShovelItem) {
					if (event != null && event.isCancelable()) {
						event.setCanceled(true);
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, (float) 1.5, (float) 0.8);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, (float) 1.5, (float) 0.8, false);
						}
					}
					entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), sourceentity),
							(float) dmg);
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SWEEP_ATTACK, (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 1, 0, 0, 0, 0);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 45, 0.25, 0.25, 0.25, 0.25);
					entity.setDeltaMovement(new Vec3((2 * sourceentity.getLookAngle().x), (2 * sourceentity.getLookAngle().y), (2 * sourceentity.getLookAngle().z)));
					sx = -3;
					found = false;
					for (int index0 = 0; index0 < 6; index0++) {
						sy = -3;
						for (int index1 = 0; index1 < 6; index1++) {
							sz = -3;
							for (int index2 = 0; index2 < 6; index2++) {
								if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(new ResourceLocation("minecraft:glass")))) {
									world.destroyBlock(BlockPos.containing(x + sx, y + sy, z + sz), false);
								}
								sz = sz + 1;
							}
							sy = sy + 1;
						}
						sx = sx + 1;
					}
				} else {
					if (event != null && event.isCancelable()) {
						event.setCanceled(true);
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 1, 0, 0, 0, 0);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 45, 0.25, 0.25, 0.25, 0.25);
					entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), sourceentity),
							(float) dmg);
					entity.setDeltaMovement(new Vec3((2 * sourceentity.getLookAngle().x), (2 * sourceentity.getLookAngle().y), (2 * sourceentity.getLookAngle().z)));
					sx = -3;
					found = false;
					for (int index3 = 0; index3 < 6; index3++) {
						sy = -3;
						for (int index4 = 0; index4 < 6; index4++) {
							sz = -3;
							for (int index5 = 0; index5 < 6; index5++) {
								if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(new ResourceLocation("minecraft:glass")))) {
									world.destroyBlock(BlockPos.containing(x + sx, y + sy, z + sz), false);
								}
								sz = sz + 1;
							}
							sy = sy + 1;
						}
						sx = sx + 1;
					}
				}
			}
		}
	}
}
