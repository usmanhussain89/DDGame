package soen.game.dd.weapon.enchantments;

import java.util.List;

/**
 * this is part of the decorator for the weapon enchantment Concrete Decorators
 * 
 * @author fyounis
 *
 */
public class SlayingDecorator extends WeaponDecorator {

	/**
	 * This is the constructor of SlayingDecorator class
	 * 
	 * @param c
	 */
	public SlayingDecorator(Weapon c) {
		super(c);
	}

	/**
	 * This method return list of enchantents
	 */
	@Override
	public List<EnchantmentTypes> getEnchantments() {
		List<EnchantmentTypes> enchantments = super.getEnchantments();
		enchantments.add(EnchantmentTypes.Slaying);
		return enchantments;

	}
}
