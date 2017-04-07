package soen.game.dd.weapon.enchantments;


/**
 * this is part of the decorator for the weapon enchantment  
 * Concrete Decorators 
 * @author fyounis
 *
 */
public class Pacifying extends WeaponDecorator{

	public Pacifying(SpecialEffectInterface c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see soen.game.dd.weapon.enchantments.WeaponDecorator#setSpecialEffict(soen.game.dd.weapon.enchantments.EnchantmentTypes)
	 */
	@Override
	public void setSpecialEffict(EnchantmentTypes enchantmentTypes) {
		// TODO Auto-generated method stub
		super.setSpecialEffict(enchantmentTypes);
	}

	/* (non-Javadoc)
	 * @see soen.game.dd.weapon.enchantments.WeaponDecorator#getSpecialEffict()
	 */
	@Override
	public EnchantmentTypes getSpecialEffict() {
		// TODO Auto-generated method stub
		return super.getSpecialEffict();
	}

}
