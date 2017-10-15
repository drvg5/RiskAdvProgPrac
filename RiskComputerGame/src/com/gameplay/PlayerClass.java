package com.gameplay;

import java.util.*;
import java.util.Map.Entry;

public class PlayerClass {

	/**
	 * playerInfo HashMap stores the values corresponding to a particular player
	 * <p>
	 * It stores in <strong>KEY</strong> as a  String of combination of player number, territory, continent
	 * each separated by a hyphen "-"
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
	 *This TreeSet stores the number of territories per continent.
	 */
	public static TreeSet<String> terrPerCont = new TreeSet<String>();
	
	
	
	public static void startUpPhase(int players,HashMap<String, HashMap<String, List<String>>> continentMap ){
		
		//TreeSet to track whether a country has been alloted or not
		//Takes in input a String of (country+"-"+(0 or 1))
		//0 if not taken; 1 if taken
		TreeSet<String> countryTaken = new TreeSet<String>();
		
		
		
		//Total no. of territories
		int totalTerr = 0;

		
		int count = 0;
		
		
		//populate terrPerCont, countryTaken and contTerr
		for(String continent : continentMap.keySet()){
			
			String continentCount = null;
			count = 0;
			
			for(String country : continentMap.get(continent).keySet()){
				count ++ ;
				totalTerr++;
				
				
				//populate countryTaken
				countryTaken.add(country+"-"+0);
				//populate contTerr
				terrCont.put(country, continent);
			
			}
			continentCount = continent + "-" +count;
			
			//populate terrPerCont
			terrPerCont.add(continentCount);
			
			
		}
		
		
		terrPerPlayerPopulate(3,totalTerr);
		System.out.println("T per p"+terrPerPlayer);
		assignTerritories(3, terrPerCont, countryTaken, totalTerr);
		
		for(String k : playerInfo.keySet())
			System.out.println("key:"+k+":::::::value:"+playerInfo.get(k));
		
		
	}
	
	public static void terrPerPlayerPopulate(int players, int totalTerr){
		
		int initialUnit = totalTerr/players;
	      for(int pl =1; pl<= players;pl++){
	    	  terrPerPlayer.put(String.valueOf(pl), initialUnit);
	      }
	      
	      int remUnit = totalTerr - (initialUnit * players);
	      
	    
	      Random randomPl = new Random();
	      List<String> keyPl = new ArrayList<String>(terrPerPlayer.keySet());
	      
	      int remCount = remUnit;
	      
	    //get random Players and assign them remaining countries one by one
	      while(remCount!=0){
	    	  
	    	  String randomPlKey = keyPl.get(randomPl.nextInt(keyPl.size() ) );
	    	  int randomPlUnits = terrPerPlayer.get(randomPlKey) + 1;
	    	  terrPerPlayer.put(randomPlKey, randomPlUnits);
	    	  remCount--;
	    	  
	      }
	      
	}
	
	
	
	public static void assignTerritories(int players,TreeSet<String> terrPerCont,TreeSet<String> countryTaken,int totalTerr){
		
	      // Creating a List of TreeSet countryTaken elements
	      
	      
	      Random random = new Random();
	      
	      int loop = 0;
	      for(int i = 1;i<=players;i++){
	    	  
	    	  loop = i;
	    	  //count of territories each player can have
	    	  int countPT = 0;
	    	  
	    	  //count of territories each player have in a continent
	    	  int playerContCount = 0;
	    	
	    	  
	    	  for(String plyr : terrPerPlayer.keySet()){
	    		  
	    		  if(plyr.equals(Integer.toString(i)) || plyr == String.valueOf(i)){
		    		  countPT = terrPerPlayer.get(plyr);
		    		  System.out.println("countPT for "+i+" is "+countPT);
		    		 // break;
	    		  }
	    		  else
	    			  continue;
	    		  
	    	  }
	    	  
	    	  
		      while(countPT != 0){
		    	 
		    	  
		    	  	  List<String> list = new ArrayList<String>(countryTaken);
		    	  	  //get territory randomly from list of country taken
			    	  String randomVal =  list.get(random.nextInt(list.size()));
			    	  System.out.println("here:"+randomVal);
			    	  String[] randomTerr = randomVal.split("-");
			    	  
			    	  String randomTerrContinent = null ;
			    	  String playerKey = null;
			    	
			    	  
			    	  //check to make sure not all territories of a continent has been assigned to a player
			    	  //REMAINING
			    	  
			    	  
			    	  
			    	  //check to make sure player is assigned already randomly decided number of territories
			    	  if(countPT != 0){
			    		  
				    	  //check if a territory has been assigned or not 
				    	  if(randomTerr[1].equals("0")){
				    		  
				    		  
					    	  
					    	  //get continent for randomCountry from terrCont
					   
					    		  randomTerrContinent = terrCont.get(randomTerr[0]);
					    	  
					    	  
					    	  playerKey = String.valueOf(i)+"-"+randomTerr[0] + "-" + randomTerrContinent;
					    	  playerInfo.put(playerKey,0);
					    	  //modify countryTaken to indicate that country has been assigned to a player
					    	  countryTaken.add(randomTerr[0]+"-1");
					    	 
					    	  countryTaken.remove(randomVal);
					    	  System.out.println(countryTaken);
					    	  
					    	  //decrease number of terr each player can have now
					    	  countPT--;
				    	  }
			    	  
			    	  }
			    	  
			    
			    	//  System.out.println("Random Country--" + randomTerr[0] +": "+randomTerr[1]);
			    	 // j++;
			    	 
		      }
			
	    	  
	      }
	     
	}
	
	
	public static void main(String [] args){
		
		HashMap<String, HashMap<String, List<String>>> continentMap = new HashMap<String, HashMap<String, List<String>>>();
		HashMap<String,List<String>> country = new HashMap(); 
			country.put("India", null);
			country.put("China", null);
			country.put("Pakistan", null);
			country.put("Japan", null);
			
			continentMap.put("Asia", country);
			country = new HashMap(); 
			country.put("England", null);
			country.put("France", null);
			country.put("Spain", null);
			country.put("Germany", null);
			
			continentMap.put("Europe", country);
		//	continentMap.put("Asia,3", null);
		//	continentMap.put("Asia,4", null);
			startUpPhase(4,continentMap);
	}
	
}
