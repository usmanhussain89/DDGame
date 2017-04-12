package soen.game.dd.weapon.enchantments;

import java.util.List;

/**
 * this is part of the decorator for the weapon enchantment Concrete Decorators
 * 
 * @author fyounis
 *
 */
public class PacifyingDecorator extends WeaponDecorator {

	/**
	 * Constructor of Pacifying class
	 * 
	 * @param c
	 */
	public PacifyingDecorator(Weapon c) {
		super(c);
	}

	/**
	 * This method return list of enchantents
	 */
	@Override
	public List<EnchantmentTypes> getEnchantments() {
		List<EnchantmentTypes> enchantments = super.getEnchantments();
		enchantments.add(EnchantmentTypes.Pacifying);
		return enchantments;
	}

}
