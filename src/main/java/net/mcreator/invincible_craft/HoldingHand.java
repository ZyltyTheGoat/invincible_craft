package net.mcreator.invincible_craft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HoldingHand {
	//change both placeholders to the name of this script/custom element
	public HoldingHand() {
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onRenderHand(RenderHandEvent event) {
		var player = net.minecraft.client.Minecraft.getInstance().player;
		if (player == null) {
			return;
		}
		double MainHandXr = 15.0;
		double MainHandYr = 25.0;
		double MainHandZr = 15.0;
		//.
		double MainHandZp = 0.0;
		double MainHandXp = 0.0;
		double MainHandYp = 0.0;
		//.
		double OffHandZr = 0.0;
		double OffHandXr = 0.0;
		double OffHandYr = 0.0;
		double OffHandZp = 0.0;
		double OffHandXp = 0.0;
		double OffHandYp = 0.0;
		//dont touch here
		if (player.getPersistentData().getBoolean("Holding")) {
			PoseStack poseStack = event.getPoseStack();
			if (event.getHand() == net.minecraft.world.InteractionHand.MAIN_HAND) {
				poseStack.mulPose(Axis.ZP.rotationDegrees((float) MainHandZr));
				poseStack.mulPose(Axis.XP.rotationDegrees((float) MainHandXr));
				poseStack.mulPose(Axis.YP.rotationDegrees((float) MainHandYr));
				poseStack.translate((float) MainHandZp, (float) MainHandYp, (float) MainHandXp);
			} else if (event.getHand() == net.minecraft.world.InteractionHand.OFF_HAND) {
				poseStack.mulPose(Axis.ZP.rotationDegrees((float) OffHandZr));
				poseStack.mulPose(Axis.XP.rotationDegrees((float) OffHandXr));
				poseStack.mulPose(Axis.YP.rotationDegrees((float) OffHandYr));
				poseStack.translate((float) OffHandZp, (float) OffHandYp, (float) OffHandXp);
			}
		}
	}
}
