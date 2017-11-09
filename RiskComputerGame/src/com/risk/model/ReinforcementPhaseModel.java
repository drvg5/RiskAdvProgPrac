package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import com.risk.utility.RiskConstants;

public class ReinforcementPhaseModel extends Observable {

	public static HashMap<String,Integer> reinforcement = new HashMap<String,Integer>();
	public static HashMap<String,ArrayList<String>> playerCards = new HashMap<String,ArrayList<String>>();
	//key -> playernumber + "-" + number of times cards exchanged already
	//value -> list of 5 current cards
	
	public static HashMap<String,ArrayList<String>> prevPlayerCards = new HashMap<String,ArrayList<String>>();
		
	//public static int reinforcment;
	public static ArrayList<String> prevCards = new ArrayList<String>(); ;
	public static ArrayList<String> chosenCards = new ArrayList<String>();
	public static ArrayList<String> newCards = new ArrayList<String>();
	

	
	private int totalReinforcementArmies ;
	
	private int latestArmies;

	private static int cardArmies ;
	
	private String cardsMsg ;
	
	private String msgUI ; 
	
	private ArrayList<String> cntrlValReinforcements = new ArrayList<>();
	
	private HashMap<String,Integer> playerContTerr = new HashMap<String,Integer>();
	
	
	//setters and getters
	

	public int getLatestArmies() {
		return latestArmies;
	}

	public void setLatestArmies(int latestArmies) {
		this.latestArmies = latestArmies;
	}

	
	public static int getCardArmies() {
		return cardArmies;
	}

	public static void setCardArmies(int armies) {
		cardArmies = armies;
	}
	
	public String getCardsMsg() {
		return cardsMsg;
	}

	public void setCardsMsg(String cardsMsg) {
		this.cardsMsg = cardsMsg;
	}
	
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
	
	public int getTotalReinforcementArmies() {
		return totalReinforcementArmies;
	}

