package com.risk.behavior;

import com.risk.model.ReinforcementPhaseModel;
import com.risk.ui.CardExchangeUI;
import com.risk.ui.ReinforcementsUI;

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

    
}
