// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelViltrumiteMale<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "viltrumitemale"), "main");
	private final ModelPart bone;
	private final ModelPart Head2;
	private final ModelPart Body2;
	private final ModelPart RightArm2;
	private final ModelPart LeftArm2;
	private final ModelPart RightLeg2;
	private final ModelPart LeftLeg2;

	public ModelViltrumiteMale(ModelPart root) {
		this.bone = root.getChild("bone");
		this.Head2 = this.bone.getChild("Head2");
		this.Body2 = this.bone.getChild("Body2");
		this.RightArm2 = this.bone.getChild("RightArm2");
		this.LeftArm2 = this.bone.getChild("LeftArm2");
		this.RightLeg2 = this.bone.getChild("RightLeg2");
		this.LeftLeg2 = this.bone.getChild("LeftLeg2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Head2 = bone.addOrReplaceChild("Head2",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-3.2F, -4.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)).texOffs(32, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Body2 = bone.addOrReplaceChild("Body2",
				CubeListBuilder.create().texOffs(16, 16)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 32)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition BodyLayer_r1 = Body2.addOrReplaceChild("BodyLayer_r1",
				CubeListBuilder.create().texOffs(50, 57).addBox(-3.5F, -0.0436F, -0.001F, 7.0F, 7.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 12.0F, -2.2F, -0.1309F, 0.0F, 0.0F));

		PartDefinition BodyLayer_r2 = Body2.addOrReplaceChild("BodyLayer_r2",
				CubeListBuilder.create().texOffs(50, 57).mirror()
						.addBox(-3.5F, -0.0436F, -0.001F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 11.9F, 2.1F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RightArm2 = bone.addOrReplaceChild("RightArm2",
				CubeListBuilder.create().texOffs(40, 16)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(40, 32)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition LeftArm2 = bone.addOrReplaceChild("LeftArm2", CubeListBuilder.create().texOffs(40, 16).mirror()
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(40, 32)
				.mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false),
				PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition RightLeg2 = bone.addOrReplaceChild("RightLeg2",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 32)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition LeftLeg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create().texOffs(0, 16).mirror()
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 32)
				.mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false),
				PartPose.offset(1.9F, -12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.RightArm2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.LeftArm2.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.Head2.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head2.xRot = headPitch / (180F / (float) Math.PI);
		this.RightLeg2.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.LeftLeg2.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}