package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.AtomEveAwakeningEntity;

public class AtomEveAwakeningModel extends GeoModel<AtomEveAwakeningEntity> {
	@Override
	public ResourceLocation getAnimationResource(AtomEveAwakeningEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/atomeveawakeningmodel.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AtomEveAwakeningEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/atomeveawakeningmodel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AtomEveAwakeningEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
