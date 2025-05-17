package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.ConquestEntity;

public class ConquestModel extends GeoModel<ConquestEntity> {
	@Override
	public ResourceLocation getAnimationResource(ConquestEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/conquest.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ConquestEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/conquest.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ConquestEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
