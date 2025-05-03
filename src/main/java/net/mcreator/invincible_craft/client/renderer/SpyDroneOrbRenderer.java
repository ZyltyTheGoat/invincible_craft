package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.SpyDroneOrbEntity;
import net.mcreator.invincible_craft.client.model.ModelDimensionalTravelProjectile1;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class SpyDroneOrbRenderer extends EntityRenderer<SpyDroneOrbEntity> {
	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/entities/dimensional_travel_projectile_texture.png");
	private final ModelDimensionalTravelProjectile1 model;

	public SpyDroneOrbRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new ModelDimensionalTravelProjectile1(context.bakeLayer(ModelDimensionalTravelProjectile1.LAYER_LOCATION));
	}

	@Override
	public void render(SpyDroneOrbEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(SpyDroneOrbEntity entity) {
		return texture;
	}
}
