package com.risk.ui;

import java.io.IOException;
import java.util.*;

import com.risk.model.PlayerClass;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class ReinforcementsUI implements Observer {

	
	
	@Override
	public void update(Observable obs, Object arg) {
		// TODO Auto-generated method stub
		
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
			
			totalReinforcementsView(msgUISplit[1]);
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
						+ " total " +  terrPerContinentCount + " territories in " + continent);
				
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
	
	public void totalReinforcementsView(String player){
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.printf("\n\n\n\t" + " TOTAL REINFORCEMENTS RECIEVED BY PLAYER " + player + " : " );
		
		int total = ReinforcementPhaseModel.reinforcement.get(player);
		
		System.out.printf("" + total + "\n\n");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
