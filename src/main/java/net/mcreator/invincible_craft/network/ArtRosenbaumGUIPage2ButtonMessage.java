
package net.mcreator.invincible_craft.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.world.inventory.ArtRosenbaumGUIPage2Menu;
import net.mcreator.invincible_craft.procedures.PreviousPageProcedure;
import net.mcreator.invincible_craft.procedures.NextPageProcedure;
import net.mcreator.invincible_craft.procedures.ArtRosenbaumCraftOmniManSuitProcedure;
import net.mcreator.invincible_craft.procedures.ArtRosenbaumCraftDupliKateSuitProcedure;
import net.mcreator.invincible_craft.procedures.ArtRosenbaumCraftAtomEveSuitProcedure;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArtRosenbaumGUIPage2ButtonMessage {
	private final int buttonID, x, y, z;
	private HashMap<String, String> textstate;

	public ArtRosenbaumGUIPage2ButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public ArtRosenbaumGUIPage2ButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textstate = textstate;

	}

	public static void buffer(ArtRosenbaumGUIPage2ButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(ArtRosenbaumGUIPage2ButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			HashMap<String, String> textstate = message.textstate;
			handleButtonAction(entity, buttonID, x, y, z, textstate);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		Level world = entity.level();
		HashMap guistate = ArtRosenbaumGUIPage2Menu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			NextPageProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			PreviousPageProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ArtRosenbaumCraftOmniManSuitProcedure.execute(entity);
		}
		if (buttonID == 3) {

			ArtRosenbaumCraftDupliKateSuitProcedure.execute(entity);
		}
		if (buttonID == 4) {

			ArtRosenbaumCraftAtomEveSuitProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		InvincibleCraftMod.addNetworkMessage(ArtRosenbaumGUIPage2ButtonMessage.class, ArtRosenbaumGUIPage2ButtonMessage::buffer, ArtRosenbaumGUIPage2ButtonMessage::new, ArtRosenbaumGUIPage2ButtonMessage::handler);
	}

	public static void writeTextState(HashMap<String, String> map, FriendlyByteBuf buffer) {
		buffer.writeInt(map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			buffer.writeComponent(Component.literal(entry.getKey()));
			buffer.writeComponent(Component.literal(entry.getValue()));
		}
	}

	public static HashMap<String, String> readTextState(FriendlyByteBuf buffer) {
		int size = buffer.readInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = buffer.readComponent().getString();
			String value = buffer.readComponent().getString();
			map.put(key, value);
		}
		return map;
	}
}
