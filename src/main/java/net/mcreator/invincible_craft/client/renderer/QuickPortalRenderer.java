
package net.mcreator.invincible_craft.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.model.QuickPortalModel;
import net.mcreator.invincible_craft.entity.layer.QuickPortalLayer;
import net.mcreator.invincible_craft.entity.QuickPortalEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class QuickPortalRenderer extends GeoEntityRenderer<QuickPortalEntity> {
	public QuickPortalRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new QuickPortalModel());
		this.shadowRadius = 2f;
		this.addRenderLayer(new QuickPortalLayer(this));
	}

	@Override
	public RenderType getRenderType(QuickPortalEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, QuickPortalEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
