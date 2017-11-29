package com.risk.ui;

import java.io.IOException;
import java.util.*;

import com.risk.model.FortificationPhaseModel;
import com.risk.model.StartUpPhaseModel;


public class FortificationUI implements Observer {

	
	
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
					msgSplit[2]);
			
		}
		
		if(msgUI.contains("fortifyPathsPrint")){
			
			displayFortifyPath();
			
		}
		
		if(msgUI.contains("fortifyCheaterTerrPrint")){
			
			displayFortifyCheaterTerr();
			
		}
		
		if(FortificationPhaseModel.fortifySetEmpty == 1){
			displayFortificationNotPossible(obj.getPlayer());
		}
		else
			displayFortification(obj.getPlayer(),obj.getSourceTerr(),obj.getDestTerr(),obj.getFortifyUnits(), obj.getUpdatedSource(), obj.getUpdatedDest());
		
	}
	
	public void displayFortificationNotPossible(String player){
		
		
		System.out.println("\n\n\t****** NO TWO ADJACENT COUNTRIES ARE OWNED BY PLAYER "+ player + " ********\n");
		
	}
	
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
		System.out.println("\t" + "User chosen number of armies : " + toTerr);
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
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}//end displayHumanFortification

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
			
			System.out.println("\n\t Territory : " + keySplit[0].toUpperCase() + "\t Continent : " + keySplit[1].toUpperCase() + "\t Armies : " 
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
