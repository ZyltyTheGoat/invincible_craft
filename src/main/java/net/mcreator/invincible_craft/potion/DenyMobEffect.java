
package net.mcreator.invincible_craft.potion;

public class DenyMobEffect extends MobEffect {
	public DenyMobEffect() {
		super(MobEffectCategory.HARMFUL, -13434880);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}