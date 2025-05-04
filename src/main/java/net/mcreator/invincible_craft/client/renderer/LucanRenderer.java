
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.invincible_craft.entity.LucanEntity;
import net.mcreator.invincible_craft.client.model.ModelLucan;

public class LucanRenderer extends MobRenderer<LucanEntity, ModelLucan<LucanEntity>> {
	public LucanRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLucan(context.bakeLayer(ModelLucan.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(LucanEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/lucan.png");
	}
}
