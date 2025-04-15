
package net.mcreator.invincible_craft.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.invincible_craft.entity.model.BanishmentPortalModel;
import net.mcreator.invincible_craft.entity.layer.BanishmentPortalLayer;
import net.mcreator.invincible_craft.entity.BanishmentPortalEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class BanishmentPortalRenderer extends GeoEntityRenderer<BanishmentPortalEntity> {
	public BanishmentPortalRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new BanishmentPortalModel());
		this.shadowRadius = 2f;
		this.addRenderLayer(new BanishmentPortalLayer(this));
	}

	@Override
	public RenderType getRenderType(BanishmentPortalEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, BanishmentPortalEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
