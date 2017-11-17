package com.risk.ui;

import java.util.*;

import com.risk.model.*;

public class PlayerDominationView implements Observer {

	@Override
	public void update(Observable obs, Object arg) {
		
		displayPercentage(((PlayerDominationModel)obs).getDomination());
		
	}

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