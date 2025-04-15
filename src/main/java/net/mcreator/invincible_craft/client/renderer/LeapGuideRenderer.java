
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.invincible_craft.entity.LeapGuideEntity;
import net.mcreator.invincible_craft.client.model.Modelguideentity;

public class LeapGuideRenderer extends MobRenderer<LeapGuideEntity, Modelguideentity<LeapGuideEntity>> {
	public LeapGuideRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelguideentity(context.bakeLayer(Modelguideentity.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(LeapGuideEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/inv.png");
	}
}
