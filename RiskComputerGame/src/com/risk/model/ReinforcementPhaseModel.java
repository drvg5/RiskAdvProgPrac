package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class ReinforcementPhaseModel {

	public static HashMap<String,Integer> reinforcement = new HashMap<String,Integer>();
	public static HashMap<String,String> playerCards = new HashMap<String,String>();
	
	//public static int reinforcment;
	
	
	public static void calculateReinforcement(String player){
		
		int reinforcementArmies = 0;
		int playerTerr = 0;
		
		//calculate reinforcement armies by number of countries owned
		for(String plyrVal : StartUpPhaseModel.playerInfo.keySet()){
			String plyrInfo [] = plyrVal.split("-");
			if(plyrInfo[0].equals(player) || plyrInfo[0] == player){
				playerTerr ++ ; 
			}
		}
		
		
		if(playerTerr < 9 ){
			reinforcementArmies = reinforcementArmies + 3;
		}
		if(playerTerr >= 9){
			reinforcementArmies = reinforcementArmies + ((int) Math.floor((double)playerTerr/3));
		}
	
		
		//calculate reinforcement armies according to card values if any contained
		
		//calculate reinforcement armies if the player owns the whole continent
		
		reinforcement.put(player,reinforcementArmies);
		
	}
	
	
	public static String calcReinforcementsByTerr(String player){
		
		String reinTerrMsg;
		
		int reinforcementArmies = 0;
		int playerTerr = 0;
		
		
		//calculate reinforcement armies by number of countries owned
		for(String plyrVal : StartUpPhaseModel.playerInfo.keySet()){
			String plyrInfo [] = plyrVal.split("-");
			if(plyrInfo[0].equals(player) || plyrInfo[0] == player){
				playerTerr ++ ; 
			}
		}
		
		
		if(playerTerr < 9 ){
			reinforcementArmies = reinforcementArmies + 3;
		}
		if(playerTerr >= 9){
			reinforcementArmies = reinforcementArmies + ((int) Math.floor((double)playerTerr/3));
		}
		
		reinTerrMsg = playerTerr + "," + reinforcementArmies;
		
		reinforcement.put(player,reinforcementArmies);
		
		return reinTerrMsg;
	}
	
	
	public static ArrayList<String> calcReinforcementByCntrlVal(String player){
		
		ArrayList<String> cntrlValMsg = new ArrayList<String>();
				
		HashMap<String,Integer> playerContTerr = new HashMap<String,Integer>();
		
		for(String playerKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String[] playerKeySplit = playerKey.split("-");
			
			if(playerKeySplit[0].equals(player) || playerKeySplit[0] == player){
				
				if(!playerContTerr.isEmpty()){
					
					if(playerContTerr.containsKey(playerKeySplit[2])){
						
						int numberOfTerr = 	playerContTerr.get(playerKeySplit[2]);
						playerContTerr.put(playerKeySplit[2], numberOfTerr + 1);
						
					}
					else{
						playerContTerr.put(playerKeySplit[2],1);
					}
				}//end if(!playerContTerr.isEmpty())
				
				else{
					playerContTerr.put(playerKeySplit[2],1);
				}//end else for if(!playerContTerr.isEmpty())...
				
				
			}//end if(playerKeySplit[0].equals(player)...
			
		}//end for(String playerKey : Sta...
		
		for(String continent : StartUpPhaseModel.terrPerCont.keySet()){
			
			int territories = StartUpPhaseModel.terrPerCont.get(continent);
			
			if(playerContTerr.containsKey(continent)){
				
				int playerTerr = playerContTerr.get(continent);
				
				//if player has all the territories of a continent
				if(playerTerr == territories){
				
					
					//get current reinforcements
					int reinforcements = reinforcement.get(player);
					
					//get control value of the continent
					
					int cntrlVal  = ConfigureMapModel.continentControlValueHashMap.get(continent);
					
					cntrlValMsg.add(continent+","+reinforcements+","+cntrlVal);
					
					//add control Value of the continent owned to the reinforcment armies.
					reinforcement.put(player,reinforcements + cntrlVal);
					
				}//end if(playerTerr == territories)...
				
			}
			else
				continue;
			
		}//end for(String continent : StartUpPhase...
		
		return cntrlValMsg;
		
	}//end method calcReinforcementByCntrlVal
	
	
	public static void reinforceRandom(String player){
		
		
		int reinforcementArmies = reinforcement.get(player);
		
		
		//create a list of keys from playerInfo HashMap 
		//for only the player concerned
		List<String> playerInfoKeyList = new ArrayList<String>();
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String [] playerVals = playerInfoKey.split("-");
			if(playerVals[0].equals(player) || playerVals[0] == player){
				
				playerInfoKeyList.add(playerInfoKey);
			}
			
		}
		
		
		Random randomKey = new Random();
	      
		
		//loop until all reinforcement armies have been assigned
		while(reinforcementArmies != 0){
			
			
			//choose territory randomly to put armies into
			String randomPlayerKey = playerInfoKeyList.get(randomKey.nextInt(playerInfoKeyList.size()) );
			
			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomPlayerKey);
			playerInfoValue = playerInfoValue + 1;
			
			
			StartUpPhaseModel.playerInfo.put(randomPlayerKey, playerInfoValue);
			
			reinforcementArmies--;
			
			
		}
		
	}
	
	
	public static void main(String[] args){
		

		HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>(); 
			territoryMap.put("Asia,India", null);
			territoryMap.put("Asia,China", null);
			territoryMap.put("Asia,Pakistan", null);
			territoryMap.put("Asia,Japan", null);
			territoryMap.put("Asia,Afghanistan", null);
			territoryMap.put("Asia,Russia", null);
			territoryMap.put("Asia,Iran", null);
			territoryMap.put("Asia,Sri Lanka", null);
			 
			territoryMap.put("Europe,England", null);
			territoryMap.put("Europe,France", null);
			territoryMap.put("Europe,Spain", null);
			territoryMap.put("Europe,Germany", null);
			territoryMap.put("Europe,Italy", null);
			territoryMap.put("Europe,Ireland", null);
			territoryMap.put("Europe,Ukraine", null);
			territoryMap.put("Europe,Greece", null);
		
			territoryMap.put("North America,Mexico", null);
			territoryMap.put("North America,USA", null);
			territoryMap.put("North America,Canada", null);
			
			
			territoryMap.put("South America,Brazil", null);
			territoryMap.put("South America,Colombia", null);
			territoryMap.put("South America,Argentina", null);
			territoryMap.put("South America,Peru", null);
			
			territoryMap.put("Africa,Egypt", null);
			territoryMap.put("Africa,Zimbabwe", null);
			territoryMap.put("Africa,Libya", null);
			territoryMap.put("Africa,Sudan", null);
			territoryMap.put("Africa,South Africa", null);
			territoryMap.put("Africa,Ghana", null);
		
			
			int numberOfPlayers = 3;
			StartUpPhaseModel.preStartUp(numberOfPlayers,territoryMap);
			
			calculateReinforcement("1");
			calculateReinforcement("2");
			calculateReinforcement("3");
			
			System.out.println("----------------------------------------");
			reinforceRandom("1");
			reinforceRandom("2");
			reinforceRandom("3");
			System.out.println(StartUpPhaseModel.playerInfo);
	}
}
