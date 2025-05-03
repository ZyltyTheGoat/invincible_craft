package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.PortalDashPortalEntity;

public class PortalDashPortalModel extends GeoModel<PortalDashPortalEntity> {
	@Override
	public ResourceLocation getAnimationResource(PortalDashPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/dimensionaltravelportal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PortalDashPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/dimensionaltravelportal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PortalDashPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
