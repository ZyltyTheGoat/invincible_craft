package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.ThraggExiledEntity;

public class ThraggExiledModel extends GeoModel<ThraggExiledEntity> {
	@Override
	public ResourceLocation getAnimationResource(ThraggExiledEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/thraggexiled.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ThraggExiledEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/thraggexiled.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ThraggExiledEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
