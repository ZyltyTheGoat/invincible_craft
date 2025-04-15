package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.BanishmentPortalEntity;

public class BanishmentPortalModel extends GeoModel<BanishmentPortalEntity> {
	@Override
	public ResourceLocation getAnimationResource(BanishmentPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/dimensionaltravelportal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(BanishmentPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/dimensionaltravelportal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BanishmentPortalEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
