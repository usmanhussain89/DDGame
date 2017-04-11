package soen.game.dd.models;

import java.util.Observable;

/**
 * This class calculate damage points between player and hostile character
 * 
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
	 * 
	 * @param playableCharacter
	 * @param NPC
	 * @param npcType
	 * @param weapon
	 * @return
	 */
	public int getDamagePoint(Character playableCharacter, Character NPC, NPCType npcType, Item weapon) {
		if (npcType.equals(NPCType.HOSTILE)) {
			AC = NPC.getArmorClass();
			die = d20Dice();
			if (die == 1) {
				attackScore = 0;
				damagePoint = 0;
				System.out.println("<Game Logging> : The d20 Dice score: " + die);
				System.out.println("<Game Logging> : encounter DISMISSED");
			} else {
				System.out.println("<Game Logging> : The d20 Dice score: " + die);
				System.out.println("<Game Logging> : encounter ENTERED");
				if (die != 20) {
					if (weapon.getWeaponType() == WeaponType.MELEE) {
						attackScore = (int) (die + playableCharacter.attackBonus
								+ playableCharacter.getStrengthModifier());
						System.out.println("<Game Logging> : The attack score: " + attackScore);
					} else if (weapon.getWeaponType() == WeaponType.RANGED) {
						attackScore = (int) (die + playableCharacter.attackBonus
								+ playableCharacter.getDexterityModifier());
						System.out.println("<Game Logging> : The attack score: " + attackScore);
					}
				}
				if (die == 20 || attackScore >= AC) {
					int rolld10Result = playableCharacter.roll1d10();
					System.out.println("<Game Logging> : The roll d10 Dice score: " + rolld10Result);
					if (weapon.getWeaponType() == WeaponType.MELEE) {
						damagePoint = (int) (rolld10Result + playableCharacter.getStrengthModifier()
								+ playableCharacter.getDamageBonus());
						System.out.println("<Game Logging> : The attack score: " + attackScore + " is"
								+ " >= than NPC Armor Class score: " + AC);

					} else if (weapon.getWeaponType() == WeaponType.RANGED) {
						damagePoint = (int) (rolld10Result + playableCharacter.getDexterityModifier()
								+ playableCharacter.getDamageBonus());
						System.out.println("<Game Logging> : The attack score: " + attackScore + " is"
								+ " < than NPC Armor Class score: " + AC);
					}

				}
				// NPC.hitPoint-=damagePoint;
			}

		}

		setChanged();
		System.out.println("<Game Logging> : The damagePoint score: " + damagePoint);
		return damagePoint;
	}

	/**
	 * Generate random score
	 * 
	 * @return
	 */
	private int d20Dice() {
		int score = (int) (Math.random() * 20) + 1;

		return score;
	}

}
