package net.mcreator.invincible_craft.client.model;

import org.apache.http.impl.conn.Wire;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelKillCannon<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("invincible_craft", "model_kill_cannon"), "main");
	public final ModelPart bone;
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart LeftArm2;
	public final ModelPart Wire;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;

	public ModelKillCannon(ModelPart root) {
		this.bone = root.getChild("bone");
		this.Head = this.bone.getChild("Head");
		this.Body = this.bone.getChild("Body");
		this.RightArm = this.bone.getChild("RightArm");
		this.LeftArm = this.bone.getChild("LeftArm");
		this.LeftArm2 = this.LeftArm.getChild("LeftArm2");
		this.Wire = this.LeftArm2.getChild("Wire");
		this.RightLeg = this.bone.getChild("RightLeg");
		this.LeftLeg = this.bone.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition Head = bone.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(40, 9).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, -24.0F, 0.0F));
		PartDefinition Body = bone.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(48, 0)
				.addBox(-3.0F, 0.0F, 2.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));
		PartDefinition RightArm = bone.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(40, 32)
				.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(40, 33).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));
		PartDefinition LeftArm = bone.addOrReplaceChild(
				"LeftArm", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(42, 53).mirror()
						.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false).texOffs(28, 53).mirror().addBox(1.0F, -4.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
				PartPose.offset(5.0F, -22.0F, 0.0F));
		PartDefinition LeftArm2 = LeftArm.addOrReplaceChild("LeftArm2",
				CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.25F)).mirror(false).texOffs(16, 51).mirror()
						.addBox(-1.5F, 1.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(25, 0).mirror().addBox(-1.5F, 11.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.0F, 5.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition LeftArm_r1 = LeftArm2.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(28, 53).mirror().addBox(-1.0F, -4.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition Wire = LeftArm2.addOrReplaceChild("Wire", CubeListBuilder.create(), PartPose.offset(0.0F, -2.4F, 0.9F));
		PartDefinition LeftArm_r2 = Wire.addOrReplaceChild("LeftArm_r2", CubeListBuilder.create().texOffs(41, 35).mirror().addBox(8.0F, -4.0F, 3.0F, 0.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-8.0F, 1.0F, -4.0F, 0.1745F, 0.0F, 0.0F));
		PartDefinition RightLeg = bone.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-1.9F, -12.0F, 0.0F));
		PartDefinition LeftLeg = bone.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 32).mirror()
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.LeftLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}
