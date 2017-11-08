package com.risk.ui;

import java.util.*;

import com.risk.model.StartUpPhaseModel;

public class PhaseUI implements Observer {

	
	public void reinforcementView(){
		
	}
	
	public void forticationView(String player, HashMap<String,List<String>> territoryMap){
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
		
		String[] playerInfoVal = playerInfoKey.split("-");
		
		if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
			
			System.out.println("\n\t--------------");
			System.out.println("\t" + "Territory : " + playerInfoVal[1]);
			
			System.out.println("\t\t" + "Continent : " + playerInfoVal[2]);
			
			System.out.println("\t\t" + "Armies : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
			
			System.out.printf("\t\t"+"Adjacent Countries : ");
			
			List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
			
			for(String adjacent : adjacentList){
				System.out.printf(adjacent+"\t");
			}
			
		}
		
	}

	}
	
	public void attackView(){
		
	}
	
	public void update(Observable obs, Object obj){
		
	}
}