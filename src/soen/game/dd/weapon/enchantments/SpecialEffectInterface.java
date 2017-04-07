package soen.game.dd.weapon.enchantments;

/**
 * Interface for the Weapon decorator pattern 
 * Component Interface 
 * @author fyounis
 *
 */
public interface SpecialEffectInterface {
	
	public void setSpecialEffict(EnchantmentTypes enchantmentTypes);
	public EnchantmentTypes getSpecialEffict();
}
