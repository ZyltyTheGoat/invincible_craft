
package net.mcreator.invincible_craft.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.model.AtomEveAwakeningModel;
import net.mcreator.invincible_craft.entity.layer.AtomEveAwakeningLayer;
import net.mcreator.invincible_craft.entity.AtomEveAwakeningEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class AtomEveAwakeningRenderer extends GeoEntityRenderer<AtomEveAwakeningEntity> {
	public AtomEveAwakeningRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AtomEveAwakeningModel());
		this.shadowRadius = 0f;
		this.addRenderLayer(new AtomEveAwakeningLayer(this));
	}

	@Override
	public RenderType getRenderType(AtomEveAwakeningEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, AtomEveAwakeningEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(AtomEveAwakeningEntity entityLivingBaseIn) {
		return 0.0F;
	}
}
