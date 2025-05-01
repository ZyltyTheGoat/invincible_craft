package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;

import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

import java.util.List;
import java.util.ArrayList;

public class ViltrumiteOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		List<Object> viltrumite_prefixes = new ArrayList<>();
		List<Object> viltrumite_suffixes = new ArrayList<>();
		List<Object> viltrumite_titles = new ArrayList<>();
		double size = 0;
		double random = 0;
		double rank = 0;
		double random_prefix = 0;
		double random_suffix = 0;
		double random_title = 0;
		rank = Mth.nextInt(RandomSource.create(), 1, 10);
		viltrumite_prefixes.add("Thra");
		viltrumite_prefixes.add("Kry");
		viltrumite_prefixes.add("Vil");
		viltrumite_prefixes.add("Zor");
		viltrumite_prefixes.add("Nar");
		viltrumite_prefixes.add("Ghor");
		viltrumite_prefixes.add("Xan");
		viltrumite_prefixes.add("Drak");
		viltrumite_prefixes.add("Rax");
		viltrumite_prefixes.add("Mord");
		viltrumite_prefixes.add("Kor");
		viltrumite_prefixes.add("Bor");
		viltrumite_prefixes.add("Tor");
		viltrumite_prefixes.add("Vex");
		viltrumite_prefixes.add("Zyr");
		viltrumite_prefixes.add("Luc");
		viltrumite_prefixes.add("Vyr");
		viltrumite_suffixes.add("us");
		viltrumite_suffixes.add("ak");
		viltrumite_suffixes.add("on");
		viltrumite_suffixes.add("ax");
		viltrumite_suffixes.add("ar");
		viltrumite_suffixes.add("oth");
		viltrumite_suffixes.add("ek");
		viltrumite_suffixes.add("ir");
		viltrumite_suffixes.add("ok");
		viltrumite_suffixes.add("un");
		viltrumite_suffixes.add("ath");
		viltrumite_suffixes.add("ix");
		viltrumite_suffixes.add("or");
		viltrumite_suffixes.add("ul");
		viltrumite_suffixes.add("yn");
		viltrumite_suffixes.add("th");
		viltrumite_suffixes.add("rak");
		viltrumite_titles.add(" the Conqueror");
		viltrumite_titles.add(" the Merciless");
		viltrumite_titles.add(" the Annihilator");
		viltrumite_titles.add(" the Relentless");
		viltrumite_titles.add(" the Fearless");
		viltrumite_titles.add(" the Ruthless");
		viltrumite_titles.add(" the Ferocious");
		viltrumite_titles.add(" the Unbroken");
		viltrumite_titles.add(" the Indomitable");
		viltrumite_titles.add(" the Scourge");
		random_prefix = Mth.nextInt(RandomSource.create(), 0, (int) (viltrumite_prefixes.size() - 1));
		random_suffix = Mth.nextInt(RandomSource.create(), 0, (int) (viltrumite_suffixes.size() - 1));
		random_title = Mth.nextInt(RandomSource.create(), 0, (int) (viltrumite_titles.size() - 1));
		if (rank <= 5) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_rank, 1);
			entity.setCustomName(Component.literal(("Soldier - " + (viltrumite_prefixes.get((int) random_prefix) instanceof String _s ? _s : "") + (viltrumite_suffixes.get((int) random_suffix) instanceof String _s ? _s : ""))));
		} else if (rank <= 9) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_rank, 2);
			entity.setCustomName(Component.literal(("Officer - " + (viltrumite_prefixes.get((int) random_prefix) instanceof String _s ? _s : "") + (viltrumite_suffixes.get((int) random_suffix) instanceof String _s ? _s : ""))));
		} else {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_rank, 3);
			entity.setCustomName(Component.literal(("General - " + (viltrumite_prefixes.get((int) random_prefix) instanceof String _s ? _s : "") + (viltrumite_suffixes.get((int) random_suffix) instanceof String _s ? _s : "")
					+ (viltrumite_titles.get((int) random_title) instanceof String _s ? _s : ""))));
		}
		if (entity instanceof ViltrumiteEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_eyes, Mth.nextInt(RandomSource.create(), 1, 6));
		if (entity instanceof ViltrumiteEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_hair, Mth.nextInt(RandomSource.create(), 1, 9));
		if (entity instanceof ViltrumiteEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_mouth, Mth.nextInt(RandomSource.create(), 1, 2));
		if (entity instanceof ViltrumiteEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_mustache, Mth.nextInt(RandomSource.create(), 1, 6));
		if (rank <= 5) {
			if (entity instanceof LivingEntity _livingEntity77 && _livingEntity77.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity77.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8);
			if (entity instanceof LivingEntity _livingEntity78 && _livingEntity78.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity78.getAttribute(Attributes.MAX_HEALTH).setBaseValue(75);
		} else if (rank <= 8) {
			if (entity instanceof LivingEntity _livingEntity79 && _livingEntity79.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity79.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(12);
			if (entity instanceof LivingEntity _livingEntity80 && _livingEntity80.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity80.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100);
		} else {
			if (entity instanceof LivingEntity _livingEntity81 && _livingEntity81.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity81.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(15);
			if (entity instanceof LivingEntity _livingEntity82 && _livingEntity82.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity82.getAttribute(Attributes.MAX_HEALTH).setBaseValue(125);
		}
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
	}
}
