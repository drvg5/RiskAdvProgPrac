package com.risk.ui;

import java.util.*;

import com.risk.model.*;


/**
 * The Class PlayerDominationView is an Observer class which uses one of its methods 
 * for printing the World Domination Statistics. 
 * It observes {@link com.risk.model.PlayerClass PlayerClass} objects.
 */
public class PlayerDominationView implements Observer {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		
		String message = ((PlayerClass)obs).msg;
		
		if(message.equals("domination") || message == "domination")
			displayPercentage(((PlayerClass)obs).getDominationNew());
		
	}

	/**
	 * Display percentage method displays the world domination statistics.
	 *
	 * @param domination the domination
	 */
	public void displayPercentage(HashMap<String,String> domination){
		
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("*************************** WORLD DOMINATION STATISTICS ********************************");
		System.out.println("----------------------------------------------------------------------------------------");
		
		
		
		
		for(String player : domination.keySet()){
			
			System.out.println("**                       PLAYER "+ player + " DOMINATES "+ domination.get(player)+ " OF THE TOTAL WORLD                    **");
		}
		
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("****************************************************************************************");
		System.out.println("----------------------------------------------------------------------------------------");
		
	}
}
