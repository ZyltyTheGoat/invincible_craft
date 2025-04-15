
package net.mcreator.invincible_craft.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
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
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.invincible_craft.procedures.SetupAnimationsProcedure;
import net.mcreator.invincible_craft.procedures.BanditOnInitialEntitySpawnProcedure;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

import javax.annotation.Nullable;

public class BanditEntity extends Monster {
	public static final EntityDataAccessor<Integer> DATA_hair = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_bottom = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_top = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_mask = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_base = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);

	public BanditEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvincibleCraftModEntities.BANDIT.get(), world);
	}

	public BanditEntity(EntityType<BanditEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(1.2f);
		xpReward = 5;
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
		this.entityData.define(DATA_hair, 0);
		this.entityData.define(DATA_bottom, 0);
		this.entityData.define(DATA_top, 0);
		this.entityData.define(DATA_mask, 0);
		this.entityData.define(DATA_base, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 2.25;
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Villager.class, false, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Player.class, false, false));
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
	public double getMyRidingOffset() {
		return -0.35D;
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

		SetupAnimationsProcedure.execute();
		if (damagesource.is(DamageTypes.FALL))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		BanditOnInitialEntitySpawnProcedure.execute(this);
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Datahair", this.entityData.get(DATA_hair));
		compound.putInt("Databottom", this.entityData.get(DATA_bottom));
		compound.putInt("Datatop", this.entityData.get(DATA_top));
		compound.putInt("Datamask", this.entityData.get(DATA_mask));
		compound.putInt("Database", this.entityData.get(DATA_base));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Datahair"))
			this.entityData.set(DATA_hair, compound.getInt("Datahair"));
		if (compound.contains("Databottom"))
			this.entityData.set(DATA_bottom, compound.getInt("Databottom"));
		if (compound.contains("Datatop"))
			this.entityData.set(DATA_top, compound.getInt("Datatop"));
		if (compound.contains("Datamask"))
			this.entityData.set(DATA_mask, compound.getInt("Datamask"));
		if (compound.contains("Database"))
			this.entityData.set(DATA_base, compound.getInt("Database"));
	}

	public static void init() {
		SpawnPlacements.register(InvincibleCraftModEntities.BANDIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
		builder = builder.add(Attributes.MAX_HEALTH, 35);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 4);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.2);
		return builder;
	}
}
