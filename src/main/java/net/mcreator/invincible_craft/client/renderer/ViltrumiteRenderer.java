
package net.mcreator.invincible_craft.client.renderer;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair9Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair8Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair7Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair6Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair5Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair4Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair3Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair2Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair1Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair16Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair15Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair14Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair13Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair12Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair11Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteHair10Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase9Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase8Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase7Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase6Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase5Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase4Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase3Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase2Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase1Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase12Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase11Procedure;
import net.mcreator.invincible_craft.procedures.ReturnViltrumiteBase10Procedure;
import net.mcreator.invincible_craft.procedures.ReturnGenderProcedure;
import net.mcreator.invincible_craft.procedures.ReturnGenderMaleProcedure;
import net.mcreator.invincible_craft.entity.ViltrumiteEntity;
import net.mcreator.invincible_craft.client.model.Modelinv;
import net.mcreator.invincible_craft.client.model.ModelViltrumiteMale;
import net.mcreator.invincible_craft.client.model.ModelViltrumiteFemale;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ViltrumiteRenderer extends MobRenderer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>> {
	public ViltrumiteRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelinv(context.bakeLayer(Modelinv.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair3Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair4Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair7.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair5Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair6Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair7Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair8.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair8Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair9.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair9Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair10.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair10Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_hair11.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair11Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase7Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase8Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase9Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase10Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase11Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/biped_base6.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteBase12Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/female_hair_1.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair12Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/female_hair_2.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair13Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/female_hair_3.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair14Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/female_hair_4.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair15Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/female_hair_5.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnViltrumiteHair16Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_female_suit.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnGenderProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteFemale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteFemale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
		this.addLayer(new RenderLayer<ViltrumiteEntity, Modelinv<ViltrumiteEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("invincible_craft:textures/entities/viltrumite_male_suit.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ViltrumiteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (ReturnGenderMaleProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new ModelViltrumiteMale(Minecraft.getInstance().getEntityModels().bakeLayer(ModelViltrumiteMale.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(ViltrumiteEntity entity) {
		return new ResourceLocation("invincible_craft:textures/entities/inv.png");
	}
}
