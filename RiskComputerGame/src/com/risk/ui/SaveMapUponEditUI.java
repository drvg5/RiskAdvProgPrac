package com.risk.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import javax.swing.table.DefaultTableModel;
import com.risk.model.SaveMapUponEditModel;


/**
 * <h1>Save Map Upon EditUI</h1>
 * <p>
 * <b>This class consists of methods to save map file to local folder after user edits the map attributes </b>
 * User would be able to configure Adjacency between Countries.
 * <p>
 * 
 * @author Khashyap
 * @version 1.0
 */

public class SaveMapUponEditUI {

	/** Frame to display data */
	static JInternalFrame jframeToUpdate = new JInternalFrame();
	
	/** Model to bind data to panel */
	public static DefaultTableModel modelToUpdate = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List", "Control Value" }, 0);
	
	/** List consists of continents */
	public static List<String> continentList = new ArrayList<String>();
	
	/** List consists of countries */
	public static List<String> countryList = new ArrayList<String>();
	
	/**Hash map to update the Map attributes. */
	public static HashMap<String, List<String>> hashMapToUpdate = new HashMap<String, List<String>>();
	
	/** Hash map to update control value of  Continents */
	public static HashMap<String, Integer> hashMapControlToUpdate = new HashMap<String, Integer>();
	
	/** Consists of Continent selected by user to update adjacency. */
	static String strContinentSelectedInRow;
	
	/** Consists of Country selected by user to update adjacency. */
	static String strCountrySelectedInRow;
	
	/** File name selected by user */
	String textFileName;

	/** The save map upon edit model. */
	// static JDesktopPane desktopUploadForUpdate;
	SaveMapUponEditModel saveMapUponEditModel = new SaveMapUponEditModel();

	/**
	 * <p>
	 * This method is used to update and save Map file after User completes the editing.
	 *
	 * @author Khashyap
	 * @param continentHashMapToEdit Hash map to edit the Map attributes
	 * @param continentControlValueHashMapToEdit Hash map to edit control value of  Continents
	 * @param desktop To bind the InternalFrame with Main window frame
	 * @param UploadFileName Filename selected by user
	 */
		
