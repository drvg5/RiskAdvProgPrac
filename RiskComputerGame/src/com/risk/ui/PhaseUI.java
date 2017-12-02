package com.risk.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.risk.model.PlayerClass;
import com.risk.model.StartUpPhaseModel;

public class PhaseUI implements Observer {

	
	public static int attackCounter  = 0;
	
	public void preDeployingStartupDisplay(int numberOfPlayers, HashMap<String,List<String>> territoryMap){
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("***************************** START UP PHASE   ****************************");
		System.out.println("---------------------------------------------------------------------------");
		
		System.out.println("\n\t" + "TOTAL NUMBER OF TERRITORIES ON THE MAP : " + StartUpPhaseModel.totalTerr);
		System.out.println("\t" + "TOTAL NUMBER OF PLAYERS IN THE GAME : " + numberOfPlayers);
		System.out.println("\n");
		
		for(int plyr = 1 ; plyr <= numberOfPlayers; plyr++){
			
			System.out.printf("\t" + "Number of Territories assigned to PLAYER " + plyr + " : ");
			System.out.println(StartUpPhaseModel.terrPerPlayer.get(Integer.toString(plyr)));
			
			
		}
		
		System.out.println("\n\t" + "NUMBER OF ARMY UNITS PROVIDED INITIALLY TO EACH PLAYER : " + StartUpPhaseModel.initialArmies);
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int plyr = 1 ; plyr <= numberOfPlayers; plyr++){
		
			System.out.println("\n\n\t" + "TERRITORIES ASSIGNED TO PLAYER " + plyr + " -> ");
			
			for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
				
				String[] playerInfoVal = playerInfoKey.split("-");
				String player = Integer.toString(plyr);
				
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
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
				String s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}//end for(int plyr = 1 ; plyr...
		
		System.out.println("\n\n");
		System.out.println("\n\n\t******DEPLOYING ARMIES RANDOMLY IN ROUND ROBIN FASHION******");
//		System.out.println("---------------------------------------------------------------------------");
//		System.out.println("************************ START UP PHASE COMPLETED *************************");
//		System.out.println("---------------------------------------------------------------------------");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}//end preDeployingStartupDisplay
	
	public void postDeployingStartupDisplay(int numberOfPlayers, HashMap<String,List<String>> territoryMap){
		
		
	
		System.out.println("\n\n\t**************DEPLOYMENT OF ARMIES COMPLETED****************");

		
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\n\n\t************** TERRITORIES AFTER DEPLOYMENT ****************");
		
		for(int plyr = 1 ; plyr <= numberOfPlayers; plyr++){
		
			System.out.println("\n\n\t" + "TERRITORIES ASSIGNED TO PLAYER " + plyr + " -> ");
			
			for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
				
				String[] playerInfoVal = playerInfoKey.split("-");
				String player = Integer.toString(plyr);
				
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
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
				String s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}//end for(int plyr = 1 ; plyr...
		
		System.out.println("\n\n");
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("************************ START UP PHASE COMPLETED *************************");
		System.out.println("---------------------------------------------------------------------------");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}//end postDeployingStartupDisplay
	
