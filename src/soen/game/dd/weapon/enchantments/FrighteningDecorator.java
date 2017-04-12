package soen.game.dd.weapon.enchantments;

import java.util.List;

/**
 * this is part of the decorator for the weapon enchantment Concrete Decorators
 * 
 * @author fyounis
 *
 */
public class FrighteningDecorator extends WeaponDecorator {

	/**
	 * This is the constructor of FrighteningDecoratar
	 * 
	 * @param c
	 */
	public FrighteningDecorator(Weapon c) {
		super(c);
	}

	/**
	 * This method return list of enchantents
	 */
	@Override
	public List<EnchantmentTypes> getEnchantments() {
		List<EnchantmentTypes> enchantments = super.getEnchantments();
		enchantments.add(EnchantmentTypes.Frightening);
		return enchantments;
	}

}