	public void updateAndSave(HashMap<String, List<String>> continentHashMapToEdit,
			HashMap<String, Integer> continentControlValueHashMapToEdit, JDesktopPane desktop,
			final String UploadFileName) {

		hashMapToUpdate = continentHashMapToEdit;
		hashMapControlToUpdate = continentControlValueHashMapToEdit;
		textFileName = UploadFileName;
		final JTable tableToUpdate = new JTable();
		JLabel labelContinentToEdit = new JLabel("Continent");
		labelContinentToEdit.setBounds(20, 260, 60, 25);
		final JTextField textContinentToEdit = new JTextField();
		textContinentToEdit.setBounds(150, 260, 100, 25);
		JLabel labelCountryToEdit = new JLabel("Country");
		labelCountryToEdit.setBounds(20, 290, 60, 25);
		final JTextField textCountryToEdit = new JTextField();
		textCountryToEdit.setBounds(150, 290, 100, 25);
		JLabel labelContinentControlValueToEdit = new JLabel("Control Value");
		labelContinentControlValueToEdit.setBounds(20, 350, 130, 25);
		final JTextField textContinentControlValueToEdit = new JTextField();
		textContinentControlValueToEdit.setBounds(150, 350, 50, 25);
		JLabel labelAdjListToEdit = new JLabel("Adjacent Countries");
		labelAdjListToEdit.setBounds(20, 320, 130, 25);
		final JTextField textAdjListToEdit = new JTextField();
		textAdjListToEdit.setBounds(150, 320, 200, 25);
		JLabel labelAdjMessageToEdit = new JLabel("<-----Please Enter Comma seperated");
		labelAdjMessageToEdit.setForeground(Color.RED);
		labelAdjMessageToEdit.setBounds(360, 320, 300, 25);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(100, 400, 100, 25);
		JButton btnSave = new JButton("Save Map");
		btnSave.setBounds(360, 400, 100, 25);

		for (Map.Entry<String, List<String>> entry : hashMapToUpdate.entrySet()) {
			String strKey = entry.getKey();
			String[] strKeyArrayToEdit = strKey.split(",");
			if(!(continentList.contains(strKeyArrayToEdit[0])))
			{
			continentList.add(strKeyArrayToEdit[0]);
			}
			if(!(countryList.contains(strKeyArrayToEdit[1])))
			countryList.add(strKeyArrayToEdit[1]);
		}

		reloadModel();

		tableToUpdate.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int count = 0;
				int i = tableToUpdate.getSelectedRow();
				String strtextContinentToUpdate = modelToUpdate.getValueAt(i, 0).toString().toLowerCase();
				String strtextCountryToUpdate = modelToUpdate.getValueAt(i, 1).toString().toLowerCase();
				String strtextAdjToUpdate = modelToUpdate.getValueAt(i, 2).toString().toLowerCase();
				List<String> listAdjCountryToRemove = new ArrayList<String>();
				List<String> listAdjCountryToRemoveCapitalize = new ArrayList<String>();
				listAdjCountryToRemove = new ArrayList<String>(Arrays.asList(strtextAdjToUpdate.split(",")));
				String strtextControlValueToUpdate = modelToUpdate.getValueAt(i, 3).toString().toLowerCase();
				textContinentToEdit.setText(strtextContinentToUpdate);
				textCountryToEdit.setText(strtextCountryToUpdate);
				textAdjListToEdit.setText(strtextAdjToUpdate);
				textContinentControlValueToEdit.setText(strtextControlValueToUpdate);

				if (!(strtextAdjToUpdate.isEmpty())) {

					// call to model
					saveMapUponEditModel.removeRecord(strtextContinentToUpdate, strtextCountryToUpdate,
							strtextAdjToUpdate);
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				boolean checkCountry = false;
				boolean checkEqual = false;
				int row = tableToUpdate.getSelectedRow();
				if (row > -1) {
					String strContinentToUpdate = textContinentToEdit.getText().toString().toLowerCase();
					String strCountryToUpdate = textCountryToEdit.getText().toString().toLowerCase();
					String strAdjToUpdate = textAdjListToEdit.getText().toString().toLowerCase();
					String strContinentControlValueToUpdate = textContinentControlValueToEdit.getText();

					if ((strContinentToUpdate.isEmpty()) || (strCountryToUpdate.isEmpty()) || (strAdjToUpdate.isEmpty())
							|| (strContinentControlValueToUpdate.isEmpty())) {
						JOptionPane.showMessageDialog(null, "Oops!Please enter values", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					else if (!(strContinentControlValueToUpdate.matches("^[0-9]+$"))) {
						JOptionPane.showMessageDialog(null, "Only Numbers are  allowed", "Error in Control Value",
								JOptionPane.ERROR_MESSAGE);
					}

					else {
						List<String> listAdjCountry = new ArrayList<String>();
						List<String> listAdjCountryCapitalize = new ArrayList<String>();
						listAdjCountry = new ArrayList<String>(Arrays.asList(strAdjToUpdate.split(",")));
						for (String capital : listAdjCountry) {
							capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
							listAdjCountryCapitalize.add(capital);
						}
						String strContinentToUpdateCapitalize = strContinentToUpdate.substring(0, 1).toUpperCase()
								+ strContinentToUpdate.substring(1);
						String strCountryToUpdateCapitalize = strCountryToUpdate.substring(0, 1).toUpperCase()
								+ strCountryToUpdate.substring(1);

						for (String st : listAdjCountryCapitalize) {
							if (st.equals(strCountryToUpdateCapitalize)) {
								checkEqual = true;
							}
						}

						if (!(countryList.containsAll(listAdjCountryCapitalize))) {
							checkCountry = true;
						}

						if (checkEqual) {
							JOptionPane.showMessageDialog(null, "You cannot enter same country", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

						else if (checkCountry) {
							JOptionPane.showMessageDialog(null, "You can link only countries listed here", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

						else {
							continentList.remove(strContinentSelectedInRow);
							countryList.remove(strContinentSelectedInRow);
							StringJoiner joinerToUpdateHashMap = new StringJoiner(",");
							joinerToUpdateHashMap.add(strContinentToUpdateCapitalize).add(strCountryToUpdateCapitalize);
							String concatString = joinerToUpdateHashMap.toString();
							int continetControlValue = Integer.parseInt(strContinentControlValueToUpdate);
							hashMapControlToUpdate.put(strContinentToUpdateCapitalize, continetControlValue);
							hashMapToUpdate.put(concatString, listAdjCountryCapitalize);
							for (String tempAdj : listAdjCountryCapitalize) {

								for (Map.Entry<String, List<String>> maplist : hashMapToUpdate.entrySet()) {
									List<String> fetchLinksFromHashMap = new ArrayList<String>();
									List<String> fetchLinksToAddHashMap = new ArrayList<String>();
									String getAllKeys = maplist.getKey();
									String[] getIndividual = getAllKeys.split(",");
									if (getIndividual[1].equals(tempAdj)) {
										fetchLinksFromHashMap = hashMapToUpdate.get(getAllKeys);
										fetchLinksToAddHashMap.addAll(fetchLinksFromHashMap);
										fetchLinksToAddHashMap.add(strCountryToUpdateCapitalize);
										hashMapToUpdate.replace(getAllKeys, fetchLinksToAddHashMap);
									}
								}

							}

							if (!(continentList.contains(strContinentToUpdateCapitalize))) {
								continentList.add(strContinentToUpdateCapitalize);
							}

							if (!(countryList.contains(strCountryToUpdateCapitalize))) {
								countryList.add(strCountryToUpdateCapitalize);
							}

							reloadModel();
						}
					}
				}
				textAdjListToEdit.setText(null);
				textContinentControlValueToEdit.setText(null);
				textContinentToEdit.setText(null);
				textCountryToEdit.setText(null);

			}

		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileWriter fstream;
				BufferedWriter out;
				List<String> continentListToPrint = new ArrayList<String>();
				boolean checkConnected = false;
				String textFileNameToShow = textFileName.split("\\.", 2)[0];
				List<String> connectivityCheck = new ArrayList<String>();
				Iterator<Map.Entry<String, List<String>>> iter = hashMapToUpdate.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, List<String>> entry = iter.next();
					String strKey = entry.getKey();
					String[] strKeyArrayToCheck = strKey.split(",");
					Iterator<Map.Entry<String, List<String>>> innerIter = hashMapToUpdate.entrySet().iterator();
					while (innerIter.hasNext()) {
						Map.Entry<String, List<String>> innerEntry = innerIter.next();
						String strinnerKey = innerEntry.getKey();
						String[] strinnerKeyArrayToCheck = strinnerKey.split(",");
						List<String> innerList = innerEntry.getValue();
						if (!((strKeyArrayToCheck[0]).equals(strinnerKeyArrayToCheck[0]))) {
							for (int inner = 0; inner < innerList.size(); inner++) {
								if (strKeyArrayToCheck[1].equals(innerList.get(inner))) {
									if (!(connectivityCheck.contains(strKeyArrayToCheck[1]))) {
										connectivityCheck.add(strKeyArrayToCheck[0]);
									}
								}
							}
						}
					}

				}
				List<String> continentListCheckNow = new ArrayList<String>();
				for (Map.Entry<String, List<String>> entry : hashMapToUpdate.entrySet()) {
					String strKey = entry.getKey();
					String[] strKeyArrayToEdit = strKey.split(",");
					if(!(continentListCheckNow.contains(strKeyArrayToEdit[0])))
					{
					continentListCheckNow.add(strKeyArrayToEdit[0]);
					}
				}
				
				
				int sizeOfCont = continentListCheckNow.size();
				Set<String> hs = new HashSet<>();
				hs.addAll(connectivityCheck);
				connectivityCheck.clear();
				connectivityCheck.addAll(hs);
				Collection<String> collectionAdjCont = connectivityCheck;
				Collection<String> collectionTotalCont = continentListCheckNow;
				collectionTotalCont.removeAll(collectionAdjCont);
				if (collectionTotalCont.isEmpty() || sizeOfCont == 1) {
					checkConnected = true;
				}

				if (!(checkConnected)) {
					JOptionPane.showMessageDialog(null,
							"Invalid Map! Not a connected graph. Check adjacency for" + collectionTotalCont,
							"Adjacency Error", JOptionPane.ERROR_MESSAGE);
				}

				else {

					try {

						fstream = new FileWriter("C:/Users/Khashyap/Documents/Maps/" + textFileName);
						out = new BufferedWriter(fstream);
						out.write("[Map]");
						out.write(System.getProperty("line.separator"));
						out.write(System.getProperty("line.separator"));
						out.write("[Continents]");
						out.write(System.getProperty("line.separator"));
						for (Map.Entry<String, Integer> temp : hashMapControlToUpdate.entrySet()) {

							continentListToPrint.add(temp.getKey());
							out.write(temp.getKey() + "=" + temp.getValue());
							out.write(System.getProperty("line.separator"));
						}
						out.write(System.getProperty("line.separator"));
						out.write("[Territories]");
						out.write(System.getProperty("line.separator"));
						for (String obj : continentListToPrint) {
							for (Map.Entry<String, List<String>> iterate : hashMapToUpdate.entrySet()) {
								String strKey = iterate.getKey();
								String[] strKeyArray = strKey.split(",");
								if (obj.equals(strKeyArray[0])) {
									String printWithoutBraces = iterate.getValue().toString()
											.replaceAll("(^\\[|\\s|\\]$)", "");
									out.write(strKeyArray[1] + "," + "50" + "," + "50" + "," + strKeyArray[0] + ","
											+ printWithoutBraces);
									out.write(System.getProperty("line.separator"));
								}

							}
							out.write(System.getProperty("line.separator"));
						}
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, textFileNameToShow + " \t is saved");
					jframeToUpdate.setVisible(false);
				}
			}
		});

		tableToUpdate.setModel(modelToUpdate);
		JScrollPane panel = new JScrollPane(tableToUpdate);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 250);
		jframeToUpdate = new JInternalFrame("Add Continent");
		jframeToUpdate.setLayout(null);
		jframeToUpdate.add(panel);
		jframeToUpdate.add(labelContinentToEdit);
		jframeToUpdate.add(labelCountryToEdit);
		jframeToUpdate.add(labelContinentControlValueToEdit);
		jframeToUpdate.add(labelAdjListToEdit);
		jframeToUpdate.add(labelAdjMessageToEdit);
		jframeToUpdate.add(textContinentToEdit);
		jframeToUpdate.add(textCountryToEdit);
		jframeToUpdate.add(textContinentControlValueToEdit);
		jframeToUpdate.add(textAdjListToEdit);
		jframeToUpdate.add(btnUpdate);
		jframeToUpdate.add(btnSave);
		jframeToUpdate.setSize(800, 600);
		jframeToUpdate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeToUpdate.setVisible(true);
		desktop.add(jframeToUpdate);

	}

	
	/**
	 * <p>
	 * This method is used to reload Data model after changes in HashMap value.
	 *
	 * @author Khashyap
	 */

	
	public static void reloadModel() {
		modelToUpdate.setRowCount(0);
		List<String> tempList = new ArrayList<String>();
		for (Map.Entry<String, Integer> temp : hashMapControlToUpdate.entrySet()) {

			tempList.add(temp.getKey());

		}

		for (String obj : tempList) {

			for (Map.Entry<String, List<String>> entry : hashMapToUpdate.entrySet()) {
				String strKey = entry.getKey();
				String[] strKeyArrayToUpdate = strKey.split(",");
				if (obj.equals(strKeyArrayToUpdate[0])) {
					String printWithoutBraces = entry.getValue().toString().replaceAll("(^\\[|\\s|\\]$)", "");
					modelToUpdate.addRow(new Object[] { strKeyArrayToUpdate[0], strKeyArrayToUpdate[1],
							printWithoutBraces, hashMapControlToUpdate.get(strKeyArrayToUpdate[0]) });
				}

			}
		}
	}


	/**
	 * This methods displays all error messages to User.
	 *
	 * @author Dhruv
	 * @param  i number for particular error message
	 */
	
	public void errorMessageForUpdate(int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "You cannot enter same country", "Error", JOptionPane.ERROR_MESSAGE);

		} else if (i == 2) {
			JOptionPane.showMessageDialog(null, "You can link only countries listed here", "Error",
					JOptionPane.ERROR_MESSAGE);

		}
	}
}
