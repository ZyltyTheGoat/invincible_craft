
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMustache6Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMustache5Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMustache4Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMustache3Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMustache2Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMustache1Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMouth2Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionMouth1Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionHair6Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionHair5Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionHair4Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionHair3Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionHair2Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionHair1Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionEyes6Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionEyes5Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionEyes4Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionEyes3Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionEyes2Procedure;
import net.mcreator.invincible_craft.procedures.ViltrumiteDisplayConditionEyes1Procedure;
import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ViltrumiteRenderer extends HumanoidMobRenderer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>> {
	public ViltrumiteRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionEyes1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionEyes2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionEyes3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionEyes4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionEyes5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionHair1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionHair2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionHair3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionHair4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionHair5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionHair6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mouth_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMouth1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mouth_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMouth2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mustache_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMustache1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mustache_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMustache2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mustache_3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMustache3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mustache_4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMustache4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mustache_5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMustache5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_mustache_6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionMustache6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, HumanoidModel<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ViltrumiteDisplayConditionEyes6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(ViltrumiteEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/base_1.png");
	}
}
