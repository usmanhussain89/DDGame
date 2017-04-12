package soen.game.dd.weapon.enchantments;

import java.util.List;

/**
 * this is part of the decorator for the weapon enchantment Decorator
 * 
 * @author fyounis
 *
 */
public abstract class WeaponDecorator extends Weapon {

	Weapon weapon;

	/**
	 * This is the Constructor of WeaponDecorater
	 * 
	 * @param weapon
	 */
	public WeaponDecorator(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * This method return unenhane weapon
	 */
	public Weapon getUnenhancedWeapon() {
		return this.weapon;
	}

	/**
	 * This method return list of enchantents
	 */
	public List<EnchantmentTypes> getEnchantments() {
		return this.weapon.getEnchantments();
	}

}
