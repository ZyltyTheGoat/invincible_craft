package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.LucanEntity;

public class LucanModel extends GeoModel<LucanEntity> {
	@Override
	public ResourceLocation getAnimationResource(LucanEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/lucan.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(LucanEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/lucan.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(LucanEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
