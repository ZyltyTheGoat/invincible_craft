package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.KillCannonBlastEntity;

public class KillCannonBlastModel extends GeoModel<KillCannonBlastEntity> {
	@Override
	public ResourceLocation getAnimationResource(KillCannonBlastEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/killcannonblast.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(KillCannonBlastEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/killcannonblast.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(KillCannonBlastEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

}
