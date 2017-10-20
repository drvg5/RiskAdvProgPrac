package com.risk.model;

import java.util.*;


public class StartUpPhase {

	/**
	 *playerInfo HashMap stores the values corresponding to a particular player
	 * <p>
	 *It stores in <strong>KEY</strong> as a  String of combination of player number, territory, continent
	 *each separated by a hyphen "-"
	 *</p>
	 *Examples 
	 *<ul>
	 *<li>2-Germany-Europe</li>
	 *<li>3-England-Europe</li>
	 *<li>2-India-Europe</li>
	 *</ul>
	 *<p>The <strong>VALUE</strong> to which each key points is the number of army units in that particular
	 *territory</p>
	 */
	public static HashMap<String,Integer> playerInfo = new HashMap<String,Integer>();
	
	/**
	 * HashMap for to store number of territories which must be assigned to each player initially
	 */
	public static HashMap<String,Integer> terrPerPlayer = new HashMap<String,Integer>();
	
	/**
	 *This HashMap stores a territory as <strong>KEY</strong> and continent to which a territory
	 *belongs as <strong>VALUE</strong>
	 */
	public static HashMap<String,String> terrCont = new HashMap<String,String>();
	
	
	/**
	 *This TreeMap stores the number of territories per continent.
	 */
	public static TreeMap<String,Integer> terrPerCont = new TreeMap<String,Integer>();
	
	
	/**
	 *This TreeSet is used to track whether a country has been alloted or not
	 *<p>
	 *Takes in input a String of (country+"-"+(0 or 1))
	 *0 if not taken; 1 if taken</p>
	 */
	public static TreeSet<String> countryTaken = new TreeSet<String>();
	
	
	/**
	 * This method populates {@link StartUpPhase#countryTaken} TreeSet and  {@link StartUpPhase#terrCont}  HashMap and HashMap to Number of Territories per Continent
	 * <p>
	 * This method calls method {@link StartUpPhase#terrPerPlayerPopulate} to populate the {@link StartUpPhase#terrPerPlayer} HashMap 
	 * and then calls {@link StartUpPhase#assignTerritories} to assign territories randomly
	 * </p>
	 * @param numberOfPlayers Total number of players in a game
	 * @param territoryMap    HashMap which stores the Game Map values
	 */
	public static void startUpPhase(int numberOfPlayers,HashMap<String, List<String>> territoryMap ){
	
		
		//Total no. of territories
		int totalTerr = 0;
		
		//populate countryTaken and terrCont
		for(String terrMapKey : territoryMap.keySet()){
			
			//String continentCount = null;
			totalTerr++;
			
			String[] keySplit = terrMapKey.split(",");
			String continent = keySplit[0];
			String territory = keySplit[1];
			
			//populate countryTaken
			countryTaken.add(territory+"-"+0);
			
			//populate TerrCont
			terrCont.put(territory, continent);
			
			//populate terrPerCont
			int continentCount = 0;
			if(terrPerCont.containsKey(continent)){
				
				continentCount = terrPerCont.get(continent);
				
			}
			
			terrPerCont.put(continent, continentCount + 1);
				

		}
		
	
		if(numberOfPlayers > totalTerr){
			System.out.println("Players can not be greater than the number of territories");
			
			GameDriver.playerGTTerr = 0;
			
			return;
		}
		
		
		
		GameDriver.playerGTTerr = 1;
		
		terrPerPlayerPopulate(numberOfPlayers,totalTerr);
		
		
		assignTerritories(numberOfPlayers, countryTaken, totalTerr);
		
		
		deployArmiesRandomly(numberOfPlayers);
		
		
		
		
	}
	
	
	/**
	 * This method populates the {@link StartUpPhase#terrPerPlayer} HashMap
	 * @param numberOfPlayers Number of Players in a game
	 * @param numberOfTerr	Number of territories in a whole map
	 */
	public static void terrPerPlayerPopulate(int numberOfPlayers, int numberOfTerr){
		
			int initialTerr = numberOfTerr/numberOfPlayers;
		
			int roundUpNumber= (int) Math.ceil((double)numberOfTerr/numberOfPlayers);
			
			
		      for(int pl =1; pl<= numberOfPlayers;pl++)
		      {
		    	  terrPerPlayer.put(String.valueOf(pl), initialTerr);
		      }
		      
		      int remainingTerr = numberOfTerr - (initialTerr * numberOfPlayers);
		      
		    
		      Random randomPlayer = new Random();
		      List<String> listOfPlayerKeys = new ArrayList<String>(terrPerPlayer.keySet());
		      
		      int remCount = remainingTerr;
		      
		      	      
		      
		     
		    //get random Players and assign them remaining countries one by one
		      while(remCount!=0)
		      {
		    
		    	    String randomPlayerKey = listOfPlayerKeys.get(randomPlayer.nextInt(numberOfPlayers) );
		    	    
		    	    if(terrPerPlayer.get(randomPlayerKey) < roundUpNumber )
		    	    {
		    	    	int newPlayerArmies = terrPerPlayer.get(randomPlayerKey) + 1;
			    	    terrPerPlayer.put(randomPlayerKey, newPlayerArmies);
			    	    remCount--;
		    	    }
		    	  	  
		    	    
		    	  
		      }
		
		
	} //end terrPerPlayerPopulate
	
	
	/**
	 * This method assigns territories randomly to the players
	 * and populate the {@link StartUpPhase#playerInfo} HashMap
	 * <p>
	 * This method while assigning also checks whether a country has already been assigned or not
	 * and that a player does not get all the countries in a continent
	 * </p>
	 * @param numberOfPlayers
	 * @param countryTaken
	 * @param numberOfTerr
	 */
	public static void assignTerritories(int numberOfPlayers,TreeSet<String> countryTaken,int numberOfTerr){
		
	      // Creating a List of TreeSet countryTaken elements
	      
	      
	      Random random = new Random();
	      
	      for(int pl = 1;pl<=numberOfPlayers;pl++){
	    	  
	    	  //count of total territories each player can have in a map
	    	  int countPT = 0;
	    	 
	    	  for(String plyr : terrPerPlayer.keySet()){
	    		  
	    		  if(plyr.equals(Integer.toString(pl)) || plyr == String.valueOf(pl)){
		    		  countPT = terrPerPlayer.get(plyr);
		    		 
	    		  }
	    		  else
	    			  continue;  
	    	  }
	    	  
	    	  
	    	//loop until the player gets the randomly decided total number of territories in a map 
	    	
		      while(countPT != 0){
		    	 
		    	  	  List<String> list = new ArrayList<String>(countryTaken);
		    	  	  //get territory randomly from list of country taken
			    	  String randomVal =  list.get(random.nextInt(list.size()));
			    	 
			    	  String[] randomTerr = randomVal.split("-");
			    	  
			    	//get continent for randomCountry from terrCont
					   
			    	  String randomTerrContinent = terrCont.get(randomTerr[0]);
			    	  
			    	//get randomTerrContinent count
			    	  int randomTerrContinentCount =  0 ;
			    	  randomTerrContinentCount = terrPerCont.get(randomTerrContinent);
			    	 
			    	  
			    	  //playerInfo HashMap iterate to calculate the randomTerrContinent count the player has
			    	  int continentTerrPlayerCount = 0;
			    	  
			    	  for(String plyrVals : playerInfo.keySet()){
			    		  String[] plyrInfo = plyrVals.split("-");
			    		  if(plyrInfo[0].equals(Integer.toString(pl)) || plyrInfo[0] == String.valueOf(pl)){
				    		  if(randomTerrContinent.equals(plyrInfo[2]) || randomTerrContinent == plyrInfo[2]){
				    			  continentTerrPlayerCount++;
				    		  }
			    		  }
			    	  }
			    	  
			    	  
			    	  String playerKey = null;
			    	
 
			    	  if(randomTerr[1].equals("0")){
				    	 
				    	 //Compare the continent territories value and the player has territories already in a continent
			    		//to make sure not all territories of a continent has been assigned to a player
				    	int terr = randomTerrContinentCount-1;
				   
					    if(continentTerrPlayerCount < terr){
						    	continentTerrPlayerCount = 0;
						    	
						    
				    	
					    	  playerKey = String.valueOf(pl)+"-"+randomTerr[0] + "-" + randomTerrContinent;
					    	  playerInfo.put(playerKey,0);
					    	  
					    	  //modify countryTaken to indicate that country has been assigned to a player
					    	  countryTaken.add(randomTerr[0]+"-1");
					    	 
					    	  countryTaken.remove(randomVal);
					    
					    	  
					    	  //decrease number of territory each player further can have 
					    	  countPT--;
					   } //end if(continentTerrPlayerCount < terr)
					    
				    	  
			    	  } //end if(randomTerr[1].equals("0"))
		 
		      } //end while(countPT != 0)
			
	    	  
	      }//end for(int pl = 1;pl<=numberOfPlayers;pl++)
	     
	}//end assignTerritories
	
	
	
