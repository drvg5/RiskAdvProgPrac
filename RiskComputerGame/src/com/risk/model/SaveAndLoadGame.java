package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class SaveAndLoadGame implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public HashMap<String, List<String>> mainMapToSave;
	public HashMap<String, String> dominationOldToSave = new HashMap<String, String>();
	public HashMap<String, String> dominationNewToSave = new HashMap<String, String>();
	public HashMap<String, Integer> playerInfoToSave = new HashMap<String, Integer>();
	public HashMap<String, Integer> terrPerPlayerToSave = new HashMap<String, Integer>();
	public HashMap<String, String> terrContToSave = new HashMap<String, String>();
	public TreeMap<String, Integer> terrPerContToSave = new TreeMap<String, Integer>();
	public HashMap<String, Integer> reinforcementToSave = new HashMap<String, Integer>();
	public HashMap<String, ArrayList<String>> playerCardsToSave = new HashMap<String, ArrayList<String>>();
	public HashMap<String, ArrayList<String>> prevPlayerCardsToSave = new HashMap<String, ArrayList<String>>();
	public HashMap<Integer, String> strategiesToSave = new HashMap<Integer, String>();
	public HashMap<String, Integer> continentControlValueHashMapToSave = new HashMap<String, Integer>();
	public int numberOfPlayersToSave;
	public String currentPlyrStrategyToSave;
	public String state;
	public TreeSet<String> countryTakenToSave = new TreeSet<String>();
	public  int totalTerrToSave = 0;
}
