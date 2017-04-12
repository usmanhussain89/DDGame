package soen.game.dd.weapon.enchantments;

import java.util.List;

import soen.game.dd.models.Item;

/**
 * This is the abstraact class Weapon extends from Item
 * 
 * @author Usman
 *
 */
public abstract class Weapon extends Item {
	// Abstract method get Enchantments
	public abstract List<EnchantmentTypes> getEnchantments();

	// Abstract method unenhance weapon
	public abstract Weapon getUnenhancedWeapon();
}
