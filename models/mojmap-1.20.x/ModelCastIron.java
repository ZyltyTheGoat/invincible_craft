// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelCastIron<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "castiron"), "main");
	private final ModelPart Brute;
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart bone;
	private final ModelPart Right_Arm;
	private final ModelPart Left_Arm;
	private final ModelPart Right_Leg;
	private final ModelPart Left_Leg;

	public ModelCastIron(ModelPart root) {
		this.Brute = root.getChild("Brute");
		this.Head = this.Brute.getChild("Head");
		this.Body = this.Brute.getChild("Body");
		this.bone = this.Body.getChild("bone");
		this.Right_Arm = this.Brute.getChild("Right_Arm");
		this.Left_Arm = this.Brute.getChild("Left_Arm");
		this.Right_Leg = this.Brute.getChild("Right_Leg");
		this.Left_Leg = this.Brute.getChild("Left_Leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Brute = partdefinition.addOrReplaceChild("Brute", CubeListBuilder.create(),
				PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Head = Brute.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(68, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition Body = Brute.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 75)
						.addBox(-5.0F, 6.0F, -2.0F, 10.0F, 8.0F, 5.0F, new CubeDeformation(0.4F)).texOffs(65, 18)
						.addBox(-5.0F, 6.0F, -2.0F, 10.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bone = Body.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(66, 46)
						.addBox(-6.5F, -18.0F, -2.5F, 11.0F, 6.0F, 6.0F, new CubeDeformation(0.2F)).texOffs(64, 31)
						.addBox(-6.6F, -18.0F, -2.5F, 11.0F, 6.0F, 6.0F, new CubeDeformation(0.4F)),
				PartPose.offset(1.0F, 18.0F, 0.0F));

		PartDefinition Right_Arm = Brute.addOrReplaceChild("Right_Arm",
				CubeListBuilder.create().texOffs(0, 17)
						.addBox(-3.5F, -1.5F, -2.5F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(17, 17)
						.addBox(-3.5F, -1.5F, -2.5F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(-6.0F, -6.0F, 1.0F));

		PartDefinition Left_Arm = Brute.addOrReplaceChild("Left_Arm",
				CubeListBuilder.create().texOffs(0, 17).mirror()
						.addBox(-0.5F, -1.5F, -2.5F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
						.texOffs(17, 17).mirror()
						.addBox(-0.5F, -1.5F, -2.5F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
				PartPose.offset(6.0F, -6.0F, 1.0F));

		PartDefinition Right_Leg = Brute.addOrReplaceChild("Right_Leg",
				CubeListBuilder.create().texOffs(0, 37)
						.addBox(-3.0F, -1.0F, -2.0F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(33, 16)
						.addBox(-3.0F, -1.0F, -2.0F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.25F)),
				PartPose.offset(-1.9F, 7.0F, 0.0F));

		PartDefinition Left_Leg = Brute.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(0, 37).mirror()
				.addBox(-2.0F, -1.0F, -2.0F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(33, 16)
				.mirror().addBox(-2.0F, -1.0F, -2.0F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.25F)).mirror(false),
				PartPose.offset(1.9F, 7.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 100, 100);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Brute.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.Right_Leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.Right_Arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.Left_Leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.Left_Arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}