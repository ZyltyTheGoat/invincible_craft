package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.AtomEveConstructTridentProjectileEntity;
import net.mcreator.invincible_craft.client.model.ModelAtomEveTridentConstruct;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class AtomEveConstructTridentProjectileRenderer extends EntityRenderer<AtomEveConstructTridentProjectileEntity> {
	private static final ResourceLocation texture = new ResourceLocation("invincible_craft:textures/entities/atom_eve_construct_trident.png");
	private final ModelAtomEveTridentConstruct model;

	public AtomEveConstructTridentProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new ModelAtomEveTridentConstruct(context.bakeLayer(ModelAtomEveTridentConstruct.LAYER_LOCATION));
	}

	@Override
	public void render(AtomEveConstructTridentProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(AtomEveConstructTridentProjectileEntity entity) {
		return texture;
	}
}
