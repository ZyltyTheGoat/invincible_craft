
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.invincible_craft.entity.KillCannonEntity;
import net.mcreator.invincible_craft.client.model.ModelKillCannon;

public class KillCannonRenderer extends MobRenderer<KillCannonEntity, ModelKillCannon<KillCannonEntity>> {
	public KillCannonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelKillCannon(context.bakeLayer(ModelKillCannon.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(KillCannonEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/killcannon.png");
	}
}
