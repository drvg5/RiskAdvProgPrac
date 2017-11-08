package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

public class ReinforcementPhaseModel extends Observable {

	public static HashMap<String,Integer> reinforcement = new HashMap<String,Integer>();
	public static HashMap<String,ArrayList<String>> playerCards = new HashMap<String,ArrayList<String>>();
	//key -> playernumber + "-" + number of times cards exchanged already
	//value -> list of 5 cards
	public static final int FIRST_EXG_ARMIES = 5;
	public static final String CARD_1 = "Artillery";
	public static final String CARD_2 = "Cavalry";
	public static final String CARD_3 = "Infantry";
	public static final String[] CARD_TYPES = {CARD_1,CARD_2,CARD_3}; 
	//public static int reinforcment;
	public static TreeSet<String> prevCards = new TreeSet<String>(); ;
	public static TreeSet<String> chosenCards = new TreeSet<String>();
	public static TreeSet<String> newCards = new TreeSet<String>();
	
	private String msgUI ; 
	
	private ArrayList<String> cntrlValReinforcements = new ArrayList<>();
	
	private HashMap<String,Integer> playerContTerr = new HashMap<String,Integer>();
	
	//setters and getters
	public HashMap<String, Integer> getPlayerContTerr() {
		return playerContTerr;
	}

	public void setPlayerContTerr(HashMap<String, Integer> playerContTerr) {
		this.playerContTerr = playerContTerr;
	}

	public ArrayList<String> getCntrlValReinforcements() {
		return cntrlValReinforcements;
	}

	public void setCntrlValReinforcements(ArrayList<String> cntrlValReinforcements) {
		this.cntrlValReinforcements = cntrlValReinforcements;
	}

	public String getMsgUI(){
		
		return msgUI;
	}
	
	public void setMsgUI(String msgUI){
		
		this.msgUI = msgUI;
	}
	
	
	
	
	public String calcReinforcementsByTerr(String player){
		
		String reinTerrMsg;
		
		int reinforcementArmies = 0;
		int playerTerr = 0;
		
		
		//calculate reinforcement armies by number of countries owned
		for(String plyrVal : StartUpPhaseModel.playerInfo.keySet()){
			String plyrInfo [] = plyrVal.split("-");
			if(plyrInfo[0].equals(player) || plyrInfo[0] == player){
				playerTerr ++ ; 
			}
		}
		
		
		if(playerTerr < 9 ){
			reinforcementArmies = reinforcementArmies + 3;
		}
		if(playerTerr >= 9){
			reinforcementArmies = reinforcementArmies + ((int) Math.floor((double)playerTerr/3));
		}
		 
		reinTerrMsg = player + "," + playerTerr + "," + reinforcementArmies;
		
		if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
			int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
			reinforcementArmies = existingReinforcements + reinforcementArmies;
			ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
		}
		else
			ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
		
		setMsgUI("byTerr," + reinTerrMsg);
		
		setChanged();
		notifyObservers(this);
		
