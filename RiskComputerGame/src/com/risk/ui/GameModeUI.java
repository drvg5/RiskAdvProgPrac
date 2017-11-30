package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import com.risk.model.SaveAndLoadGame;
import com.risk.model.StartUpPhaseModel;
import com.risk.model.ResourceManager;
import com.risk.model.GameDriverNew;
import com.risk.model.PlayerClass;
import com.risk.model.ReinforcementPhaseModel;


/**
 * The Class GameModeUI show the varios buttons for load saved game, single game mode, tournament game mode and close window.
 */
public class GameModeUI {

	/** The jframe game mode. */
	static JInternalFrame jframeGameMode;
	
	/** The continent control value hash map to save. */
	static HashMap<String, Integer> continentControlValueHashMapToSave = new HashMap<String, Integer>();
	
	/** The obj player class. */
	static PlayerClass objPlayerClass = new PlayerClass();

	/**
	 * <p>
	 * This method is used to load map file from local folder to play game.
	 *
	 * @author Dhruv
	 * @param desktop            To bind the InternalFrame with Main window frame
	 */

	public static void GameModeWindow(final JDesktopPane desktop) {

		jframeGameMode = new JInternalFrame("Game Mode");
		jframeGameMode.setLayout(null);
		jframeGameMode.setSize(400, 300);
		jframeGameMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton singlePlayerBbutton = new JButton("Single Game Mode");
		singlePlayerBbutton.setBounds(100, 100, 200, 30);
		JButton multiPlayerBbutton = new JButton("Tournament Mode");
		multiPlayerBbutton.setBounds(100, 150, 200, 30);
		JButton loadBbutton = new JButton("Load Saved Map");
		loadBbutton.setBounds(100, 200, 200, 30);
		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(100, 250, 200, 30);
		jframeGameMode.add(singlePlayerBbutton);
		jframeGameMode.add(multiPlayerBbutton);
		jframeGameMode.add(buttonCloseUpload);
		jframeGameMode.add(loadBbutton);
		jframeGameMode.setVisible(true);
		desktop.add(jframeGameMode);

		singlePlayerBbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				UploadMapUI.LoadMap(desktop);
				try {
					jframeGameMode.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		multiPlayerBbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TournamentModeUI.getTournamentInput(desktop);
				try {
					jframeGameMode.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}

			}
		});

		loadBbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("SaveToLoadAgain");
					PlayerClass.currentMap = data.mainMapToSave;
					PlayerClass.setDominationOld(data.dominationOldToSave);
					PlayerClass.setDominationNew(data.dominationNewToSave);
					GameDriverNew.strategies = data.strategiesToSave;
					StartUpPhaseModel.playerInfo = data.playerInfoToSave;
					StartUpPhaseModel.terrPerPlayer = data.terrPerPlayerToSave;
					StartUpPhaseModel.terrCont = data.terrContToSave;
					StartUpPhaseModel.terrPerCont = data.terrPerContToSave;
					ReinforcementPhaseModel.reinforcement = data.reinforcementToSave;
					ReinforcementPhaseModel.playerCards = data.playerCardsToSave;
					ReinforcementPhaseModel.prevPlayerCards = data.prevPlayerCardsToSave;
				//	objPlayerClass.currentPlyrStrategy = data.currentPlyrStrategyToSave;
					
					PlayerClass.setCurrentPlayer(3);
					
					//= data.numberOfPlayersToSave;
					continentControlValueHashMapToSave = data.continentControlValueHashMapToSave;
					StartUpPhaseModel.totalTerr = data.totalTerrToSave;
					StartUpPhaseModel.countryTaken = data.countryTakenToSave;
					GameDriverNew gameDriver = new GameDriverNew();
					
					gameDriver.gameStart(PlayerClass.currentMap, continentControlValueHashMapToSave, GameDriverNew.strategies, true);
					
					
					
					
					
					
//					objPlayerClass.gamePlay(objPlayerClass.plyr, PlayerClass.currentMap,
//							continentControlValueHashMapToSave, GameDriverNew.strategies, data.state);
					
					
				} catch (Exception exLoad) {
					System.out.println("Unable to load saved data" + exLoad.getMessage());
				}
			}
		});

		buttonCloseUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeGameMode.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	/**
	 * <p>
	 * This method is used to close upload button.
	 */

	public void closeUpload() {
		try {
			jframeGameMode.setClosed(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}

}
