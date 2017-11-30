package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import com.risk.utility.RiskConstants;


/**
 * The Class ReinforcementPhaseModel is an Observable class which has methods for processing reiforcements and also this class is being
 * observed by an Observer {@link com.risk.ui.ReinforcementsUI ReinforcementsUI} and {@link com.risk.ui.CardExchangeUI CardExchangeUI}
 * to display the internal dyanmic details during the reinforcement phase.
 * @author Ashish Sharma
 * @see com.risk.ui.ReinforcementsUI ReinforcementsUI
 */
public class ReinforcementPhaseModel extends Observable {

	/** The HashMap reinforcement stores the reinforcements recieved for a player in a single turn.
	 * It updates at every single turn for a player.
	 * <p>The key here is a String storing the player and value is the reinforcements recieved in a single turn.</p>
	 */
	public static HashMap<String,Integer> reinforcement = new HashMap<String,Integer>();
	
	/** The HashMap playerCards stores the previous history of card exchange(if any) for a particular player
	 * and each key points to an ArrayList of the cards available.
	 */
	public static HashMap<String,ArrayList<String>> playerCards = new HashMap<String,ArrayList<String>>();
	//key -> playernumber + "-" + number of times cards exchanged already
	//value -> list of 5 current cards
	
	
	/** The HashMap prevPlayerCards stores the cards before exchange.
	 * It is used by Observer {@link com.risk.ui.CardExchangeUI CardExchangeUI} to display the cards before exchange.
	 */
	public static HashMap<String,ArrayList<String>> prevPlayerCards = new HashMap<String,ArrayList<String>>();
		
	/** The ArrayList prevCards stores the cards before exchange. */
	//public static int reinforcment;
	public static ArrayList<String> prevCards = new ArrayList<String>(); ;
	
	/** The ArrayList chosenCards stores the cards chosen for exchange. */
	public static ArrayList<String> chosenCards = new ArrayList<String>();
	
	/** The ArrayList newCards stores the updated cards after exchange. */
	public static ArrayList<String> newCards = new ArrayList<String>();
	
	/** The static HashMap prevPlayerInfo stores the playerInfo before the reinforcement. */
	public static HashMap<String, Integer> prevPlayerInfo = new HashMap<String, Integer>();
	
	/** The totalReinforcementArmies stores the totalReinforcementArmies calculated for a player in a single turn.
	 * This gets updated with every player and is used by Observer {@link com.risk.ui.ReinforcementsUI ReinforcementsUI} methods.
	 */
	private int totalReinforcementArmies ;
	
	/** The latestArmies stores the latestArmies for a territory owned by a player after the addition of reinforcement armies.
	 * This gets updated with every player and is used by Observer {@link com.risk.ui.ReinforcementsUI ReinforcementsUI} methods.
	 */
	private int latestArmies;
	
	/** The prevArmies stores the prevArmies for a territory owned by a player before the addition of reinforcement armies.
	 * This gets updated with every player and is used by Observer {@link com.risk.ui.ReinforcementsUI ReinforcementsUI} methods.
	 */
	private int prevArmies;

	/** The  int cardArmies stores the armies calculated through card exchange for a particular player in a single turn.
	 * This gets updated with every player.
	 */
	private static int cardArmies ;
	
	/** The String cardsMsg stores the message which varies whether cards can be exchanged or not.
	 * This gets updated with every player and is used by Observer {@link com.risk.ui.CardExchangeUI CardExchangeUI} to run appropriate methods
	 * as per this message.
	 */
	private String cardsMsg ;
	
	/** The String msgUI stores the message which as the process of reinforcement phase proceeds.
	 * This gets updated with every player and is used by Observer {@link com.risk.ui.ReinforcementsUI ReinforcementsUI} to run appropriate methods
	 * as per this message.
	 */
	private String msgUI ; 
	
	/** The ArrayList cntrlValReinforcements stores the multiple String messages all pertaining to a single player and varying on the fact
	 * whether a player is eligible to receive the control value of a particular continent.
	 * This is used by Observer {@link com.risk.ui.ReinforcementsUI ReinforcementsUI}.
	 */
	private ArrayList<String> cntrlValReinforcements = new ArrayList<>();
	
