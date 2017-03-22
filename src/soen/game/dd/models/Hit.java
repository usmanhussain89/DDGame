package soen.game.dd.models;

import java.util.Observable;

/**
 * This class calculate damage points between player and hostile character
 * @author Usman
 *
 */
public class Hit extends Observable {
	private NPCType npcType;
	private Character playableCharacter;
	private Character NPC;
	private Item weapon;
	private WeaponType weaponType;
	private int damagePoint;
	private int AC;
	private int die;
	private int attackScore;
	
	/**
	 * this method return damage point
	 * @param playableCharacter
	 * @param NPC
	 * @param npcType
	 * @param weapon
	 * @return
	 */
	public int getDamagePoint(Character playableCharacter,Character NPC, NPCType npcType, Item weapon){
		if(npcType.equals(NPCType.HOSTILE)){
			AC=NPC.getArmorClass();
			die=d20Dice();
			if(die==1){
				attackScore=0;
				damagePoint=0;
			}
			else{
				if(die!=20){
					if(weapon.getWeaponType()==WeaponType.MELEE){
						attackScore=(int) (die + playableCharacter.attackBonus + playableCharacter.getStrengthModifier());
					}
					else if(weapon.getWeaponType()==WeaponType.RANGED){
						attackScore=(int) (die + playableCharacter.attackBonus + playableCharacter.getDexterityModifier());
					}
				}
				if(die==20||attackScore>=AC)
				{
					if(weapon.getWeaponType()==WeaponType.MELEE){
						damagePoint=(int) (playableCharacter.roll1d10()+playableCharacter.getStrengthModifier()+playableCharacter.getDamageBonus());
					}
					else if(weapon.getWeaponType()==WeaponType.RANGED){
						damagePoint=(int) (playableCharacter.roll1d10()+playableCharacter.getDexterityModifier()+playableCharacter.getDamageBonus());
					}	
				}
				//NPC.hitPoint-=damagePoint;
			}
			
		}
			
		setChanged();
		return damagePoint;
	}
	
	/**
	 * Generate random score
	 * @return
	 */
	private int d20Dice() {
		int score = (int) (Math.random() * 20) + 1;
		
		return score;
	}
		

}
