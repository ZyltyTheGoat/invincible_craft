package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.eventbus.api.Event;

public class ViltrumiteUpslamProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double knockbackres = 0;
		sx = -6;
		if (entity instanceof LivingEntity _entity)
			_entity.swing(InteractionHand.MAIN_HAND, true);
		for (int index0 = 0; index0 < 12; index0++) {
			sy = -6;
			for (int index1 = 0; index1 < 12; index1++) {
				sz = -6;
				for (int index2 = 0; index2 < 12; index2++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(new ResourceLocation("minecraft:glass")))) {
						world.destroyBlock(BlockPos.containing(x + sx, y + sy, z + sz), false);
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.PLAYERS, 1, (float) 1.5);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.PLAYERS, 1, (float) 1.5, false);
			}
		}
		{
			final Vec3 _center = new Vec3((entity.getX() + entity.getLookAngle().x), (entity.getY() + entity.getLookAngle().y), (entity.getZ() + entity.getLookAngle().z));
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator == entity)) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.PUNCH_IMPACT_1.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0, 0, 0, 0);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 45, 0.25, 0.25, 0.25,
								0.25);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")), SoundSource.NEUTRAL, 1, 2, false);
						}
					}
					entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("invincible_craft:viltrumite_punch"))), entity),
							(float) ((entity instanceof LivingEntity _livingEntity23 && _livingEntity23.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity23.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0) * 0.875));
					knockbackres = Math.min(1, Math.max(
							entityiterator instanceof LivingEntity _livingEntity26 && _livingEntity26.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE) ? _livingEntity26.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() : 0, 0));
					entityiterator.setDeltaMovement(new Vec3((entityiterator.getDeltaMovement().x()), (5 * (1 - knockbackres / 1)), (entityiterator.getDeltaMovement().z())));
					if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.STUN.get(), 60, Mth.nextInt(RandomSource.create(), 0, 1), false, false));
					if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 5, 0, false, false));
					if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.DELETED_MOD_ELEMENT.get(), 10, 3, false, false));
				}
			}
		}
	}
}
