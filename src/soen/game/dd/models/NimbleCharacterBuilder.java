package soen.game.dd.models;

import java.util.ArrayList;
import java.util.List;

public class NimbleCharacterBuilder extends CharacterBuilder {
	public void setAbilityScores(){
		//The following loop is made sort the scores retrieved from 4d6 dices from
		//big to small so we can then manipulate their assignment to fighter type 
		//preference
		
		int[] die = new int[6];
		int average=0;
		for (int i = 0; i < 6; i++){
			die[i] = roll4d6();
			average+=die[i];
		}
		for(int i=0;i<6;i++){
			int max=die[i];
			int index=i;
			for(int j=i;j<6;j++){
				if(max<die[j]){
					max=die[j];
					index=j;
				}
			}
			int temp=die[i];
			die[i]=max;
			die[index]=temp;
		}
		average=average/3;
		this.dexterity = die[0]+average/2;
		this.constitution = die[1]+average/3;
		this.strength= die[2]+average/4;
		this.intelligence = die[3]+average/8;
		this.charisma = die[4]+average/10;
		this.wisdom = die[5];
		
		List<Item> listItem = fighterTypeProduct.getEquippedItems();
		//ArrayList<Item> listItem = (ArrayList<Item>) getEquippedItems();
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.CHARISMA) {
				charisma += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.CONSTITUTION) {
				constitution += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.INTELLIGENCE) {
				intelligence += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.DEXTERITY) {
				dexterity += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.CONSTITUTION) {
				constitution += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.DAMAGE_BONUS) {
				damageBonus += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.STRENGTH) {
				strength += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.INTELLIGENCE) {
				intelligence += item.getBonusAmount();
			}
		}
		
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.WISDOM) {
				wisdom += item.getBonusAmount();
			}
		
		
	}

		setChanged();
	}
}