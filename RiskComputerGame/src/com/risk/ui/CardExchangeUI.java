package com.risk.ui;

import java.util.Observable;
import java.util.Observer;

public class CardExchangeUI implements Observer {

	
	public void cardExchangeView(String player){
		
		System.out.println("--------------------------------------");
		System.out.println("|       CARDS EXCHANGING FOR PLAYER " + player + "        |");
		System.out.println("--------------------------------------");
		
		System.out.println("\t\nCards Available for exchange : ");
		System.out.println("\t\t"+"Number of Artillery Cards : " + "");
		System.out.println("\t\t"+"Number of Infantry Cards : " + "");
		System.out.println("\t\t"+"Number of Cavalry Cards : " + "");
		
		
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		
	}
	

}
