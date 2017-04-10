package soen.game.dd.weapon.enchantments;

import java.util.List;

/**
 * this is part of the decorator for the weapon enchantment  
 * Decorator 
 * @author fyounis
 *
 */
public abstract class WeaponDecorator extends Weapon{

	Weapon weapon;
	
	public WeaponDecorator(Weapon weapon){
		this.weapon = weapon;
	}
	
	public Weapon getUnenhancedWeapon(){
		return this.weapon;
	}

	public List<EnchantmentTypes> getEnchantments(){
		return this.weapon.getEnchantments();
	}

}
