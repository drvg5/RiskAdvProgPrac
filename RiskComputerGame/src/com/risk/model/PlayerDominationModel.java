package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


/**
 * PlayerDominationModel is an Observable class which has method to calculate
 * the domination values.
 *
 * @author Ashish Sharma
 */
public class PlayerDominationModel extends Observable{

	
	/** This HashMap stores the domination values. */
	private HashMap<String,String> domination = new  HashMap<String,String>();
	
	
	/**
	 * Gets the domination HashMap.
	 *
	 * @return the domination
	 */
	public HashMap<String, String> getDomination() {
		
		return domination;
	}


	/**
	 * Set the domination HashMap.
	 *
	 * @param domination the domination
	 */
	public void setDomination(HashMap<String, String> domination) {
		
		this.domination = domination;
	}


	/**
	 * Calculate the domination values for a player.
	 *
	 * @param playerInfo HashMap containing the player info
	 * @param numberOfPlayers the number of players
	 * @param totalTerr the total territory in a map
	 */
	public void calcDominationValues(HashMap<String,Integer> playerInfo, int numberOfPlayers, int totalTerr){
		
		HashMap<String,String> dominationMap = new HashMap<String,String>();
		
		
		for(int i = 1; i <= numberOfPlayers; i++){
			
			int terr = 0;
			int percentage  = 0;
			for(String playerInfoKey : playerInfo.keySet()){
				
				String[] keySplit = playerInfoKey.split("-");
				
				
				if(keySplit[0].equals(Integer.toString(i)) || keySplit[0] ==  Integer.toString(i)){
					
					
					terr++;
					
				}
				
			}
			
			percentage = (terr * 100 )/totalTerr ;
			
			dominationMap.put(Integer.toString(i), percentage + "%");
			
		}
		
		setDomination(dominationMap);
		
		setChanged();
		
		notifyObservers(this);
		
	}

}
