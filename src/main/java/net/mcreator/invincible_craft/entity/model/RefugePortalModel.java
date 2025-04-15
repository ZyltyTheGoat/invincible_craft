package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.RefugePortalEntity;

public class RefugePortalModel extends GeoModel<RefugePortalEntity> {
	@Override
	public ResourceLocation getAnimationResource(RefugePortalEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/dimensionaltravelportal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RefugePortalEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/dimensionaltravelportal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RefugePortalEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
