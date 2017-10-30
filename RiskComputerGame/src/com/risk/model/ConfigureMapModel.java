package com.risk.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.risk.ui.SaveMapUponConfigUI;

/**
 * <h1>Configure Map</h1>
 * <p>
 * <b>This class consists methods to create Continents and Countries.</b>
 * <p>
 * 
 * @author Khashyap
 * @author drvg5 - modified class to implement Modified MVC architecture
 * 
 */

public class ConfigureMapModel {

	static HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
	static JInternalFrame jframeContinent = new JInternalFrame();
	static List<String> listToCheckDuplicateCountry = new ArrayList<String>();
	static List<String> listKeyForHashMap = new ArrayList<String>();
	static List<String> listToCheckDuplicateContinent = new ArrayList<String>();
	static List<String> listToCheckDuplicateCountryCapitalize = new ArrayList<String>();
	static List<String> listToCheckDuplicateContinentCapitalize = new ArrayList<String>();

	/**
	 * <p>
	 * This method is used to add continents and countries to Map. User can key in
	 * the list of Continents and Countries one by one Continent Control Value can
	 * also be added
	 * 
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 * 
	 */

	public static void createContinentandCountry(JDesktopPane desktop) {

		JTable tableContinent = new JTable();
		DefaultTableModel modelContinent = new DefaultTableModel(
				new Object[] { "Continent List", "Country", "Continent Control Value" }, 0);
		JLabel labelContinent = new JLabel("Continent");
		labelContinent.setBounds(20, 390, 60, 25);
		JTextField textContinent = new JTextField();
		textContinent.setBounds(150, 390, 100, 25);
		JLabel labelCountry = new JLabel("Country");
		labelCountry.setBounds(280, 390, 60, 25);
		JTextField textCountry = new JTextField();
		textCountry.setBounds(350, 390, 100, 25);
		JLabel labelContinentControlValue = new JLabel(
				"<html>Control Value(<font color='blue'>Alter while changing Continent</font>)</html>");
		labelContinentControlValue.setBounds(460, 390, 380, 25);
		JTextField textContinentControlValue = new JTextField();
		textContinentControlValue.setBounds(730, 390, 50, 25);
		JButton btnAddAll = new JButton("Add to Map");
		btnAddAll.setBounds(20, 450, 150, 25);
		JButton btnConfigureAdj = new JButton("Add Adjacency after Configuring");
		btnConfigureAdj.setBounds(180, 450, 300, 25);
		JButton btnEdit = new JButton("Update incorrect entry by selecting row");
		// btnEdit.setVisible(false);
		btnEdit.setBounds(490, 450, 270, 25);

		tableContinent.setModel(modelContinent);

		// Listen for changes in the Continent value and enables
		// ControlContinentValue
		// Text Box

		textContinent.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				textContinentControlValue.setText(null);
				textContinentControlValue.setEnabled(true);
			}

			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textContinentControlValue.setText(null);
				textContinentControlValue.setEnabled(true);
			}

			public void changedUpdate(DocumentEvent e) {
				textContinentControlValue.setText(null);
				textContinentControlValue.setEnabled(true);
			}
		});

		// Mouse Listener Event to handle Edit and Update Functionality

		tableContinent.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int i = tableContinent.getSelectedRow();
				String strtextContinentToChange = modelContinent.getValueAt(i, 0).toString().toLowerCase();
				String strtextCountryToChange = modelContinent.getValueAt(i, 1).toString().toLowerCase();
				String strtextContinentControlValueToChange = modelContinent.getValueAt(i, 2).toString();
				textContinent.setText(strtextContinentToChange);
				textCountry.setText(strtextCountryToChange);
				textContinentControlValue.setText(strtextContinentControlValueToChange);
				String strtextContinentToChangeCapitalize = strtextContinentToChange.substring(0, 1).toUpperCase()
						+ strtextContinentToChange.substring(1);
				String strtextCountryToChangeCapitalize = strtextCountryToChange.substring(0, 1).toUpperCase()
						+ strtextCountryToChange.substring(1);
				StringJoiner joinerToDelete = new StringJoiner(",");
				joinerToDelete.add(strtextContinentToChangeCapitalize).add(strtextCountryToChangeCapitalize);
				String concatStringToDelete = joinerToDelete.toString();
				listKeyForHashMap.remove(concatStringToDelete);
				listToCheckDuplicateContinent.add(strtextContinentToChange);
				listToCheckDuplicateCountry.remove(strtextCountryToChange);
				listToCheckDuplicateContinentCapitalize.add(strtextContinentToChangeCapitalize);
				listToCheckDuplicateCountryCapitalize.add(strtextCountryToChangeCapitalize);
				continentControlValueHashMap.remove(strtextContinentToChangeCapitalize);
			}
		});

		// Button to Edit Continent and Country value

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableContinent.getSelectedRow();
				if (row > -1) {
					String strContinentToUpdate = textContinent.getText().toString().toLowerCase();
					String strContinentToUpdateCapitalize = strContinentToUpdate.substring(0, 1).toUpperCase()
							+ strContinentToUpdate.substring(1);
					String strCountryToUpdate = textCountry.getText().toString().toLowerCase();
					String strCountryToUpdateCapitalize = strCountryToUpdate.substring(0, 1).toUpperCase()
							+ strCountryToUpdate.substring(1);
					String strContinentControlValueToUpdate = textContinentControlValue.getText();
					modelContinent.setValueAt(strContinentToUpdateCapitalize, row, 0);
					modelContinent.setValueAt(strCountryToUpdateCapitalize, row, 1);
					modelContinent.setValueAt(strContinentControlValueToUpdate, row, 2);
					int continetControlValue = Integer.parseInt(strContinentControlValueToUpdate);
					StringJoiner joinerToUpdateHashMap = new StringJoiner(",");
					joinerToUpdateHashMap.add(strContinentToUpdateCapitalize).add(strCountryToUpdateCapitalize);
					String concatString = joinerToUpdateHashMap.toString();
					listKeyForHashMap.add(concatString);

					// int continetControlValue =
					// Integer.parseInt(strContinentControlValueToUpdate);

					continentControlValueHashMap.put(strContinentToUpdateCapitalize, continetControlValue);
					listToCheckDuplicateContinent.add(strContinentToUpdate);
					listToCheckDuplicateCountry.add(strCountryToUpdate);
					listToCheckDuplicateContinentCapitalize.add(strContinentToUpdateCapitalize);
					listToCheckDuplicateCountryCapitalize.add(strCountryToUpdateCapitalize);

				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to Update", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 380);
		jframeContinent = new JInternalFrame("Continent and Country");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(labelContinent);
		jframeContinent.add(labelCountry);
		jframeContinent.add(labelContinentControlValue);
		jframeContinent.add(textContinent);
		jframeContinent.add(textCountry);
		jframeContinent.add(textContinentControlValue);
		jframeContinent.add(btnAddAll);
		jframeContinent.add(btnConfigureAdj);
		jframeContinent.add(btnEdit);
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);
	}

	public String[] addContinentsAndCountries(String[] data) {

		String strContinent = data[0];
		String strCountry = data[1];
		String strControlValue = data[2];
		String[] dataReturned = new String[3];
		;
		boolean checkCountry = false;
		boolean checkContinentWithCountry = false;
		boolean checkCountryWithContinent = false;

		if (!(listToCheckDuplicateCountry.isEmpty())) {
			for (String str : listToCheckDuplicateCountry) {
				if (str.equals(strCountry)) {
					checkCountry = true;
				}
			}
		}

		if (!(listToCheckDuplicateContinent.isEmpty())) {
			for (String temp : listToCheckDuplicateContinent) {
				if (temp.equals(strCountry)) {
					checkContinentWithCountry = true;
				}
			}
		}

		if (!(listToCheckDuplicateCountry.isEmpty())) {
			for (String temp : listToCheckDuplicateCountry) {
				if (temp.equals(strContinent)) {
					checkCountryWithContinent = true;
				}
			}
		}

		if ((strContinent.isEmpty()) || (strCountry.isEmpty()) || (strControlValue.isEmpty())) {
			JOptionPane.showMessageDialog(null, "Oops!Please enter values", "Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (!(strContinent.matches("^[a-zA-Z]+$")) || !(strCountry.matches("^[a-zA-Z]+$"))) {
			JOptionPane.showMessageDialog(null, "Only Alphabets are allowed", "Error in Continent and Country",
					JOptionPane.ERROR_MESSAGE);
		}

		else if (checkCountry) {
			JOptionPane.showMessageDialog(null, "Invalid Map!" + strCountry + " already exists in Country List",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (checkCountryWithContinent) {

			JOptionPane.showMessageDialog(null, "Invalid Map!" + strContinent + " already exists in Country List",
					"Error", JOptionPane.ERROR_MESSAGE);

		}

		else if (checkContinentWithCountry) {
			JOptionPane.showMessageDialog(null, "Invalid Map!" + strCountry + " already exists in Continent List",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (strContinent.equals(strCountry)) {
			JOptionPane.showMessageDialog(null, "Invalid Map! Continent and Country name cant be same", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		else if (!(strControlValue.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Only Numbers are  allowed", "Error in Control Value",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			String strContinentCapitalize = strContinent.substring(0, 1).toUpperCase() + strContinent.substring(1);
			String strCountryCapitalize = strCountry.substring(0, 1).toUpperCase() + strCountry.substring(1);
			int continetControlValue = Integer.parseInt(strControlValue);
			StringJoiner joiner = new StringJoiner(",");
			joiner.add(strContinentCapitalize).add(strCountryCapitalize);
			String concatString = joiner.toString();
			listKeyForHashMap.add(concatString);
			continentControlValueHashMap.put(strContinentCapitalize, continetControlValue);
			listToCheckDuplicateContinentCapitalize.add(strContinentCapitalize);
			listToCheckDuplicateCountryCapitalize.add(strCountryCapitalize);
			listToCheckDuplicateContinent.add(strContinent);
			listToCheckDuplicateCountry.add(strCountry);

			dataReturned[0] = strContinentCapitalize;
			dataReturned[1] = strCountryCapitalize;
			dataReturned[2] = strControlValue;

		}
		return dataReturned;

	}

	public void updateOnClick(String[] data) {
		listKeyForHashMap.remove(data[0]);
		listToCheckDuplicateContinent.add(data[1]);
		listToCheckDuplicateCountry.remove(data[2]);
		listToCheckDuplicateContinentCapitalize.add(data[3]);
		listToCheckDuplicateCountryCapitalize.add(data[4]);
		continentControlValueHashMap.remove(data[5]);
	}

	public void updateOnClickOfButton(String[] data) {

		listKeyForHashMap.add(data[0]);
		int continetControlValue = Integer.parseInt(data[2]);
		continentControlValueHashMap.put(data[1], continetControlValue);
		listToCheckDuplicateContinent.add(data[3]);
		listToCheckDuplicateCountry.add(data[4]);
		listToCheckDuplicateContinentCapitalize.add(data[5]);
		listToCheckDuplicateCountryCapitalize.add(data[6]);
	}

	public void inputForAdjacency(JDesktopPane desktop) {
		new SaveMapUponConfigUI().saveToFile(listKeyForHashMap, continentControlValueHashMap,
				listToCheckDuplicateContinentCapitalize, listToCheckDuplicateCountryCapitalize, desktop);
	}

}
