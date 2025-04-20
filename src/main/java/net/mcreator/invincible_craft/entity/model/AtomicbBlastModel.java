package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.AtomicbBlastEntity;

public class AtomicbBlastModel extends GeoModel<AtomicbBlastEntity> {
	@Override
	public ResourceLocation getAnimationResource(AtomicbBlastEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/atomicblastmodel.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AtomicbBlastEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/atomicblastmodel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AtomicbBlastEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
