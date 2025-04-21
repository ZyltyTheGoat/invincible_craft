package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;
import net.mcreator.invincible_craft.entity.SpikeBallEntityEntity;

import java.util.List;
import java.util.Comparator;

public class SpikeBallEntityOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("Timer") == 0) {
			if (entity instanceof SpikeBallEntityEntity) {
				((SpikeBallEntityEntity) entity).setAnimation("spawn");
			}
		}
		if (entity.getPersistentData().getDouble("Timer") == 8) {
			entity.setDeltaMovement(new Vec3((entity.getPersistentData().getDouble("dirX") * 0.3), (entity.getPersistentData().getDouble("dirY") * 0.3), (entity.getPersistentData().getDouble("dirZ") * 0.3)));
		}
		if (entity.getPersistentData().getDouble("Timer") < 30) {
			entity.getPersistentData().putDouble("Timer", (entity.getPersistentData().getDouble("Timer") + 1));
			if (!(entity instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false) && entity.getPersistentData().getDouble("Timer") > 2) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (!(entity == entityiterator) && entityiterator instanceof LivingEntity
							&& !(entityiterator instanceof TamableAnimal _tamIsTamedBy && (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false)
							&& !((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) == entityiterator)) {
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null)),
								(float) (4 + 0.2 * ((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new InvincibleCraftModVariables.PlayerVariables())).stat_intelligence));
						entityiterator.invulnerableTime = 0;
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_BIG_BLAST.get()), x, (y + 3), z, 1, 0, 0, 0, 0);
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK.get()), x, (y + 1), z, 7, 2, 2, 2, 0.4);
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK_2.get()), x, (y + 1), z, 7, 2, 2, 2, 0.4);
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK_3.get()), x, (y + 1), z, 7, 2, 2, 2, 0.4);
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.ATOM_EVE_BUBBLE_BREAK_4.get()), x, (y + 1), z, 7, 2, 2, 2, 0.4);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						if (!entity.level().isClientSide())
							entity.discard();
					}
				}
			}
		} else {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}
