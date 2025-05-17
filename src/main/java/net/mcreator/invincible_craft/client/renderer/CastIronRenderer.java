
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.invincible_craft.entity.CastIronEntity;
import net.mcreator.invincible_craft.client.model.ModelCastIron;

public class CastIronRenderer extends MobRenderer<CastIronEntity, ModelCastIron<CastIronEntity>> {
	public CastIronRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelCastIron(context.bakeLayer(ModelCastIron.LAYER_LOCATION)), 0.7f);
	}

	@Override
	public ResourceLocation getTextureLocation(CastIronEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/cast_iron.png");
	}
}
