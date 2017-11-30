package com.risk.ui;

import java.util.*;



/**
 * The Class PlayerDominationUI. Obsolete Class.
 */
public class PlayerDominationUI implements Observer {

	/** The player domination map. */
	public static HashMap<String,Float> playerDominationMap = new  HashMap<String,Float>();
	
	/**
	 * Player domination view.
	 */
	public void playerDominationView(){
		
		int player = 0  ;
		System.out.println("Player " + player );
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable obs, Object x){
		
		
	}
}