package com.risk.model;

import java.io.IOException;
import java.util.*;


/**
 * The Class FortificationPhaseModel is an Observable class which has methods for processing fortifications and also this class is being
 * observed by an Observer {@link com.risk.ui.FortificationUI FortificationUI}
 * to display the internal dyanmic details during the reinforcement phase.
 * @author Navjot Kaur Bhamrah
 * @see com.risk.ui.FortificationUI FortificationUI
 */
public class FortificationPhaseModel extends Observable {

	
	/** This TreeSet stores the territories in String values in form "fromTerr-toTerr" where
	 * fromTerr is the source territory from which fortifications can be sent and toTerr is the destination territory which can recieve 
	 * forces from fromTerr. Basically it stores paths available for fortifications.
	 * <p>Only applicable for Aggressive, Benevolent, Human and Random Player</p> 
	 */
	public static TreeSet<String> fortifySet = new TreeSet<String>();
	
	/** This TreeSet stores the territories of cheater player where the territory has adjacent territories belonging to other players.
	 */
	public static TreeSet<String> fortifySetCheater = new TreeSet<String>();

	/**
	 * This method allows the player to move the armies after attack phase has been completed.
	 * <p>
	 * The armies will only be moved if the there is adjacency between two countries and also
	 * If each territory has armies equal to or more than one.
	 * </p>
	 */

	public static int fortifySetEmpty = 0;

	/** The fortify units. */
	private int fortifyUnits ;
	
	/** The updated source armies. */
	private int updatedSource;
	
	/** The updated destination armies. */
	private int updatedDest;
	
	/** The source territory. */
	private String sourceTerr; 
	
	/** The destination territory. */
	private String destTerr;
	
	/** The player number. */
	private String player;
	
	/** The msg UI. */
	private String msgUI;
	
	/**
	 * Gets the msg UI.
	 *
	 * @return the msg UI
	 */
	public String getMsgUI() {
		return msgUI;
	}


	/**
	 * Sets the msg UI.
	 *
	 * @param msgUI the new msg UI
	 */
	public void setMsgUI(String msgUI) {
		this.msgUI = msgUI;
	}


	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public String getPlayer() {
		return player;
	}


	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(String player) {
		this.player = player;
	}


	/**
	 * Gets the destination terr.
	 *
	 * @return the destination terr
	 */
	public String getDestTerr() {
		return destTerr;
	}


	/**
	 * Sets the destination territory.
	 *
	 * @param destTerr the new destination terr
	 */
	public void setDestTerr(String destTerr) {
		this.destTerr = destTerr;
	}


	/**
	 * Gets the source territory.
	 *
	 * @return the source terr
	 */
	public String getSourceTerr() {
		return sourceTerr;
	}


	/**
	 * Sets the source territory.
	 *
	 * @param sourceTerr the new source terr
	 */
	public void setSourceTerr(String sourceTerr) {
		this.sourceTerr = sourceTerr;
	}


	/**
	 * Gets the fortify units.
	 *
	 * @return the fortify units
	 */
	public int getFortifyUnits() {
		return fortifyUnits;
	}


	/**
	 * Sets the fortify units.
	 *
	 * @param randomUnits the new fortify units
	 */
	public void setFortifyUnits(int randomUnits) {
		this.fortifyUnits = randomUnits;
	}

	/**
	 * Gets the updated source armies.
	 *
	 * @return the updated source armies.
	 */
	public int getUpdatedSource() {
		return updatedSource;
	}


	/**
	 * Sets the updated source armies.
	 *
	 * @param updatedSource the new updated source armies.
	 */
	public void setUpdatedSource(int updatedSource) {
		this.updatedSource = updatedSource;
	}

	
	/**
	 * Gets the updated destination amries.
	 *
	 * @return the updated destination armies
	 */
	public int getUpdatedDest() {
		return updatedDest;
	}


	/**
	 * Sets the updated destination armies.
	 *
	 * @param updatedDest the new updated destination armies
	 */
	public void setUpdatedDest(int updatedDest) {
		this.updatedDest = updatedDest;
	}

