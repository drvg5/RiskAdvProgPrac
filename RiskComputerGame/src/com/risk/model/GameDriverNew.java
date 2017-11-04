package com.risk.model;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameDriverNew extends Object{
	
	public static int playerGTTerr = 0;
	
	public static void gameStart(HashMap<String,List<String>> territoryMap){
		
		int numberOfPlayers;
		
		while(playerGTTerr != 1)	{
			
			try{
				numberOfPlayers = enterPlayersMenu();
				
				StartUpPhaseModel.preStartUp(numberOfPlayers, territoryMap);
				
				if(playerGTTerr == 0){
					continue;
				}
				else
					playerGTTerr = 1;
				
		
			}
			catch(InputMismatchException e){
				System.out.println("----------------------------------------");
				System.out.println("Please enter only numbers");
				continue;
				
			}
		
		}
		
		
		
	}
	
	
	/**
	 * 
	 * @return numberOfPlayers
	 */
	public static int enterPlayersMenu(){
		
		System.out.println("---------------------------------------------");
		System.out.println("Please enter number of players : ");
		
		int numberOfPlayers = 0;
		
		Scanner input = new Scanner(System.in);
		numberOfPlayers = input.nextInt();
		return numberOfPlayers;
	}
}
