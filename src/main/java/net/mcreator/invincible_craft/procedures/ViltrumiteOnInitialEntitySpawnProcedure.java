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
		if (entity instanceof ViltrumiteEntity _datEntSetI)
			_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_gender, Mth.nextInt(RandomSource.create(), 1, 2));
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_gender) : 0) == 1) {
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
		} else {
			viltrumite_prefixes.add("Ara");
			viltrumite_prefixes.add("Vys");
			viltrumite_prefixes.add("Lyr");
			viltrumite_prefixes.add("Nyx");
			viltrumite_prefixes.add("Zyl");
			viltrumite_prefixes.add("Kyr");
			viltrumite_prefixes.add("Xyr");
			viltrumite_prefixes.add("Rys");
			viltrumite_prefixes.add("Dyl");
			viltrumite_prefixes.add("Tys");
			viltrumite_prefixes.add("Ely");
			viltrumite_prefixes.add("Ily");
			viltrumite_prefixes.add("Thu");
			viltrumite_prefixes.add("Xyra");
			viltrumite_prefixes.add("Vexa");
			viltrumite_prefixes.add("Eln");
			viltrumite_prefixes.add("Ill");
			viltrumite_suffixes.add("ara");
			viltrumite_suffixes.add("syn");
			viltrumite_suffixes.add("dria");
			viltrumite_suffixes.add("vys");
			viltrumite_suffixes.add("lyth");
			viltrumite_suffixes.add("sira");
			viltrumite_suffixes.add("thra");
			viltrumite_suffixes.add("nyx");
			viltrumite_suffixes.add("cyra");
			viltrumite_suffixes.add("phira");
			viltrumite_suffixes.add("thia");
			viltrumite_suffixes.add("lyra");
			viltrumite_suffixes.add("syra");
			viltrumite_suffixes.add("vyra");
			viltrumite_suffixes.add("thor");
			viltrumite_suffixes.add("drix");
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
		}
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_gender) : 0) == 1) {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_hair, Mth.nextInt(RandomSource.create(), 1, 11));
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_base, Mth.nextInt(RandomSource.create(), 1, 6));
		} else {
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_hair, Mth.nextInt(RandomSource.create(), 12, 16));
			if (entity instanceof ViltrumiteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(ViltrumiteEntity.DATA_base, Mth.nextInt(RandomSource.create(), 7, 12));
		}
		if (rank <= 5) {
			if (entity instanceof LivingEntity _livingEntity148 && _livingEntity148.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity148.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8);
			if (entity instanceof LivingEntity _livingEntity149 && _livingEntity149.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity149.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100);
		} else if (rank <= 8) {
			if (entity instanceof LivingEntity _livingEntity150 && _livingEntity150.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity150.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(10);
			if (entity instanceof LivingEntity _livingEntity151 && _livingEntity151.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity151.getAttribute(Attributes.MAX_HEALTH).setBaseValue(150);
		} else {
			if (entity instanceof LivingEntity _livingEntity152 && _livingEntity152.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity152.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(12);
			if (entity instanceof LivingEntity _livingEntity153 && _livingEntity153.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity153.getAttribute(Attributes.MAX_HEALTH).setBaseValue(175);
		}
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
	}
}
