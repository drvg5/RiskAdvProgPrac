package com.risk.ui;

import java.io.IOException;
import java.util.*;

import com.risk.model.PlayerClass;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.utility.RiskConstants;

public class CardExchangeUI implements Observer {


	
	
	public void cardExchangeView(String player, String msg){
		
		
		
		System.out.println("\n\n\t******CALCULATION OF REINFORCEMENTS BY EXCHANGE OF CARDS******");
		
		
		int card1 = 0;
		int card2 = 0;
		int card3 = 0;
		
		
		if(!ReinforcementPhaseModel.prevCards.isEmpty()){
			
			System.out.println("\n\t" + "Cards available to PLAYER " + player + " -> ");
			
			for(String cardKey : ReinforcementPhaseModel.prevCards){
				if(cardKey.equals(RiskConstants.CARD_1) || cardKey == RiskConstants.CARD_1)
					card1++;
				if(cardKey.equals(RiskConstants.CARD_2) || cardKey == RiskConstants.CARD_2)
					card2++;
				if(cardKey.equals(RiskConstants.CARD_3) || cardKey == RiskConstants.CARD_3)
					card3++;
			}//end for(String cardKey : cardsList)
			
			
			
			if(card1 > 0)
			System.out.println("\t\t*"+RiskConstants.CARD_1+ " : " + card1);
			
			if(card2 > 0)
			System.out.println("\t\t*"+RiskConstants.CARD_2+ " : " + card2);
			
			if(card3 > 0)
			System.out.println("\t\t*"+RiskConstants.CARD_3+ " : " + card3);
		
		}
		else
			System.out.println("\n\t" + "Cards Available to PLAYER " + player + " -> NONE");
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(msg.equals("not enough cards") || msg == "not enough cards"){
			
			if(!ReinforcementPhaseModel.prevCards.isEmpty()){
			System.out.println("\n\t" + "THE CARDS ARE INELIGIBLE FOR EXCHANGE.");
			}
			
			System.out.println("\t" + "EITHER 3 SIMILAR CARDS OR 3 DIFFERENT CARDS CAN BE EXCHANGED");
			System.out.println("\n\n\t" + ReinforcementPhaseModel.getCardArmies() + " REINFORCEMENTS RECIEVED AS NO CARD EXCHANGE PERFORMED"  );
			
		}
		
		else if(msg.contains("enough cards,")){
			
			
			String [] msgSplit = msg.split(",");
			
			if(msgSplit[1].equalsIgnoreCase("no") || msgSplit[1] == "no" || msgSplit[1].equalsIgnoreCase("yes") || msgSplit[1] == "yes")
				System.out.println("\n\t" + "Whether Choosing to Exchange Cards : " + msgSplit[1].toUpperCase());
			
			if(msgSplit[1].equalsIgnoreCase("no") || msgSplit[1] == "no")
				System.out.println("\n\n\t" + ReinforcementPhaseModel.getCardArmies() + " REINFORCEMENTS RECIEVED AS NO CARD EXCHANGE PERFORMED"  );
			
			
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(msgSplit[1].equalsIgnoreCase("yes") || msgSplit[1] == "yes" || msgSplit[1].equalsIgnoreCase("must") || msgSplit[1] == "must"){
				
				System.out.println("\n\t" + "Cards Chosen for Exchange -> ");
				
				card1 = 0;
				card2 = 0;
				card3 = 0;
				
				if(ReinforcementPhaseModel.chosenCards.isEmpty())
					System.out.println("Its empty :: " + ReinforcementPhaseModel.chosenCards);
				for(String cardKey : ReinforcementPhaseModel.chosenCards){
					if(cardKey.equals(RiskConstants.CARD_1) || cardKey == RiskConstants.CARD_1)
						card1++;
					if(cardKey.equals(RiskConstants.CARD_2) || cardKey == RiskConstants.CARD_2)
						card2++;
					if(cardKey.equals(RiskConstants.CARD_3) || cardKey == RiskConstants.CARD_3)
						card3++;
				}//end for(String cardKey : cardsList)
				

				if(card1 > 0)
				System.out.println("\t\t* "+RiskConstants.CARD_1.toUpperCase()+ " : " + card1);
				
				if(card2 > 0)
				System.out.println("\t\t* "+RiskConstants.CARD_2.toUpperCase()+ " : " + card2);
				
				if(card3 > 0)
				System.out.println("\t\t* "+RiskConstants.CARD_3.toUpperCase()+ " : " + card3);
				
				
				System.out.printf("\n\t" + "Number of times cards exchanged previously by PLAYER " + player + " : ");
				
				int times = 0;
				ArrayList<String> cardsList = new ArrayList<String>();
				for(String playerInfoKey : ReinforcementPhaseModel.playerCards.keySet()){
					
					String[] keySplit = playerInfoKey.split("-");
					
					if(keySplit[0].equals(player) || keySplit[0] == player){
						
						times = Integer.parseInt(keySplit[1]) - 1 ;
						cardsList = ReinforcementPhaseModel.playerCards.get(playerInfoKey);
						
						break;
					}
				}
				
				System.out.printf(""+times);
				
				if(!cardsList.isEmpty()){
				System.out.println("\n\n\t" + "Cards remaining after this exchange -> ");
				
				card1 = 0;
				card2 = 0;
				card3 = 0;
				
				
				for(String cardKey : cardsList){
					if(cardKey.equals(RiskConstants.CARD_1) || cardKey == RiskConstants.CARD_1)
						card1++;
					if(cardKey.equals(RiskConstants.CARD_2) || cardKey == RiskConstants.CARD_2)
						card2++;
					if(cardKey.equals(RiskConstants.CARD_3) || cardKey == RiskConstants.CARD_3)
						card3++;
				}//end for(String cardKey : cardsList)
				

				if(card1 > 0)
				System.out.println("\t\t*"+RiskConstants.CARD_1+ " : " + card1);
				
				if(card2 > 0)
				System.out.println("\t\t*"+RiskConstants.CARD_2+ " : " + card2);
				
				if(card3 > 0)
				System.out.println("\t\t*"+RiskConstants.CARD_3+ " : " + card3);
				
				
				
				}
				else{
				
					System.out.println("\n\n\t" + "Cards remaining after this exchange -> NONE");
				}
				
				System.out.println("\n\n\t" + "REINFORCEMENTS RECIEVED FOR THE CARD EXCHANGE PERFORMED : " + ReinforcementPhaseModel.getCardArmies() );
				
				
			}
			
			try {
				System.in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	@Override
	public void update(Observable obs, Object arg) {

		String msgUI = ((ReinforcementPhaseModel)obs).getMsgUI();

		
		if(msgUI.contains("byCards")){
			
			//"byCards,"+player
			String[] msgUISplit = msgUI.split(",");
			cardExchangeView(msgUISplit[1], ((ReinforcementPhaseModel)obs).getCardsMsg());
		}
		
		
		
	}
		
		
	
	
	


}



	


	