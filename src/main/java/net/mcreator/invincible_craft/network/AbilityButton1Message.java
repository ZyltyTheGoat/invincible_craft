
package net.mcreator.invincible_craft.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.invincible_craft.procedures.AbilityButton1OnKeyReleasedProcedure;
import net.mcreator.invincible_craft.procedures.AbilityButton1OnKeyPressedProcedure;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbilityButton1Message {
	int type, pressedms;

	public AbilityButton1Message(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public AbilityButton1Message(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(AbilityButton1Message message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(AbilityButton1Message message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			AbilityButton1OnKeyPressedProcedure.execute(world, x, y, z, entity);
		}
		if (type == 1) {

			AbilityButton1OnKeyReleasedProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		InvincibleCraftMod.addNetworkMessage(AbilityButton1Message.class, AbilityButton1Message::buffer, AbilityButton1Message::new, AbilityButton1Message::handler);
	}
}