	/** The HashMap playerContTerr stores continents as Keys and number of territories which a player 
	 * owns in that continent. This gets updated with every player and used for calculating the control value reinforcements. */
	private HashMap<String,Integer> playerContTerr = new HashMap<String,Integer>();
	
	
	//setters and getters
	

	/**
	 * Gets the latest armies.
	 *
	 * @return the latest armies
	 */
	public int getLatestArmies() {
		return latestArmies;
	}

	/**
	 * Sets the latest armies.
	 *
	 * @param latestArmies the new latest armies
	 */
	public void setLatestArmies(int latestArmies) {
		this.latestArmies = latestArmies;
	}

	/**
	 * Gets the prev armies.
	 *
	 * @return the prev armies
	 */
	public int getPrevArmies() {
		return prevArmies;
	}

	/**
	 * Sets the prev armies.
	 *
	 * @param prevArmies the new prev armies
	 */
	public void setPrevArmies(int prevArmies) {
		this.prevArmies = prevArmies;
	}
	
	/**
	 * Gets the card armies.
	 *
	 * @return the card armies
	 */
	public static int getCardArmies() {
		return cardArmies;
	}

	/**
	 * Sets the card armies.
	 *
	 * @param armies the new card armies
	 */
	public static void setCardArmies(int armies) {
		cardArmies = armies;
	}
	
	/**
	 * Gets the cards msg.
	 *
	 * @return the cards msg
	 */
	public String getCardsMsg() {
		return cardsMsg;
	}

	/**
	 * Sets the cards msg.
	 *
	 * @param cardsMsg the new cards msg
	 */
	public void setCardsMsg(String cardsMsg) {
		this.cardsMsg = cardsMsg;
	}
	
	/**
	 * Gets the player cont terr.
	 *
	 * @return the player cont terr
	 */
	public HashMap<String, Integer> getPlayerContTerr() {
		return playerContTerr;
	}

	/**
	 * Sets the player cont terr.
	 *
	 * @param playerContTerr the player cont terr
	 */
	public void setPlayerContTerr(HashMap<String, Integer> playerContTerr) {
		this.playerContTerr = playerContTerr;
	}

	/**
	 * Gets the cntrl val reinforcements.
	 *
	 * @return the cntrl val reinforcements
	 */
	public ArrayList<String> getCntrlValReinforcements() {
		return cntrlValReinforcements;
	}

	/**
	 * Sets the cntrl val reinforcements.
	 *
	 * @param cntrlValReinforcements the new cntrl val reinforcements
	 */
	public void setCntrlValReinforcements(ArrayList<String> cntrlValReinforcements) {
		this.cntrlValReinforcements = cntrlValReinforcements;
	}

	/**
	 * Gets the msg UI.
	 *
	 * @return the msg UI
	 */
	public String getMsgUI(){
		
		return msgUI;
	}
	
	/**
	 * Sets the msg UI.
	 *
	 * @param msgUI the new msg UI
	 */
	public void setMsgUI(String msgUI){
		
		this.msgUI = msgUI;
	}
	
	/**
	 * Gets the total reinforcement armies.
	 *
	 * @return the total reinforcement armies
	 */
	public int getTotalReinforcementArmies() {
		return totalReinforcementArmies;
	}

	/**
	 * Sets the total reinforcement armies.
	 *
	 * @param totalReinforcementArmies the new total reinforcement armies
	 */
	public void setTotalReinforcementArmies(int totalReinforcementArmies) {
		this.totalReinforcementArmies = totalReinforcementArmies;
	}

	
	
