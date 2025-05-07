package net.mcreator.invincible_craft.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.invincible_craft.entity.AnissaEntity;

public class AnissaModel extends GeoModel<AnissaEntity> {
	@Override
	public ResourceLocation getAnimationResource(AnissaEntity entity) {
		return new ResourceLocation("invincible_craft", "animations/anissa.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AnissaEntity entity) {
		return new ResourceLocation("invincible_craft", "geo/anissa.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AnissaEntity entity) {
		return new ResourceLocation("invincible_craft", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(AnissaEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("body");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
