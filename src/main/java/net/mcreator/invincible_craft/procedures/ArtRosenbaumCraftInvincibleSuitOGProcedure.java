package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.invincible_craft.init.InvincibleCraftModItems;

import java.util.concurrent.atomic.AtomicReference;

public class ArtRosenbaumCraftInvincibleSuitOGProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double suit_fabric = 0;
		double dye_1 = 0;
		double dye_2 = 0;
		double slot = 0;
		boolean has_fabric = false;
		boolean has_dye1 = false;
		boolean has_dye2 = false;
		suit_fabric = 0;
		dye_1 = 0;
		dye_2 = 0;
		has_fabric = false;
		has_dye1 = false;
		has_dye2 = false;
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(InvincibleCraftModItems.SUIT_FABRIC.get())) : false) {
			slot = 0;
			for (int index0 = 0; index0 < 36; index0++) {
				if ((new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
						return _retval.get();
					}
				}.getItemStack((int) slot, entity)).getItem() == InvincibleCraftModItems.SUIT_FABRIC.get()) {
					suit_fabric = suit_fabric + (new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack((int) slot, entity)).getCount();
					if (suit_fabric >= 4) {
						has_fabric = true;
					}
				}
				slot = slot + 1;
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == InvincibleCraftModItems.SUIT_FABRIC.get()) {
				suit_fabric = suit_fabric + (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount();
				if (suit_fabric >= 4) {
					has_fabric = true;
				}
			}
		} else {
			has_fabric = false;
		}
		if (has_fabric) {
			if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.YELLOW_DYE)) : false) {
				slot = 0;
				for (int index1 = 0; index1 < 36; index1++) {
					if ((new Object() {
						public ItemStack getItemStack(int sltid, Entity entity) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).copy());
							});
							return _retval.get();
						}
					}.getItemStack((int) slot, entity)).getItem() == Items.YELLOW_DYE) {
						dye_1 = dye_1 + (new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
								return _retval.get();
							}
						}.getItemStack((int) slot, entity)).getCount();
						if (dye_1 >= 4) {
							has_dye1 = true;
						}
					}
					slot = slot + 1;
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.YELLOW_DYE) {
					dye_1 = dye_1 + (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount();
					if (dye_1 >= 4) {
						has_dye1 = true;
					}
				}
			} else {
				has_dye1 = false;
			}
			if (has_dye1) {
				if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.LIGHT_BLUE_DYE)) : false) {
					slot = 0;
					for (int index2 = 0; index2 < 36; index2++) {
						if ((new Object() {
							public ItemStack getItemStack(int sltid, Entity entity) {
								AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
								entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
								return _retval.get();
							}
						}.getItemStack((int) slot, entity)).getItem() == Items.LIGHT_BLUE_DYE) {
							dye_2 = dye_2 + (new Object() {
								public ItemStack getItemStack(int sltid, Entity entity) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										_retval.set(capability.getStackInSlot(sltid).copy());
									});
									return _retval.get();
								}
							}.getItemStack((int) slot, entity)).getCount();
							if (dye_2 >= 4) {
								has_dye2 = true;
							}
						}
						slot = slot + 1;
					}
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.LIGHT_BLUE_DYE) {
						dye_2 = dye_2 + (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount();
						if (dye_2 >= 4) {
							has_dye2 = true;
						}
					}
				} else {
					has_dye1 = false;
				}
				if (has_dye2) {
					if (entity instanceof Player _player)
						_player.closeContainer();
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Art the Suit Guy: Here you go.. This suit fits you perfectly!"), false);
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("invincible_craft:advancement_buy_first_suit"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(InvincibleCraftModItems.INVINCIBLE_SUIT_HELMET.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(InvincibleCraftModItems.INVINCIBLE_SUIT_CHESTPLATE.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(InvincibleCraftModItems.INVINCIBLE_SUIT_LEGGINGS.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(InvincibleCraftModItems.INVINCIBLE_SUIT_BOOTS.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(InvincibleCraftModItems.SUIT_FABRIC.get());
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 4, _player.inventoryMenu.getCraftSlots());
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(Items.YELLOW_DYE);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 4, _player.inventoryMenu.getCraftSlots());
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(Items.LIGHT_BLUE_DYE);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 4, _player.inventoryMenu.getCraftSlots());
					}
				} else {
					if (entity instanceof Player _player)
						_player.closeContainer();
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Art the Suit Guy: You are missing some Light Blue Dye."), false);
				}
			} else {
				if (entity instanceof Player _player)
					_player.closeContainer();
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Art the Suit Guy: You are missing some Yellow Dye"), false);
			}
		} else {
			if (entity instanceof Player _player)
				_player.closeContainer();
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Art the Suit Guy: You are missing some Suit Fabric."), false);
		}
	}
}
