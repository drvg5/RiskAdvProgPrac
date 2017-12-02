package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * The Class SaveAndLoadGame contains the variables used to store the data and load the data at a certain instant in the game.
 */
public class SaveAndLoadGame implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The main map to save. */
	public HashMap<String, List<String>> mainMapToSave;
	
	/** The domination old to save. */
	public HashMap<String, String> dominationOldToSave = new HashMap<String, String>();
	
	/** The domination new to save. */
	public HashMap<String, String> dominationNewToSave = new HashMap<String, String>();
	
	/** The player info to save. */
	public HashMap<String, Integer> playerInfoToSave = new HashMap<String, Integer>();
	
	/** The terr per player to save. */
	public HashMap<String, Integer> terrPerPlayerToSave = new HashMap<String, Integer>();
	
	/** The terr cont to save. */
	public HashMap<String, String> terrContToSave = new HashMap<String, String>();
	
	/** The terr per cont to save. */
	public TreeMap<String, Integer> terrPerContToSave = new TreeMap<String, Integer>();
	
	/** The reinforcement to save. */
	public HashMap<String, Integer> reinforcementToSave = new HashMap<String, Integer>();
	
	/** The player cards to save. */
	public HashMap<String, ArrayList<String>> playerCardsToSave = new HashMap<String, ArrayList<String>>();
	
	/** The prev player cards to save. */
	public HashMap<String, ArrayList<String>> prevPlayerCardsToSave = new HashMap<String, ArrayList<String>>();
	
	/** The strategies to save. */
	public HashMap<Integer, String> strategiesToSave = new HashMap<Integer, String>();
	
	/** The continent control value hash map to save. */
	public HashMap<String, Integer> continentControlValueHashMapToSave = new HashMap<String, Integer>();
	
	/** The number of players to save. */
	public int currentPlayer;
	
	/** The current plyr strategy to save. */
	public String currentPlyrStrategyToSave;
	
	/** The state. */
	public String state;
	
	/** The country taken to save. */
	public TreeSet<String> countryTakenToSave = new TreeSet<String>();
	
	/** The total terr to save. */
	public  int totalTerrToSave = 0;
	
	
	
	/** The current plyr index to save. */
	public int currentPlyrIndexToSave;
	
	/** The current plyrs tree set to save. */
	public TreeSet<Integer> currentPlyrsTreeSetToSave = new TreeSet<Integer>();
	
	/** The players list to save. */
	public  ArrayList<Integer> playersListToSave = new ArrayList<Integer>();
}
