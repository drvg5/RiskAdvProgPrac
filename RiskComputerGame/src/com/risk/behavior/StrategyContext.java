package com.risk.behavior;

import java.util.HashMap;
import java.util.List;

public class StrategyContext {

	private PlayerBehavior strategy;

	/**
	 * Plugs in a specific strategy to be used
	 */
	public void setStrategy(PlayerBehavior strategy) {

		this.strategy = strategy;
	}

	/**
	 * Method that executes a different strategy depending on what strategy was
	 * plugged in upon instantiation.
	 */
	public void executeReinforce(String player) {

		this.strategy.reinforce(player);
		// return this.strategy.reinforce(a);
	}

	/*
	 * dynamic execution for attack phase
	 */
	public void executeAttack(String player, HashMap<String, List<String>> territoryMap) {
		this.strategy.attack(player,territoryMap);
	}

}
