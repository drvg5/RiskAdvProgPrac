package com.risk.ui;

import java.io.IOException;
import java.util.*;

import com.risk.model.FortificationPhaseModel;
import com.risk.model.StartUpPhaseModel;



/**
 * The Class FortificationUI is an Observer observing the elements of {@link com.risk.model.FortificationPhaseModel FortificationPhaseModel} Class.
 * @author Navjot Kaur Bhamrah
 */
public class FortificationUI implements Observer {

	
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		
		FortificationPhaseModel obj = (FortificationPhaseModel)obs;
		
		String msgUI = obj.getMsgUI();
		
		if(msgUI.contains("fortifyEmpty")){
				
			displayFortificationNotPossible(obj.getPlayer());
			
		}
		
		if(msgUI.contains("randomFortify")){
			
			displayRandomFortification(obj.getPlayer(),obj.getSourceTerr(),obj.getDestTerr(),
					obj.getFortifyUnits(), obj.getUpdatedSource(), obj.getUpdatedDest());
			
			
		}
		
		if(msgUI.contains("aggressiveFortify")){
			
			displayAggressiveFortification(obj.getPlayer(),obj.getSourceTerr(),obj.getDestTerr(),
					obj.getFortifyUnits(), obj.getUpdatedSource(), obj.getUpdatedDest());
			
		}
		
		if(msgUI.contains("benevolentFortify")){
			
			displayBenevolentFortification(obj.getPlayer(),obj.getSourceTerr(),obj.getDestTerr(),
					obj.getFortifyUnits(), obj.getUpdatedSource(), obj.getUpdatedDest());
			
		}
		
		if(msgUI.contains("humanFortify")){
			
			displayHumanFortification(obj.getPlayer(),obj.getSourceTerr(),obj.getDestTerr(),
					obj.getFortifyUnits(), obj.getUpdatedSource(), obj.getUpdatedDest());
			
		}
		
		if(msgUI.contains("cheaterFortify")){
			
			String [] msgSplit = msgUI.split(",");
			displayCheaterFortification(obj.getPlayer(),msgSplit[1],msgSplit[2],
					msgSplit[3]);
			
		}
		
		if(msgUI.contains("fortifyPathsPrint")){
			
			displayFortifyPath();
			
		}
		