		return reinTerrMsg;
		
	}
	
	
	public ArrayList<String> calcReinforcementByCntrlVal(String player, HashMap<String, Integer> continentControlValueHashMap){
		
		ArrayList<String> cntrlValMsg = new ArrayList<String>();
				
		HashMap<String,Integer> playerContTerr = new HashMap<String,Integer>();
		
		for(String playerKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String[] playerKeySplit = playerKey.split("-");
			
			if(playerKeySplit[0].equals(player) || playerKeySplit[0] == player){
				
				if(!playerContTerr.isEmpty()){
					
					if(playerContTerr.containsKey(playerKeySplit[2])){
						
						int numberOfTerr = 	playerContTerr.get(playerKeySplit[2]);
						playerContTerr.put(playerKeySplit[2], numberOfTerr + 1);
						
					}
					else{
						playerContTerr.put(playerKeySplit[2],1);
					}
				}//end if(!playerContTerr.isEmpty())
				
				else{
					playerContTerr.put(playerKeySplit[2],1);
				}//end else for if(!playerContTerr.isEmpty())...
				
				
			}//end if(playerKeySplit[0].equals(player)...
			
		}//end for(String playerKey : Sta...
		
		setPlayerContTerr(playerContTerr);
		
		for(String continent : StartUpPhaseModel.terrPerCont.keySet()){
			
			int territories = StartUpPhaseModel.terrPerCont.get(continent);
			
			if(playerContTerr.containsKey(continent)){
				
				int playerTerr = playerContTerr.get(continent);
				
				//if player has all the territories of a continent
				if(playerTerr == territories){
				
					
					//get current reinforcements
					int reinforcements = reinforcement.get(player);
					
					//get control value of the continent
					
					int cntrlVal  = continentControlValueHashMap.get(continent);
					
					cntrlValMsg.add(continent+","+reinforcements+","+cntrlVal);
					
					//add control Value of the continent owned to the reinforcment armies.
					reinforcement.put(player,reinforcements + cntrlVal);
					
				}//end if(playerTerr == territories)...
				
			}
			else
				continue;
			
		}//end for(String continent : StartUpPhase...
		
		setMsgUI("byCtrlVal,"+player);
		setCntrlValReinforcements(cntrlValMsg);
		
		setChanged();
		notifyObservers(this);
		
		return cntrlValMsg;
		
	}//end method calcReinforcementByCntrlVal
	
	
	public static String calcReinforcementByCards(String player){
		
		String cardsMsg = null;
		int reinforcementArmies = 0;
		int prevExg = 0;
		int foundHistoryFlag = 0;
		int numberOfCards = 0;
		
		
		ArrayList<String> cardsList = new ArrayList<String>();
		
		for(String plyrCardExgHistory : ReinforcementPhaseModel.playerCards.keySet()){
			
			String [] keySplit = plyrCardExgHistory.split("-");
			
			if(keySplit[0].equals(player) || keySplit[0] == player){
				
				foundHistoryFlag = 1;
				
				//check number of cards
				cardsList = ReinforcementPhaseModel.playerCards.get(plyrCardExgHistory);
				numberOfCards = cardsList.size();
				prevExg = Integer.parseInt(keySplit[1]);
				
				break;
				
			}//end if(keySplit[0].equals(player) || keySplit[0] == player)

		}//end for
		
		if(foundHistoryFlag == 0){
			cardsMsg = "not enough cards";
		}
		else if(foundHistoryFlag == 1){
			
			if( numberOfCards < 3){
				cardsMsg = "not enough cards";
			}
			else if( numberOfCards > 3 && numberOfCards < 5 ){
				
				//check card types
				TreeSet<String> cardsSet= new TreeSet<String>(cardsList);
				int cardTypes  = cardsSet.size();
				
				if(cardTypes == 1 || cardTypes == 3){
					
					boolean tradeDecision;
					
					//randomly choose to trade in cards or not
					Random randomDecision = new Random();
					tradeDecision = randomDecision.nextBoolean();
		    	    
					if(tradeDecision){
						
						//calculate reinforcement armies
						int newExg = prevExg + 1;
						reinforcementArmies = ReinforcementPhaseModel.FIRST_EXG_ARMIES * newExg;
						
						//edit the playerCards HashMap
						ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
						
						ReinforcementPhaseModel.playerCards.put(player + "-" + newExg,null);
						
						//create Msg
						cardsMsg = ">>>" + reinforcementArmies + ">>>";
						
						//edit reinforcement HashMap
						if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
							int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
							reinforcementArmies = existingReinforcements + reinforcementArmies;
							ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
						}
						else{
							
							ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
							
						}
						
						
					}//end if(tradeDecision)
					
					
				}//end if(cardTypes == 1 || cardTypes == 3)
				
			}
			else if( numberOfCards == 5){
				foundHistoryFlag = 1;
				boolean tradeDecision;
				
				//randomly choose whether to trade in 3 different cards or 3 similar cards
				//if true then 3 different , if false then 3 similar cards should be exchanged
				Random randomDecision = new Random();
				tradeDecision = randomDecision.nextBoolean();
				
				
				if(tradeDecision){
					
					//trade in the 3 different cards
					
					int card1 = 0;
					int card2 = 0;
					int card3 = 0;
					TreeSet<String> cardsToBeExchanged = new TreeSet<String>();
					
					for(String cardKey : cardsList){
						if(cardKey.equals(ReinforcementPhaseModel.CARD_1) || cardKey == ReinforcementPhaseModel.CARD_1)
							card1++;
						if(cardKey.equals(ReinforcementPhaseModel.CARD_2) || cardKey == ReinforcementPhaseModel.CARD_2)
							card2++;
						if(cardKey.equals(ReinforcementPhaseModel.CARD_3) || cardKey == ReinforcementPhaseModel.CARD_3)
							card3++;
					}//end for(String cardKey : cardsList)
					
					if(card1 == 1){
						
						cardsToBeExchanged.add(ReinforcementPhaseModel.CARD_1);
					}
					if(card2 == 1){
						cardsToBeExchanged.add(ReinforcementPhaseModel.CARD_2);
					}
					if(card3 == 1){
						cardsToBeExchanged.add(ReinforcementPhaseModel.CARD_3);
					}
					
					TreeSet<String> newCardSet = new TreeSet<String>(cardsList);
					
					
					for(String card : cardsToBeExchanged){
						
						newCardSet.remove(card);
					}
					
					
					int newExg = prevExg + 1;
					
					//calculate reinforcement armies
					reinforcementArmies = ReinforcementPhaseModel.FIRST_EXG_ARMIES * newExg;
					
					//edit the playerCards HashMap
					ArrayList<String> newCardsList = new ArrayList<String>(newCardSet);
					ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
					
					ReinforcementPhaseModel.playerCards.put(player + "-" + newExg,newCardsList);
					
					//create Msg
					cardsMsg = ">>>" + reinforcementArmies + ">>>";
					
					//edit reinforcement HashMap
					if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
						int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
						reinforcementArmies = existingReinforcements + reinforcementArmies;
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
					}
					else{
						
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
						
					}
					
					
					
					
				}//end if(tradeDecision)
				
				else{
					
					
					//trade in the similar 3 cards
					int card1 = 0;
					int card2 = 0;
					int card3 = 0;
					String cardToBeExchanged = new String() ;
					
					for(String cardKey : cardsList){
						if(cardKey.equals(ReinforcementPhaseModel.CARD_1) || cardKey == ReinforcementPhaseModel.CARD_1)
							card1++;
						if(cardKey.equals(ReinforcementPhaseModel.CARD_2) || cardKey == ReinforcementPhaseModel.CARD_2)
							card2++;
						if(cardKey.equals(ReinforcementPhaseModel.CARD_3) || cardKey == ReinforcementPhaseModel.CARD_3)
							card3++;
					}//end for(String cardKey : cardsList)
					
					if(card1 == 3){	
						cardToBeExchanged = ReinforcementPhaseModel.CARD_1;
					}
					else if(card2 == 3){
						cardToBeExchanged = ReinforcementPhaseModel.CARD_2;
					}
					else if(card3 == 3){
						cardToBeExchanged = ReinforcementPhaseModel.CARD_3;
					}
					
					
					TreeSet<String> cardSet = new TreeSet<String>(cardsList);
					cardSet.remove(cardToBeExchanged);
					
					int newExg = prevExg + 1;
					
					//calculate reinforcement armies
					reinforcementArmies = ReinforcementPhaseModel.FIRST_EXG_ARMIES * newExg;
					
					//edit the playerCards HashMap
					ArrayList<String> newCardsList = new ArrayList<String>(cardSet);
					ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
					
					ReinforcementPhaseModel.playerCards.put(player + "-" + newExg,newCardsList);
					
					//create Msg
					cardsMsg = ">>>" + reinforcementArmies + ">>>";
					
					//edit reinforcement HashMap
					if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
						int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
						reinforcementArmies = existingReinforcements + reinforcementArmies;
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
					}
					else{
						
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
						
					}
					
					
					
				}//end else for if(tradeDecision)
				
			}//end else if(numberOfCards == 5)
			
		}
		
		
		return cardsMsg;
	}
	
	
	public static void reinforceRandom(String player){
		
		
		int reinforcementArmies = reinforcement.get(player);
		
		
		//create a list of keys from playerInfo HashMap 
		//for only the player concerned
		List<String> playerInfoKeyList = new ArrayList<String>();
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String [] playerVals = playerInfoKey.split("-");
			if(playerVals[0].equals(player) || playerVals[0] == player){
				
				playerInfoKeyList.add(playerInfoKey);
			}
			
		}
		
		
		Random randomKey = new Random();
	      
		
		//loop until all reinforcement armies have been assigned
		while(reinforcementArmies != 0){
			
			
			//choose territory randomly to put armies into
			String randomPlayerKey = playerInfoKeyList.get(randomKey.nextInt(playerInfoKeyList.size()) );
			
			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomPlayerKey);
			playerInfoValue = playerInfoValue + 1;
			
			
			StartUpPhaseModel.playerInfo.put(randomPlayerKey, playerInfoValue);
			
			reinforcementArmies--;
			
			
		}
		
	}
	
	
	public static int calculateCardArmies(ArrayList<String> cardsList){
		
		int cardArmies = 0;

		
		return cardArmies;
	}
	
	
}
