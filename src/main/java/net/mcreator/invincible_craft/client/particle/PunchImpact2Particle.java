
package net.mcreator.invincible_craft.client.particle;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class PunchImpact2Particle extends TextureSheetParticle {
	public static PunchImpact2ParticleProvider provider(SpriteSet spriteSet) {
		return new PunchImpact2ParticleProvider(spriteSet);
	}

	public static class PunchImpact2ParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public PunchImpact2ParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new PunchImpact2Particle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected PunchImpact2Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.4f, 0.4f);
		this.quadSize *= 16f;
		this.lifetime = 7;
		this.gravity = 0f;
		this.hasPhysics = true;
		this.xd = vx * 0;
		this.yd = vy * 0;
		this.zd = vz * 0;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			this.setSprite(this.spriteSet.get((this.age / 2) % 4 + 1, 4));
		}
	}
}
