package soen.game.dd.weapon.enchantments;

import java.util.List;

/**
 * this is part of the decorator for the weapon enchantment  
 * Concrete Decorators 
 * @author fyounis
 *
 */
public class FreezingDecorator extends WeaponDecorator{

	public FreezingDecorator(Weapon c) {
		super(c);
	}

	@Override
	public List<EnchantmentTypes> getEnchantments() {
		List<EnchantmentTypes> enchantments = super.getEnchantments();
		enchantments.add(EnchantmentTypes.Freezing);
		return enchantments;
	}
	
	

}
