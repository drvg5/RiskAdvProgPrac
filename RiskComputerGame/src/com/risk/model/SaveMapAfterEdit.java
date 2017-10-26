package com.risk.model;

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
import java.util.Iterator;
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

public class SaveMapAfterEdit {

	static JInternalFrame jframeToUpdate = new JInternalFrame();
	static DefaultTableModel modelToUpdate = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List", "Control Value" }, 0);
	static List<String> continentList = new ArrayList<String>();
	static List<String> countryList = new ArrayList<String>();

	public static void updateAndSave(HashMap<String, List<String>> hashMapToUpdate,
			HashMap<String, Integer> hashMapControlToUpdate, JDesktopPane desktop, String File) {
		JTable tableToUpdate = new JTable();
		JLabel labelContinentToEdit = new JLabel("Continent");
		labelContinentToEdit.setBounds(20, 260, 60, 25);
		JTextField textContinentToEdit = new JTextField();
		textContinentToEdit.setBounds(150, 260, 100, 25);
		JLabel labelCountryToEdit = new JLabel("Country");
		labelCountryToEdit.setBounds(20, 290, 60, 25);
		JTextField textCountryToEdit = new JTextField();
		textCountryToEdit.setBounds(150, 290, 100, 25);
		JLabel labelContinentControlValueToEdit = new JLabel("Control Value");
		labelContinentControlValueToEdit.setBounds(20, 350, 130, 25);
		JTextField textContinentControlValueToEdit = new JTextField();
		textContinentControlValueToEdit.setBounds(150, 350, 50, 25);
		JLabel labelAdjListToEdit = new JLabel("Adjacent Countries");
		labelAdjListToEdit.setBounds(20, 320, 130, 25);
		JTextField textAdjListToEdit = new JTextField();
		textAdjListToEdit.setBounds(150, 320, 200, 25);
		JLabel labelAdjMessageToEdit = new JLabel("<-----Please Enter Comma seperated");
		labelAdjMessageToEdit.setForeground(Color.RED);
		labelAdjMessageToEdit.setBounds(360, 320, 300, 25);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(100, 400, 100, 25);
		JButton btnSave = new JButton("Save Map");
		btnSave.setBounds(360, 400, 100, 25);

		for (Map.Entry<String, Integer> temp : hashMapControlToUpdate.entrySet()) {
			continentList.add(temp.getKey());
		}

		for (String obj : continentList) {
			for (Map.Entry<String, List<String>> entry : hashMapToUpdate.entrySet()) {
				String strKey = entry.getKey();
				String[] strKeyArrayToEdit = strKey.split(",");
				if (obj.equals(strKeyArrayToEdit[0])) {
					String printWithoutBraces = entry.getValue().toString().replaceAll("(^\\[|\\s|\\]$)", "");
					modelToUpdate.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces,
							hashMapControlToUpdate.get(strKeyArrayToEdit[0]) });
				}
			}
		}
		
		for (Map.Entry<String, List<String>> entry : hashMapToUpdate.entrySet()) {
			String strKey = entry.getKey();
			String[] strKeyArrayToEdit = strKey.split(",");
			continentList.add(strKeyArrayToEdit[0]);
			countryList.add(strKeyArrayToEdit[1]);
		}

		tableToUpdate.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int count = 0;
				int i = tableToUpdate.getSelectedRow();
				String strtextContinentToUpdate = modelToUpdate.getValueAt(i, 0).toString().toLowerCase();
				String strtextCountryToUpdate = modelToUpdate.getValueAt(i, 1).toString().toLowerCase();
				String strtextAdjToUpdate = modelToUpdate.getValueAt(i, 2).toString().toLowerCase();
				List<String> listAdjCountryToRemove = new ArrayList<String>();
				listAdjCountryToRemove = new ArrayList<String>(Arrays.asList(strtextAdjToUpdate.split(",")));
				String strtextControlValueToUpdate = modelToUpdate.getValueAt(i, 3).toString().toLowerCase();
				textContinentToEdit.setText(strtextContinentToUpdate);
				textCountryToEdit.setText(strtextCountryToUpdate);
				textAdjListToEdit.setText(strtextAdjToUpdate);
				textContinentControlValueToEdit.setText(strtextControlValueToUpdate);
				String strtextContinentToChangeCapitalize = strtextContinentToUpdate.substring(0, 1).toUpperCase()
						+ strtextContinentToUpdate.substring(1);
				String strtextCountryToChangeCapitalize = strtextCountryToUpdate.substring(0, 1).toUpperCase()
						+ strtextCountryToUpdate.substring(1);
				StringJoiner joinerToDelete = new StringJoiner(",");
				joinerToDelete.add(strtextContinentToChangeCapitalize).add(strtextCountryToChangeCapitalize);
				String concatStringToDelete = joinerToDelete.toString();
				hashMapToUpdate.remove(concatStringToDelete);
				
				
				
				Iterator<Map.Entry<String, List<String>>> iter = hashMapToUpdate.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, List<String>> entry = iter.next();
					List<String> list = entry.getValue();
					for (int k = 0; k < list.size(); k++) {
						if (list.get(k).equals(strtextCountryToChangeCapitalize)) {
							list.remove(k);
						}
					}
					
				}
				
				//continentList.remove(strtextContinentToChangeCapitalize);
			//	countryList.remove(strtextCountryToChangeCapitalize);
				for (int t = 0; t < modelToUpdate.getRowCount(); t++) {
					if (modelToUpdate.getValueAt(t, 0).equals(strtextContinentToUpdate)) {
						count++;
					}
				}
				if (count < 2) {
					hashMapControlToUpdate.remove(strtextContinentToChangeCapitalize);
				}

			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				boolean checkCountry = false;
				boolean CheckEqual = false;
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
								CheckEqual = true;
							}
						}

						if (!(countryList.containsAll(listAdjCountryCapitalize))) {
							checkCountry = true;
						}

						if (CheckEqual) {
							JOptionPane.showMessageDialog(null, "You cannot enter same country", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

						else if (checkCountry) {
							JOptionPane.showMessageDialog(null, "You can link only countries listed here", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

						else {
							StringJoiner joinerToUpdateHashMap = new StringJoiner(",");
							joinerToUpdateHashMap.add(strContinentToUpdateCapitalize).add(strCountryToUpdateCapitalize);
							String concatString = joinerToUpdateHashMap.toString();
							int continetControlValue = Integer.parseInt(strContinentControlValueToUpdate);
							hashMapControlToUpdate.put(strContinentToUpdateCapitalize, continetControlValue);
							hashMapToUpdate.put(concatString, listAdjCountryCapitalize);
							for (String tempAdj : listAdjCountryCapitalize) {

								for (Map.Entry<String, List<String>> maplist : hashMapToUpdate.entrySet()) {
									List<String> fetchLinks = new ArrayList<String>();
									List<String> fetchLinks2 = new ArrayList<String>();
									String getAllKeys = maplist.getKey();
									String[] getIndividual = getAllKeys.split(",");
									if (getIndividual[1].equals(tempAdj)) {
										fetchLinks = hashMapToUpdate.get(getAllKeys);
										fetchLinks2.addAll(fetchLinks);
										fetchLinks2.add(strCountryToUpdateCapitalize);
										hashMapToUpdate.replace(getAllKeys, fetchLinks2);
									}
								}

							}
							
							continentList.add(strContinentToUpdateCapitalize);
							countryList.add(strCountryToUpdateCapitalize);
							
							modelToUpdate.setRowCount(0);
							List<String> tempList = new ArrayList<String>();
							for (Map.Entry<String, Integer> temp : hashMapControlToUpdate.entrySet()) {

								tempList.add(temp.getKey());

							}

							for (String obj : tempList) {

								for (Map.Entry<String, List<String>> entry : hashMapToUpdate.entrySet()) {
									String strKey = entry.getKey();
									String[] strKeyArrayToEdit = strKey.split(",");
									if (obj.equals(strKeyArrayToEdit[0])) {
										String printWithoutBraces = entry.getValue().toString()
												.replaceAll("(^\\[|\\s|\\]$)", "");
										modelToUpdate.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1],
												printWithoutBraces, hashMapControlToUpdate.get(strKeyArrayToEdit[0]) });
									}

								}
							}
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
				String textFileName;
				
				
				textFileName = File;
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
}