	public void roundRobinHeader(){
		
//		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\n");
		System.out.println("*************************** ROUND ROBIN STARTS ****************************");
		System.out.println("\n");
//		System.out.println("---------------------------------------------------------------------------");
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void roundRobinsHeader(String roundrobins){
		
//		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\n\n\n");
		System.out.println("*************************** ROUND ROBIN " + roundrobins + " ****************************");
		System.out.println("\n");
//		System.out.println("---------------------------------------------------------------------------");
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reinforcementViewHeader(String player){
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("********************* REINFORCEMENTS FOR PLAYER " + player + " *************************");
		System.out.println("---------------------------------------------------------------------------");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void reinforcementView(String player, HashMap<String,List<String>> territoryMap){
		
				
		
		System.out.println("\n\t" + "STATUS OF TERRITORIES OWNED BY PLAYER " + player + " -> ");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
		
			String[] playerInfoVal = playerInfoKey.split("-");
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				System.out.println("\t    " + playerInfoVal[1] + " owned in " + " continent " + playerInfoVal[2]);
							
				
				
				
				System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
				System.out.printf("\t    " + "Adjacent territories to " + playerInfoVal[1] + " are " );
				
				
				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
				
				int index = 0;
				for(String adjacent : adjacentList){
					
					if(index == 0)
						System.out.printf(adjacent);
					else
						System.out.printf("," + adjacent);
					
					index++;
				}//for(String adjacent : adjacentList)
				
				System.out.println("\n\t-------------------------------------------------------------------");
				
				try {
					System.in.read();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//end if(player.equals(playe...
		
		}//end for(String playerInfoKey...

		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("**************** REINFORCEMENT PHASE FOR PLAYER " + player + " ENDS ********************");
		System.out.println("---------------------------------------------------------------------------");
		
		PhaseUI.attackCounter = 0;
		
	}
	
	public void preFortificationView(String player, HashMap<String,List<String>> territoryMap){
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("********************* FORTIFICATION FOR PLAYER " + player + " *************************");
		System.out.println("---------------------------------------------------------------------------");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "STATUS OF TERRITORIES OWNED BY PLAYER " + player + " -> ");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
		
			String[] playerInfoVal = playerInfoKey.split("-");
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				System.out.println("\t    " + playerInfoVal[1] + " owned in " + " continent " + playerInfoVal[2]);
				System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
				System.out.printf("\t    " + "Adjacent territories to " + playerInfoVal[1] + " are " );
				
				
				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
				
				int index = 0;
				for(String adjacent : adjacentList){
					
					if(index == 0)
						System.out.printf(adjacent);
					else
						System.out.printf("," + adjacent);
					
					index++;
				}//for(String adjacent : adjacentList)
				
				System.out.println("\n\t-------------------------------------------------------------------");
				
				try {
					System.in.read();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//end if(player.equals(playe...
		
		}//end for(String playerInfoKey...
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}//end method preFortificationView
	


	public void postFortificationView(String player,HashMap<String,List<String>> territoryMap){
		
		
		System.out.println("\n\t" + "TERRITORIES STATUS' OWNED BY PLAYER " + player + " AFTER FORTIFICATION PHASE -> \n");
		
		
		
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
		
			String[] playerInfoVal = playerInfoKey.split("-");
			
			
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				
				System.out.println("\t    " + playerInfoVal[1] + " owned in continent " + playerInfoVal[2]);
				
				System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
					
				System.out.printf("\t    " + "Adjacent territories to " + playerInfoVal[1] + " are " );
				
				
				
				
				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
				
				int index = 0;
				
				
				for(String adjacent : adjacentList){
					
					if(index == 0)
						System.out.printf(adjacent);
					else
						System.out.printf("," + adjacent);
					
					index++;
					
				}//for(String adjacent : adjacentList)
				
				System.out.println("\n\t-----------------------------------------------------------------------");
				try {
					System.in.read();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//end if(player.equals(playe...
		
		}//end for(String playerInfoKey...

		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("******************* FORTIFICATION PHASE FOR PLAYER " + player + " ENDS **********************");
		System.out.println("---------------------------------------------------------------------------------");
		
		
	}
	public void attackView(String player, String attacker, String attacked){
		
		System.out.printf("\n\t" + "Attacking Territory : " + attacker);
		System.out.printf("\t" + "Armies : ");
		System.out.println("\t" + "Number of Dice chosen : ");
		System.out.printf("\t" + "Defending Territory : " + attacked);
		System.out.printf("\t" + "Armies : ");
		System.out.println("\t" + "Number of Dice chosen : ");
		
		
	}//end method attack view
	
	public void diceRollView(){
	
		System.out.printf("\n\t" + "Attacker Dice Rolls : ");
		System.out.println("\t" + "Defender Dice Rolls : ");
		System.out.println("\tResult: ");
		
	}
	

	public void attackViewHeader(String player){
		
		System.out.println("------------------------------------------------");
		System.out.println("********* ATTACK PHASE OF PLAYER " + player + " **********");
		System.out.println("------------------------------------------------");
		
	}
	
	public void attackViewFooter(String player){
		
		System.out.println("------------------------------------------------");
		System.out.println("********** ATTACK OF PLAYER " + player + " ENDS***********");
		System.out.println("------------------------------------------------");
	}
	
	public void update(Observable obs, Object msg){
		
		
		String message = ((PlayerClass)obs).msg;
		
		
		
		
		if(message.equals("preDeployStartUp") || message == "preDeployStartUp"){
			
			
			
			preDeployingStartupDisplay(((PlayerClass) obs).players,((PlayerClass) obs).currentMap);
			
		}
		
		if(message.equals("postDeployStartUp") || message == "postDeployStartUp"){
			
			
			
			postDeployingStartupDisplay(((PlayerClass) obs).players,((PlayerClass) obs).currentMap);
			
		}
		
		if(message.equals("roundrobin") || message == "roundrobin"){
			
			roundRobinHeader();
			
		}
		
		if(message.contains("ROUND")) {
			
			String[] msgSplit = message.split(",");
			roundRobinsHeader(msgSplit[1]);
			
		}
	
		
		if(message.contains("reinforceHead")){
			
			String[] msgSplit = message.split(",");
			
			reinforcementViewHeader(msgSplit[1]);
		}
		
		if(message.contains("reinforce done")){
			
			String[] msgSplit = message.split(",");
			
			
			reinforcementView(msgSplit[1], ((PlayerClass) obs).currentMap);
		}
		
		if(message.contains("pre fortification")){
			
			String[] msgSplit = message.split(",");
			
			preFortificationView(msgSplit[1], ((PlayerClass) obs).currentMap);
		}
		
		if(message.contains("fortification done")){
			
			String[] msgSplit = message.split(",");
			
			postFortificationView(msgSplit[1], ((PlayerClass) obs).currentMap);
			
		}
		
		
		
		
	}
}