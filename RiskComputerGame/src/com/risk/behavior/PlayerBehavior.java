package com.risk.behavior;

import java.util.HashMap;
import java.util.List;


/**
 * The Interface PlayerBehavior is the strategy interface which declares the methods for reinforce, attack and fortify.
 * It is implemented by the following classes.
 * <ul>
 * <li>{@link com.risk.behavior.AggressiveBehaviorImpl AggressiveBehaviorImpl}</li>
 * <li>{@link com.risk.behavior.BenevolantBehaviorImpl BenevolantBehaviorImpl}</li>
 * <li>{@link com.risk.behavior.RandomBehaviorImpl RandomBehaviorImpl}</li>
 * <li>{@link com.risk.behavior.HumanBehaviorImpl HumanBehaviorImpl}</li>
 * <li>{@link com.risk.behavior.CheaterBehaviorImpl CheaterBehaviorImpl}</li>
 * </ul>
 */
public interface PlayerBehavior {
	
	/**
	 * Reinforce.
	 *
	 * @param player the player
	 */
	public void reinforce(String player);

	/**
	 * Attack.
	 *
	 * @param player the player
	 * @param territoryMap the territory map
	 */
	public void attack(String player, HashMap<String, List<String>> territoryMap);

	/**
	 * Fortify.
	 *
	 * @param player the player
	 */
	public void fortify(String player);

}
