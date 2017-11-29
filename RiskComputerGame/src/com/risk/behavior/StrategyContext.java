package com.risk.behavior;

import java.util.HashMap;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class StrategyContext.
 * 
 * @author ashish
 */
public class StrategyContext {

	/** The strategy. */
	private PlayerBehavior strategy;

	/**
	 * Plugs in a specific strategy to be used.
	 *
	 * @param strategy Strategy selected by User
	 */
	public void setStrategy(PlayerBehavior strategy) {

		this.strategy = strategy;
	}

	/**
	 * Method that executes a different strategy depending on what strategy was
	 * plugged in upon instantiation.
	 * @param player Player number
	 */
	public void executeReinforce(String player) {

		this.strategy.reinforce(player);
		// return this.strategy.reinforce(a);
	}

	/**
	 * Execute attack.
	 *
	 * @param player the player
	 * @param territoryMap the territory map
	 */
	/*
	 * dynamic execution for attack phase
	 */
	public void executeAttack(String player, HashMap<String, List<String>> territoryMap) {
		this.strategy.attack(player,territoryMap);
	}
	
	
	public void executeFortification(String player) {

		this.strategy.fortify(player);
		// return this.strategy.reinforce(a);
	}

}