	/**
	 * This method initially assigns armies to the territories randomly 
	 * and add these to appropriate places in the {@link StartUpPhase#playerInfo} HashMap
	 * <p>
	 * Each player is given 10 armies initially
	 * </p>
	 * @param numberOfPlayers
	 * @param numberOfTerr
	 */
	public static void deployArmiesRandomly(int numberOfPlayers){
			
		
		Random randomCountry = new Random();
		
		
		
		
		 for(int pl = 1;pl<=numberOfPlayers;pl++){
			 
			 List<String> playerCountryList = new ArrayList<String>();
			 
			 int armiesCount = 10;
			 
			 //populate playerCountryList
			 for(String playerInfo : StartUpPhase.playerInfo.keySet()){
				 
				 String [] playerInfoArr = playerInfo.split("-");
				 if(playerInfoArr[0].equals(Integer.toString(pl)) || playerInfoArr[0] == Integer.toString(pl)){
					 playerCountryList.add(playerInfo);
				 }
			 }
			 
			 //assign armies to territories until armiesCount is 0 for a player
			 while(armiesCount != 0){
				 
				//choose territory randomly to put armies into
				String randomChosenCountry = playerCountryList.get(randomCountry.nextInt(playerCountryList.size()) );
				 
				int playerInfoValue = StartUpPhase.playerInfo.get(randomChosenCountry);
				playerInfoValue = playerInfoValue + 1;
				
				StartUpPhase.playerInfo.put(randomChosenCountry, playerInfoValue);
				
				armiesCount--;
			 }//end while(armiesCount != 0)
			 
		 }//end for(int pl = 1;pl<=numberOfPlayers;pl++)
		
	}//end deployArmiesRandoml(int numberOfPlayers)
	
	
	public static void main(String [] args){
		

		HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>(); 
			territoryMap.put("Asia,India", null);
			territoryMap.put("Asia,China", null);
			territoryMap.put("Asia,Pakistan", null);
			territoryMap.put("Asia,Japan", null);
			territoryMap.put("Asia,Afghanistan", null);
			 
			territoryMap.put("Europe,England", null);
			territoryMap.put("Europe,France", null);
			territoryMap.put("Europe,Spain", null);
			territoryMap.put("Europe,Germany", null);
			territoryMap.put("Europe,Italy", null);
		
			territoryMap.put("North America,Mexico", null);
			territoryMap.put("North America,USA", null);
			
			
			territoryMap.put("South America,Brazil", null);
			territoryMap.put("South America,Colombia", null);
		
			
			int numberOfPlayers = 6;
			startUpPhase(numberOfPlayers,territoryMap);
	}
	
}
