package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.ArtRosenbaumEntity;

public class ArtRosenbaumModel extends GeoModel<ArtRosenbaumEntity> {
	@Override
	public ResourceLocation getAnimationResource(ArtRosenbaumEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/artrosenbaum.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ArtRosenbaumEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/artrosenbaum.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ArtRosenbaumEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
