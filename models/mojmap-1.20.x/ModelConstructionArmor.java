// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelConstructionArmor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "constructionarmor"), "main");
	private final ModelPart bone;
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart RightBoot;
	private final ModelPart LeftLeg;
	private final ModelPart LeftBoot;

	public ModelConstructionArmor(ModelPart root) {
		this.bone = root.getChild("bone");
		this.Head = this.bone.getChild("Head");
		this.Body = this.bone.getChild("Body");
		this.RightArm = this.bone.getChild("RightArm");
		this.LeftArm = this.bone.getChild("LeftArm");
		this.RightLeg = this.bone.getChild("RightLeg");
		this.RightBoot = this.bone.getChild("RightBoot");
		this.LeftLeg = this.bone.getChild("LeftLeg");
		this.LeftBoot = this.bone.getChild("LeftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Head = bone.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)).texOffs(96, 21)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)),
				PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Body = bone.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(32, 0)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)).texOffs(77, 0)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(104, 0)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.75F)).texOffs(56, 0)
						.addBox(-2.0F, 5.4F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition RightArm = bone.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(73, 23)
						.addBox(-3.0F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(16, 48)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(57, 64)
						.addBox(-3.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).texOffs(0, 89)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition LeftArm = bone.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(73, 30)
				.addBox(-1.0F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(16, 56)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(57, 64).mirror()
				.addBox(-1.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false).texOffs(0, 89)
				.mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
				PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition RightLeg = bone.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(56, 38).addBox(-2.7351F, -0.6774F, -2.0F, 3.0F, 4.0F, 4.0F,
						new CubeDeformation(0.75F)),
				PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition RightBoot = bone.addOrReplaceChild("RightBoot",
				CubeListBuilder.create().texOffs(0, 48)
						.addBox(-2.0F, 3.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(0, 67)
						.addBox(-2.0F, 3.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition LeftLeg = bone.addOrReplaceChild("LeftLeg",
				CubeListBuilder.create().texOffs(56, 38).mirror()
						.addBox(-0.7096F, -0.7382F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false),
				PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition LeftBoot = bone.addOrReplaceChild("LeftBoot",
				CubeListBuilder.create().texOffs(48, 11)
						.addBox(-2.0F, 3.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(0, 67)
						.mirror().addBox(-2.0F, 3.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
				PartPose.offset(1.9F, -12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.LeftLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.LeftBoot.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.RightBoot.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}