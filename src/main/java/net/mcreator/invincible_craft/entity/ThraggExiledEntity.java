
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

import net.mcreator.invincible_craft.procedures.ThraggOnEntityTickUpdateProcedure;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.SlowRotMoveControl;

public class ThraggExiledEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Boolean> DATA_Flying = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> DATA_State = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_SonicClapCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DownslamCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AttackDuration = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_GlobalAttackCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_UpslamCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_MeleeCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ChopCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ImpaleCooldown = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_moveX = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_moveY = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_moveZ = SynchedEntityData.defineId(ThraggExiledEntity.class, EntityDataSerializers.INT);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.WHITE, ServerBossEvent.BossBarOverlay.PROGRESS);

	public ThraggExiledEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvincibleCraftModEntities.THRAGG_EXILED.get(), world);
	}

	public ThraggExiledEntity(EntityType<ThraggExiledEntity> type, Level world) {
		super(type, world);
		xpReward = 1000;
		setNoAi(false);
		setMaxUpStep(1.2f);
		setCustomName(Component.literal("Thragg (Exiled)"));
		setCustomNameVisible(true);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
		this.moveControl = new SlowRotMoveControl(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "exiled_thragg");
		this.entityData.define(DATA_Flying, false);
		this.entityData.define(DATA_State, "");
		this.entityData.define(DATA_SonicClapCooldown, 0);
		this.entityData.define(DATA_DownslamCooldown, 0);
		this.entityData.define(DATA_AttackDuration, 0);
		this.entityData.define(DATA_GlobalAttackCooldown, 0);
		this.entityData.define(DATA_UpslamCooldown, 0);
		this.entityData.define(DATA_MeleeCooldown, 0);
		this.entityData.define(DATA_ChopCooldown, 0);
		this.entityData.define(DATA_ImpaleCooldown, 0);
		this.entityData.define(DATA_moveX, 0);
		this.entityData.define(DATA_moveY, 0);
		this.entityData.define(DATA_moveZ, 0);
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
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 3, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = ThraggExiledEntity.this.getRandom();
				double dir_x = ThraggExiledEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = ThraggExiledEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = ThraggExiledEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}
		});
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
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
		compound.putInt("DataSonicClapCooldown", this.entityData.get(DATA_SonicClapCooldown));
		compound.putInt("DataDownslamCooldown", this.entityData.get(DATA_DownslamCooldown));
		compound.putInt("DataAttackDuration", this.entityData.get(DATA_AttackDuration));
		compound.putInt("DataGlobalAttackCooldown", this.entityData.get(DATA_GlobalAttackCooldown));
		compound.putInt("DataUpslamCooldown", this.entityData.get(DATA_UpslamCooldown));
		compound.putInt("DataMeleeCooldown", this.entityData.get(DATA_MeleeCooldown));
		compound.putInt("DataChopCooldown", this.entityData.get(DATA_ChopCooldown));
		compound.putInt("DataImpaleCooldown", this.entityData.get(DATA_ImpaleCooldown));
		compound.putInt("DatamoveX", this.entityData.get(DATA_moveX));
		compound.putInt("DatamoveY", this.entityData.get(DATA_moveY));
		compound.putInt("DatamoveZ", this.entityData.get(DATA_moveZ));
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
		if (compound.contains("DataSonicClapCooldown"))
			this.entityData.set(DATA_SonicClapCooldown, compound.getInt("DataSonicClapCooldown"));
		if (compound.contains("DataDownslamCooldown"))
			this.entityData.set(DATA_DownslamCooldown, compound.getInt("DataDownslamCooldown"));
		if (compound.contains("DataAttackDuration"))
			this.entityData.set(DATA_AttackDuration, compound.getInt("DataAttackDuration"));
		if (compound.contains("DataGlobalAttackCooldown"))
			this.entityData.set(DATA_GlobalAttackCooldown, compound.getInt("DataGlobalAttackCooldown"));
		if (compound.contains("DataUpslamCooldown"))
			this.entityData.set(DATA_UpslamCooldown, compound.getInt("DataUpslamCooldown"));
		if (compound.contains("DataMeleeCooldown"))
			this.entityData.set(DATA_MeleeCooldown, compound.getInt("DataMeleeCooldown"));
		if (compound.contains("DataChopCooldown"))
			this.entityData.set(DATA_ChopCooldown, compound.getInt("DataChopCooldown"));
		if (compound.contains("DataImpaleCooldown"))
			this.entityData.set(DATA_ImpaleCooldown, compound.getInt("DataImpaleCooldown"));
		if (compound.contains("DatamoveX"))
			this.entityData.set(DATA_moveX, compound.getInt("DatamoveX"));
		if (compound.contains("DatamoveY"))
			this.entityData.set(DATA_moveY, compound.getInt("DatamoveY"));
		if (compound.contains("DatamoveZ"))
			this.entityData.set(DATA_moveZ, compound.getInt("DatamoveZ"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		ThraggOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
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
		builder = builder.add(Attributes.MOVEMENT_SPEED, 1.5);
		builder = builder.add(Attributes.MAX_HEALTH, 500);
		builder = builder.add(Attributes.ARMOR, 10);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 30);
		builder = builder.add(Attributes.FOLLOW_RANGE, 98);
		builder = builder.add(Attributes.FLYING_SPEED, 1.5);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if (this.isAggressive() && event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("targeting"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
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
			this.remove(ThraggExiledEntity.RemovalReason.KILLED);
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