	public void setTotalReinforcementArmies(int totalReinforcementArmies) {
		this.totalReinforcementArmies = totalReinforcementArmies;
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
	
	
	public void calcReinforcementByCards(String player){
		
		setMsgUI("byCards," + player);
		
		int reinforcementArmies = 0;
		int prevExg = 0;
		int foundHistoryFlag = 0;
		int numberOfCards = 0;
		
		
		ArrayList<String> cardsList = new ArrayList<String>();
		
		prevPlayerCards = new HashMap<String,ArrayList<String>>();
		
		chosenCards = new ArrayList<String>();
		
		prevCards = new ArrayList<String>();
		
		setCardArmies(0);
				
		if(!playerCards.isEmpty()){
			
			prevPlayerCards = playerCards;
			
			for(String plyrCardExgHistory : ReinforcementPhaseModel.playerCards.keySet()){
				
				String [] keySplit = plyrCardExgHistory.split("-");
				
				if(keySplit[0].equals(player) || keySplit[0] == player){
					
					foundHistoryFlag = 1;
					
					//check number of cards
					cardsList = ReinforcementPhaseModel.playerCards.get(plyrCardExgHistory);
					
					if(!cardsList.isEmpty()){
					prevCards = cardsList;
					numberOfCards = cardsList.size();
					}
					
					prevExg = Integer.parseInt(keySplit[1]);
					
					break;
					
				}//end if(keySplit[0].equals(player) || keySplit[0] == player)

			}//end for
		}
		
		
		//also checks if currently any cards available
		if(foundHistoryFlag == 0){
			
			setCardsMsg("not enough cards");
			
			setChanged();
			
			notifyObservers(this);
			
			
		}
		
		else if(foundHistoryFlag == 1){
			
			if( numberOfCards < 3){
				
				setCardsMsg("not enough cards");
				
				setChanged();
				
				notifyObservers(this);
				
				
				
			}
			else if( numberOfCards == 3){
				
				//check card types
				TreeSet<String> cardsSet= new TreeSet<String>(cardsList);
				
				int cardTypes  = cardsSet.size();
				
				if(cardTypes == 1 || cardTypes == 3){
					
					boolean tradeDecision;
					
					//randomly choose to trade in cards or not
					Random randomDecision = new Random();
					tradeDecision = randomDecision.nextBoolean();
					
					
					if(tradeDecision){
						
						
						chosenCards = new ArrayList<String>();
						
						//calculate reinforcement armies
						int newExg = prevExg + 1;
						reinforcementArmies = RiskConstants.FIRST_EXG_ARMIES * newExg;

						setCardArmies(reinforcementArmies);
						
						chosenCards = ReinforcementPhaseModel.playerCards.get(player + "-" + prevExg);
						
						//edit the playerCards HashMap
						ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
						
						
						ReinforcementPhaseModel.playerCards.put(player + "-" + newExg, new ArrayList<String>());
						
						//edit reinforcement HashMap
						if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
							int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
							reinforcementArmies = existingReinforcements + reinforcementArmies;
							ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
						}
						else{
							
							ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
							
						}
						
						
						
						setCardsMsg("enough cards,yes");
						
						setChanged();
						
						notifyObservers(this);
						
						
					}
					
					else{
						setCardsMsg("enough cards,no");
						setChanged();
						
						notifyObservers(this);
						
						
					}
					
				}//end if(cardTypes == 1 || cardTypes == 3)
				
				else{
					
					setCardsMsg("not enough cards");
					
					setChanged();
					
					notifyObservers(this);
					
				}
					
					
				
			}//end else if( numberOfCards == 3)
			else if( numberOfCards == 4){
				
				
				int card1 = 0;
				int card2 = 0;
				int card3 = 0;
				TreeSet<String> cardsToBeExchanged = new TreeSet<String>();
				
				for(String cardKey : cardsList){
					if(cardKey.equals(RiskConstants.CARD_1) || cardKey == RiskConstants.CARD_1)
						card1++;
					if(cardKey.equals(RiskConstants.CARD_2) || cardKey == RiskConstants.CARD_2)
						card2++;
					if(cardKey.equals(RiskConstants.CARD_3) || cardKey == RiskConstants.CARD_3)
						card3++;
				}//end for(String cardKey : cardsList)
				
				boolean check1 = (card1 == 0 && card2 == 2 && card3 == 2);
				boolean check2 = (card1 == 2 && card2 == 0 && card3 == 2);
				boolean check3 = (card1 == 2 && card2 == 2 && card3 == 0);
				
				if(check1 || check2 || check3){
					
					setCardsMsg("not enough cards");
					
					setChanged();
					
					notifyObservers(this);
					
					
				}
				else{
				
					
					boolean tradeDecision;
					
					//randomly choose to trade in cards or not
					Random randomDecision = new Random();
					tradeDecision = randomDecision.nextBoolean();
					
					if(tradeDecision){
						
						int similar = 0;
						int different = 0;
						//check if cards are similar or different
						if(card1 > 2 || card2 > 2 || card3 > 2){
							
							similar = 1;
						}
						else{
							
							different = 1;
						}
						
						if(similar == 1){
							
							chosenCards = new ArrayList<String>();
							
							String similarCard;
							
							int newCountCard1 = card1;
							
							int newCountCard2 = card2;
							
							int newCountCard3 = card3;
							
							if(card1 > card2 && card1 > card3){
								
								similarCard = RiskConstants.CARD_1;
								newCountCard1 = card1 - 3 ;
								chosenCards.add(RiskConstants.CARD_1);
								chosenCards.add(RiskConstants.CARD_1);
								chosenCards.add(RiskConstants.CARD_1);
							}
							
							else if(card2 > card1 && card2 > card3){
								
								similarCard = RiskConstants.CARD_2;
								newCountCard2 = card2 - 3 ;
								chosenCards.add(RiskConstants.CARD_2);
								chosenCards.add(RiskConstants.CARD_2);
								chosenCards.add(RiskConstants.CARD_2);
							}
							
							else if(card3 > card1 && card3 > card2){
								
								similarCard = RiskConstants.CARD_3;
								newCountCard3 = card3 - 3 ;
								
								chosenCards.add(RiskConstants.CARD_3);
								chosenCards.add(RiskConstants.CARD_3);
								chosenCards.add(RiskConstants.CARD_3);
							}
							
							
							ArrayList<String> updatedCardsList = new ArrayList<String>();
							
							
							if(newCountCard1 > 0){
								
								for(int i = 0; i < newCountCard1; i++ ){
									
									updatedCardsList.add(RiskConstants.CARD_1);
								}
							}
							
							if(newCountCard2 > 0){
								
								for(int i = 0; i < newCountCard2; i++ ){
									
									updatedCardsList.add(RiskConstants.CARD_2);
								}
							}
							
							if(newCountCard3 > 0){
								
								for(int i = 0; i < newCountCard3; i++ ){
									
									updatedCardsList.add(RiskConstants.CARD_3);
								}
							}
							
							System.out.println("updated cards list : "+updatedCardsList);
							//calculate reinforcement armies
							int newExg = prevExg + 1;
							reinforcementArmies = RiskConstants.FIRST_EXG_ARMIES * newExg;

							setCardArmies(reinforcementArmies);
							
							//edit the playerCards HashMap
							ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
							
							
							ReinforcementPhaseModel.playerCards.put(player + "-" + newExg, updatedCardsList);
							
							//edit reinforcement HashMap
							if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
								int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
								reinforcementArmies = existingReinforcements + reinforcementArmies;
								ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
							}
							else{
								
								ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
								
							}
							
							
							
							
							
						}//if(similar = 1)
						
						if(different == 1){
							
							chosenCards = new ArrayList<String>();
							chosenCards.add(RiskConstants.CARD_1);
							chosenCards.add(RiskConstants.CARD_2);
							chosenCards.add(RiskConstants.CARD_3);
							
							int newCountCard1 = card1 - 1;
							
							int newCountCard2 = card2 - 1;
							
							int newCountCard3 = card3 - 1;
							
							ArrayList<String> updatedCardsList = new ArrayList<String>();
							
							
							
							if(newCountCard1 > 0){
								
								for(int i = 0; i < newCountCard1; i++ ){
									
									updatedCardsList.add(RiskConstants.CARD_1);
								}
							}
							
							if(newCountCard2 > 0){
								
								for(int i = 0; i < newCountCard2; i++ ){
									
									updatedCardsList.add(RiskConstants.CARD_2);
								}
							}
							
							if(newCountCard3 > 0){
								
								for(int i = 0; i < newCountCard3; i++ ){
									
									updatedCardsList.add(RiskConstants.CARD_3);
								}
							}
							
							
							//calculate reinforcement armies
							int newExg = prevExg + 1;
							reinforcementArmies = RiskConstants.FIRST_EXG_ARMIES * newExg;
							setCardArmies(reinforcementArmies);
							
							//edit the playerCards HashMap
							ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
							
							
							ReinforcementPhaseModel.playerCards.put(player + "-" + newExg, updatedCardsList);
							
							//edit reinforcement HashMap
							if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
								int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
								reinforcementArmies = existingReinforcements + reinforcementArmies;
								ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
							}
							else{
								
								ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
								
							}
							
							
							
							
							
						}//end if(different == 1)

						setCardsMsg("enough cards,yes");
						
						setChanged();
						
						notifyObservers(this);
						
					}//end if(tradeDecision)
					else{
						
						
						setCardsMsg("enough cards,no");
						
						setChanged();
						
						notifyObservers(this);
						
					}
					
					
				}//end else for if(check1 || check2 || check3)
				

			}//end else if(numberOfCards = 4)
			else if( numberOfCards == 5){
				

				int card1 = 0;
				int card2 = 0;
				int card3 = 0;
				
				TreeSet<String> cardsToBeExchanged = new TreeSet<String>();
				
				for(String cardKey : cardsList){
					if(cardKey.equals(RiskConstants.CARD_1) || cardKey == RiskConstants.CARD_1)
						card1++;
					if(cardKey.equals(RiskConstants.CARD_2) || cardKey == RiskConstants.CARD_2)
						card2++;
					if(cardKey.equals(RiskConstants.CARD_3) || cardKey == RiskConstants.CARD_3)
						card3++;
				}//end for(String cardKey : cardsList)
				
				
				int similar = 0;
				int different = 0;
				
				//absolute similar cases aaabb
				boolean check1 = (card1 == 0 && card2 == 2 && card3 == 3);
				boolean check2 = (card1 == 0 && card2 == 3 && card3 == 2);
				boolean check3 = (card1 == 2 && card2 == 0 && card3 == 3);
				boolean check4 = (card1 == 2 && card2 == 3 && card3 == 0);
				boolean check5 = (card1 == 3 && card2 == 0 && card3 == 2);
				boolean check6 = (card1 == 3 && card2 == 2 && card3 == 0);
				
				boolean check7 = (card1 == 2 && card2 == 2 && card3 == 1);
				boolean check8 = (card1 == 2 && card2 == 1 && card3 == 2);
				boolean check9 = (card1 == 1 && card2 == 2 && card3 == 2);
				
				
				
				//check if cards are similar or different
				if(card1 > 3 || card2 > 3 || card3 > 3){
					
					//aaaaa aaaab
					similar = 1;
				}
				
				else if(check1 || check2 || check3 || check4 || check5 || check6){
					
					//aaabb
					similar = 1;
				}
				else if(check7 || check8 || check9){
					
					different = 1;
				}
				else{
					
					//aaabc
					
					boolean typesToExchange;
					Random randomDecision = new Random();
					typesToExchange = randomDecision.nextBoolean();
					
					if(typesToExchange)
						different = 1;
					else
						similar = 1;
				}
				
				if(similar == 1){
					
					chosenCards = new ArrayList<String>();
					
					String similarCard;
					
					int newCountCard1 = card1;
					
					int newCountCard2 = card2;
					
					int newCountCard3 = card3;
					
					//find which card has highest frequency
					if(card1 > card2 && card1 > card3){
						
						similarCard = RiskConstants.CARD_1;
						newCountCard1 = card1 - 3 ;
						chosenCards.add(RiskConstants.CARD_1);
						chosenCards.add(RiskConstants.CARD_1);
						chosenCards.add(RiskConstants.CARD_1);
					}
					
					else if(card2 > card1 && card2 > card3){
						
						similarCard = RiskConstants.CARD_2;
						newCountCard2 = card2 - 3 ;
						chosenCards.add(RiskConstants.CARD_2);
						chosenCards.add(RiskConstants.CARD_2);
						chosenCards.add(RiskConstants.CARD_2);
					}
					
					else if(card3 > card1 && card3 > card2){
						
						similarCard = RiskConstants.CARD_3;
						newCountCard3 = card3 - 3 ;
						
						chosenCards.add(RiskConstants.CARD_3);
						chosenCards.add(RiskConstants.CARD_3);
						chosenCards.add(RiskConstants.CARD_3);
					}
					
					
					
					ArrayList<String> updatedCardsList = new ArrayList<String>();
					
					
					
					if(newCountCard1 > 0){
						
						for(int i = 0; i < newCountCard1; i++ ){
							
							updatedCardsList.add(RiskConstants.CARD_1);
						}
					}
					
					if(newCountCard2 > 0){
						
						for(int i = 0; i < newCountCard2; i++ ){
							
							updatedCardsList.add(RiskConstants.CARD_2);
						}
					}
					
					if(newCountCard3 > 0){
						
						for(int i = 0; i < newCountCard3; i++ ){
							
							updatedCardsList.add(RiskConstants.CARD_3);
						}
					}
					
					
					
					//calculate reinforcement armies
					int newExg = prevExg + 1;
					reinforcementArmies = RiskConstants.FIRST_EXG_ARMIES * newExg;

					setCardArmies(reinforcementArmies);
					
					//edit the playerCards HashMap
					ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
					
					
					ReinforcementPhaseModel.playerCards.put(player + "-" + newExg, updatedCardsList);
					
					//edit reinforcement HashMap
					if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
						
						int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
						reinforcementArmies = existingReinforcements + reinforcementArmies;
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
					}
					else{
						
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
						
					}
					
					
					
				}//end if (similar == 1)
				
