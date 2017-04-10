package soen.game.dd.weapon.enchantments;

import java.util.ArrayList;
import java.util.List;

import soen.game.dd.models.Item;
/**
 * this is part of the decorator for the weapon enchantment  
 * Component Implementation 
 * @author fyounis
 *
 */

public class WeaponBasic extends Weapon{
	
	Item undecoratedItem;
	
	@Override
	public Weapon getUnenhancedWeapon() {
		return this;
	}
	
	@Override
	public List<EnchantmentTypes> getEnchantments() {
		return new ArrayList<EnchantmentTypes>();
	}

}
