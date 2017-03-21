package soen.game.dd.models;

public class NimbleCharacterBuilder extends CharacterBuilder {
	public void setAbilityScores(){
		//The following loop is made sort the scores retrieved from 4d6 dices from
		//big to small so we can then manipulate their assignment to fighter type 
		//preference
		
		int[] die = new int[6];
		for(int i=0; i<6;i++)
			die[i]=roll4d6();
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
		this.dexterity = die[0]+4;
		this.constitution = die[1]+2;
		this.strength= die[2]+1;
		this.intelligence = die[3];
		this.charisma = die[4];
		this.wisdom = die[5];
	}



}
