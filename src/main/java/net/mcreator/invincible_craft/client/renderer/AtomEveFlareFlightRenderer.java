
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.invincible_craft.entity.AtomEveFlareFlightEntity;
import net.mcreator.invincible_craft.client.model.ModelAtomEveFlare;

public class AtomEveFlareFlightRenderer extends MobRenderer<AtomEveFlareFlightEntity, ModelAtomEveFlare<AtomEveFlareFlightEntity>> {
	public AtomEveFlareFlightRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelAtomEveFlare(context.bakeLayer(ModelAtomEveFlare.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(AtomEveFlareFlightEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/atom_eve_flare.png");
	}
}
