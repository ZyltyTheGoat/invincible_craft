
package net.mcreator.invincible_craft.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
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

import net.mcreator.invincible_craft.procedures.LucanOnEntityTickUpdateProcedure;
import net.mcreator.invincible_craft.procedures.CanLucanFlyingAttackProcedure;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class LucanEntity extends Monster {
	public static final EntityDataAccessor<Boolean> DATA_Flying = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> DATA_State = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_SonicClapCooldown = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DownslamCooldown = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_BarrageCooldown = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_GlobalAttackCooldown = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AttackDuration = SynchedEntityData.defineId(LucanEntity.class, EntityDataSerializers.INT);
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.WHITE, ServerBossEvent.BossBarOverlay.PROGRESS);

	public LucanEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvincibleCraftModEntities.LUCAN.get(), world);
	}

	public LucanEntity(EntityType<LucanEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(1.2f);
		xpReward = 100;
		setNoAi(false);
		setCustomName(Component.literal("Lucan"));
		setCustomNameVisible(true);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_Flying, false);
		this.entityData.define(DATA_State, "");
		this.entityData.define(DATA_SonicClapCooldown, 0);
		this.entityData.define(DATA_DownslamCooldown, 0);
		this.entityData.define(DATA_BarrageCooldown, 0);
		this.entityData.define(DATA_GlobalAttackCooldown, 0);
		this.entityData.define(DATA_AttackDuration, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 9;
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, LivingEntity.class, false, false));
		this.goalSelector.addGoal(4, new RandomStrollGoal(this, 15, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = LucanEntity.this.getRandom();
				double dir_x = LucanEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = LucanEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = LucanEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}

			@Override
			public boolean canUse() {
				double x = LucanEntity.this.getX();
				double y = LucanEntity.this.getY();
				double z = LucanEntity.this.getZ();
				Entity entity = LucanEntity.this;
				Level world = LucanEntity.this.level();
				return super.canUse() && CanLucanFlyingAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = LucanEntity.this.getX();
				double y = LucanEntity.this.getY();
				double z = LucanEntity.this.getZ();
				Entity entity = LucanEntity.this;
				Level world = LucanEntity.this.level();
				return super.canContinueToUse() && CanLucanFlyingAttackProcedure.execute(entity);
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
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.FALL))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("DataFlying", this.entityData.get(DATA_Flying));
		compound.putString("DataState", this.entityData.get(DATA_State));
		compound.putInt("DataSonicClapCooldown", this.entityData.get(DATA_SonicClapCooldown));
		compound.putInt("DataDownslamCooldown", this.entityData.get(DATA_DownslamCooldown));
		compound.putInt("DataBarrageCooldown", this.entityData.get(DATA_BarrageCooldown));
		compound.putInt("DataGlobalAttackCooldown", this.entityData.get(DATA_GlobalAttackCooldown));
		compound.putInt("DataAttackDuration", this.entityData.get(DATA_AttackDuration));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataFlying"))
			this.entityData.set(DATA_Flying, compound.getBoolean("DataFlying"));
		if (compound.contains("DataState"))
			this.entityData.set(DATA_State, compound.getString("DataState"));
		if (compound.contains("DataSonicClapCooldown"))
			this.entityData.set(DATA_SonicClapCooldown, compound.getInt("DataSonicClapCooldown"));
		if (compound.contains("DataDownslamCooldown"))
			this.entityData.set(DATA_DownslamCooldown, compound.getInt("DataDownslamCooldown"));
		if (compound.contains("DataBarrageCooldown"))
			this.entityData.set(DATA_BarrageCooldown, compound.getInt("DataBarrageCooldown"));
		if (compound.contains("DataGlobalAttackCooldown"))
			this.entityData.set(DATA_GlobalAttackCooldown, compound.getInt("DataGlobalAttackCooldown"));
		if (compound.contains("DataAttackDuration"))
			this.entityData.set(DATA_AttackDuration, compound.getInt("DataAttackDuration"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		LucanOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
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

	public static void init() {
		SpawnPlacements.register(InvincibleCraftModEntities.LUCAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.75);
		builder = builder.add(Attributes.MAX_HEALTH, 200);
		builder = builder.add(Attributes.ARMOR, 5);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 15);
		builder = builder.add(Attributes.FOLLOW_RANGE, 98);
		return builder;
	}
}
