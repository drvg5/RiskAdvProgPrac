package com.risk.ui;

import java.util.*;

public class CardExchangeUI implements Observer {

	public static void main(String[] args) {
		
		CardExchangeUI obj = new CardExchangeUI();
		
	}
	
	
	public void cardExchangeView(String player, String msg){
		
		
		
		System.out.println("\n\n\t******CALCULATION OF REINFORCEMENTS BY EXCHANGE OF CARDS******");
		System.out.println("Cards");
		
		
		
		System.out.println("------------------------------------------------");
		System.out.println("******** CARDS EXCHANGING FOR PLAYER " + player + " *********");
		System.out.println("------------------------------------------------");
		
		System.out.println("\n" + "Cards Available for exchange : ");
		System.out.println("\t" + "Number of Artillery Cards : " + "");
		System.out.println("\t" + "Number of Infantry Cards : " + "");
		System.out.println("\t" + "Number of Cavalry Cards : " + "");
		
		
		System.out.println("\n" + "Cards Chosen for Exchange : ");
		System.out.println("\t" + "Artillery : " + "");
		System.out.println("\t" + "Infantry : " + "");
		System.out.println("\t" + "Cavalry : " + "");
		
		
		System.out.println("\n" + "Reinforcements recieved in current exchange : ");
		System.out.println("\n" + "Reinforcements recieved in previous exchange : ");
		
		System.out.println("\n" + "Cards Available after exchange : ");
		System.out.println("\t" + "Number of Artillery Cards : " + "");
		System.out.println("\t" + "Number of Infantry Cards : " + "");
		System.out.println("\t" + "Number of Cavalry Cards : " + "");
		System.out.println("\n" );
		System.out.println("------------------------------------------------");
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
		
		
	
	
	


}



	


	