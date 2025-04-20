package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.SpikeBallEntityEntity;

public class SpikeBallEntityModel extends GeoModel<SpikeBallEntityEntity> {
	@Override
	public ResourceLocation getAnimationResource(SpikeBallEntityEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/atomevespikeball.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SpikeBallEntityEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/atomevespikeball.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SpikeBallEntityEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
