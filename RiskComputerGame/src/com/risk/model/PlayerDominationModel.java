package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class PlayerDominationModel extends Observable{

	
	private HashMap<String,String> domination = new  HashMap<String,String>();
	
	
	public HashMap<String, String> getDomination() {
		
		return domination;
	}


	public void setDomination(HashMap<String, String> domination) {
		
		this.domination = domination;
	}


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
