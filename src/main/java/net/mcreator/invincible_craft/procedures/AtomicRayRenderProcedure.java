package net.mcreator.invincible_craft.procedures;

import org.joml.Matrix4f;

import org.checkerframework.checker.units.qual.m;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.systems.RenderSystem;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class AtomicRayRenderProcedure {
	private static BufferBuilder bufferBuilder = null;
	private static VertexBuffer vertexBuffer = null;
	private static VertexFormat.Mode mode = null;
	private static VertexFormat format = null;
	private static PoseStack poseStack = null;
	private static Matrix4f projectionMatrix = null;
	private static boolean worldCoordinate = true;
	private static Vec3 offset = Vec3.ZERO;
	private static int currentStage = 0;
	private static int targetStage = 0; // NONE: 0, SKY: 1, WORLD: 2

	private static void add(double x, double y, double z, int color) {
		add(x, y, z, 0.0F, 0.0F, color);
	}

	private static void add(double x, double y, double z, float u, float v, int color) {
		if (bufferBuilder == null || !bufferBuilder.building())
			return;
		if (format == DefaultVertexFormat.POSITION_COLOR) {
			bufferBuilder.vertex(x, y, z).color(color).endVertex();
		} else if (format == DefaultVertexFormat.POSITION_TEX_COLOR) {
			bufferBuilder.vertex(x, y, z).uv(u, v).color(color).endVertex();
		}
	}

	private static boolean begin(VertexFormat.Mode mode, VertexFormat format, boolean update) {
		if (AtomicRayRenderProcedure.bufferBuilder == null || !AtomicRayRenderProcedure.bufferBuilder.building()) {
			if (update)
				clear();
			if (AtomicRayRenderProcedure.vertexBuffer == null) {
				if (format == DefaultVertexFormat.POSITION_COLOR) {
					AtomicRayRenderProcedure.mode = mode;
					AtomicRayRenderProcedure.format = format;
					AtomicRayRenderProcedure.bufferBuilder = Tesselator.getInstance().getBuilder();
					AtomicRayRenderProcedure.bufferBuilder.begin(mode, DefaultVertexFormat.POSITION_COLOR);
					return true;
				} else if (format == DefaultVertexFormat.POSITION_TEX_COLOR) {
					AtomicRayRenderProcedure.mode = mode;
					AtomicRayRenderProcedure.format = format;
					AtomicRayRenderProcedure.bufferBuilder = Tesselator.getInstance().getBuilder();
					AtomicRayRenderProcedure.bufferBuilder.begin(mode, DefaultVertexFormat.POSITION_TEX_COLOR);
					return true;
				}
			}
		}
		return false;
	}

	private static void clear() {
		if (vertexBuffer != null) {
			vertexBuffer.close();
			vertexBuffer = null;
		}
	}

	private static void end() {
		if (bufferBuilder == null || !bufferBuilder.building())
			return;
		if (vertexBuffer != null)
			vertexBuffer.close();
		vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
		vertexBuffer.bind();
		vertexBuffer.upload(bufferBuilder.end());
		VertexBuffer.unbind();
	}

	private static void offset(double x, double y, double z) {
		offset = new Vec3(x, y, z);
	}

	private static void release() {
		targetStage = 0;
	}

	private static VertexBuffer shape() {
		return vertexBuffer;
	}

	private static void system(boolean worldCoordinate) {
		AtomicRayRenderProcedure.worldCoordinate = worldCoordinate;
	}

	private static boolean target(int targetStage) {
		if (targetStage == currentStage) {
			AtomicRayRenderProcedure.targetStage = targetStage;
			return true;
		}
		return false;
	}

	private static void renderShape(VertexBuffer vertexBuffer, double x, double y, double z, float yaw, float pitch, float roll, float xScale, float yScale, float zScale, int color) {
		if (currentStage == 0 || currentStage != targetStage)
			return;
		if (poseStack == null || projectionMatrix == null)
			return;
		if (vertexBuffer == null)
			return;
		float i, j, k;
		if (worldCoordinate) {
			Vec3 pos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
			i = (float) (x - pos.x());
			j = (float) (y - pos.y());
			k = (float) (z - pos.z());
		} else {
			i = (float) x;
			j = (float) y;
			k = (float) z;
		}
		poseStack.pushPose();
		poseStack.translate(i, j, k);
		poseStack.mulPose(com.mojang.math.Axis.YN.rotationDegrees(yaw));
		poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(pitch));
		poseStack.mulPose(com.mojang.math.Axis.ZN.rotationDegrees(roll));
		poseStack.scale(xScale, yScale, zScale);
		poseStack.translate(offset.x(), offset.y(), offset.z());
		RenderSystem.setShaderColor((color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F, (color >>> 24) / 255.0F);
		vertexBuffer.bind();
		vertexBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, vertexBuffer.getFormat().hasUV(0) ? GameRenderer.getPositionTexColorShader() : GameRenderer.getPositionColorShader());
		VertexBuffer.unbind();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void renderLevel(RenderLevelStageEvent event) {
		if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
			currentStage = 1;
			RenderSystem.depthMask(false);
			renderShapes(event);
			RenderSystem.enableCull();
			RenderSystem.depthMask(true);
			currentStage = 0;
		} else if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
			currentStage = 2;
			RenderSystem.depthMask(true);
			renderShapes(event);
			RenderSystem.enableCull();
			RenderSystem.depthMask(true);
			currentStage = 0;
		}
	}

	private static void renderShapes(RenderLevelStageEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		Entity entity = minecraft.gameRenderer.getMainCamera().getEntity();
		if (level != null && entity != null) {
			poseStack = event.getPoseStack();
			projectionMatrix = event.getProjectionMatrix();
			Vec3 pos = entity.getPosition(event.getPartialTick());
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			execute(event, entity, event.getPartialTick(), event.getRenderTick());
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.defaultBlendFunc();
			RenderSystem.disableBlend();
			RenderSystem.enableDepthTest();
		}
	}

	public static void execute(Entity entity, double partialTick, double ticks) {
		execute(null, entity, partialTick, ticks);
	}

	private static void execute(@Nullable Event event, Entity entity, double partialTick, double ticks) {
		if (entity == null)
			return;
		double width = 0;
		double length = 0;
		double i = 0;
		double j = 0;
		double pitch = 0;
		double k = 0;
		double sneak = 0;
		double l = 0;
		double m = 0;
		double yaw = 0;
		double speed = 0;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).atom_eve_atomic_ray) {
			if (entity.isShiftKeyDown()) {
				sneak = 0.9;
			} else {
				sneak = 1.2;
			}
			if (target(2)) {
				yaw = entity.getYRot() * 0.0174533 + Math.toRadians(145);
				pitch = entity.getXRot() * 0.0174533;
				speed = 0.2;
				width = 0.15;
				length = 15;
				RenderSystem.setShaderTexture(0, new ResourceLocation(("invincible_craft" + ":textures/" + "beam" + ".png")));
				if (begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR, (!Minecraft.getInstance().isPaused()))) {
					i = width / (-2);
					j = width / 2;
					k = length - 0.5;
					l = (ticks + partialTick) * speed;
					m = length + l;
					add(i, i, k, 0, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, i, i, 0, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, i, i, 1, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, i, k, 1, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, j, k, 0, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, j, i, 0, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, j, i, 1, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, j, k, 1, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, i, k, 0, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, i, i, 0, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, j, i, 1, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(j, j, k, 1, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, j, k, 0, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, j, i, 0, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, i, i, 1, (float) m, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					add(i, i, k, 1, (float) l, 255 << 24 | 255 << 16 | 255 << 8 | 255);
					end();
				}
				renderShape(shape(), (entity.getX() + 0 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + sneak + 0.2 * Math.sin((-1) * pitch)), (entity.getZ() + 0 * Math.sin(yaw) * Math.cos(pitch)), entity.getYRot(), entity.getXRot(), 0,
						(float) (-2.9), (float) 2.9, (float) 2.9, 64 << 24 | 255 << 16 | 141 << 8 | 192);
				renderShape(shape(), (entity.getX() + 0 * Math.cos(yaw) * Math.cos(pitch)), (entity.getY() + sneak + 0.2 * Math.sin((-1) * pitch)), (entity.getZ() + 0 * Math.sin(yaw) * Math.cos(pitch)), entity.getYRot(), entity.getXRot(), 0,
						(float) 2.3, (float) 2.3, (float) 2.3, 255 << 24 | 255 << 16 | 141 << 8 | 192);
				release();
			}
		}
	}
}
