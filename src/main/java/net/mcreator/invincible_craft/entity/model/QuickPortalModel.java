package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.QuickPortalEntity;

public class QuickPortalModel extends GeoModel<QuickPortalEntity> {
	@Override
	public ResourceLocation getAnimationResource(QuickPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/dimensionaltravelportal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(QuickPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/dimensionaltravelportal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(QuickPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
