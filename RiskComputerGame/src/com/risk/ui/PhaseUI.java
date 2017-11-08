package com.risk.ui;

import java.util.*;




import com.risk.model.StartUpPhaseModel;

public class PhaseUI implements Observer {

	
	public static int attackCounter  = 0;
	public void reinforcementView(String player, HashMap<String,List<String>> territoryMap, HashMap<String,Integer> playerInfoOld){
		
		System.out.println("------------------------------------------------");
		System.out.println("********* REINFORCEMENTS FOR PLAYER " + player + " **********");
		System.out.println("------------------------------------------------");
		
		
		System.out.println("\n\t" + "As Per Territories Owned : " + "");
		System.out.println("\t" + "As Per Cards Exchanged : " + "");
		System.out.println("\t" + "As Per Continent Control Value : " + "");
		System.out.println("" + "TOTAL REINFORCEMENTS RECIEVED : ");
		
		
		System.out.println("\n" + "TERRITORIES OWNED BY PLAYER " + player + " -> ");
		
		/*
		System.out.println("\t" + "COUNTRY" + "owned in " + " continent "+"CONTINENT");
		System.out.println("\t" + "Armies in " + "COUNTRY" + " before reinforcement : " + "OLD-ARMIES");
		System.out.println("\t" + "Armies in " + "COUNTRY" + " after reinforcement : " + "OLD-ARMIES + NEW-ARMIES" + " = " + "UPDATED ARMIES");
		*/
		
		
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
		
			String[] playerInfoVal = playerInfoKey.split("-");
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				System.out.println("\t    " + playerInfoVal[1] + "owned in " + " continent " + playerInfoVal[2]);
				System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " before reinforcement : " + playerInfoOld.get(playerInfoKey));
				
				
				int newArmies =  StartUpPhaseModel.playerInfo.get(playerInfoKey) - playerInfoOld.get(playerInfoKey);
				
				System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " after reinforcement : " + playerInfoOld.get(playerInfoKey) + " + " + newArmies + " = " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
				System.out.println("\t    " + "Adjacent territories to " + playerInfoVal[1] + " are " );
				
				
				
				
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
				
			}//end if(player.equals(playe...
		
		}//end for(String playerInfoKey...

		
		System.out.println("----------------------------------------------------");
		System.out.println("******* REINFORCEMENT FOR PLAYER " + player + " ENDS ********");
		System.out.println("----------------------------------------------------");
		
		PhaseUI.attackCounter = 0;
		
	}
	
	
	public void forticationView(String player, HashMap<String,List<String>> territoryMap, HashMap<String,Integer> playerInfoOld, String fromTerr, String toTerr, int randomArmies){
		
		System.out.println("------------------------------------------------");
		System.out.println("********* FORTIFICATIONS FOR PLAYER " + player + " **********");
		System.out.println("------------------------------------------------");
		
		
		System.out.println("\n\t" + "Moving " + randomArmies + " Army Units from " + fromTerr + " to " + toTerr);
		
		System.out.println("\n\t" + "Army Units in " + fromTerr + " before moving army units : "  );
		System.out.println( "\t" + "Army Units in " + fromTerr + " after moving army units : "  );
		
		System.out.println("\n" + "Army Units in " + toTerr + " before recieving army units : "  );
		System.out.println("\t" + "Army Units in " + toTerr + " after recieving army units : "  );
		
		
		System.out.println("\n" + "TERRITORIES STATUS' OWNED BY PLAYER " + player + " AFTER FORTIFICATION PHASE -> ");
		
		
		
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
		
			String[] playerInfoVal = playerInfoKey.split("-");
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				
				System.out.println("\t    " + playerInfoVal[1] + "owned in " + " continent " + playerInfoVal[2]);
				
				if(fromTerr.equals(playerInfoVal[0]) || fromTerr == playerInfoVal[0] || toTerr.equals(playerInfoVal[0]) || toTerr == playerInfoVal[0]){
					
					System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " before fortification : " + playerInfoOld.get(playerInfoKey));
					
					System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " after fortification : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
					
					
				}
				
				else
					
					System.out.println("\t    " + "Armies in " + playerInfoVal[1] + " : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
					
				System.out.println("\t    " + "Adjacent territories to " + playerInfoVal[1] + " are " );
				
				System.out.println("\n\t-----------------------------------------------------------------------");
				
				
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
				
			}//end if(player.equals(playe...
		
		}//end for(String playerInfoKey...

		
		System.out.println("----------------------------------------------------");
		System.out.println("******* FORTIFICATION FOR PLAYER " + player + " ENDS ********");
		System.out.println("----------------------------------------------------");
		
		
	}//end method fortificationView
	
	
	
	
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
	
	public void update(Observable obs, Object obj){
		
	}
}