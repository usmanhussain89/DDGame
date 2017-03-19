package soen.game.dd.models;

public class NPC extends Character{
	private NPCType NPC; 
	private Character playable;
	private int damagePoint;
	private Item item;
	
	/**
	 * @author Munjed
	 * The default constructor 
	 * @param NPC
	 */
	public NPC(NPCType NPC){
		this.NPC=NPC;
	}
	

}
