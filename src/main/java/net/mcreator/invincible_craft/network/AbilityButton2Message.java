
package net.mcreator.invincible_craft.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.invincible_craft.procedures.AbilityButton2OnKeyReleasedProcedure;
import net.mcreator.invincible_craft.procedures.AbilityButton2OnKeyPressedProcedure;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbilityButton2Message {
	int type, pressedms;

	public AbilityButton2Message(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public AbilityButton2Message(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(AbilityButton2Message message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(AbilityButton2Message message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			AbilityButton2OnKeyPressedProcedure.execute(world, entity);
		}
		if (type == 1) {

			AbilityButton2OnKeyReleasedProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		InvincibleCraftMod.addNetworkMessage(AbilityButton2Message.class, AbilityButton2Message::buffer, AbilityButton2Message::new, AbilityButton2Message::handler);
	}
}
