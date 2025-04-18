
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.invincible_craft.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.invincible_craft.client.model.Modelinv;
import net.mcreator.invincible_craft.client.model.Modelguideentity;
import net.mcreator.invincible_craft.client.model.ModelInvincibleArmorModel;
import net.mcreator.invincible_craft.client.model.ModelInvincibleArmor;
import net.mcreator.invincible_craft.client.model.ModelDimensionalTravelProjectile1;
import net.mcreator.invincible_craft.client.model.ModelAtomEveFlare;
import net.mcreator.invincible_craft.client.model.ModelAtomBlast;
import net.mcreator.invincible_craft.client.model.ModelAstronautHelmet;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class InvincibleCraftModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelInvincibleArmor.LAYER_LOCATION, ModelInvincibleArmor::createBodyLayer);
		event.registerLayerDefinition(ModelAstronautHelmet.LAYER_LOCATION, ModelAstronautHelmet::createBodyLayer);
		event.registerLayerDefinition(Modelinv.LAYER_LOCATION, Modelinv::createBodyLayer);
		event.registerLayerDefinition(Modelguideentity.LAYER_LOCATION, Modelguideentity::createBodyLayer);
		event.registerLayerDefinition(ModelDimensionalTravelProjectile1.LAYER_LOCATION, ModelDimensionalTravelProjectile1::createBodyLayer);
		event.registerLayerDefinition(ModelAtomBlast.LAYER_LOCATION, ModelAtomBlast::createBodyLayer);
		event.registerLayerDefinition(ModelAtomEveFlare.LAYER_LOCATION, ModelAtomEveFlare::createBodyLayer);
		event.registerLayerDefinition(ModelInvincibleArmorModel.LAYER_LOCATION, ModelInvincibleArmorModel::createBodyLayer);
	}
}