				if(different == 1){
					
					
					
					chosenCards = new ArrayList<String>();
					
					chosenCards.add(RiskConstants.CARD_1);
					chosenCards.add(RiskConstants.CARD_2);
					chosenCards.add(RiskConstants.CARD_3);
					
					
					
					
					
					int newCountCard1 = card1 - 1;
					
					int newCountCard2 = card2 - 1;
					
					int newCountCard3 = card3 - 1;
					
					System.out.println(newCountCard1 + newCountCard3 + newCountCard2);
					
					ArrayList<String> updatedCardsList = new ArrayList<String>();
					
					if(newCountCard1 > 0){
						
						for(int i = 0; i < newCountCard1; i++ ){
							
							updatedCardsList.add(RiskConstants.CARD_1);
						}
					}
					
					if(newCountCard2 > 0){
						
						for(int i = 0; i < newCountCard2; i++ ){
							
							updatedCardsList.add(RiskConstants.CARD_2);
						}
					}
					
					if(newCountCard3 > 0){
						
						for(int i = 0; i < newCountCard3; i++ ){
							
							updatedCardsList.add(RiskConstants.CARD_3);
						}
					}
					
					
					
					//calculate reinforcement armies
					int newExg = prevExg + 1;
					reinforcementArmies = RiskConstants.FIRST_EXG_ARMIES * newExg;
					setCardArmies(reinforcementArmies);
					
					//edit the playerCards HashMap
					ReinforcementPhaseModel.playerCards.remove(player + "-" + prevExg);
					
					
					ReinforcementPhaseModel.playerCards.put(player + "-" + newExg, updatedCardsList);
					
					//edit reinforcement HashMap
					if(ReinforcementPhaseModel.reinforcement.containsKey(player)){
						int existingReinforcements = ReinforcementPhaseModel.reinforcement.get(player);
						reinforcementArmies = existingReinforcements + reinforcementArmies;
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
					}
					else{
						
						ReinforcementPhaseModel.reinforcement.put(player,reinforcementArmies);
						
					}
					
				}//end if (different == 1)
				
				
				setCardsMsg("enough cards,must");
				
				setChanged();
				
				notifyObservers(this);
				
				
				
			}//end else if(numberOfCards == 5)
			
		}//end else if(historyFoundflag == 1)
		
	}//end calculateReinforcementByCards
	
	
	public void reinforceRandom(String player){
		
		setMsgUI("total reinforcement print," + player);
		
		
		
		setChanged();
		notifyObservers(this);
		
		
		
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
			
			String[] playerKeySplit = randomPlayerKey.split("-");
			reinforcementArmies--;
			
			setTotalReinforcementArmies(reinforcementArmies);
			setLatestArmies(playerInfoValue);
			
			setChanged();
			setMsgUI("placementView," + playerKeySplit[0] + "," + playerKeySplit[1]);
			notifyObservers(this);
			
		}
		
	}
	
	
	
	
	
}
