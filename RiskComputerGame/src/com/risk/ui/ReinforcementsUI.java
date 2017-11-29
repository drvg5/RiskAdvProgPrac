package com.risk.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.risk.model.PlayerClass;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class ReinforcementsUI implements Observer {

	
	
	@Override
	public void update(Observable obs, Object arg) {
		// TODO Auto-generated method stub
		
		
		
		//String msgUI = ReinforcementPhaseModel.getMsgUI();
		String msgUI = ((ReinforcementPhaseModel)obs).getMsgUI();
		
		if(msgUI.contains("byTerr")){
			
			//"byTerr," + player + "," + playerTerr + "," + reinforcementArmies;
			
			String[] msgUISplit = msgUI.split(",");
			
			calcByTerrView(msgUISplit[1],msgUISplit[2],msgUISplit[3]);
			
			
		}
		
		if(msgUI.contains("byCtrlVal")){
			
			//"byCtrlVal,"+player
			
			String[] msgUISplit = msgUI.split(",");
			calcByCtrlValView(msgUISplit[1],((ReinforcementPhaseModel)obs).getCntrlValReinforcements(),PlayerClass.currentMap);
			
			
			
		}
		
		if(msgUI.contains("total reinforcement print")){
			
			//"total reinforcement print," + player
			String[] msgUISplit = msgUI.split(",");
			
			totalReinforcementsView(msgUISplit[1],PlayerClass.currentMap);
		}
		
		if(msgUI.contains("reinforceRandom")){
			
			//"reinforceRandom," + player + "," + territory
			String[] msgUISplit = msgUI.split(",");
			
			placementRandomView(msgUISplit[1], msgUISplit[2], ((ReinforcementPhaseModel)arg));
			
		}
		
		if(msgUI.contains("reinforceAggressive")){
			
			//"reinforceAgressive," + player + "," + territory
			String[] msgUISplit = msgUI.split(",");
			placementAggressiveView(msgUISplit[1], msgUISplit[2], ((ReinforcementPhaseModel)arg));
		}
		
		if(msgUI.contains("reinforceBenevolent")){
			
			//"reinforceBenevolent," + player + "," + territory
			String[] msgUISplit = msgUI.split(",");
			placementBenevolentView(msgUISplit[1], msgUISplit[2], ((ReinforcementPhaseModel)arg));
		}
		
		if(msgUI.contains("reinforceCheaterTerr")){
			
			//"reinforceCheater," + player
			String[] msgUISplit = msgUI.split(",");
			//placementCheaterView(msgUISplit[1], msgUISplit[2], ((ReinforcementPhaseModel)arg));
		}

		if(msgUI.contains("reinforceCheater")){
			
			//"reinforceCheater," + player
			String[] msgUISplit = msgUI.split(",");
			placementCheaterView(msgUISplit[1], msgUISplit[2], ((ReinforcementPhaseModel)arg));
		}
		
		if(msgUI.contains("reinforceHuman")){
			
			//"reinforceHuman," + player + "," + territory
			String[] msgUISplit = msgUI.split(",");
			placementHumanView(msgUISplit[1], msgUISplit[2], ((ReinforcementPhaseModel)arg));
		}
		
		if(msgUI.contains("displayCheaterTerr")){
			
			//"reinforceHuman," + player + "," + territory
			String[] msgUISplit = msgUI.split(",");
			displayCheaterTerr(msgUISplit[1],PlayerClass.currentMap);
		}
		
		
	}//end update method
	
	public void calcByTerrView(String player, String terrOwned, String byTerrReinforcements){
		
		System.out.println("\n\t******CALCULATION OF REINFORCEMENTS BY NUMBER OF TERRITORIES OWNED******");
		System.out.println("\n\t" + "TOTAL NUMBER OF TERRITORIES OWNED BY PLAYER " + player + " : " + terrOwned);
		System.out.println("\t" + "REINFORCEMENTS RECIEVED AS PER NUMBER OF TERRITORIES OWNED : " + byTerrReinforcements);
		
	}//end method calcByTerrView

	public void calcByCtrlValView(String player, ArrayList<String> ctrlValMsg, HashMap<String,List<String>> territoryMap){
		
		
		int continentReinforcements = 0;
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\n");
		
		
		
		System.out.println("\t******CALCULATION OF REINFORCEMENTS BY CONTINENTS OWNED******");
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String continent : StartUpPhaseModel.terrPerCont.keySet()){
			
			System.out.printf("\n");
			
			int terrCount = 0;
			
			//get all territories for a continent owned by this player
			ArrayList<String> terrArr = new ArrayList<String>();
			
			for(String playerInfoKey :  StartUpPhaseModel.playerInfo.keySet()){
				
				String[] playerInfoKeySplit = playerInfoKey.split("-");
				
				if(playerInfoKeySplit[0].equals(player) || playerInfoKeySplit[0] == player ){
					
					if(playerInfoKeySplit[2].equals(continent) || playerInfoKeySplit[2] == continent){
						
						terrArr.add(playerInfoKeySplit[1]);
						terrCount++;
						
					}
				}
			}//end for(String playerInfoKey...
			
			
			
			int terrPerContinentCount = StartUpPhaseModel.terrPerCont.get(continent);
			
			if(terrCount == 0){
				
				System.out.println("\n\t" + "NO TERRITORIES owned by PLAYER " + player + " in continent " + continent );
//				System.out.println("\t" + "Number of territories in " + continent + " : 0 out of " + terrPerContinentCount);
//				
				continentReinforcements = continentReinforcements + 0;
			}
			else{
				
				System.out.printf("\n\t" + "Territories owned by PLAYER " + player + " in continent " + continent + " -> " );
				
				
				int index = 0;
				for(String adjacent : terrArr){
					
					if(index == 0)
						System.out.printf(adjacent);
					else
						System.out.printf("," + adjacent);
					
					index++;
					
				}//for(String adjacent : adjacentList)
				
				
				continentReinforcements = continentReinforcements + 0;
				
				System.out.println("\n\t" + "Number of territories owned in " + continent + " : " + terrCount + " out of "
						+ "total " +  terrPerContinentCount + " territories in " + continent);
				
//				System.out.println("terrPerContinentCount :: " + terrPerContinentCount);
//				System.out.println("terrCount :: " + terrCount);
				
				
				try {
					System.in.read();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(terrCount == terrPerContinentCount){
					
					System.out.printf("\t" + "REINFORCEMENTS RECIEVED FOR CONQUERING THE WHOLE CONTINENT OF " + continent + " : " );
					
					//ctrlValList
					//continent+","+reinforcements+","+cntrlVal
					
					for(String ctrlValMsgKey : ctrlValMsg ){
						
						String[] keySplit = ctrlValMsgKey.split(",");
						
						if(keySplit[0].equals(continent) || keySplit[0] == continent){
							
							System.out.printf(keySplit[3]);
							continentReinforcements = continentReinforcements + Integer.parseInt(keySplit[3]);
							
							break;
							
						}//end if(keySplit[0].equals(continent) 
						
					}//end for(String ctrlValMsgKey 
					
					
				}//end if(terrCount == terrPerContinentCount)
				
			}//end else
		
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end for(String continent : Sta
		
		if(continentReinforcements == 0)
			System.out.println("\n\t" + "NO REINFORCEMENTS RECIEVED AS PLAYER " + player + " DOES NOT HAVE ANY CONTINENT CONQUERED" );
		
		else	
			System.out.println("\n\t" + "REINFORCEMENTS RECIEVED AS PER CONQUERED CONTINENTS : " + continentReinforcements);
	
	}
	
	public void totalReinforcementsView(String player, HashMap<String,List<String>> territoryMap){
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.printf("\n\n\t" + "TOTAL REINFORCEMENTS RECIEVED BY PLAYER " + player + " -> " );
		
		int total = ReinforcementPhaseModel.reinforcement.get(player);
		
		System.out.printf("" + total + "\n\n");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		System.out.println("\n\t" + "TERRITORIES OWNED BY PLAYER " + player + " -> \n");
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String[] playerInfoVal = playerInfoKey.split("-");
			
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				System.out.println("\n\t\t------------------------------------");
				System.out.println("\t\t" + "Territory : " + playerInfoVal[1]);
				
				System.out.println("\t\t\t  " + "Continent : " + playerInfoVal[2]);
				
				System.out.println("\t\t\t  " + "Armies : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
				
				System.out.printf("\t\t\t  "+"Adjacent Countries : ");
				
				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
				
				int index = 0;
				for(String adjacent : adjacentList){
					
					if(index == 0)
						System.out.printf(adjacent);
					else
						System.out.printf("," + adjacent);
					
					index++;
				}//for(String adjacent : adjacentList)
				
			}//end if(player.equals(playerInfoVal[0])...
			
		}//end for(String playerInfoKey : Star...
	
	
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\n\n\n\t********************** PLACEMENT OF REINFORCEMENT ARMIES START ************************");

		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void displayCheaterTerr(String player, HashMap<String,List<String>> territoryMap){
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\n\t" + "TERRITORIES OWNED BY PLAYER " + player + " -> \n");
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String[] playerInfoVal = playerInfoKey.split("-");
			
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				System.out.println("\n\t\t------------------------------------");
				System.out.println("\t\t" + "Territory : " + playerInfoVal[1]);
				
				System.out.println("\t\t\t  " + "Continent : " + playerInfoVal[2]);
				
				System.out.println("\t\t\t  " + "Armies : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
				
				System.out.printf("\t\t\t  "+"Adjacent Countries : ");
				
				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
				
				int index = 0;
				for(String adjacent : adjacentList){
					
					if(index == 0)
						System.out.printf(adjacent);
					else
						System.out.printf("," + adjacent);
					
					index++;
				}//for(String adjacent : adjacentList)
				
			}//end if(player.equals(playerInfoVal[0])...
			
		}//end for(String playerInfoKey : Star...
	
	
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\n\n\n\t********************** CHEATER PLAYER REINFORCEMENT START ************************");

		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void placementRandomView(String player, String territory, ReinforcementPhaseModel obj){
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "Random Territory Chosen : " + territory);
		System.out.println("\t\t" + "Army units in territory updated from " + obj.getPrevArmies() + " to " + obj.getLatestArmies() );
		System.out.println("\t\t" + "Reinforcements Remaining : " + obj.getTotalReinforcementArmies());
		
		
		
	}
	
	public void placementAggressiveView(String player, String territory, ReinforcementPhaseModel obj){
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "A territory with MOST army units chosen : " + territory);
		System.out.println("\t\t" + "Army units in territory updated from " + obj.getPrevArmies() + " to " + obj.getLatestArmies() );
		System.out.println("\t\t" + "Reinforcements Remaining : " + obj.getTotalReinforcementArmies());

	}
	
	public void placementBenevolentView(String player, String territory, ReinforcementPhaseModel obj){
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "A territory with LEAST army units chosen : " + territory);
		System.out.println("\t\t" + "Army units in territory updated from " + obj.getPrevArmies() + " to " + obj.getLatestArmies() );
		System.out.println("\t\t" + "Reinforcements Remaining : " + obj.getTotalReinforcementArmies());

	}
	
	public void placementCheaterView(String player, String territory, ReinforcementPhaseModel obj){
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "Territory chosen : " + territory);
		System.out.println("\t\t" + "Army units in territory doubled. Updated from " + obj.getPrevArmies() + " to " + obj.getLatestArmies() );

	}

	public void placementHumanView(String player, String territory, ReinforcementPhaseModel obj){
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "Territory chosen : " + territory);
		System.out.println("\t\t" + "Army units in territory updated from " + obj.getPrevArmies() + " to " + obj.getLatestArmies() );
		System.out.println("\t\t" + "Reinforcements Remaining : " + obj.getTotalReinforcementArmies());

	}
	
}
