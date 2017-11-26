package com.risk.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class CheaterBehaviorImpl extends Observable implements PlayerBehavior {

	@Override
	public void reinforce(String player){
		
		
		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
//		obj.setMsgUI("total reinforcement print," + player);
//
//		setChanged();
//		notifyObservers(this);
//		
//
//		int reinforcementArmies = ReinforcementPhaseModel.reinforcement.get(player);
		
		//create a list of keys from playerInfo HashMap 
		//for only the player concerned with the most armies
		List<String> strongestTerritoryList = new ArrayList<String>();
		
		TreeSet<Integer> unitsSet = new TreeSet<Integer>();
		
		
		//populate unitsSet
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String [] playerVals = playerInfoKey.split("-");
			
			if(playerVals[0].equals(player) || playerVals[0] == player){
				
				String[] playerKeySplit = playerInfoKey.split("-");
				
				int playerInfoValue = StartUpPhaseModel.playerInfo.get(playerInfoKey);
				
				playerInfoValue = 2 * playerInfoValue;
				
				StartUpPhaseModel.playerInfo.put(playerInfoKey, playerInfoValue);
			
				setChanged();
				
				obj.setMsgUI("placementView," + playerKeySplit[0] + "," + playerKeySplit[1]);
				notifyObservers(this);
					
				
			}//end if(playerVals[0].equals(player) || playerVals[0] == player)
			
		}//end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
		
	}


	@Override
	public void fortify(int player) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {
		// TODO Auto-generated method stub
		
	}

}
