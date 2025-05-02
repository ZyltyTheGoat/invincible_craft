
package net.mcreator.invincible_craft.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.invincible_craft.procedures.ViltrumiteOnInitialEntitySpawnProcedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteOnEntityTickUpdateProcedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteEntityIsHurtProcedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteEntityDiesProcedure;
import net.mcreator.invincible_craft.procedures.IsViltrumiteRecoveredProcedure;
import net.mcreator.invincible_craft.procedures.CanViltrumiteFlyingAttackProcedure;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

import javax.annotation.Nullable;

public class ViltrumiteEntity extends Monster {
	public static final EntityDataAccessor<Boolean> DATA_flying = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_recovery = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ClapTimer = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ComboTimer = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ComboAmount = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_SlamMeter = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_HitMeterRecovery = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_Slamming = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_SlamIA = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_SlamLogic = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_ChopTimer = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_base = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_hair = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_rank = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_gender = SynchedEntityData.defineId(ViltrumiteEntity.class, EntityDataSerializers.INT);

	public ViltrumiteEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvincibleCraftModEntities.VILTRUMITE.get(), world);
	}

	public ViltrumiteEntity(EntityType<ViltrumiteEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(1.2f);
		xpReward = 50;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_flying, false);
		this.entityData.define(DATA_recovery, 0);
		this.entityData.define(DATA_ClapTimer, 0);
		this.entityData.define(DATA_ComboTimer, 0);
		this.entityData.define(DATA_ComboAmount, 0);
		this.entityData.define(DATA_SlamMeter, 0);
		this.entityData.define(DATA_HitMeterRecovery, 0);
		this.entityData.define(DATA_Slamming, false);
		this.entityData.define(DATA_SlamIA, 0);
		this.entityData.define(DATA_SlamLogic, false);
		this.entityData.define(DATA_ChopTimer, 0);
		this.entityData.define(DATA_base, 0);
		this.entityData.define(DATA_hair, 0);
		this.entityData.define(DATA_rank, 0);
		this.entityData.define(DATA_gender, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 9;
			}

			@Override
			public boolean canUse() {
				double x = ViltrumiteEntity.this.getX();
				double y = ViltrumiteEntity.this.getY();
				double z = ViltrumiteEntity.this.getZ();
				Entity entity = ViltrumiteEntity.this;
				Level world = ViltrumiteEntity.this.level();
				return super.canUse() && IsViltrumiteRecoveredProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = ViltrumiteEntity.this.getX();
				double y = ViltrumiteEntity.this.getY();
				double z = ViltrumiteEntity.this.getZ();
				Entity entity = ViltrumiteEntity.this;
				Level world = ViltrumiteEntity.this.level();
				return super.canContinueToUse() && IsViltrumiteRecoveredProcedure.execute(entity);
			}

		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.goalSelector.addGoal(4, new RandomStrollGoal(this, 15, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = ViltrumiteEntity.this.getRandom();
				double dir_x = ViltrumiteEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = ViltrumiteEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = ViltrumiteEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}

			@Override
			public boolean canUse() {
				double x = ViltrumiteEntity.this.getX();
				double y = ViltrumiteEntity.this.getY();
				double z = ViltrumiteEntity.this.getZ();
				Entity entity = ViltrumiteEntity.this;
				Level world = ViltrumiteEntity.this.level();
				return super.canUse() && CanViltrumiteFlyingAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = ViltrumiteEntity.this.getX();
				double y = ViltrumiteEntity.this.getY();
				double z = ViltrumiteEntity.this.getZ();
				Entity entity = ViltrumiteEntity.this;
				Level world = ViltrumiteEntity.this.level();
				return super.canContinueToUse() && CanViltrumiteFlyingAttackProcedure.execute(entity);
			}

		});
		this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(7, new FloatGoal(this));
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
	public boolean hurt(DamageSource damagesource, float amount) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		Entity sourceentity = damagesource.getEntity();
		Entity immediatesourceentity = damagesource.getDirectEntity();

		ViltrumiteEntityIsHurtProcedure.execute(entity);
		if (damagesource.is(DamageTypes.FALL))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		ViltrumiteEntityDiesProcedure.execute(source.getEntity());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		ViltrumiteOnInitialEntitySpawnProcedure.execute(this);
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Dataflying", this.entityData.get(DATA_flying));
		compound.putInt("Datarecovery", this.entityData.get(DATA_recovery));
		compound.putInt("DataClapTimer", this.entityData.get(DATA_ClapTimer));
		compound.putInt("DataComboTimer", this.entityData.get(DATA_ComboTimer));
		compound.putInt("DataComboAmount", this.entityData.get(DATA_ComboAmount));
		compound.putInt("DataSlamMeter", this.entityData.get(DATA_SlamMeter));
		compound.putInt("DataHitMeterRecovery", this.entityData.get(DATA_HitMeterRecovery));
		compound.putBoolean("DataSlamming", this.entityData.get(DATA_Slamming));
		compound.putInt("DataSlamIA", this.entityData.get(DATA_SlamIA));
		compound.putBoolean("DataSlamLogic", this.entityData.get(DATA_SlamLogic));
		compound.putInt("DataChopTimer", this.entityData.get(DATA_ChopTimer));
		compound.putInt("Database", this.entityData.get(DATA_base));
		compound.putInt("Datahair", this.entityData.get(DATA_hair));
		compound.putInt("Datarank", this.entityData.get(DATA_rank));
		compound.putInt("Datagender", this.entityData.get(DATA_gender));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Dataflying"))
			this.entityData.set(DATA_flying, compound.getBoolean("Dataflying"));
		if (compound.contains("Datarecovery"))
			this.entityData.set(DATA_recovery, compound.getInt("Datarecovery"));
		if (compound.contains("DataClapTimer"))
			this.entityData.set(DATA_ClapTimer, compound.getInt("DataClapTimer"));
		if (compound.contains("DataComboTimer"))
			this.entityData.set(DATA_ComboTimer, compound.getInt("DataComboTimer"));
		if (compound.contains("DataComboAmount"))
			this.entityData.set(DATA_ComboAmount, compound.getInt("DataComboAmount"));
		if (compound.contains("DataSlamMeter"))
			this.entityData.set(DATA_SlamMeter, compound.getInt("DataSlamMeter"));
		if (compound.contains("DataHitMeterRecovery"))
			this.entityData.set(DATA_HitMeterRecovery, compound.getInt("DataHitMeterRecovery"));
		if (compound.contains("DataSlamming"))
			this.entityData.set(DATA_Slamming, compound.getBoolean("DataSlamming"));
		if (compound.contains("DataSlamIA"))
			this.entityData.set(DATA_SlamIA, compound.getInt("DataSlamIA"));
		if (compound.contains("DataSlamLogic"))
			this.entityData.set(DATA_SlamLogic, compound.getBoolean("DataSlamLogic"));
		if (compound.contains("DataChopTimer"))
			this.entityData.set(DATA_ChopTimer, compound.getInt("DataChopTimer"));
		if (compound.contains("Database"))
			this.entityData.set(DATA_base, compound.getInt("Database"));
		if (compound.contains("Datahair"))
			this.entityData.set(DATA_hair, compound.getInt("Datahair"));
		if (compound.contains("Datarank"))
			this.entityData.set(DATA_rank, compound.getInt("Datarank"));
		if (compound.contains("Datagender"))
			this.entityData.set(DATA_gender, compound.getInt("Datagender"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		ViltrumiteOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 1);
		builder = builder.add(Attributes.MAX_HEALTH, 50);
		builder = builder.add(Attributes.ARMOR, 10);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.2);
		return builder;
	}
}
