
package net.mcreator.invincible_craft.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.procedures.CanAnissaFlyingAttackProcedure;
import net.mcreator.invincible_craft.procedures.AnissaOnEntityTickUpdateProcedure;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class AnissaEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Boolean> DATA_Flying = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> DATA_State = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_BarrageCooldown = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AttackDuration = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_GlobalAttackCooldown = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_UpslamCooldown = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_MeleeCooldown = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DownslamCooldown = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DashBarrage = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DashIndex = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_DashTick = SynchedEntityData.defineId(AnissaEntity.class, EntityDataSerializers.BOOLEAN);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.WHITE, ServerBossEvent.BossBarOverlay.PROGRESS);

	public AnissaEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvincibleCraftModEntities.ANISSA.get(), world);
	}

	public AnissaEntity(EntityType<AnissaEntity> type, Level world) {
		super(type, world);
		xpReward = 1000;
		setNoAi(false);
		setMaxUpStep(1.2f);
		setCustomName(Component.literal("Anissa"));
		setCustomNameVisible(true);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "anissa");
		this.entityData.define(DATA_Flying, false);
		this.entityData.define(DATA_State, "");
		this.entityData.define(DATA_BarrageCooldown, 0);
		this.entityData.define(DATA_AttackDuration, 0);
		this.entityData.define(DATA_GlobalAttackCooldown, 0);
		this.entityData.define(DATA_UpslamCooldown, 0);
		this.entityData.define(DATA_MeleeCooldown, 0);
		this.entityData.define(DATA_DownslamCooldown, 0);
		this.entityData.define(DATA_DashBarrage, 0);
		this.entityData.define(DATA_DashIndex, 0);
		this.entityData.define(DATA_DashTick, false);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, LivingEntity.class, false, false));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(4, new RandomStrollGoal(this, 15, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = AnissaEntity.this.getRandom();
				double dir_x = AnissaEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = AnissaEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = AnissaEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}

			@Override
			public boolean canUse() {
				double x = AnissaEntity.this.getX();
				double y = AnissaEntity.this.getY();
				double z = AnissaEntity.this.getZ();
				Entity entity = AnissaEntity.this;
				Level world = AnissaEntity.this.level();
				return super.canUse() && CanAnissaFlyingAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = AnissaEntity.this.getX();
				double y = AnissaEntity.this.getY();
				double z = AnissaEntity.this.getZ();
				Entity entity = AnissaEntity.this;
				Level world = AnissaEntity.this.level();
				return super.canContinueToUse() && CanAnissaFlyingAttackProcedure.execute(entity);
			}

		});
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(6, new FloatGoal(this));
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
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.FALL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putBoolean("DataFlying", this.entityData.get(DATA_Flying));
		compound.putString("DataState", this.entityData.get(DATA_State));
		compound.putInt("DataBarrageCooldown", this.entityData.get(DATA_BarrageCooldown));
		compound.putInt("DataAttackDuration", this.entityData.get(DATA_AttackDuration));
		compound.putInt("DataGlobalAttackCooldown", this.entityData.get(DATA_GlobalAttackCooldown));
		compound.putInt("DataUpslamCooldown", this.entityData.get(DATA_UpslamCooldown));
		compound.putInt("DataMeleeCooldown", this.entityData.get(DATA_MeleeCooldown));
		compound.putInt("DataDownslamCooldown", this.entityData.get(DATA_DownslamCooldown));
		compound.putInt("DataDashBarrage", this.entityData.get(DATA_DashBarrage));
		compound.putInt("DataDashIndex", this.entityData.get(DATA_DashIndex));
		compound.putBoolean("DataDashTick", this.entityData.get(DATA_DashTick));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.contains("DataFlying"))
			this.entityData.set(DATA_Flying, compound.getBoolean("DataFlying"));
		if (compound.contains("DataState"))
			this.entityData.set(DATA_State, compound.getString("DataState"));
		if (compound.contains("DataBarrageCooldown"))
			this.entityData.set(DATA_BarrageCooldown, compound.getInt("DataBarrageCooldown"));
		if (compound.contains("DataAttackDuration"))
			this.entityData.set(DATA_AttackDuration, compound.getInt("DataAttackDuration"));
		if (compound.contains("DataGlobalAttackCooldown"))
			this.entityData.set(DATA_GlobalAttackCooldown, compound.getInt("DataGlobalAttackCooldown"));
		if (compound.contains("DataUpslamCooldown"))
			this.entityData.set(DATA_UpslamCooldown, compound.getInt("DataUpslamCooldown"));
		if (compound.contains("DataMeleeCooldown"))
			this.entityData.set(DATA_MeleeCooldown, compound.getInt("DataMeleeCooldown"));
		if (compound.contains("DataDownslamCooldown"))
			this.entityData.set(DATA_DownslamCooldown, compound.getInt("DataDownslamCooldown"));
		if (compound.contains("DataDashBarrage"))
			this.entityData.set(DATA_DashBarrage, compound.getInt("DataDashBarrage"));
		if (compound.contains("DataDashIndex"))
			this.entityData.set(DATA_DashIndex, compound.getInt("DataDashIndex"));
		if (compound.contains("DataDashTick"))
			this.entityData.set(DATA_DashTick, compound.getBoolean("DataDashTick"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		AnissaOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
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
		builder = builder.add(Attributes.MOVEMENT_SPEED, 1);
		builder = builder.add(Attributes.MAX_HEALTH, 200);
		builder = builder.add(Attributes.ARMOR, 5);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 14);
		builder = builder.add(Attributes.FOLLOW_RANGE, 98);
		builder = builder.add(Attributes.FLYING_SPEED, 1);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			return event.setAndContinue(RawAnimation.begin().thenLoop("targeting"));
		}
		return PlayState.STOP;
	}

	String prevAnim = "empty";

	private PlayState procedurePredicate(AnimationState event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
			if (!this.animationprocedure.equals(prevAnim))
				event.getController().forceAnimationReset();
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			prevAnim = "empty";
			return PlayState.STOP;
		}
		prevAnim = this.animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(AnissaEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
