package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.TheGiantEntity;

public class TheGiantModel extends GeoModel<TheGiantEntity> {
	@Override
	public ResourceLocation getAnimationResource(TheGiantEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/red_giant.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TheGiantEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/red_giant.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TheGiantEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
