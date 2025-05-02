
package net.mcreator.invincible_craft.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.model.KillCannonBlastModel;
import net.mcreator.invincible_craft.entity.layer.KillCannonBlastLayer;
import net.mcreator.invincible_craft.entity.KillCannonBlastEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class KillCannonBlastRenderer extends GeoEntityRenderer<KillCannonBlastEntity> {
	public KillCannonBlastRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new KillCannonBlastModel());
		this.shadowRadius = 0f;
		this.addRenderLayer(new KillCannonBlastLayer(this));
	}

	@Override
	public RenderType getRenderType(KillCannonBlastEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, KillCannonBlastEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		float scale = 3f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