		if(msgUI.contains("fortifyCheaterTerrPrint")){
			
			displayFortifyCheaterTerr();
			
		}
		
//		if(FortificationPhaseModel.fortifySetEmpty == 1){
//			displayFortificationNotPossible(obj.getPlayer());
//		}
//		else
//			displayFortification(obj.getPlayer(),obj.getSourceTerr(),obj.getDestTerr(),obj.getFortifyUnits(), obj.getUpdatedSource(), obj.getUpdatedDest());
//		
	}
	
	/**
	 * Display View for fortification not possible.
	 *
	 * @param player the player
	 */
	public void displayFortificationNotPossible(String player){
		
		
		System.out.println("\n\n\t****** NO TWO ADJACENT COUNTRIES ARE OWNED BY PLAYER "+ player + " ********\n");
		
	}
	
	/**
	 * Displays the fortification process for Random Player.
	 *
	 * @param player the player
	 * @param fromTerr Source Territory
	 * @param toTerr Destination Territory
	 * @param randomArmies the random armies chosen for fortification
	 * @param updatedFromArmies the updated source territory armies 
	 * @param updatedToArmies the updated destination territory armies 
	 */
	public void displayRandomFortification(String player,String fromTerr, String toTerr, int randomArmies, int updatedFromArmies, int updatedToArmies){
		
		
		System.out.println("\n\n\t***************PERFORMING FORTIFICATION ***********************");
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\t" + "Randomly chosen source territory : " + fromTerr);
		System.out.println("\t" + "Randomly chosen adjacent destination territory : " + toTerr);
		System.out.println("\n\t" + "Moving " + randomArmies + " Army Units from " + fromTerr + " to " + toTerr);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int prevFromArmies = updatedFromArmies + randomArmies;
		int prevToArmies = updatedToArmies - randomArmies;
		
		System.out.println("\n\t" + "Army Units in " + fromTerr + " before moving army units : " + prevFromArmies );
		System.out.println( "\t" + "Army Units remaining in " + fromTerr + " after moving army units : "  + updatedFromArmies);
		
		System.out.println("\n\t" + "Army Units in " + toTerr + " before recieving army units : "  + prevToArmies );
		System.out.println("\t" + "Army Units in " + toTerr + " after recieving army units : " +  updatedToArmies);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Displays the fortification process for Aggressive Player.
	 *
	 * @param player the player
	 * @param fromTerr Source Territory
	 * @param toTerr Destination Territory
	 * @param armies the armies chosen for fortification
	 * @param updatedFromArmies the updated source territory armies 
	 * @param updatedToArmies the updated destination territory armies 
	 */
	public void displayAggressiveFortification(String player,String fromTerr, String toTerr, int armies, int updatedFromArmies, int updatedToArmies){
		
		
		System.out.println("\n\n\t***************PERFORMING FORTIFICATION ***********************");
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\t" + "Source Territory : " + fromTerr);
		System.out.println("\t" + "Eligible STRONGEST destination territory : " + toTerr);
		System.out.println("\n\t" + "Moving " + armies + " Army Units from " + fromTerr + " to " + toTerr);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int prevFromArmies = updatedFromArmies + armies;
		int prevToArmies = updatedToArmies - armies;
		
		System.out.println("\n\t" + "Army Units in " + fromTerr + " before moving army units : " + prevFromArmies );
		System.out.println( "\t" + "Army Units remaining in " + fromTerr + " after moving army units : "  + updatedFromArmies);
		
		System.out.println("\n\t" + "Army Units in " + toTerr + " before recieving army units : "  + prevToArmies );
		System.out.println("\t" + "Army Units in " + toTerr + " after recieving army units : " +  updatedToArmies);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//end displayAggressiveFortification
	
    /**
	 * Displays the fortification process for Benevolent Player.
	 *
	 * @param player the player
	 * @param fromTerr Source Territory
	 * @param toTerr Destination Territory
	 * @param armies the armies chosen for fortification
	 * @param updatedFromArmies the updated source territory armies 
	 * @param updatedToArmies the updated destination territory armies 
	 */
    public void displayBenevolentFortification(String player,String fromTerr, String toTerr, int armies, int updatedFromArmies, int updatedToArmies){
		
		
		System.out.println("\n\n\t***************PERFORMING FORTIFICATION ***********************");
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\t" + "Source Territory : " + fromTerr);
		System.out.println("\t" + "Eligible WEAKEST destination territory : " + toTerr);
		System.out.println("\n\t" + "Moving " + armies + " Army Units from " + fromTerr + " to " + toTerr);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int prevFromArmies = updatedFromArmies + armies;
		int prevToArmies = updatedToArmies - armies;
		
		System.out.println("\n\t" + "Army Units in " + fromTerr + " before moving army units : " + prevFromArmies );
		System.out.println( "\t" + "Army Units remaining in " + fromTerr + " after moving army units : "  + updatedFromArmies);
		
		System.out.println("\n\t" + "Army Units in " + toTerr + " before recieving army units : "  + prevToArmies );
		System.out.println("\t" + "Army Units in " + toTerr + " after recieving army units : " +  updatedToArmies);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//end displayBenevolentFortification
	
    /**
	 * Displays the fortification process for Human Player.
	 *
	 * @param player the player
	 * @param fromTerr Source Territory
	 * @param toTerr Destination Territory
	 * @param armies the armies chosen for fortification
	 * @param updatedFromArmies the updated source territory armies 
	 * @param updatedToArmies the updated destination territory armies 
	 */
    public void displayHumanFortification(String player,String fromTerr, String toTerr, int armies, int updatedFromArmies, int updatedToArmies){
		
		
		System.out.println("\n\n\t***************PERFORMING FORTIFICATION ***********************");
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\t" + "User chosen Source Territory : " + fromTerr);
		System.out.println("\t" + "User chosen Destination territory : " + toTerr);
		System.out.println("\t" + "User chosen number of armies : " + armies);
		System.out.println("\n\t" + "Moving " + armies + " Army Units from " + fromTerr + " to " + toTerr);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int prevFromArmies = updatedFromArmies + armies;
		int prevToArmies = updatedToArmies - armies;
		
		System.out.println("\n\t" + "Army Units in " + fromTerr + " before moving army units : " + prevFromArmies );
		System.out.println( "\t" + "Army Units remaining in " + fromTerr + " after moving army units : "  + updatedFromArmies);
		
		System.out.println("\n\t" + "Army Units in " + toTerr + " before recieving army units : "  + prevToArmies );
		System.out.println("\t" + "Army Units in " + toTerr + " after recieving army units : " +  updatedToArmies);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//end displayHumanFortification
	
    /**
     * Displays the fortification process for Cheater Player.
     *
     * @param player the player
     * @param terr the territory
     * @param prevArmyUnits the army units in territory before increment.
     * @param doubledArmyUnits the doubled army units in territory before increment.
     */
    public void displayCheaterFortification(String player,String terr, String prevArmyUnits, String doubledArmyUnits){
		
		
		
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\t" + "Territory : " + terr );
		System.out.println("\n\t" + "Army Units in " + terr + " before : " + prevArmyUnits );
		System.out.println("\n\t" + "Army Units in " + terr + " after being doubled: " + doubledArmyUnits );
		
	
		System.out.println("\t---------------------------------------------------------------------------\n");
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}//end displayHumanFortification

    /**
     * Display fortify paths for Aggressive, Benevolent, Human and Random players.
     */
    public void displayFortifyPath(){
    	
    	
    	System.out.println("\n\n\t************* PATHS AVAILABLE FOR FORTIFICATION ***************");
    	
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String key : FortificationPhaseModel.fortifySet){
			
			String [] keySplit = key.split("-");
			System.out.println("\n\t" + keySplit[0].toUpperCase() +" to " + keySplit[1].toUpperCase());
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end for(String key : FortificationPhaseModel.fortifySet)
    	
    }//end displayFortifyPath
	
    /**
     * Display Cheater Territories which shares a border with other players' territories.
     */
    public void displayFortifyCheaterTerr(){
    	
    	
    	System.out.println("\n\n\t**** TERRITORIES WITH NEIGHBOURS BELONGING TO OTHER PLAYERS ***");
    	
    	try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String key : FortificationPhaseModel.fortifySetCheater){
			
			String [] keySplit = key.split("-");
			
			System.out.println("\n\t Territory : " + keySplit[1].toUpperCase() + "\t Continent : " + keySplit[2].toUpperCase() + "\t Armies : " 
					+ StartUpPhaseModel.playerInfo.get(key));
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end for(String key : FortificationPhaseModel.fortifySet)
    	
		System.out.println("\n\n\t************** DOUBLING ABOVE TERRITORIES' ARMIES *************");
    	
    }
    
	/**
	 * Display fortification. Obsolete Method.
	 *
	 * @param player the player
	 * @param fromTerr the from terr
	 * @param toTerr the to terr
	 * @param randomArmies the random armies
	 * @param updatedFromArmies the updated from armies
	 * @param updatedToArmies the updated to armies
	 */
	public void displayFortification(String player,String fromTerr, String toTerr, int randomArmies, int updatedFromArmies, int updatedToArmies){
		
		                   
		System.out.println("\n\n\t***************PERFORMING FORTIFICATION ***********************");
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\t" + "Randomly chosen source territory : " + fromTerr);
		System.out.println("\t" + "Randomly chosen adjacent destination territory : " + toTerr);
		System.out.println("\n\t" + "Moving " + randomArmies + " Army Units from " + fromTerr + " to " + toTerr);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int prevFromArmies = updatedFromArmies + randomArmies;
		int prevToArmies = updatedToArmies - randomArmies;
		
		System.out.println("\n\t" + "Army Units in " + fromTerr + " before moving army units : " + prevFromArmies );
		System.out.println( "\t" + "Army Units remaining in " + fromTerr + " after moving army units : "  + updatedFromArmies);
		
		System.out.println("\n\t" + "Army Units in " + toTerr + " before recieving army units : "  + prevToArmies );
		System.out.println("\t" + "Army Units in " + toTerr + " after recieving army units : " +  updatedToArmies);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
