
package net.mcreator.invincible_craft.client.renderer;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;

import net.mcreator.invincible_craft.entity.DuplicationCloneEntity;

import java.util.UUID;
import java.util.Map;

import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

import java.net.URL;
import java.net.HttpURLConnection;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.GameProfile;

public class DuplicationCloneRenderer extends HumanoidMobRenderer<DuplicationCloneEntity, HumanoidModel<DuplicationCloneEntity>> {
	private static final MinecraftSessionService sessionService = Minecraft.getInstance().getMinecraftSessionService();
	private final TextureManager textureManager = Minecraft.getInstance().getTextureManager();
	private final HumanoidModel<DuplicationCloneEntity> slimModel;
	private final HumanoidModel<DuplicationCloneEntity> defaultModel;

	public DuplicationCloneRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.slimModel = new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM));
		this.defaultModel = new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER));
		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(DuplicationCloneEntity entity) {
		if (entity.isTame() && entity.getOwnerUUID() != null) {
			UUID ownerUUID = entity.getOwnerUUID();
			return getPlayerSkin(ownerUUID);
		} else {
			return new ResourceLocation("invincible_craft:textures/entities/base_1.png");
		}
	}

	@Override
	public void render(DuplicationCloneEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity.isTame() && entity.getOwnerUUID() != null) {
			UUID ownerUUID = entity.getOwnerUUID();
			this.model = getModel(ownerUUID);
		}
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	private HumanoidModel<DuplicationCloneEntity> getModel(UUID uuid) {
		boolean isSlim = isSlimSkin(uuid);
		return isSlim ? slimModel : defaultModel;
	}

	private ResourceLocation getPlayerSkin(UUID uuid) {
		GameProfile profile = new GameProfile(uuid, null);
		Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures = sessionService.getTextures(sessionService.fillProfileProperties(profile, false), false);
		if (textures.containsKey(MinecraftProfileTexture.Type.SKIN)) {
			MinecraftProfileTexture profileTexture = textures.get(MinecraftProfileTexture.Type.SKIN);
			String skinUrl = profileTexture.getUrl();
			String skinHash = profileTexture.getHash();
			ResourceLocation resourceLocation = new ResourceLocation("skins/" + skinHash);
			File skinDir = new File(FMLPaths.GAMEDIR.get().toFile(), "cached_skins");
			File skinFile = new File(skinDir, skinHash + ".png");
			if (!skinDir.exists()) {
				skinDir.mkdirs();
			}
			if (!skinFile.exists()) {
				try {
					downloadSkin(skinUrl, skinFile);
				} catch (IOException e) {
					e.printStackTrace();
					return DefaultPlayerSkin.getDefaultSkin(uuid);
				}
			}
			// Register the texture with Minecraft's texture manager using HttpTexture
			HttpTexture httpTexture = new HttpTexture(skinFile, skinUrl, DefaultPlayerSkin.getDefaultSkin(uuid), false, null);
			textureManager.register(resourceLocation, httpTexture);
			return resourceLocation;
		} else {
			return DefaultPlayerSkin.getDefaultSkin(uuid);
		}
	}

	private void downloadSkin(String skinUrl, File skinFile) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(skinUrl).openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		try (InputStream inputStream = connection.getInputStream()) {
			Files.copy(inputStream, skinFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} finally {
			connection.disconnect();
		}
	}

	private boolean isSlimSkin(UUID uuid) {
		GameProfile profile = new GameProfile(uuid, null);
		profile = sessionService.fillProfileProperties(profile, false);
		if (profile.getProperties().containsKey("textures")) {
			for (Property property : profile.getProperties().get("textures")) {
				String value = new String(java.util.Base64.getDecoder().decode(property.getValue()));
				if (value.contains("\"slim\"")) {
					return true;
				}
			}
		}
		return false;
	}
}
