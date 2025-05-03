package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.OrbsSpawnerPortalEntity;

public class OrbsSpawnerPortalModel extends GeoModel<OrbsSpawnerPortalEntity> {
	@Override
	public ResourceLocation getAnimationResource(OrbsSpawnerPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/dimensionaltravelportal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(OrbsSpawnerPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/dimensionaltravelportal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(OrbsSpawnerPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