	/**
	 * This method calculates reinforcements by territories owned by a player.
	 *
	 * @param player Player Number passed as a String
	 * @return String The return String contains of the player, the number of territories owned by player and the reinforcements calculated
	 * all separated by a comma ','
	 */
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
	
	
	/**
	 * This method calculates reinforcements by control value of the continents.
	 * It adds up to the reinforcements armies for a player a number equal to the control value of the continents the player owns
	 *
	 * @param player Player Number passed as a String
	 * @param continentControlValueHashMap The HashMap continentControlValueHashMap stores the control value of the continents
	 * @return Return an ArrayList cntrlValMsg containing for a player appropriate messages for each continent.
	 * 
	 */
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
	
	
	/**
	 * This method calculates reinforcements by exchange of cards a player has.
	 *
	 * @param player Player Number passed as a String
	 */
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
		
	
	/**
	 * This method is defines the reinforcement process for aggressive player.
	 * It is invoked in {@link com.risk.behavior.AggressiveBehaviorImpl#reinforce(String) reinforce} 
	 * method of {@link com.risk.behavior.AggressiveBehaviorImpl AggressiveBehaviorImpl} class
	 * @param player Player Number passed as a String
	 */
	public void reinforceAggressive(String player){
		
		setMsgUI("total reinforcement print," + player);

		setChanged();
		notifyObservers(this);
		
		
		int reinforcementArmies = ReinforcementPhaseModel.reinforcement.get(player);

		int highestArmies = 0;

		// create a list of keys from playerInfo HashMap
		// for only the player concerned with the most armies
		List<String> strongestTerritoryList = new ArrayList<String>();

		TreeSet<Integer> unitsSet = new TreeSet<Integer>();

		// populate unitsSet
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				unitsSet.add(StartUpPhaseModel.playerInfo.get(playerInfoKey));

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())

		highestArmies = unitsSet.last();

		// populate strongestTerritoryList
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				if (StartUpPhaseModel.playerInfo.get(playerInfoKey) == highestArmies) {

					strongestTerritoryList.add(playerInfoKey);

				} // end if(StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestArmies)

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()

		Random randomKey = new Random();

		// loop until all reinforcement armies have been assigned
		while (reinforcementArmies != 0) {

			// choose strongest territory to put armies into
			String randomStrongestKey = strongestTerritoryList.get(randomKey.nextInt(strongestTerritoryList.size()));

			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomStrongestKey);

			setPrevArmies(playerInfoValue);
			// playerInfoValue = playerInfoValue + 1;

			playerInfoValue = playerInfoValue + reinforcementArmies;

			StartUpPhaseModel.playerInfo.put(randomStrongestKey, playerInfoValue);

			String[] playerKeySplit = randomStrongestKey.split("-");
			// reinforcementArmies--;

			reinforcementArmies = 0;

			setTotalReinforcementArmies(reinforcementArmies);
			setLatestArmies(playerInfoValue);