	/** The territory map. */
	public static HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>();
	
	/**
	 * Populates the fortifySet Treeset with the paths available for fortification.
	 * Only for aggressive, benevolent, random and human player
	 * @param player the player
	 * @param territoryMap the territory map
	 */
	public static void createFortifySet(String player,HashMap<String,List<String>> territoryMap){
		
	   fortifySet.clear();
		
       for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
    	   
    	   String[] keyArray = playerInfoKey.split("-");

    	   if(keyArray[0].equals(player) || keyArray[0] == player){
    		   
    		   
	    	   if(StartUpPhaseModel.playerInfo.get(playerInfoKey) >= 1){
	    		   
	    		   
	    		   
	    		   String continent = keyArray[2];
	    		   String territory = keyArray[1];
	    		   String mapKey = continent + "," + territory;
	    		   
	    		   //change to continentHashMap
	    		   List<String> adjacencyList = territoryMap.get(mapKey);
	    		   
	    		   //check if player own any adjacent territory to one which already owns
	    		   for(String adjacentTerr : adjacencyList){
	    			   
	    			   String key = player + "-" + adjacentTerr + "-" + StartUpPhaseModel.terrCont.get(adjacentTerr);
	    			   
	    			   if(StartUpPhaseModel.playerInfo.containsKey(key)){
	    				   
	    				   fortifySet.add(territory + "-" +adjacentTerr);
	    				   
	    			   }
	    			   
	    		   }//end for(String adjacentTerr : adjacencyList)
	    		   
	    	   }//end if(PlayerClass.playerInfo.get(playerInfoKey) >= 1)
	    	   
    	   }//end if(keyArray[0].equals(player) || keyArray == player){
    	   
       }//end for(String playerInfoKey : PlayerClass.playerInfo.keySet())
    	
	}//end method createFortifySet
	
	/**
	 * This method contains the process for fortification for random player.
	 *
	 * @param player the player
	 */
	public void randomFortification(String player){
		
		Random random = new Random();
		
		List<String> fortifyList = new ArrayList<String>(fortifySet);
		
		fortifySetEmpty = 0;
		
		setPlayer(player);
		
		if(fortifyList.isEmpty()){
			
			fortifySetEmpty = 1;
			
			setMsgUI("fortifyEmpty,");
			
			setPlayer(player);
			
			setChanged();
			
			notifyObservers(this);
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
			
		}
		
		
		setMsgUI("fortifyPathsPrint,");
		
		setPlayer(player);
		
		setChanged();
		
		notifyObservers(this);	
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String randomPath = fortifyList.get(random.nextInt(fortifyList.size()));
				
		String[] randomTerr = randomPath.split("-");
		
		String fromTerr = randomTerr[0];
		
		String toTerr = randomTerr[1];
		
		String fromCont = StartUpPhaseModel.terrCont.get(fromTerr);
		
		String toCont = StartUpPhaseModel.terrCont.get(toTerr);
		
		String from = player + "-" + fromTerr + "-" + fromCont;
		
		String to = player + "-" + toTerr + "-" + toCont;
		
		int fromArmies = StartUpPhaseModel.playerInfo.get(from);
		
		int toArmies = StartUpPhaseModel.playerInfo.get(to);
		
		int value = 0;
		
		if(fromArmies > 1){
			
			Random randomArmies = new Random();
			value = randomArmies.nextInt(fromArmies - 1) + 1; 
		}
		
		fromArmies = fromArmies - value;
		
		toArmies = toArmies + value;
		
		StartUpPhaseModel.playerInfo.put(from, fromArmies);
		
		StartUpPhaseModel.playerInfo.put(to, toArmies);
		
		setMsgUI("randomFortify,");
				
		setPlayer(player);
		
		setFortifyUnits(value);
		
		setUpdatedSource(fromArmies);
		
		setUpdatedDest(toArmies);
	
		setSourceTerr(fromTerr);
		
		setDestTerr(toTerr);
		
		setChanged();
		
		notifyObservers(this);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method contains the process for fortification for aggressive player.
	 *
	 * @param player the player
	 */
    public void aggressiveFortification(String player){
		
		List<String> fortifyList = new ArrayList<String>(fortifySet);
		
		fortifySetEmpty = 0;
		
		setPlayer(player);
		
		if(fortifyList.isEmpty()){
			
			fortifySetEmpty = 1;
			
			setMsgUI("fortifyEmpty,");
			
			setPlayer(player);
			
			setChanged();
			
			notifyObservers(this);
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;	
		}
		
		setMsgUI("fortifyPathsPrint,");
		
		setPlayer(player);
		
		setChanged();
		
		notifyObservers(this);
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		int highestArmies = 0;
				
		TreeSet<Integer> unitsSet = new TreeSet<Integer>();

		for(String path : fortifyList){
			
			String[] pathSplit = path.split("-");
			
			String fromTerr = pathSplit[0];
			
			String toTerr = pathSplit[1];
			
			
			//populate unitsSet with the different number armies of receiving territories
			for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

				String[] playerVals = playerInfoKey.split("-");

				if (playerVals[0].equals(player) || playerVals[0] == player) {
					
					if(playerVals[1].equals(toTerr) || playerVals[1] == toTerr){
						
						unitsSet.add(StartUpPhaseModel.playerInfo.get(playerInfoKey));
						
					}//end if(playerVals[1].equals(fromTerr) || playerVals[1] == fromTerr)

				}// end if(playerVals[0].equals(player) || playerVals[0] == player)

			}// end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
			
		}//end for(String path : fortifyList)
		
		
		//get the highest number of armies among the recieving territories
		highestArmies = unitsSet.last();
		
		
		// create a list of eligible keys from playerInfo HashMap
		// for only the player concerned with the highestArmies
		List<String> strongestTerritoryList = new ArrayList<String>();


		// populate strongestTerritoryList
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				if (StartUpPhaseModel.playerInfo.get(playerInfoKey) == highestArmies) {

					strongestTerritoryList.add(playerInfoKey);

				} // end if(StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestArmies)

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()

		Random randomKey = new Random();

		// choose strongest recieving territory to move armies into
		String randomStrongestKey = strongestTerritoryList.get(randomKey.nextInt(strongestTerritoryList.size()));
		
		
		
		String toTerr = randomStrongestKey.split("-")[1];
		
		String fromTerr = new String();
		
		//get fromTerr
		
		for(String path : fortifyList){
			
			String[] pathSplit = path.split("-");
			
			if(toTerr.equals(pathSplit[1])){
				
				fromTerr = pathSplit[0];
				break;
				
			}//end if(toTerr.equals(pathSplit[1]))
			
		}//end for(String path : fortifyList)
		
		int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomStrongestKey);
		
		String fromCont = StartUpPhaseModel.terrCont.get(fromTerr);
		
		String toCont = StartUpPhaseModel.terrCont.get(toTerr);
		
		String from = player + "-" + fromTerr + "-" + fromCont;
		
		String to = player + "-" + toTerr + "-" + toCont;
		
		int fromArmies = StartUpPhaseModel.playerInfo.get(from);
		
		int toArmies = StartUpPhaseModel.playerInfo.get(to);
		
		int value = fromArmies - 1;
		
		fromArmies = fromArmies - value;
		
		toArmies = toArmies + value;
		
		StartUpPhaseModel.playerInfo.put(from, fromArmies);
		
		StartUpPhaseModel.playerInfo.put(to, toArmies);
		
		setMsgUI("aggressiveFortify,");
		
		setPlayer(player);
		
		setFortifyUnits(value);
		
		setUpdatedSource(fromArmies);
		
		setUpdatedDest(toArmies);
	
		setSourceTerr(fromTerr);
		
		setDestTerr(toTerr);
		
		setChanged();
		
		notifyObservers(this);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}//end aggressiveFortification
	
	/**
	 * This method contains the process for fortification for benevolent player.
	 *
	 * @param player the player
	 */
	public void benevolentFortification(String player){
		

		List<String> fortifyList = new ArrayList<String>(fortifySet);
		
		fortifySetEmpty = 0;
		
		setPlayer(player);
		
		if(fortifyList.isEmpty()){
			
			fortifySetEmpty = 1;
			
			setMsgUI("fortifyEmpty,");
			
			setPlayer(player);
			
			setChanged();
			
			notifyObservers(this);
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;	
		}
		
		
		setMsgUI("fortifyPathsPrint,");
		
		setPlayer(player);
		
		setChanged();
		
		notifyObservers(this);
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		int lowestArmies = 0;
				
		TreeSet<Integer> unitsSet = new TreeSet<Integer>();

		for(String path : fortifyList){
			
			String[] pathSplit = path.split("-");
			
			String fromTerr = pathSplit[0];
			
			String toTerr = pathSplit[1];
			
			
			//populate unitsSet with the different number armies of receiving territories
			for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

				String[] playerVals = playerInfoKey.split("-");

				if (playerVals[0].equals(player) || playerVals[0] == player) {
					
					if(playerVals[1].equals(toTerr) || playerVals[1] == toTerr){
						
						unitsSet.add(StartUpPhaseModel.playerInfo.get(playerInfoKey));
						
					}//end if(playerVals[1].equals(fromTerr) || playerVals[1] == fromTerr)

				}// end if(playerVals[0].equals(player) || playerVals[0] == player)

			}// end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
			
		}//end for(String path : fortifyList)
		
		
		//get the lowest number of armies among the recieving territories
		lowestArmies = unitsSet.first();
		
		
		//create a list of eligible keys from playerInfo HashMap
		//for only the player concerned with the lowestArmies
		List<String> weakestTerritoryList = new ArrayList<String>();


		//populate weakestTerritoryList
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				if (StartUpPhaseModel.playerInfo.get(playerInfoKey) == lowestArmies) {

					weakestTerritoryList.add(playerInfoKey);

				} // end if(StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestArmies)

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()

		Random randomKey = new Random();

		// choose strongest recieving territory to move armies into
		String randomStrongestKey = weakestTerritoryList.get(randomKey.nextInt(weakestTerritoryList.size()));
		
		String toTerr = randomStrongestKey.split("-")[1];
		
		String fromTerr = new String();
		
		//get fromTerr
		
		for(String path : fortifyList){
			
			String[] pathSplit = path.split("-");
			
			if(toTerr.equals(pathSplit[1])){
				
				fromTerr = pathSplit[0];
				
				break;
				
			}//end if(toTerr.equals(pathSplit[1]))
			
		}//end for(String path : fortifyList)
		
		int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomStrongestKey);
		
		String fromCont = StartUpPhaseModel.terrCont.get(fromTerr);
		
		String toCont = StartUpPhaseModel.terrCont.get(toTerr);
		
		String from = player + "-" + fromTerr + "-" + fromCont;
		
		String to = player + "-" + toTerr + "-" + toCont;
		
		int fromArmies = StartUpPhaseModel.playerInfo.get(from);
		
		int toArmies = StartUpPhaseModel.playerInfo.get(to);
		
		int value = fromArmies - 1;
		
		fromArmies = fromArmies - value;
		
		toArmies = toArmies + value;
		
		StartUpPhaseModel.playerInfo.put(from, fromArmies);
		
		StartUpPhaseModel.playerInfo.put(to, toArmies);
		
		setMsgUI("benevolentFortify,");
		
		setPlayer(player);
		
		setFortifyUnits(value);
		
		setUpdatedSource(fromArmies);
		
		setUpdatedDest(toArmies);
	
		setSourceTerr(fromTerr);
		
		setDestTerr(toTerr);
		
		setChanged();
		
		notifyObservers(this);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}//end benevolentFortification
	
	/**
	 * This method contains the process for fortification for cheater player.
	 *
	 * @param player the player
	 */
	public void cheaterFortification(String player){
		
		fortifySetCheater.clear();

       for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
    	   
    	   String[] keyArray = playerInfoKey.split("-");

    	   if(keyArray[0].equals(player) || keyArray[0] == player){
    		      
    		   String continent = keyArray[2];
    		   String territory = keyArray[1];
    		   String mapKey = continent + "," + territory;
    		   
    		   //change to continentHashMap
    	//	   List<String> adjacencyList = territoryMap.get(mapKey);
    		   
    		   List<String> adjacencyList = PlayerClass.currentMap.get(mapKey);
    		   
    		   int otherPlayerAdjacentFlag = 0;
    		   //check if player has any adjacent territory owned by some other player to one which already owns
    		   for(String adjacentTerr : adjacencyList){
    			   
    			   String key = player + "-" + adjacentTerr + "-" + StartUpPhaseModel.terrCont.get(adjacentTerr);
    			   
    			   if(!StartUpPhaseModel.playerInfo.containsKey(key)){
    				   
    				   otherPlayerAdjacentFlag = 1;
    				   fortifySetCheater.add(playerInfoKey);
    				   break;
    				   
    			   }//end if(!StartUpPhaseModel.playerInfo.containsKey(key))
    			   
    		   }//end for(String adjacentTerr : adjacencyList)
	    		   
    	   }//end if(keyArray[0].equals(player) || keyArray == player){
    	   
       }//end for(String playerInfoKey : PlayerClass.playerInfo.keySet())
    	
	       
       //print territory status before reinforcement using StartUpPhaseModel.playerInfo
       
		setMsgUI("fortifyCheaterTerrPrint," + player);
		setChanged();
		notifyObservers(this);
		
		//update armies in each territory for cheater player
		for(String playerInfoKey : fortifySetCheater){
			
			String [] playerVals = playerInfoKey.split("-");
			
			int playerInfoValue = StartUpPhaseModel.playerInfo.get(playerInfoKey);
			
			String msg = "cheaterFortify," + playerVals[1].toUpperCase() + "," + playerInfoValue;
			
			
			playerInfoValue = 2 * playerInfoValue;
			
			setMsgUI(msg + "," + playerInfoValue);
			
			setPlayer(player);
			
			StartUpPhaseModel.playerInfo.put(playerInfoKey, playerInfoValue);
			
			setChanged();
			
			notifyObservers(this);

		}//end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
		
		
	}//end cheaterFortification
	
	/**
	 * This method contains the process for fortification for human player.
	 *
	 * @param player the player
	 */
	public void humanFortification(String player){
		
		List<String> fortifyList = new ArrayList<String>(fortifySet);
		
		fortifySetEmpty = 0;
		
		setPlayer(player);
		
		if(fortifyList.isEmpty()){
			
			fortifySetEmpty = 1;
			
			setMsgUI("fortifyEmpty,");
			
			setPlayer(player);
			
			setChanged();
			
			notifyObservers(this);
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;	
		}
		
		
		setMsgUI("fortifyPathsPrint,");
		
		setPlayer(player);
		
		setChanged();
		
		notifyObservers(this);	
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//need to print the territories which can be fortified
		
		
		
		String toTerr = new String();
		
		String fromTerr = new String();
		
		int fortifyingUnits;
		
		
		
		//take input from human user
		
		System.out.println("\n\t----------------------------------------------------------------------------");
		System.out.printf("\n\t" + "Please enter the territory to fortify : ");
		
		int msg = 0;
		
		
		while(true){

			if(msg == 1){
				System.out.println("\n\t------------------------ INCORRECT INPUT --------------------------");
				System.out.printf("\n\t" + "Please choose a territory to fortify. Please enter only a territory you own : ");
			}
			
			//Take territory input
			Scanner toTerritoryInput = new Scanner(System.in);
			toTerr = toTerritoryInput.nextLine().trim().toUpperCase();
			
			int foundFlag = 0;
			
			int count = 0;
			for(String path : fortifyList){
				
				String[] pathSplit = path.split("-");
				
				if(toTerr.equalsIgnoreCase(pathSplit[1])){
					
					fromTerr = pathSplit[0].toUpperCase();
					
					foundFlag = 1;
					
					count++;
					
				}//end if(toTerr.equals(pathSplit[1]))
				
			}//end for(String path : fortifyList)
			
			if(foundFlag == 0){
				
				msg = 1;
				continue;
				
			}
			
			if(count > 1){
				
				
				int msg2 = 0;
				System.out.println("\n\t----------------------------------------------------------------------------");
				System.out.printf("\n\t" + "Please enter the territory from which you would like to move the army units to " + toTerr +  " : ");
				
				while(true){

					if(msg2 == 1){
						System.out.println("\n\t------------------------ INCORRECT INPUT --------------------------");
					}
					System.out.printf("\n\t" + "Please enter only a territory which you own and is adjacent to " + toTerr + " : ");
					
					//Take territory input
					Scanner fromTerritoryInput = new Scanner(System.in);
					fromTerr = fromTerritoryInput.nextLine().trim().toUpperCase();
					
					int foundFrom = 0;		
					for(String path : fortifyList){
						
						String[] pathSplit = path.split("-");
						
						if(toTerr.equalsIgnoreCase(pathSplit[1])){
							
							if(fromTerr.equalsIgnoreCase(pathSplit[0])){
								
								foundFrom = 1;
																
							}
							
						}//end if(toTerr.equals(pathSplit[1]))
						
					}//end for(String path : fortifyList)
					
					
					if(foundFrom == 0){
						
						msg2 = 1;
						continue;
						
					}
					else
						break;
					
				}//end while(true)
			
			}//end if(count > 1)
			
			break;
			
		}//end while(true)
		
		
		int fromArmies = 0;
		int toArmies = 0;
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String [] playerInfoKeySplit = playerInfoKey.split("-");
			
			if(playerInfoKeySplit[1].equalsIgnoreCase(fromTerr)){
				fromArmies = StartUpPhaseModel.playerInfo.get(playerInfoKey);
				fromTerr = playerInfoKeySplit[1];
			}
			
			if(playerInfoKeySplit[1].equalsIgnoreCase(toTerr)){
				toArmies = StartUpPhaseModel.playerInfo.get(playerInfoKey);
				toTerr = playerInfoKeySplit[1];
		
			}
			
			
			
		}
		
		System.out.println("\n\t----------------------------------------------------------------------------");
		System.out.println("\n\t" + "You can move " + (fromArmies - 1) + " army units out of " + fromArmies + " army units present in "
							+ fromTerr.toUpperCase() + " to " + toTerr.toUpperCase() + ".");
		System.out.printf("\n\tPlease enter number of army units to move : ");
		
		msg = 0;
		
		while(true){
			
			if(msg == 1){
				
				System.out.println("\n\t------------------------ INCORRECT INPUT --------------------------");
				
				System.out.printf("\n\tPlease enter only 1 to " + (fromArmies - 1) + " army units to move : ");
					
			}
			
			//Take reinforcement armies input
			Scanner fortifyInput = new Scanner(System.in);
			
			if (!fortifyInput.hasNextInt()) {
				msg = 1;
				continue;
			}
			
			
			fortifyingUnits = fortifyInput.nextInt();
			
			if (fortifyingUnits <= 0) {
				msg = 1;
				continue;
			}
			
			
			if(fromArmies > fortifyingUnits)
				
				break;
			
			else{
				msg = 1;
				continue;
			}
		}//end while(true)	
		
		String fromCont = StartUpPhaseModel.terrCont.get(fromTerr);
		
		String toCont = StartUpPhaseModel.terrCont.get(toTerr);
		
		String from = player + "-" + fromTerr + "-" + fromCont;
		
		String to = player + "-" + toTerr + "-" + toCont;
		
		int value = fortifyingUnits;
		
		fromArmies = fromArmies - value;
		
		toArmies = toArmies + value;
		
		StartUpPhaseModel.playerInfo.put(from, fromArmies);
		
		StartUpPhaseModel.playerInfo.put(to, toArmies);
		
		setMsgUI("humanFortify,");
		
		setPlayer(player);
		
		setFortifyUnits(value);
		
		setUpdatedSource(fromArmies);
		
		setUpdatedDest(toArmies);
	
		setSourceTerr(fromTerr);
		
		setDestTerr(toTerr);
		
		setChanged();
		
		notifyObservers(this);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end humanFortification
	
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String [] args){
		
		HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>();
		
		List<String> adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Pakistan");
		adjList.add("Sri Lanka");
		territoryMap.put("Asia,India",adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Afghanistan");
		adjList.add("Iran");
		territoryMap.put("Asia,Pakistan",adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("India");
		adjList.add("Japan");
		adjList.add("Russia");
		territoryMap.put("Asia,China",adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Russia");
		territoryMap.put("Asia,Japan", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Pakistan");
		adjList.add("Iran");
		territoryMap.put("Asia,Afghanistan", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Japan");
		adjList.add("Canada");
		territoryMap.put("Asia,Russia", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Pakistan");
		adjList.add("Afghanistan");
		adjList.add("Greece");
		territoryMap.put("Asia,Iran", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("India");
		territoryMap.put("Asia,Sri Lanka", adjList);
		 
		
		adjList = new ArrayList<String>();
		adjList.add("France");
		adjList.add("Ireland");
		territoryMap.put("Europe,England", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("England");
		adjList.add("Spain");
		adjList.add("Germany");
		territoryMap.put("Europe,France", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("France");
		adjList.add("Italy");
		territoryMap.put("Europe,Spain", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("France");
		adjList.add("Ukraine");
		territoryMap.put("Europe,Germany", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Spain");
		adjList.add("Greece");
		adjList.add("Germany");
		territoryMap.put("Europe,Italy", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("England");
		adjList.add("Canada");
		territoryMap.put("Europe,Ireland", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Russia");
		adjList.add("Germany");
		territoryMap.put("Europe,Ukraine", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Italy");
		adjList.add("Iran");
		territoryMap.put("Europe,Greece", adjList);
	
		adjList = new ArrayList<String>();
		adjList.add("USA");
		territoryMap.put("North America,Mexico", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Canada");		
		territoryMap.put("North America,USA", adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Russia");	
		adjList.add("Ireland");
		adjList.add("USA");
		territoryMap.put("North America,Canada", adjList);
		
		/*
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
	*/
		
		

		int numberOfPlayers = 6;

		StartUpPhaseModel.preStartUp(numberOfPlayers, territoryMap);
		
//		ReinforcementPhaseModel.calculateReinforcement("1");
//		ReinforcementPhaseModel.calculateReinforcement("2");
//		ReinforcementPhaseModel.calculateReinforcement("3");
		
		System.out.println("----------------------------------------");
//		ReinforcementPhaseModel.reinforceRandom("1");
//		ReinforcementPhaseModel.reinforceRandom("2");
//		ReinforcementPhaseModel.reinforceRandom("3");
		
		
		System.out.println(StartUpPhaseModel.playerInfo);
		
		createFortifySet("1",territoryMap);
		System.out.println("--------\nPlayer1 ::"+fortifySet);
		System.out.println("--------\n"+StartUpPhaseModel.playerInfo);
		
//		randomFortification("1");
//		System.out.println("--------\nAfter::"+StartUpPhaseModel.playerInfo);
//		
//		createFortifySet("2",territoryMap);
//		System.out.println("--------\nPlayer2 ::"+fortifySet);
//		System.out.println("--------\n"+StartUpPhaseModel.playerInfo);
//		
//		randomFortification("2");
//		System.out.println("--------\nAfter::"+StartUpPhaseModel.playerInfo);
//		
//		createFortifySet("3",territoryMap);
//		System.out.println("--------\nPlayer3 ::"+fortifySet);
//		System.out.println("--------\n"+StartUpPhaseModel.playerInfo);
//		
//
//		randomFortification("3");
//		System.out.println("--------\nAfter::"+StartUpPhaseModel.playerInfo);
	}
	
	
}



