package com.risk.behavior;

import java.util.HashMap;
import java.util.List;

public interface PlayerBehavior {
	
	public void reinforce(String player);

	public void attack(String player, HashMap<String, List<String>> territoryMap);

	public void fortify(int player);

}
