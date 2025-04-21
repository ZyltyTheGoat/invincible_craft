package net.mcreator.invincible_craft.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.function.Supplier;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvincibleCraftModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		InvincibleCraftMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.power = original.power;
			clone.flying_speed = original.flying_speed;
			clone.generated_structure = original.generated_structure;
			clone.arm1x = original.arm1x;
			clone.arm1y = original.arm1y;
			clone.arm1z = original.arm1z;
			clone.got_power = original.got_power;
			clone.refuge_x = original.refuge_x;
			clone.refuge_z = original.refuge_z;
			clone.age = original.age;
			clone.reputation = original.reputation;
			clone.moon_x = original.moon_x;
			clone.moon_y = original.moon_y;
			clone.moon_z = original.moon_z;
			clone.stat_strength = original.stat_strength;
			clone.stat_speed = original.stat_speed;
			clone.stat_vitality = original.stat_vitality;
			clone.stat_intelligence = original.stat_intelligence;
			clone.xp = original.xp;
			clone.stat_points = original.stat_points;
			clone.level = original.level;
			clone.adj_speed = original.adj_speed;
			clone.adj_flight = original.adj_flight;
			clone.adj_strength = original.adj_strength;
			clone.clone_limit = original.clone_limit;
			clone.duplication_max = original.duplication_max;
			clone.awakening_helmet_old = original.awakening_helmet_old;
			clone.awakening_chest_old = original.awakening_chest_old;
			clone.awakening_leggings_old = original.awakening_leggings_old;
			clone.awakening_boot_old = original.awakening_boot_old;
			if (!event.isWasDeath()) {
				clone.flying = original.flying;
				clone.current_speed = original.current_speed;
				clone.boost_timer = original.boost_timer;
				clone.boost_cooldown = original.boost_cooldown;
				clone.banishment_x = original.banishment_x;
				clone.banishment_y = original.banishment_y;
				clone.banishment_z = original.banishment_z;
				clone.disable_fall = original.disable_fall;
				clone.refuge_old_x = original.refuge_old_x;
				clone.refuge_old_y = original.refuge_old_y;
				clone.refuge_old_z = original.refuge_old_z;
				clone.impact_frame_timer = original.impact_frame_timer;
				clone.ability_cooldown_1 = original.ability_cooldown_1;
				clone.ability_cooldown_2 = original.ability_cooldown_2;
				clone.ability_cooldown_3 = original.ability_cooldown_3;
				clone.ability_cooldown_4 = original.ability_cooldown_4;
				clone.ability_cooldown_5 = original.ability_cooldown_5;
				clone.flying_speed_changed = original.flying_speed_changed;
				clone.oxygen_percentage = original.oxygen_percentage;
				clone.oxygen_meter = original.oxygen_meter;
				clone.punch_limit = original.punch_limit;
				clone.duplication_percentage = original.duplication_percentage;
				clone.duplication_meter = original.duplication_meter;
				clone.clone_list = original.clone_list;
				clone.displacemet_targetting = original.displacemet_targetting;
				clone.displacement_target = original.displacement_target;
				clone.exchange_target = original.exchange_target;
				clone.exchange_targetting = original.exchange_targetting;
				clone.holding = original.holding;
				clone.holding_entity = original.holding_entity;
				clone.clone_number = original.clone_number;
				clone.pierce_timer = original.pierce_timer;
				clone.pierceX = original.pierceX;
				clone.pierceY = original.pierceY;
				clone.pierceZ = original.pierceZ;
				clone.atom_eve_atomic_bubble = original.atom_eve_atomic_bubble;
				clone.atom_eve_atomic_bubble_timer = original.atom_eve_atomic_bubble_timer;
				clone.atom_eve_atomic_bubble_damage = original.atom_eve_atomic_bubble_damage;
				clone.atom_eve_air_density = original.atom_eve_air_density;
				clone.atom_eve_atomic_ray = original.atom_eve_atomic_ray;
				clone.battle_beast_blood_hunt = original.battle_beast_blood_hunt;
				clone.battle_beast_roar = original.battle_beast_roar;
				clone.battle_beast_bite_timer = original.battle_beast_bite_timer;
				clone.atom_eve_flare_rotation_left = original.atom_eve_flare_rotation_left;
				clone.atom_eve_flare_rotation_right = original.atom_eve_flare_rotation_right;
				clone.art_page = original.art_page;
				clone.atom_eve_spike_ball_targetting = original.atom_eve_spike_ball_targetting;
				clone.atom_eve_spike_ball_target = original.atom_eve_spike_ball_target;
				clone.atom_eve_atomic_blast_holding = original.atom_eve_atomic_blast_holding;
				clone.atom_eve_atomic_blast_scale = original.atom_eve_atomic_blast_scale;
				clone.atom_eve_awakened_timer = original.atom_eve_awakened_timer;
				clone.atom_eve_glitch_1 = original.atom_eve_glitch_1;
				clone.atom_eve_glitch_2 = original.atom_eve_glitch_2;
				clone.atom_eve_glitch_3 = original.atom_eve_glitch_3;
			}
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("invincible_craft", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public String power = "";
		public boolean flying = false;
		public double flying_speed = 1.0;
		public double current_speed = 0;
		public double boost_timer = 0;
		public double boost_cooldown = 0;
		public double banishment_x = 0;
		public double banishment_y = 0;
		public double banishment_z = 0;
		public boolean disable_fall = false;
		public boolean generated_structure = false;
		public double refuge_old_x = 0;
		public double refuge_old_y = 0;
		public double refuge_old_z = 0;
		public double impact_frame_timer = 0;
		public double arm1x = 0;
		public double arm1y = 0;
		public double arm1z = 0;
		public double ability_cooldown_1 = 0;
		public double ability_cooldown_2 = 0;
		public double ability_cooldown_3 = 0;
		public double ability_cooldown_4 = 0;
		public double ability_cooldown_5 = 0;
		public boolean got_power = false;
		public double refuge_x = 0;
		public double refuge_z = 0;
		public double age = 1.0;
		public double flying_speed_changed = 0.0;
		public double reputation = 0;
		public double oxygen_percentage = 100.0;
		public double oxygen_meter = 0;
		public double moon_x = 0;
		public double moon_y = 0;
		public double moon_z = 0;
		public double punch_limit = 0;
		public double duplication_percentage = 100.0;
		public double duplication_meter = 0;
		public String clone_list = "\"\"";
		public double stat_strength = 0;
		public double stat_speed = 0;
		public double stat_vitality = 0;
		public double stat_intelligence = 0;
		public double xp = 0;
		public double stat_points = 0;
		public double level = 0;
		public boolean displacemet_targetting = false;
		public String displacement_target = "\"\"";
		public String exchange_target = "";
		public boolean exchange_targetting = false;
		public boolean holding = false;
		public String holding_entity = "\"\"";
		public double adj_speed = 100.0;
		public double adj_flight = 100.0;
		public double adj_strength = 100.0;
		public double clone_limit = 3.0;
		public double clone_number = 0;
		public double duplication_max = 100.0;
		public double pierce_timer = 0;
		public double pierceX = 0;
		public double pierceY = 0;
		public double pierceZ = 0;
		public boolean atom_eve_atomic_bubble = false;
		public double atom_eve_atomic_bubble_timer = 0;
		public double atom_eve_atomic_bubble_damage = 0;
		public boolean atom_eve_air_density = false;
		public boolean atom_eve_atomic_ray = false;
		public double battle_beast_blood_hunt = 0;
		public double battle_beast_roar = 0;
		public double battle_beast_bite_timer = 0;
		public double atom_eve_flare_rotation_left = 0;
		public double atom_eve_flare_rotation_right = 0;
		public double art_page = 0;
		public boolean atom_eve_spike_ball_targetting = false;
		public String atom_eve_spike_ball_target = "\"\"";
		public boolean atom_eve_atomic_blast_holding = false;
		public double atom_eve_atomic_blast_scale = 0;
		public double atom_eve_awakened_timer = 0;
		public boolean atom_eve_glitch_1 = false;
		public boolean atom_eve_glitch_2 = false;
		public boolean atom_eve_glitch_3 = false;
		public ItemStack awakening_helmet_old = ItemStack.EMPTY;
		public ItemStack awakening_chest_old = ItemStack.EMPTY;
		public ItemStack awakening_leggings_old = ItemStack.EMPTY;
		public ItemStack awakening_boot_old = ItemStack.EMPTY;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				InvincibleCraftMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(entity.level()::dimension), new PlayerVariablesSyncMessage(this, entity.getId()));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("power", power);
			nbt.putBoolean("flying", flying);
			nbt.putDouble("flying_speed", flying_speed);
			nbt.putDouble("current_speed", current_speed);
			nbt.putDouble("boost_timer", boost_timer);
			nbt.putDouble("boost_cooldown", boost_cooldown);
			nbt.putDouble("banishment_x", banishment_x);
			nbt.putDouble("banishment_y", banishment_y);
			nbt.putDouble("banishment_z", banishment_z);
			nbt.putBoolean("disable_fall", disable_fall);
			nbt.putBoolean("generated_structure", generated_structure);
			nbt.putDouble("refuge_old_x", refuge_old_x);
			nbt.putDouble("refuge_old_y", refuge_old_y);
			nbt.putDouble("refuge_old_z", refuge_old_z);
			nbt.putDouble("impact_frame_timer", impact_frame_timer);
			nbt.putDouble("arm1x", arm1x);
			nbt.putDouble("arm1y", arm1y);
			nbt.putDouble("arm1z", arm1z);
			nbt.putDouble("ability_cooldown_1", ability_cooldown_1);
			nbt.putDouble("ability_cooldown_2", ability_cooldown_2);
			nbt.putDouble("ability_cooldown_3", ability_cooldown_3);
			nbt.putDouble("ability_cooldown_4", ability_cooldown_4);
			nbt.putDouble("ability_cooldown_5", ability_cooldown_5);
			nbt.putBoolean("got_power", got_power);
			nbt.putDouble("refuge_x", refuge_x);
			nbt.putDouble("refuge_z", refuge_z);
			nbt.putDouble("age", age);
			nbt.putDouble("flying_speed_changed", flying_speed_changed);
			nbt.putDouble("reputation", reputation);
			nbt.putDouble("oxygen_percentage", oxygen_percentage);
			nbt.putDouble("oxygen_meter", oxygen_meter);
			nbt.putDouble("moon_x", moon_x);
			nbt.putDouble("moon_y", moon_y);
			nbt.putDouble("moon_z", moon_z);
			nbt.putDouble("punch_limit", punch_limit);
			nbt.putDouble("duplication_percentage", duplication_percentage);
			nbt.putDouble("duplication_meter", duplication_meter);
			nbt.putString("clone_list", clone_list);
			nbt.putDouble("stat_strength", stat_strength);
			nbt.putDouble("stat_speed", stat_speed);
			nbt.putDouble("stat_vitality", stat_vitality);
			nbt.putDouble("stat_intelligence", stat_intelligence);
			nbt.putDouble("xp", xp);
			nbt.putDouble("stat_points", stat_points);
			nbt.putDouble("level", level);
			nbt.putBoolean("displacemet_targetting", displacemet_targetting);
			nbt.putString("displacement_target", displacement_target);
			nbt.putString("exchange_target", exchange_target);
			nbt.putBoolean("exchange_targetting", exchange_targetting);
			nbt.putBoolean("holding", holding);
			nbt.putString("holding_entity", holding_entity);
			nbt.putDouble("adj_speed", adj_speed);
			nbt.putDouble("adj_flight", adj_flight);
			nbt.putDouble("adj_strength", adj_strength);
			nbt.putDouble("clone_limit", clone_limit);
			nbt.putDouble("clone_number", clone_number);
			nbt.putDouble("duplication_max", duplication_max);
			nbt.putDouble("pierce_timer", pierce_timer);
			nbt.putDouble("pierceX", pierceX);
			nbt.putDouble("pierceY", pierceY);
			nbt.putDouble("pierceZ", pierceZ);
			nbt.putBoolean("atom_eve_atomic_bubble", atom_eve_atomic_bubble);
			nbt.putDouble("atom_eve_atomic_bubble_timer", atom_eve_atomic_bubble_timer);
			nbt.putDouble("atom_eve_atomic_bubble_damage", atom_eve_atomic_bubble_damage);
			nbt.putBoolean("atom_eve_air_density", atom_eve_air_density);
			nbt.putBoolean("atom_eve_atomic_ray", atom_eve_atomic_ray);
			nbt.putDouble("battle_beast_blood_hunt", battle_beast_blood_hunt);
			nbt.putDouble("battle_beast_roar", battle_beast_roar);
			nbt.putDouble("battle_beast_bite_timer", battle_beast_bite_timer);
			nbt.putDouble("atom_eve_flare_rotation_left", atom_eve_flare_rotation_left);
			nbt.putDouble("atom_eve_flare_rotation_right", atom_eve_flare_rotation_right);
			nbt.putDouble("art_page", art_page);
			nbt.putBoolean("atom_eve_spike_ball_targetting", atom_eve_spike_ball_targetting);
			nbt.putString("atom_eve_spike_ball_target", atom_eve_spike_ball_target);
			nbt.putBoolean("atom_eve_atomic_blast_holding", atom_eve_atomic_blast_holding);
			nbt.putDouble("atom_eve_atomic_blast_scale", atom_eve_atomic_blast_scale);
			nbt.putDouble("atom_eve_awakened_timer", atom_eve_awakened_timer);
			nbt.putBoolean("atom_eve_glitch_1", atom_eve_glitch_1);
			nbt.putBoolean("atom_eve_glitch_2", atom_eve_glitch_2);
			nbt.putBoolean("atom_eve_glitch_3", atom_eve_glitch_3);
			nbt.put("awakening_helmet_old", awakening_helmet_old.save(new CompoundTag()));
			nbt.put("awakening_chest_old", awakening_chest_old.save(new CompoundTag()));
			nbt.put("awakening_leggings_old", awakening_leggings_old.save(new CompoundTag()));
			nbt.put("awakening_boot_old", awakening_boot_old.save(new CompoundTag()));
			return nbt;
		}

		public void readNBT(Tag tag) {
			if (tag == null) {
				tag = writeNBT();
			}
			CompoundTag nbt = (CompoundTag) tag;
			if (nbt == null) {
				nbt = (CompoundTag) writeNBT();
			}
			power = nbt.getString("power");
			flying = nbt.getBoolean("flying");
			flying_speed = nbt.getDouble("flying_speed");
			current_speed = nbt.getDouble("current_speed");
			boost_timer = nbt.getDouble("boost_timer");
			boost_cooldown = nbt.getDouble("boost_cooldown");
			banishment_x = nbt.getDouble("banishment_x");
			banishment_y = nbt.getDouble("banishment_y");
			banishment_z = nbt.getDouble("banishment_z");
			disable_fall = nbt.getBoolean("disable_fall");
			generated_structure = nbt.getBoolean("generated_structure");
			refuge_old_x = nbt.getDouble("refuge_old_x");
			refuge_old_y = nbt.getDouble("refuge_old_y");
			refuge_old_z = nbt.getDouble("refuge_old_z");
			impact_frame_timer = nbt.getDouble("impact_frame_timer");
			arm1x = nbt.getDouble("arm1x");
			arm1y = nbt.getDouble("arm1y");
			arm1z = nbt.getDouble("arm1z");
			ability_cooldown_1 = nbt.getDouble("ability_cooldown_1");
			ability_cooldown_2 = nbt.getDouble("ability_cooldown_2");
			ability_cooldown_3 = nbt.getDouble("ability_cooldown_3");
			ability_cooldown_4 = nbt.getDouble("ability_cooldown_4");
			ability_cooldown_5 = nbt.getDouble("ability_cooldown_5");
			got_power = nbt.getBoolean("got_power");
			refuge_x = nbt.getDouble("refuge_x");
			refuge_z = nbt.getDouble("refuge_z");
			age = nbt.getDouble("age");
			flying_speed_changed = nbt.getDouble("flying_speed_changed");
			reputation = nbt.getDouble("reputation");
			oxygen_percentage = nbt.getDouble("oxygen_percentage");
			oxygen_meter = nbt.getDouble("oxygen_meter");
			moon_x = nbt.getDouble("moon_x");
			moon_y = nbt.getDouble("moon_y");
			moon_z = nbt.getDouble("moon_z");
			punch_limit = nbt.getDouble("punch_limit");
			duplication_percentage = nbt.getDouble("duplication_percentage");
			duplication_meter = nbt.getDouble("duplication_meter");
			clone_list = nbt.getString("clone_list");
			stat_strength = nbt.getDouble("stat_strength");
			stat_speed = nbt.getDouble("stat_speed");
			stat_vitality = nbt.getDouble("stat_vitality");
			stat_intelligence = nbt.getDouble("stat_intelligence");
			xp = nbt.getDouble("xp");
			stat_points = nbt.getDouble("stat_points");
			level = nbt.getDouble("level");
			displacemet_targetting = nbt.getBoolean("displacemet_targetting");
			displacement_target = nbt.getString("displacement_target");
			exchange_target = nbt.getString("exchange_target");
			exchange_targetting = nbt.getBoolean("exchange_targetting");
			holding = nbt.getBoolean("holding");
			holding_entity = nbt.getString("holding_entity");
			adj_speed = nbt.getDouble("adj_speed");
			adj_flight = nbt.getDouble("adj_flight");
			adj_strength = nbt.getDouble("adj_strength");
			clone_limit = nbt.getDouble("clone_limit");
			clone_number = nbt.getDouble("clone_number");
			duplication_max = nbt.getDouble("duplication_max");
			pierce_timer = nbt.getDouble("pierce_timer");
			pierceX = nbt.getDouble("pierceX");
			pierceY = nbt.getDouble("pierceY");
			pierceZ = nbt.getDouble("pierceZ");
			atom_eve_atomic_bubble = nbt.getBoolean("atom_eve_atomic_bubble");
			atom_eve_atomic_bubble_timer = nbt.getDouble("atom_eve_atomic_bubble_timer");
			atom_eve_atomic_bubble_damage = nbt.getDouble("atom_eve_atomic_bubble_damage");
			atom_eve_air_density = nbt.getBoolean("atom_eve_air_density");
			atom_eve_atomic_ray = nbt.getBoolean("atom_eve_atomic_ray");
			battle_beast_blood_hunt = nbt.getDouble("battle_beast_blood_hunt");
			battle_beast_roar = nbt.getDouble("battle_beast_roar");
			battle_beast_bite_timer = nbt.getDouble("battle_beast_bite_timer");
			atom_eve_flare_rotation_left = nbt.getDouble("atom_eve_flare_rotation_left");
			atom_eve_flare_rotation_right = nbt.getDouble("atom_eve_flare_rotation_right");
			art_page = nbt.getDouble("art_page");
			atom_eve_spike_ball_targetting = nbt.getBoolean("atom_eve_spike_ball_targetting");
			atom_eve_spike_ball_target = nbt.getString("atom_eve_spike_ball_target");
			atom_eve_atomic_blast_holding = nbt.getBoolean("atom_eve_atomic_blast_holding");
			atom_eve_atomic_blast_scale = nbt.getDouble("atom_eve_atomic_blast_scale");
			atom_eve_awakened_timer = nbt.getDouble("atom_eve_awakened_timer");
			atom_eve_glitch_1 = nbt.getBoolean("atom_eve_glitch_1");
			atom_eve_glitch_2 = nbt.getBoolean("atom_eve_glitch_2");
			atom_eve_glitch_3 = nbt.getBoolean("atom_eve_glitch_3");
			awakening_helmet_old = ItemStack.of(nbt.getCompound("awakening_helmet_old"));
			awakening_chest_old = ItemStack.of(nbt.getCompound("awakening_chest_old"));
			awakening_leggings_old = ItemStack.of(nbt.getCompound("awakening_leggings_old"));
			awakening_boot_old = ItemStack.of(nbt.getCompound("awakening_boot_old"));
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		InvincibleCraftMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	public static class PlayerVariablesSyncMessage {
		private final int target;
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
			this.target = buffer.readInt();
		}

		public PlayerVariablesSyncMessage(PlayerVariables data, int entityid) {
			this.data = data;
			this.target = entityid;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
			buffer.writeInt(message.target);
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.level().getEntity(message.target).getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.power = message.data.power;
					variables.flying = message.data.flying;
					variables.flying_speed = message.data.flying_speed;
					variables.current_speed = message.data.current_speed;
					variables.boost_timer = message.data.boost_timer;
					variables.boost_cooldown = message.data.boost_cooldown;
					variables.banishment_x = message.data.banishment_x;
					variables.banishment_y = message.data.banishment_y;
					variables.banishment_z = message.data.banishment_z;
					variables.disable_fall = message.data.disable_fall;
					variables.generated_structure = message.data.generated_structure;
					variables.refuge_old_x = message.data.refuge_old_x;
					variables.refuge_old_y = message.data.refuge_old_y;
					variables.refuge_old_z = message.data.refuge_old_z;
					variables.impact_frame_timer = message.data.impact_frame_timer;
					variables.arm1x = message.data.arm1x;
					variables.arm1y = message.data.arm1y;
					variables.arm1z = message.data.arm1z;
					variables.ability_cooldown_1 = message.data.ability_cooldown_1;
					variables.ability_cooldown_2 = message.data.ability_cooldown_2;
					variables.ability_cooldown_3 = message.data.ability_cooldown_3;
					variables.ability_cooldown_4 = message.data.ability_cooldown_4;
					variables.ability_cooldown_5 = message.data.ability_cooldown_5;
					variables.got_power = message.data.got_power;
					variables.refuge_x = message.data.refuge_x;
					variables.refuge_z = message.data.refuge_z;
					variables.age = message.data.age;
					variables.flying_speed_changed = message.data.flying_speed_changed;
					variables.reputation = message.data.reputation;
					variables.oxygen_percentage = message.data.oxygen_percentage;
					variables.oxygen_meter = message.data.oxygen_meter;
					variables.moon_x = message.data.moon_x;
					variables.moon_y = message.data.moon_y;
					variables.moon_z = message.data.moon_z;
					variables.punch_limit = message.data.punch_limit;
					variables.duplication_percentage = message.data.duplication_percentage;
					variables.duplication_meter = message.data.duplication_meter;
					variables.clone_list = message.data.clone_list;
					variables.stat_strength = message.data.stat_strength;
					variables.stat_speed = message.data.stat_speed;
					variables.stat_vitality = message.data.stat_vitality;
					variables.stat_intelligence = message.data.stat_intelligence;
					variables.xp = message.data.xp;
					variables.stat_points = message.data.stat_points;
					variables.level = message.data.level;
					variables.displacemet_targetting = message.data.displacemet_targetting;
					variables.displacement_target = message.data.displacement_target;
					variables.exchange_target = message.data.exchange_target;
					variables.exchange_targetting = message.data.exchange_targetting;
					variables.holding = message.data.holding;
					variables.holding_entity = message.data.holding_entity;
					variables.adj_speed = message.data.adj_speed;
					variables.adj_flight = message.data.adj_flight;
					variables.adj_strength = message.data.adj_strength;
					variables.clone_limit = message.data.clone_limit;
					variables.clone_number = message.data.clone_number;
					variables.duplication_max = message.data.duplication_max;
					variables.pierce_timer = message.data.pierce_timer;
					variables.pierceX = message.data.pierceX;
					variables.pierceY = message.data.pierceY;
					variables.pierceZ = message.data.pierceZ;
					variables.atom_eve_atomic_bubble = message.data.atom_eve_atomic_bubble;
					variables.atom_eve_atomic_bubble_timer = message.data.atom_eve_atomic_bubble_timer;
					variables.atom_eve_atomic_bubble_damage = message.data.atom_eve_atomic_bubble_damage;
					variables.atom_eve_air_density = message.data.atom_eve_air_density;
					variables.atom_eve_atomic_ray = message.data.atom_eve_atomic_ray;
					variables.battle_beast_blood_hunt = message.data.battle_beast_blood_hunt;
					variables.battle_beast_roar = message.data.battle_beast_roar;
					variables.battle_beast_bite_timer = message.data.battle_beast_bite_timer;
					variables.atom_eve_flare_rotation_left = message.data.atom_eve_flare_rotation_left;
					variables.atom_eve_flare_rotation_right = message.data.atom_eve_flare_rotation_right;
					variables.art_page = message.data.art_page;
					variables.atom_eve_spike_ball_targetting = message.data.atom_eve_spike_ball_targetting;
					variables.atom_eve_spike_ball_target = message.data.atom_eve_spike_ball_target;
					variables.atom_eve_atomic_blast_holding = message.data.atom_eve_atomic_blast_holding;
					variables.atom_eve_atomic_blast_scale = message.data.atom_eve_atomic_blast_scale;
					variables.atom_eve_awakened_timer = message.data.atom_eve_awakened_timer;
					variables.atom_eve_glitch_1 = message.data.atom_eve_glitch_1;
					variables.atom_eve_glitch_2 = message.data.atom_eve_glitch_2;
					variables.atom_eve_glitch_3 = message.data.atom_eve_glitch_3;
					variables.awakening_helmet_old = message.data.awakening_helmet_old;
					variables.awakening_chest_old = message.data.awakening_chest_old;
					variables.awakening_leggings_old = message.data.awakening_leggings_old;
					variables.awakening_boot_old = message.data.awakening_boot_old;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
