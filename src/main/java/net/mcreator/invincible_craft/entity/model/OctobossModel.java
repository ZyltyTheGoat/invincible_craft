package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.OctobossEntity;

public class OctobossModel extends GeoModel<OctobossEntity> {
	@Override
	public ResourceLocation getAnimationResource(OctobossEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/octoboss.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(OctobossEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/octoboss.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(OctobossEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
