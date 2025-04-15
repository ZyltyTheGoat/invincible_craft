
package net.mcreator.invincible_craft.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.procedures.LeapGuideOnEntityTickUpdateProcedure;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class LeapGuideEntity extends PathfinderMob {
	public static final EntityDataAccessor<Integer> DATA_LIFE = SynchedEntityData.defineId(LeapGuideEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_IS_RETRACTING = SynchedEntityData.defineId(LeapGuideEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> DATA_LEAPER_UUID = SynchedEntityData.defineId(LeapGuideEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_PROGRESS = SynchedEntityData.defineId(LeapGuideEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_MAX_HEIGHT = SynchedEntityData.defineId(LeapGuideEntity.class, EntityDataSerializers.INT);

	public LeapGuideEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvincibleCraftModEntities.LEAP_GUIDE.get(), world);
	}

	public LeapGuideEntity(EntityType<LeapGuideEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_LIFE, 10);
		this.entityData.define(DATA_IS_RETRACTING, false);
		this.entityData.define(DATA_LEAPER_UUID, "");
		this.entityData.define(DATA_PROGRESS, 0);
		this.entityData.define(DATA_MAX_HEIGHT, 10);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		if (damagesource.getDirectEntity() instanceof AbstractArrow)
			return false;
		if (damagesource.getDirectEntity() instanceof Player)
			return false;
		if (damagesource.getDirectEntity() instanceof ThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud)
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.CACTUS))
			return false;
		if (damagesource.is(DamageTypes.DROWN))
			return false;
		if (damagesource.is(DamageTypes.LIGHTNING_BOLT))
			return false;
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
			return false;
		if (damagesource.is(DamageTypes.TRIDENT))
			return false;
		if (damagesource.is(DamageTypes.FALLING_ANVIL))
			return false;
		if (damagesource.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DataLIFE", this.entityData.get(DATA_LIFE));
		compound.putBoolean("DataIS_RETRACTING", this.entityData.get(DATA_IS_RETRACTING));
		compound.putString("DataLEAPER_UUID", this.entityData.get(DATA_LEAPER_UUID));
		compound.putInt("DataPROGRESS", this.entityData.get(DATA_PROGRESS));
		compound.putInt("DataMAX_HEIGHT", this.entityData.get(DATA_MAX_HEIGHT));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataLIFE"))
			this.entityData.set(DATA_LIFE, compound.getInt("DataLIFE"));
		if (compound.contains("DataIS_RETRACTING"))
			this.entityData.set(DATA_IS_RETRACTING, compound.getBoolean("DataIS_RETRACTING"));
		if (compound.contains("DataLEAPER_UUID"))
			this.entityData.set(DATA_LEAPER_UUID, compound.getString("DataLEAPER_UUID"));
		if (compound.contains("DataPROGRESS"))
			this.entityData.set(DATA_PROGRESS, compound.getInt("DataPROGRESS"));
		if (compound.contains("DataMAX_HEIGHT"))
			this.entityData.set(DATA_MAX_HEIGHT, compound.getInt("DataMAX_HEIGHT"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		LeapGuideOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity entityIn) {
	}

	@Override
	protected void pushEntities() {
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public void setNoGravity(boolean ignored) {
		super.setNoGravity(true);
	}

	public void aiStep() {
		super.aiStep();
		this.setNoGravity(true);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.FLYING_SPEED, 0.3);
		return builder;
	}
}
