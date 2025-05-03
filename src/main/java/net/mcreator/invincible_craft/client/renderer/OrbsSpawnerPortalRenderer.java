
package net.mcreator.invincible_craft.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.model.OrbsSpawnerPortalModel;
import net.mcreator.invincible_craft.entity.layer.OrbsSpawnerPortalLayer;
import net.mcreator.invincible_craft.entity.OrbsSpawnerPortalEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class OrbsSpawnerPortalRenderer extends GeoEntityRenderer<OrbsSpawnerPortalEntity> {
	public OrbsSpawnerPortalRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new OrbsSpawnerPortalModel());
		this.shadowRadius = 2f;
		this.addRenderLayer(new OrbsSpawnerPortalLayer(this));
	}

	@Override
	public RenderType getRenderType(OrbsSpawnerPortalEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, OrbsSpawnerPortalEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
