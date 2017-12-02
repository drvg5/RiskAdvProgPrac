package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.model.TournamentModeModel;
 

/**
 * The Class TournamentModeUI shows buttons for tournament mode elements such as number of players, maps, strategies, etc.
 */
public class TournamentModeUI {

	/** The jframe tournament. */
	static JInternalFrame jframeTournament = new JInternalFrame();
	
	/** The intcount of gameselected value. */
	static int intcountOfGameselectedValue;
	
	/** The intcount of mapselected value. */
	static int intcountOfMapselectedValue;
	
	/** The selected player mode list. */
	static List<String> selectedPlayerModeList;
	
	/** The intcount of number of round robins value. */
	static int intCountOfRoundRobins;

	/**
	 * Gets the tournament input.
	 *
	 * @param desktop the desktop
	 */
	public static void getTournamentInput(final JDesktopPane desktop) {

		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(300, 350, 140, 30);
		String[] countOfMap = new String[] { "0", "1", "2", "3", "4", "5" };
		String[] countOfGame = new String[] { "1", "2", "3", "4", "5" };
		
		
		String[] arrRounds = new String[41];
		for(int i = 10; i <=50; i++){
			
			arrRounds[i-10] = Integer.toString(i);
		}
		
		String[] countOfRounds = arrRounds;
		
		DefaultListModel<String> modelPlayerMode = new DefaultListModel<>();

		final JList<String> jlistPlayerMode;
		JLabel labelGameCount = new JLabel("No.Of Games");
		labelGameCount.setBounds(20, 60, 100, 25);
		final JComboBox<String> jomboCountOfGame = new JComboBox<String>(countOfGame);
		jomboCountOfGame.setBounds(160, 60, 60, 25);
		modelPlayerMode.addElement("Aggressive");
		modelPlayerMode.addElement("Benevolent");
		modelPlayerMode.addElement("Random");
		modelPlayerMode.addElement("Cheater");
		JLabel labelPlayerMode = new JLabel("Select Player Mode");
		labelPlayerMode.setBounds(20, 140, 140, 25);
		jlistPlayerMode = new JList<>(modelPlayerMode);
		jlistPlayerMode.setBounds(160, 140, 80, 80);
		JLabel labelCountOfTurns = new JLabel("No.Of Game Turns");
		labelCountOfTurns.setBounds(20, 100, 140, 25);
		SpinnerListModel roundModel = new SpinnerListModel(countOfRounds);
		final JSpinner jomboCountOfRounds = new JSpinner(roundModel);
		jomboCountOfRounds.setBounds(160, 100, 80, 25);
//		JTextField textCountOfTurns = new JTextField();
//		textCountOfTurns.setBounds(160, 100, 80, 25);
		final JFileChooser fileChooser = new JFileChooser();
		JLabel labelMapCount = new JLabel("No.Of Maps");
		labelMapCount.setBounds(20, 250, 100, 25);
		final JComboBox<String> jomboCountOfMap = new JComboBox<String>(countOfMap);
		jomboCountOfMap.setBounds(160, 250, 60, 25);
		final JButton buttonMap1 = new JButton("Upload Map1");
		buttonMap1.setBounds(20, 280, 140, 30);
		buttonMap1.setVisible(false);
		final JButton buttonMap2 = new JButton("Upload Map2");
		buttonMap2.setBounds(180, 280, 140, 30);
		buttonMap2.setVisible(false);
		final JButton buttonMap3 = new JButton("Upload Map3");
		buttonMap3.setBounds(340, 280, 140, 30);
		buttonMap3.setVisible(false);
		final JButton buttonMap4 = new JButton("Upload Map4");
		buttonMap4.setBounds(500, 280, 140, 30);
		buttonMap4.setVisible(false);
		final JButton buttonMap5 = new JButton("Upload Map5");
		buttonMap5.setBounds(660, 280, 140, 30);
		buttonMap5.setVisible(false);

		jlistPlayerMode.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					selectedPlayerModeList = jlistPlayerMode.getSelectedValuesList();
				}
			}
		});

		jomboCountOfRounds.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				 
				String roundRobins = (String) jomboCountOfRounds.getValue();
				
				intCountOfRoundRobins = Integer.parseInt(roundRobins);
				
			
				
			}
		});
		
		
		jomboCountOfMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buttonMap1.setVisible(false);
				buttonMap2.setVisible(false);
				buttonMap3.setVisible(false);
				buttonMap4.setVisible(false);
				buttonMap5.setVisible(false);
				String countOfMapselectedValue = (String) jomboCountOfMap.getSelectedItem();
				intcountOfMapselectedValue = Integer.parseInt(countOfMapselectedValue);

				switch (intcountOfMapselectedValue) {

				case 1:
					buttonMap1.setVisible(true);
					break;
				case 2:
					buttonMap1.setVisible(true);
					buttonMap2.setVisible(true);
					break;
				case 3:
					buttonMap1.setVisible(true);
					buttonMap2.setVisible(true);
					buttonMap3.setVisible(true);
					break;
				case 4:
					buttonMap1.setVisible(true);
					buttonMap2.setVisible(true);
					buttonMap3.setVisible(true);
					buttonMap4.setVisible(true);
					break;
				case 5:
					buttonMap1.setVisible(true);
					buttonMap2.setVisible(true);
					buttonMap3.setVisible(true);
					buttonMap4.setVisible(true);
					buttonMap5.setVisible(true);
					break;

				}

			}
		});

		jomboCountOfGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String countOfGameselectedValue = (String) jomboCountOfGame.getSelectedItem();
				intcountOfGameselectedValue = Integer.parseInt(countOfGameselectedValue);
			}
		});
		

		
		buttonMap1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setBounds(10, 20, 30, 100);
				int returnValue = fileChooser.showOpenDialog(null);
				new TournamentModeModel().loadHashMap(returnValue, fileChooser.getSelectedFile(),
						intcountOfMapselectedValue, intcountOfGameselectedValue, selectedPlayerModeList);
			}
		});

		buttonMap2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setBounds(10, 20, 30, 100);
				int returnValue = fileChooser.showOpenDialog(null);
				new TournamentModeModel().loadHashMap(returnValue, fileChooser.getSelectedFile(),
						intcountOfMapselectedValue, intcountOfGameselectedValue, selectedPlayerModeList);

			}
		});

		buttonMap3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setBounds(10, 20, 30, 100);
				int returnValue = fileChooser.showOpenDialog(null);
				new TournamentModeModel().loadHashMap(returnValue, fileChooser.getSelectedFile(),
						intcountOfMapselectedValue, intcountOfGameselectedValue, selectedPlayerModeList);

			}
		});

		buttonMap4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setBounds(10, 20, 30, 100);
				int returnValue = fileChooser.showOpenDialog(null);
				new TournamentModeModel().loadHashMap(returnValue, fileChooser.getSelectedFile(),
						intcountOfMapselectedValue, intcountOfGameselectedValue, selectedPlayerModeList);

			}
		});

		buttonMap5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileChooser.setBounds(10, 20, 30, 100);
				int returnValue = fileChooser.showOpenDialog(null);
				new TournamentModeModel().loadHashMap(returnValue, fileChooser.getSelectedFile(),
						intcountOfMapselectedValue, intcountOfGameselectedValue, selectedPlayerModeList);

			}
		});

		buttonCloseUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeTournament.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		jframeTournament = new JInternalFrame("Tournament mode");
		jframeTournament.setLayout(null);
		jframeTournament.add(labelMapCount);
		jframeTournament.add(jomboCountOfMap);
		jframeTournament.add(jomboCountOfGame);
		jframeTournament.add(jlistPlayerMode);
		jframeTournament.add(labelCountOfTurns);
		jframeTournament.add(jomboCountOfRounds);
		jframeTournament.add(labelGameCount);
		jframeTournament.add(labelPlayerMode);
//		jframeTournament.add(textCountOfTurns);
		jframeTournament.add(buttonMap1);
		jframeTournament.add(buttonMap2);
		jframeTournament.add(buttonMap3);
		jframeTournament.add(buttonMap4);
		jframeTournament.add(buttonMap5);
		jframeTournament.add(buttonCloseUpload);
		jframeTournament.setSize(800, 600);
		jframeTournament.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeTournament.setVisible(true);
		desktop.add(jframeTournament);

	}

	/**
	 * Show error message for upload.
	 *
	 * @param i the i
	 */
	public void showErrorMessageForUpload(int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Invalid Map!File extension is wrong", "Upload Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (i == 2) {
			JOptionPane.showMessageDialog(null, "Invalid Map! File is missing Map or Continent or Territory section",
					"Upload Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (i == 3) {
			JOptionPane.showMessageDialog(null, "Invalid Map! Not a connected graph", "Adjacency Error",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			JOptionPane.showMessageDialog(null, "Invalid Map! Duplicate Countries", "Load Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}