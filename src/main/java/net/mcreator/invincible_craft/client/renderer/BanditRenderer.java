
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

import net.mcreator.invincible_craft.procedures.BanditDisplayTop6Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayTop5Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayTop4Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayTop3Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayTop2Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayTop1Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayMask5Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayMask4Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayMask3Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayMask2Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayMask1Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayHair6Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayHair5Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayHair4Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayHair3Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayHair2Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayHair1Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBottom4Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBottom3Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBottom2Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBottom1Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBase3Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBase2Procedure;
import net.mcreator.invincible_craft.procedures.BanditDisplayBase1Procedure;
import net.mcreator.invincible_craft.entity.BanditEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class BanditRenderer extends HumanoidMobRenderer<BanditEntity, HumanoidModel<BanditEntity>> {
	public BanditRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/base_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBase1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/base_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBase2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/base_3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBase3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_eyes_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/bottom1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBottom1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/bottom2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBottom2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/bottom3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBottom3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/bottom4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayBottom4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/top1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayTop1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/top2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayTop2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/top3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayTop3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/top4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayTop4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/top5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayTop5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/top6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayTop6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayHair1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayHair2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayHair3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayHair4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_hair_5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayHair5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/mask1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayHair6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/mask2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayMask1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/mask2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayMask2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/mask3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayMask3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/mask4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayMask4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<BanditEntity, HumanoidModel<BanditEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/mask5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, BanditEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BanditDisplayMask5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(BanditEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/base_1.png");
	}
}
