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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class SaveMapUponEditUI {

	static JInternalFrame jframeToUpdate = new JInternalFrame();
	public static DefaultTableModel modelToUpdate = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List", "Control Value" }, 0);
<<<<<<< HEAD
	public static List<String> continentList = new ArrayList<String>();
	public static List<String> countryList = new ArrayList<String>();
	public static HashMap<String, List<String>> hashMapToUpdate = new HashMap<String, List<String>>();
	public static HashMap<String, Integer> hashMapControlToUpdate = new HashMap<String, Integer>();
	static String strContinentSelectedInRow;
	static String strCountrySelectedInRow;
	String textFileName;

	// static JDesktopPane desktopUploadForUpdate;
	SaveMapUponEditModel saveMapUponEditModel = new SaveMapUponEditModel();

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
			continentList.add(strKeyArrayToEdit[0]);
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

				int row = tableToUpdate.getSelectedRow();
				if (row > -1) {
					String strContinentToUpdate = textContinentToEdit.getText().toString().toLowerCase();
					String strCountryToUpdate = textCountryToEdit.getText().toString().toLowerCase();
					String strAdjToUpdate = textAdjListToEdit.getText().toString().toLowerCase();
					String strContinentControlValueToUpdate = textContinentControlValueToEdit.getText();
					// call to model
					saveMapUponEditModel.updateRecord(strAdjToUpdate, strContinentToUpdate, strCountryToUpdate,
							strContinentControlValueToUpdate);

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
				String textFileNameToShow = textFileName.split("\\.", 2)[0];
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
								String printWithoutBraces = iterate.getValue().toString().replaceAll("(^\\[|\\s|\\]$)",
										"");
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

	public void errorMessageForUpdate(int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "You cannot enter same country", "Error", JOptionPane.ERROR_MESSAGE);

		} else if (i == 2) {
			JOptionPane.showMessageDialog(null, "You can link only countries listed here", "Error",
					JOptionPane.ERROR_MESSAGE);
=======
	static List<String> continentList = new ArrayList<String>();
	static List<String> countryList = new ArrayList<String>();
	public static HashMap<String, List<String>> hashMapToUpdate = new HashMap<String, List<String>>();
	public static HashMap<String, Integer> hashMapControlToUpdate = new HashMap<String, Integer>();
	static String strContinentSelectedInRow;
	static String strCountrySelectedInRow;
	String textFileName;
	
	// static JDesktopPane desktopUploadForUpdate;
	SaveMapUponEditModel saveMapUponEditModel = new SaveMapUponEditModel();

	public void updateAndSave(HashMap<String, List<String>> continentHashMapToEdit,
			HashMap<String, Integer> continentControlValueHashMapToEdit, JDesktopPane desktop, final String UploadFileName) {

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
			continentList.add(strKeyArrayToEdit[0]);
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
				String textFileNameToShow = textFileName.split("\\.", 2)[0];
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
								String printWithoutBraces = iterate.getValue().toString().replaceAll("(^\\[|\\s|\\]$)",
										"");
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
>>>>>>> branch 'common' of https://github.com/drvg5/RiskAdvProgPrac.git
		}
	}

}
