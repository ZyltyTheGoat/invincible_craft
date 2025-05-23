
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.ViltrumiteEntity;
import net.mcreator.invincible_craft.entity.ThraggExiledEntity;
import net.mcreator.invincible_craft.entity.TheGiantEntity;
import net.mcreator.invincible_craft.entity.SpyDroneOrbEntity;
import net.mcreator.invincible_craft.entity.SpikeBallEntityEntity;
import net.mcreator.invincible_craft.entity.RefugePortalEntity;
import net.mcreator.invincible_craft.entity.QuickPortalEntity;
import net.mcreator.invincible_craft.entity.PortalDashPortalEntity;
import net.mcreator.invincible_craft.entity.OrbsSpawnerPortalEntity;
import net.mcreator.invincible_craft.entity.OctobossEntity;
import net.mcreator.invincible_craft.entity.LucanEntity;
import net.mcreator.invincible_craft.entity.KreggEntity;
import net.mcreator.invincible_craft.entity.KillCannonEntity;
import net.mcreator.invincible_craft.entity.KillCannonBlastEntity;
import net.mcreator.invincible_craft.entity.FireballGlobalEntity;
import net.mcreator.invincible_craft.entity.ExchangeCloneEntity;
import net.mcreator.invincible_craft.entity.DuplicationCloneEntity;
import net.mcreator.invincible_craft.entity.ConquestEntity;
import net.mcreator.invincible_craft.entity.CastIronEntity;
import net.mcreator.invincible_craft.entity.BanishmentPortalEntity;
import net.mcreator.invincible_craft.entity.BanditEntity;
import net.mcreator.invincible_craft.entity.AtomicbBlastEntity;
import net.mcreator.invincible_craft.entity.AtomEveConstructTridentProjectileEntity;
import net.mcreator.invincible_craft.entity.AtomEveAwakeningEntity;
import net.mcreator.invincible_craft.entity.ArtRosenbaumEntity;
import net.mcreator.invincible_craft.entity.AnissaEntity;
import net.mcreator.invincible_craft.InvincibleCraftMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvincibleCraftModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, InvincibleCraftMod.MODID);
	public static final RegistryObject<EntityType<QuickPortalEntity>> QUICK_PORTAL = register("quick_portal", EntityType.Builder.<QuickPortalEntity>of(QuickPortalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(QuickPortalEntity::new).fireImmune().sized(2f, 2f));
	public static final RegistryObject<EntityType<BanishmentPortalEntity>> BANISHMENT_PORTAL = register("banishment_portal", EntityType.Builder.<BanishmentPortalEntity>of(BanishmentPortalEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BanishmentPortalEntity::new).fireImmune().sized(2f, 2f));
	public static final RegistryObject<EntityType<PortalDashPortalEntity>> PORTAL_DASH_PORTAL = register("portal_dash_portal", EntityType.Builder.<PortalDashPortalEntity>of(PortalDashPortalEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PortalDashPortalEntity::new).fireImmune().sized(2f, 2f));
	public static final RegistryObject<EntityType<RefugePortalEntity>> REFUGE_PORTAL = register("refuge_portal", EntityType.Builder.<RefugePortalEntity>of(RefugePortalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RefugePortalEntity::new).fireImmune().sized(2f, 2f));
	public static final RegistryObject<EntityType<ViltrumiteEntity>> VILTRUMITE = register("viltrumite",
			EntityType.Builder.<ViltrumiteEntity>of(ViltrumiteEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(ViltrumiteEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<OrbsSpawnerPortalEntity>> ORBS_SPAWNER_PORTAL = register("orbs_spawner_portal", EntityType.Builder.<OrbsSpawnerPortalEntity>of(OrbsSpawnerPortalEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(OrbsSpawnerPortalEntity::new).fireImmune().sized(2f, 2f));
	public static final RegistryObject<EntityType<SpyDroneOrbEntity>> SPY_DRONE_ORB = register("spy_drone_orb",
			EntityType.Builder.<SpyDroneOrbEntity>of(SpyDroneOrbEntity::new, MobCategory.MISC).setCustomClientFactory(SpyDroneOrbEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ArtRosenbaumEntity>> ART_ROSENBAUM = register("art_rosenbaum",
			EntityType.Builder.<ArtRosenbaumEntity>of(ArtRosenbaumEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ArtRosenbaumEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<BanditEntity>> BANDIT = register("bandit",
			EntityType.Builder.<BanditEntity>of(BanditEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(BanditEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<DuplicationCloneEntity>> DUPLICATION_CLONE = register("duplication_clone",
			EntityType.Builder.<DuplicationCloneEntity>of(DuplicationCloneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DuplicationCloneEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ExchangeCloneEntity>> EXCHANGE_CLONE = register("exchange_clone",
			EntityType.Builder.<ExchangeCloneEntity>of(ExchangeCloneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ExchangeCloneEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<TheGiantEntity>> THE_GIANT = register("the_giant",
			EntityType.Builder.<TheGiantEntity>of(TheGiantEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TheGiantEntity::new)

					.sized(3f, 7.5f));
	public static final RegistryObject<EntityType<SpikeBallEntityEntity>> SPIKE_BALL_ENTITY = register("spike_ball_entity", EntityType.Builder.<SpikeBallEntityEntity>of(SpikeBallEntityEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SpikeBallEntityEntity::new).fireImmune().sized(2.5f, 2.5f));
	public static final RegistryObject<EntityType<AtomEveConstructTridentProjectileEntity>> ATOM_EVE_CONSTRUCT_TRIDENT_PROJECTILE = register("atom_eve_construct_trident_projectile",
			EntityType.Builder.<AtomEveConstructTridentProjectileEntity>of(AtomEveConstructTridentProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(AtomEveConstructTridentProjectileEntity::new).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).sized(0.2f, 1f));
	public static final RegistryObject<EntityType<AtomicbBlastEntity>> ATOMICB_BLAST = register("atomicb_blast", EntityType.Builder.<AtomicbBlastEntity>of(AtomicbBlastEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AtomicbBlastEntity::new).fireImmune().sized(0.2f, 0.2f));
	public static final RegistryObject<EntityType<AtomEveAwakeningEntity>> ATOM_EVE_AWAKENING = register("atom_eve_awakening", EntityType.Builder.<AtomEveAwakeningEntity>of(AtomEveAwakeningEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AtomEveAwakeningEntity::new).fireImmune().sized(0.1f, 0.1f));
	public static final RegistryObject<EntityType<KillCannonEntity>> KILL_CANNON = register("kill_cannon",
			EntityType.Builder.<KillCannonEntity>of(KillCannonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KillCannonEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<KillCannonBlastEntity>> KILL_CANNON_BLAST = register("kill_cannon_blast", EntityType.Builder.<KillCannonBlastEntity>of(KillCannonBlastEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KillCannonBlastEntity::new).fireImmune().sized(0.2f, 0.2f));
	public static final RegistryObject<EntityType<FireballGlobalEntity>> FIREBALL_GLOBAL = register("fireball_global",
			EntityType.Builder.<FireballGlobalEntity>of(FireballGlobalEntity::new, MobCategory.MISC).setCustomClientFactory(FireballGlobalEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<OctobossEntity>> OCTOBOSS = register("octoboss",
			EntityType.Builder.<OctobossEntity>of(OctobossEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(OctobossEntity::new)

					.sized(1.5f, 3f));
	public static final RegistryObject<EntityType<LucanEntity>> LUCAN = register("lucan",
			EntityType.Builder.<LucanEntity>of(LucanEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(LucanEntity::new)

					.sized(1f, 2.2f));
	public static final RegistryObject<EntityType<KreggEntity>> KREGG = register("kregg",
			EntityType.Builder.<KreggEntity>of(KreggEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(KreggEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<AnissaEntity>> ANISSA = register("anissa",
			EntityType.Builder.<AnissaEntity>of(AnissaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(AnissaEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ThraggExiledEntity>> THRAGG = register("thragg",
			EntityType.Builder.<ThraggExiledEntity>of(ThraggExiledEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(ThraggExiledEntity::new)

					.sized(1f, 2.2f));
	public static final RegistryObject<EntityType<ConquestEntity>> CONQUEST = register("conquest",
			EntityType.Builder.<ConquestEntity>of(ConquestEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(ConquestEntity::new)

					.sized(1f, 2.2f));
	public static final RegistryObject<EntityType<CastIronEntity>> CAST_IRON = register("cast_iron",
			EntityType.Builder.<CastIronEntity>of(CastIronEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(98).setUpdateInterval(3).setCustomClientFactory(CastIronEntity::new)

					.sized(0.8f, 2f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			QuickPortalEntity.init();
			BanishmentPortalEntity.init();
			PortalDashPortalEntity.init();
			RefugePortalEntity.init();
			ViltrumiteEntity.init();
			OrbsSpawnerPortalEntity.init();
			ArtRosenbaumEntity.init();
			BanditEntity.init();
			DuplicationCloneEntity.init();
			ExchangeCloneEntity.init();
			TheGiantEntity.init();
			SpikeBallEntityEntity.init();
			AtomicbBlastEntity.init();
			AtomEveAwakeningEntity.init();
			KillCannonEntity.init();
			KillCannonBlastEntity.init();
			OctobossEntity.init();
			LucanEntity.init();
			KreggEntity.init();
			AnissaEntity.init();
			ThraggExiledEntity.init();
			ConquestEntity.init();
			CastIronEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(QUICK_PORTAL.get(), QuickPortalEntity.createAttributes().build());
		event.put(BANISHMENT_PORTAL.get(), BanishmentPortalEntity.createAttributes().build());
		event.put(PORTAL_DASH_PORTAL.get(), PortalDashPortalEntity.createAttributes().build());
		event.put(REFUGE_PORTAL.get(), RefugePortalEntity.createAttributes().build());
		event.put(VILTRUMITE.get(), ViltrumiteEntity.createAttributes().build());
		event.put(ORBS_SPAWNER_PORTAL.get(), OrbsSpawnerPortalEntity.createAttributes().build());
		event.put(ART_ROSENBAUM.get(), ArtRosenbaumEntity.createAttributes().build());
		event.put(BANDIT.get(), BanditEntity.createAttributes().build());
		event.put(DUPLICATION_CLONE.get(), DuplicationCloneEntity.createAttributes().build());
		event.put(EXCHANGE_CLONE.get(), ExchangeCloneEntity.createAttributes().build());
		event.put(THE_GIANT.get(), TheGiantEntity.createAttributes().build());
		event.put(SPIKE_BALL_ENTITY.get(), SpikeBallEntityEntity.createAttributes().build());
		event.put(ATOMICB_BLAST.get(), AtomicbBlastEntity.createAttributes().build());
		event.put(ATOM_EVE_AWAKENING.get(), AtomEveAwakeningEntity.createAttributes().build());
		event.put(KILL_CANNON.get(), KillCannonEntity.createAttributes().build());
		event.put(KILL_CANNON_BLAST.get(), KillCannonBlastEntity.createAttributes().build());
		event.put(OCTOBOSS.get(), OctobossEntity.createAttributes().build());
		event.put(LUCAN.get(), LucanEntity.createAttributes().build());
		event.put(KREGG.get(), KreggEntity.createAttributes().build());
		event.put(ANISSA.get(), AnissaEntity.createAttributes().build());
		event.put(THRAGG.get(), ThraggExiledEntity.createAttributes().build());
		event.put(CONQUEST.get(), ConquestEntity.createAttributes().build());
		event.put(CAST_IRON.get(), CastIronEntity.createAttributes().build());
	}
}
