package soen.game.dd.models;

import java.util.Observable;

public class Hit extends Observable {
	private NPCType npcType;
	private Character playableCharacter;
	private Character NPC;
	private Item weapon;
	private int damagePoint;
	private int AC;
	private int die;
	private int attackScore;
	
	
	public int Hit(Character playableCharacter,Character NPC, NPCType npcType, Item weapon){
		if(npcType.equals(NPCType.HOSTILE)){
			AC=NPC.getArmorClass();
			die=d20Dice();
			if(die==1){
				attackScore=0;
				damagePoint=0;
			}
			else{
				if(die!=20){
					if(weapon.equals("melee")){
						attackScore=(int) (die + playableCharacter.attackBonus + playableCharacter.getStrengthModifier());
					}
					else if(weapon.equals("ranged")){
						attackScore=(int) (die + playableCharacter.attackBonus + playableCharacter.getDexterityModifier());
					}
				}
				if(die==20||attackScore>=AC)
				{
					if(weapon.equals("melee")){
						damagePoint=(int) (playableCharacter.roll1d10()+playableCharacter.getStrengthModifier());
					}
					else if(weapon.equals("ranged")){
						damagePoint=(int) (playableCharacter.roll1d10()+playableCharacter.getDexterityModifier());
					}	
				}
				NPC.hitPoint-=damagePoint;
			}
			
		}
			
		setChanged();
		return damagePoint;
	}

	private int d20Dice() {
		int score = (int) (Math.random() * 20) + 1;
		
		return score;
	}
		

}
