package soen.game.dd.weapon.enchantments;

import soen.game.dd.models.Item;
/**
 * this is part of the decorator for the weapon enchantment  
 * Component Implementation 
 * @author fyounis
 *
 */

public class WeaponBasic extends Item implements SpecialEffectInterface{
	
	private EnchantmentTypes enchantmentTypes;
	
	
	@Override
	public void setSpecialEffict(EnchantmentTypes enchantmentTypes) {
		// TODO Auto-generated method stub
		this.enchantmentTypes = enchantmentTypes;
	}

	@Override
	public EnchantmentTypes getSpecialEffict() {
		// TODO Auto-generated method stub
		return enchantmentTypes;
	}

}