			setChanged();
			setMsgUI("reinforceAggressive," + playerKeySplit[0] + "," + playerKeySplit[1]);
			notifyObservers(this);

		}
		
	}

	/**
	 * This method is defines the reinforcement process for benevolent player.
	 * It is invoked in {@link com.risk.behavior.BenevolantBehaviorImpl#reinforce(String) reinforce} 
	 * method of {@link com.risk.behavior.BenevolantBehaviorImpl BenevolantBehaviorImpl} class
	 * @param player Player Number passed as a String
	 */
	public void reinforceBenevolent(String player){
		
		
		setMsgUI("total reinforcement print," + player);

		setChanged();
		
		notifyObservers(this);
		
		
		
		int reinforcementArmies = ReinforcementPhaseModel.reinforcement.get(player);
		
		
		int leastArmies = 0;
		
		//create a list of keys from playerInfo HashMap 
		//for only the player concerned with the least armies
		List<String> weakestTerritoryList = new ArrayList<String>();
		
		TreeSet<Integer> unitsSet = new TreeSet<Integer>();
		
		
		//populate unitsSet
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String [] playerVals = playerInfoKey.split("-");
			
			if(playerVals[0].equals(player) || playerVals[0] == player){
				
					
					unitsSet.add(StartUpPhaseModel.playerInfo.get(playerInfoKey));
					
				
			}//end if(playerVals[0].equals(player) || playerVals[0] == player)
			
		}//end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
		
		leastArmies = unitsSet.first();
		
		
		
		//populate weakestTerritoryList
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String [] playerVals = playerInfoKey.split("-");
			
			if(playerVals[0].equals(player) || playerVals[0] == player){
				
				if(StartUpPhaseModel.playerInfo.get(playerInfoKey) == leastArmies){
					
					weakestTerritoryList.add(playerInfoKey);
					
				}//end if(StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestArmies)
				
			}//end if(playerVals[0].equals(player) || playerVals[0] == player)
			
		}//end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()
		
		
		Random randomKey = new Random();
	    
		//loop until all reinforcement armies have been assigned
		while(reinforcementArmies != 0){
			
			
			//choose weakest territory to put armies into
			String randomWeakestKey = weakestTerritoryList.get(randomKey.nextInt(weakestTerritoryList.size()) );
			
			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomWeakestKey);
			
			setPrevArmies(playerInfoValue);
			
			//playerInfoValue = playerInfoValue + 1;
			
			playerInfoValue = playerInfoValue + reinforcementArmies;
			
			StartUpPhaseModel.playerInfo.put(randomWeakestKey, playerInfoValue);
			
			String[] playerKeySplit = randomWeakestKey.split("-");
			//reinforcementArmies--;
			
			reinforcementArmies = 0;
			
			setTotalReinforcementArmies(reinforcementArmies);
			setLatestArmies(playerInfoValue);
			
			setChanged();
			setMsgUI("reinforceBenevolent," + playerKeySplit[0] + "," + playerKeySplit[1]);
			notifyObservers(this);
			
		}
		
	}

	/**
	 * This method is defines the reinforcement process for cheater player.
	 * It is invoked in {@link com.risk.behavior.CheaterBehaviorImpl#reinforce(String) reinforce} 
	 * method of {@link com.risk.behavior.CheaterBehaviorImpl CheaterBehaviorImpl} class
	 * @param player Player Number passed as a String
	 */
	public void reinforceCheater(String player){
		
		
		setMsgUI("displayCheaterTerr," + player);

		//print territory status before reinforcement using StartUpPhaseModel.playerInfo
		
		setChanged();
		notifyObservers(this);
		
		//update armies in each territory for cheater player
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			
			String [] playerVals = playerInfoKey.split("-");
			
			if(playerVals[0].equals(player) || playerVals[0] == player){
				
				
				int playerInfoValue = StartUpPhaseModel.playerInfo.get(playerInfoKey);
				
				setPrevArmies(playerInfoValue);
				
				playerInfoValue = 2 * playerInfoValue;
				
				setLatestArmies(playerInfoValue);
				
				StartUpPhaseModel.playerInfo.put(playerInfoKey, playerInfoValue);
				
				setMsgUI("reinforceCheater," + playerVals[0] + "," + playerVals[1]);
				setChanged();
				
				notifyObservers(this);
				
			}
			
			
		}//end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
		
				
		
	}//end reinforceCheater

	/**
	 * This method is defines the reinforcement process for random player.
	 * It is invoked in {@link com.risk.behavior.RandomBehaviorImpl#reinforce(String) reinforce} 
	 * method of {@link com.risk.behavior.RandomBehaviorImpl RandomBehaviorImpl} class
	 * @param player Player Number passed as a String
	 */
	public void reinforceRandom(String player){
		
		setMsgUI("total reinforcement print," + player);
		
		
		
		setChanged();
		notifyObservers(this);
		
		
		setMsgUI("reinforceRandom," + player);
		
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
			
			setPrevArmies(playerInfoValue);
			playerInfoValue = playerInfoValue + 1;
			
			
			StartUpPhaseModel.playerInfo.put(randomPlayerKey, playerInfoValue);
			
			String[] playerKeySplit = randomPlayerKey.split("-");
			reinforcementArmies--;
			
			setTotalReinforcementArmies(reinforcementArmies);
			setLatestArmies(playerInfoValue);
			
			setMsgUI("reinforceRandom," + playerKeySplit[0] + "," + playerKeySplit[1]);
			setChanged();
			
			notifyObservers(this);
			
		}//end while(reinforcementArmies != 0)
		
	}//end reinforceRandom

	/**
	 * This method is defines the reinforcement process for human player.
	 * It is invoked in {@link com.risk.behavior.HumanBehaviorImpl#reinforce(String) reinforce} 
	 * method of {@link com.risk.behavior.HumanBehaviorImpl HumanBehaviorImpl} class
	 * @param player Player Number passed as a String
	 */
	public void reinforceHuman(String player){
		
		setMsgUI("total reinforcement print," + player);
		setChanged();
		notifyObservers();
		
	
		
		// create a list of player territories HashMap
		// for only the player concerned
		List<String> playerTerritoryList = new ArrayList<String>();
		
		// populate playerTerritoryList
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

					playerTerritoryList.add(playerVals[1].toUpperCase());

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()
		
		
		int reinforcementArmies = reinforcement.get(player);
		
		String territory;
		
		int moveReinforcements;
		
		int firstCount = 1;
		
		// loop until all reinforcement armies have been assigned
		while (reinforcementArmies != 0){
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("\n\t----------------------------------------------------------------------------");
			System.out.printf("\n\t" + "Please choose a territory to reinforce : ");
			
			int msg = 0;
			
			while(true){
				
				
				
				
				if(msg == 1){
					System.out.println("\n\t------------------------ INCORRECT INPUT --------------------------");
					System.out.printf("\n\tPlease enter only a territory you own : ");
				}
				
				//Take territory input
				Scanner territoryInput = new Scanner(System.in);
				territory = territoryInput.nextLine().trim().toUpperCase();
				
				if(playerTerritoryList.contains(territory))
					
					break;
				else{
					
					msg = 1;
					continue;
				}
				
			}//end while
			
			System.out.println("\n\t----------------------------------------------------------------------------");
			System.out.printf("\n\t" + "Choose from the total reinforcements armies to move to " + territory + " : ");
			
			msg = 0;
			
			while(true){
				
				
				
				
				if(msg == 1){
					
					System.out.println("\n\t------------------------ INCORRECT INPUT --------------------------");
					
					if(firstCount == 1)
						System.out.printf("\n\tPlease enter some or all of the " + ReinforcementPhaseModel.reinforcement.get(player) + " reinforcement armies you have : ");
					else
						System.out.printf("\n\tPlease enter some or all of the remaining " + reinforcementArmies + " reinforcement armies you have : ");
						
				}
				
				//Take reinforcement armies input
				Scanner reinforcementInput = new Scanner(System.in);
				
				if (!reinforcementInput.hasNextInt()) {
					msg = 1;
					continue;
				}
				
				
				moveReinforcements = reinforcementInput.nextInt();
				
				if (moveReinforcements < 0) {
					msg = 1;
					continue;
				}
				
				
				if(reinforcementArmies >= moveReinforcements)
					
					break;
				
				else{
					msg = 1;
					continue;
				}
			}//end while(true)	
				
			for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
				
				String [] playerKeySplit = playerInfoKey.split("-");
				
				if (playerKeySplit[0].equals(player) || playerKeySplit[0] == player){

					if (playerKeySplit[1].equalsIgnoreCase(territory)){
						
						int playerInfoValue = StartUpPhaseModel.playerInfo.get(playerInfoKey);
						
						setPrevArmies(playerInfoValue);
						
						playerInfoValue = playerInfoValue + moveReinforcements;
						
						StartUpPhaseModel.playerInfo.put(playerInfoKey, playerInfoValue);
						
						reinforcementArmies = reinforcementArmies - moveReinforcements;
						
						setTotalReinforcementArmies(reinforcementArmies);
						
						setLatestArmies(playerInfoValue);
						
						setMsgUI("reinforceHuman," + playerKeySplit[0] + "," + playerKeySplit[1]);
						
						setChanged();
						
						notifyObservers(this);
						
						break;
						
					}//end if (playerKeySplit[1].equals(territory) || playerKeySplit[1] == territory)

				} // end if(playerVals[0].equals(player) || playerVals[0] == player)
				
			}//end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())
			
			firstCount = 0;

		}//end while (reinforcementArmies != 0)
		
	}//end reinforce Human
		
	
}
