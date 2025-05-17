
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
import net.mcreator.invincible_craft.client.model.ModelViltrumiteMale;
import net.mcreator.invincible_craft.client.model.ModelViltrumiteFemale;
import net.mcreator.invincible_craft.client.model.ModelLucan;
import net.mcreator.invincible_craft.client.model.ModelKillCannon;
import net.mcreator.invincible_craft.client.model.ModelInvincibleArmorModel;
import net.mcreator.invincible_craft.client.model.ModelInvincibleArmor;
import net.mcreator.invincible_craft.client.model.ModelDimensionalTravelProjectile1;
import net.mcreator.invincible_craft.client.model.ModelConstructionArmor;
import net.mcreator.invincible_craft.client.model.ModelCastIron;
import net.mcreator.invincible_craft.client.model.ModelAtomicBlastModel;
import net.mcreator.invincible_craft.client.model.ModelAtomicBlast;
import net.mcreator.invincible_craft.client.model.ModelAtomEveTridentConstruct;
import net.mcreator.invincible_craft.client.model.ModelAtomEveFlare;
import net.mcreator.invincible_craft.client.model.ModelAtomBlast;
import net.mcreator.invincible_craft.client.model.ModelAstronautHelmet;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class InvincibleCraftModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelInvincibleArmor.LAYER_LOCATION, ModelInvincibleArmor::createBodyLayer);
		event.registerLayerDefinition(ModelKillCannon.LAYER_LOCATION, ModelKillCannon::createBodyLayer);
		event.registerLayerDefinition(ModelInvincibleArmorModel.LAYER_LOCATION, ModelInvincibleArmorModel::createBodyLayer);
		event.registerLayerDefinition(ModelConstructionArmor.LAYER_LOCATION, ModelConstructionArmor::createBodyLayer);
		event.registerLayerDefinition(ModelViltrumiteMale.LAYER_LOCATION, ModelViltrumiteMale::createBodyLayer);
		event.registerLayerDefinition(ModelAtomEveFlare.LAYER_LOCATION, ModelAtomEveFlare::createBodyLayer);
		event.registerLayerDefinition(ModelLucan.LAYER_LOCATION, ModelLucan::createBodyLayer);
		event.registerLayerDefinition(ModelCastIron.LAYER_LOCATION, ModelCastIron::createBodyLayer);
		event.registerLayerDefinition(ModelAtomEveTridentConstruct.LAYER_LOCATION, ModelAtomEveTridentConstruct::createBodyLayer);
		event.registerLayerDefinition(Modelinv.LAYER_LOCATION, Modelinv::createBodyLayer);
		event.registerLayerDefinition(ModelDimensionalTravelProjectile1.LAYER_LOCATION, ModelDimensionalTravelProjectile1::createBodyLayer);
		event.registerLayerDefinition(ModelAtomicBlastModel.LAYER_LOCATION, ModelAtomicBlastModel::createBodyLayer);
		event.registerLayerDefinition(ModelAtomicBlast.LAYER_LOCATION, ModelAtomicBlast::createBodyLayer);
		event.registerLayerDefinition(ModelViltrumiteFemale.LAYER_LOCATION, ModelViltrumiteFemale::createBodyLayer);
		event.registerLayerDefinition(ModelAstronautHelmet.LAYER_LOCATION, ModelAstronautHelmet::createBodyLayer);
		event.registerLayerDefinition(Modelguideentity.LAYER_LOCATION, Modelguideentity::createBodyLayer);
		event.registerLayerDefinition(ModelAtomBlast.LAYER_LOCATION, ModelAtomBlast::createBodyLayer);
	}
}
