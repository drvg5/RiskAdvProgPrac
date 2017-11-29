package com.risk.ui;

import java.io.IOException;
import java.util.*;

import com.risk.model.FortificationPhaseModel;


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
			
			
			
		}
		
		if(msgUI.contains("humanFortify")){
			
			
			
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
	
	
	public void displayAggressiveFortification(String player,String fromTerr, String toTerr, int randomArmies, int updatedFromArmies, int updatedToArmies){
		
		
		System.out.println("\n\n\t***************PERFORMING FORTIFICATION ***********************");
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\t" + "Source Territory : " + fromTerr);
		System.out.println("\t" + "Eligible STRONGEST destination territory : " + toTerr);
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
		
		
	}//end displayAggressiveFortification
	
	
	
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
