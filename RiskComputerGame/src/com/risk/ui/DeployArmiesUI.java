package com.risk.ui;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.risk.model.StartUpPhaseModel;

/**
 * DeployArmiesUI has view to show when each player places its armies one by
 * one on its territories in a round robin fashion
 * @author ashish
 */

public class DeployArmiesUI implements Observer{

	
	
	public void displayDeploy(String player, String territory, int beforeDeployUnits, int afterDeployUnits, int remainingUnits){
		
		System.out.println("\n\tTerritory chosen by PLAYER " + player + " --> " + territory);
		System.out.println("\tArmy units currently in " + territory + " --> " + beforeDeployUnits);
		System.out.println("\tArmy units after update in " + territory + " --> " + afterDeployUnits);
		
		if(remainingUnits == 0){
			  
			System.out.println("\tNo army units remaining to deploy for PLAYER " + player);
			
		}
		else{
			
			System.out.println("\tArmies remaining for PLAYER " + player + " --> " + remainingUnits);
			
		}
		
		System.out.println("\t----------------------------------------------------");
		

		
	
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		// TODO Auto-generated method stub
		
		String player = ((StartUpPhaseModel) obs).getCurrentPlayer();
		String territory = ((StartUpPhaseModel) obs).getChosenRandomTerritory();
		int beforeDeployUnits = ((StartUpPhaseModel) obs).getBeforeDeployUnits();
		int afterDeployUnits = ((StartUpPhaseModel) obs).getAfterDeployUnits();
		int remainingUnits = ((StartUpPhaseModel) obs).getRemainingUnits();
		
		displayDeploy(player,territory,beforeDeployUnits,afterDeployUnits,remainingUnits);
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
