package soen.game.dd.models;

public class Interactor {
	private NPCType npcType;
	private Character playableCharacter;
	private int damagePoint;
	
	public Interactor(Character playableCharacter){
		this.playableCharacter=playableCharacter;
		//this.npcType=NPC;
		//Interact(NPC);
		
	}
	public void Loot(NPCType NPC){
		Item item = null; //TODO added this just to make the code compile (@kelbadawi)
		playableCharacter.addItemIntoBackpack(item);
		//remove item looted from its original location
	}
	
	public void OpenDoor(NPCType NPC){
		//if(character.key==True)
		//Map is cleared
		//Levelup() this can be done here or on the map/campaign end
	}
	
	public void Trade(NPCType NPC){
		//NPC.Loot(character)
		//Character.Loot(NPC)
	}
	
	public int Hit(NPCType NPC){
		//getcharcter attack, damage, and calculate character hit damage
		//getNPC armor and calculate its defence
		//roll approropraite dice
		//Calacuate final damage point
		return damagePoint;
	}
	public void Interact(NPCType NPC){
		if(NPC==NPCType.CHEST ||NPC==NPCType.DEAD){
			Loot(NPC);
		}
		else if(NPC==NPCType.FRINDLY){
			Trade(NPC);
		}
		else if(NPC==NPCType.HOSTILE){
			Hit(NPC);
		}
		else if(NPC==NPCType.HOSTILE){
			OpenDoor(NPC);
		}
	}
	

}
