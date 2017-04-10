package soen.game.dd.weapon.enchantments;

import java.util.List;

import soen.game.dd.models.Item;

public abstract class Weapon extends Item {

	public abstract List<EnchantmentTypes> getEnchantments();
	public abstract Weapon getUnenhancedWeapon();
}
