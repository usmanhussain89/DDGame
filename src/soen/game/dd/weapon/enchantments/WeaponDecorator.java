package soen.game.dd.weapon.enchantments;


/**
 * this is part of the decorator for the weapon enchantment  
 * Decorator 
 * @author fyounis
 *
 */
public class WeaponDecorator implements SpecialEffectInterface{

	protected SpecialEffectInterface specialEffectInterface;
	
	public WeaponDecorator(SpecialEffectInterface c){
		this.specialEffectInterface = c;
	}
	
	@Override
	public void setSpecialEffict(EnchantmentTypes enchantmentTypes) {
		// TODO Auto-generated method stub
		this.specialEffectInterface.getSpecialEffict();
	}

	@Override
	public EnchantmentTypes getSpecialEffict() {
		// TODO Auto-generated method stub
		return this.specialEffectInterface.getSpecialEffict();
	}

}
