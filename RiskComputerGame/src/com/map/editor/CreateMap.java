package com.map.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class CreateMap {

	static HashMap<String, HashMap<String, List<String>>> continentHashMap = new HashMap<String, HashMap<String, List<String>>>();
	static HashMap<String, List<String>> territoryHashMap = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
	static String TxtCountry;
	static String TxtAdjCountry;
	static JInternalFrame jframeContinent = new JInternalFrame();
	static JDesktopPane desktopCreateMap;

	public static void configureContinent(JDesktopPane desktop) {

		JTable tableContinent = new JTable();
		List<String> continentList = new ArrayList<String>();
		DefaultTableModel modelContinent = new DefaultTableModel(
				new Object[] { "Continent List", "Country", "Adjacent List" }, 0);
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
		JLabel lavelAdjList = new JLabel("Adjacent Countries");
		lavelAdjList.setBounds(20, 420, 130, 25);
		JTextField textAdjList = new JTextField();
		textAdjList.setBounds(150, 420, 300, 25);
		JLabel labelAdjMessage = new JLabel("<-----Please Enter Comma seperated");
		labelAdjMessage.setForeground(Color.RED);
		labelAdjMessage.setBounds(450, 420, 300, 25);
		JButton btnAddAll = new JButton("Add to Map");
		btnAddAll.setBounds(20, 450, 150, 25);
		JButton btnEdit = new JButton("Update incorrect entry by selecting row");
		btnEdit.setBounds(180, 450, 320, 25);
		JButton btnSave = new JButton("Save Map");
		btnSave.setBounds(510, 450, 150, 25);
		// JButton btnAddCountry = new JButton("Configure");
		// btnAddCountry.setBounds(150, 270, 200, 25);
		// for (Map.Entry<?, ?> entry : continentHashMap.entrySet())
		for (int counter = 0; counter < continentList.size(); counter++) {
			modelContinent.addRow(new Object[] { continentList.get(counter) });
		}
		tableContinent.setModel(modelContinent);
		// tableContinent.getColumnModel().getColumn(1).setCellRenderer(new
		// ButtonRenderer());

		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String strContinent = textContinent.getText().trim();
				String strCountry = textCountry.getText().trim();
				String strAdjList = textAdjList.getText();
				String strControlValue = textContinentControlValue.getText().trim();

				if ((strContinent.isEmpty()) || (strCountry.isEmpty()) || (strControlValue.isEmpty() || (strAdjList.isEmpty()))) {
					JOptionPane.showMessageDialog(null, "Oops!Please enter values", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else if (!(strContinent.matches("^[a-zA-Z]+$")) || !(strCountry.matches("^[a-zA-Z]+$"))) {
					JOptionPane.showMessageDialog(null, "Only Alphabets are allowed", "Error in Continent and Country",
							JOptionPane.ERROR_MESSAGE);
				}

				else if (!(strAdjList.matches("^[a-zA-Z,]*$"))) {
					JOptionPane.showMessageDialog(null, "Only Alphabets and commas are allowed",
							"Error in Adjacent List", JOptionPane.ERROR_MESSAGE);
				}

				else if (strAdjList.startsWith(",")) {
					JOptionPane.showMessageDialog(null, "Sorry! you cant start with comma", "Error in Adjacent List",
							JOptionPane.ERROR_MESSAGE);
				}

				else {
					StringJoiner joiner = new StringJoiner(",");
					joiner.add(strContinent).add(strCountry);
					String concatString = joiner.toString();
					List<String> listAdjCountryList = new ArrayList<String>();
					listAdjCountryList = new ArrayList<String>(Arrays.asList(strAdjList.split(",")));
					modelContinent.addRow(new Object[] { strContinent, strCountry, strAdjList });
					territoryHashMap.put(concatString, listAdjCountryList);
					textCountry.setText(null);
					textAdjList.setText(null);
					textContinentControlValue.setEnabled(false);
				}

			}
		});

		// Listen for changes in the textContinentControlValue
		textContinentControlValue.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				insertContinentControlValue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			public void insertContinentControlValue() {

				String strContinent = textContinent.getText();
				String strContinentControlValue = textContinentControlValue.getText();
				if (!(strContinentControlValue.matches("^[0-9]+$"))) {
					JOptionPane.showMessageDialog(null, "Only Numbers are  allowed", "Error in Control Value",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (!(textContinentControlValue.getText().isEmpty())) {
						int continetControlValue = Integer.parseInt(textContinentControlValue.getText());
						continentControlValueHashMap.put(strContinent, continetControlValue);
					}
				}
			}
		});

		// Listen for changes in the textContinent
		textContinent.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textContinentControlValue.setText(null);
				textContinentControlValue.setEnabled(true);
			}

			public void changedUpdate(DocumentEvent e) {
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
				textFileName = JOptionPane.showInputDialog("Please Enter Map name");

				try {

					fstream = new FileWriter("C:/Users/Khashyap/Documents/" + textFileName + ".txt");
					out = new BufferedWriter(fstream);
					out.write("[Map]");
					out.write(System.getProperty("line.separator"));
					out.write(System.getProperty("line.separator"));
					out.write("[Continents]");
					out.write(System.getProperty("line.separator"));
					for (Map.Entry<String, Integer> temp : continentControlValueHashMap.entrySet()) {

						continentListToPrint.add(temp.getKey());
						out.write(temp.getKey() + "=" + temp.getValue());
						out.write(System.getProperty("line.separator"));
					}
					System.out.println(continentListToPrint);
					System.out.println(continentListToPrint.size());
					out.write(System.getProperty("line.separator"));
					out.write("[Territories]");
					out.write(System.getProperty("line.separator"));
					for (String obj : continentListToPrint) {
						for (Map.Entry<String, List<String>> iterate : territoryHashMap.entrySet()) {
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, textFileName + " \t is saved");
				jframeContinent.setVisible(false);
			}
		});

		tableContinent.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int i = tableContinent.getSelectedRow();
				String strtextContinentToChange = modelContinent.getValueAt(i, 0).toString();
				String strtextCountryToChange = modelContinent.getValueAt(i, 1).toString();
				String strtextAdjListToChange = modelContinent.getValueAt(i, 2).toString();
				textContinent.setText(strtextContinentToChange);
				textCountry.setText(strtextCountryToChange);
				textAdjList.setText(strtextAdjListToChange);
				textContinentControlValue
						.setText(continentControlValueHashMap.get(strtextContinentToChange).toString());
				StringJoiner joinerToDelete = new StringJoiner(",");
				joinerToDelete.add(strtextContinentToChange).add(strtextCountryToChange);
				String concatStringToDelete = joinerToDelete.toString();
				territoryHashMap.remove(concatStringToDelete);
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableContinent.getSelectedRow();
				if (row > -1) {
					String strContinentToUpdate = textContinent.getText().toString();
					String strCountryToUpdate = textCountry.getText().toString();
					String strAdjListToUpdate = textAdjList.getText().toString();
					List<String> listAdjCountryToUpdate = new ArrayList<String>();
					listAdjCountryToUpdate = new ArrayList<String>(Arrays.asList(strAdjListToUpdate.split(",")));
					StringJoiner joinerToUpdate = new StringJoiner(",");
					joinerToUpdate.add(strContinentToUpdate).add(strCountryToUpdate);
					String concatStringToUpdate = joinerToUpdate.toString();
					modelContinent.setValueAt(strContinentToUpdate, row, 0);
					modelContinent.setValueAt(strCountryToUpdate, row, 1);
					modelContinent.setValueAt(strAdjListToUpdate, row, 2);
					territoryHashMap.put(concatStringToUpdate, listAdjCountryToUpdate);
					textCountry.setText(null);
					textAdjList.setText(null);
					textContinentControlValue.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to Update", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 380);
		jframeContinent = new JInternalFrame("Add Continent");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(labelContinent);
		jframeContinent.add(labelCountry);
		jframeContinent.add(labelContinentControlValue);
		jframeContinent.add(lavelAdjList);
		jframeContinent.add(labelAdjMessage);
		jframeContinent.add(textContinent);
		jframeContinent.add(textCountry);
		jframeContinent.add(textContinentControlValue);
		jframeContinent.add(textAdjList);
		jframeContinent.add(btnAddAll);
		jframeContinent.add(btnSave);
		jframeContinent.add(btnEdit);
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);
		desktopCreateMap = desktop;
	}

	// public static void addCountry(List<String> continentList)
	// public static void addCountry(String strContinent) {
	//
	// JInternalFrame frameCountry = new JInternalFrame();
	// JTable tableCountry = new JTable();
	// DefaultTableModel modelContinent = new DefaultTableModel(
	// new Object[] { "Continent", "Country", "Adjacent Countries" }, 0);
	// tableCountry.setModel(modelContinent);
	// // TableColumn columnContinent = tableCountry.getColumnModel().getColumn(0);
	// // JComboBox<String> comboContinent = new JComboBox<String>();
	// // comboContinent.setModel(new
	// DefaultComboBoxModel(continentList.toArray()));
	// // columnContinent.setCellEditor(new DefaultCellEditor(comboContinent));
	// JLabel textCountryLabel = new JLabel("Country:");
	// textCountryLabel.setBounds(20, 220, 50, 25);
	// JLabel textAdjLabel = new JLabel("Ajacent List:");
	// textAdjLabel.setBounds(20, 250, 70, 25);
	// JTextField textCountry = new JTextField();
	// textCountry.setBounds(80, 220, 80, 25);
	// JTextField textAdjacent = new JTextField();
	// textAdjacent.setBounds(120, 250, 300, 25);
	// JButton btnAdd = new JButton("Add Country and Adjacent List");
	// btnAdd.setBounds(130, 280, 300, 25);
	// JButton btnComplete = new JButton("Complete");
	// btnComplete.setBounds(130, 320, 100, 25);
	//
	// btnAdd.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	//
	// TxtCountry = textCountry.getText();
	// TxtAdjCountry = textAdjacent.getText();
	// StringJoiner joiner = new StringJoiner(",");
	// joiner.add(strContinent).add(TxtCountry);
	// String concatString = joiner.toString();
	// List<String> adjCountryList = new ArrayList<String>();
	// adjCountryList = new
	// ArrayList<String>(Arrays.asList(TxtAdjCountry.split(",")));
	// modelContinent.addRow(new Object[] { strContinent, TxtCountry, adjCountryList
	// });
	// territoryHashMap.put(concatString, adjCountryList);
	// textCountry.setText(null);
	// textAdjacent.setText(null);
	// }
	// });
	//
	// // comboContinent.addActionListener(new ActionListener() {
	// //
	// // @Override
	// // public void actionPerformed(ActionEvent e) {
	// // // TODO Auto-generated method stub
	// // // System.out.println(e.getItem() + " " + e.getStateChange() );
	// // //System.out.println(e.getActionCommand());
	// // String newSelection = (String) comboContinent.getSelectedItem();
	// // StringJoiner joiner = new StringJoiner(",");
	// // joiner.add(newSelection).add(TxtCountry);
	// // String concatString = joiner.toString();
	// // territoryHashMap.put(concatString, adjCountryList);
	// // System.out.println(territoryHashMap);
	// // }
	// // });
	//
	// JScrollPane panelCountry = new JScrollPane(tableCountry);
	// panelCountry.setBounds(0, 0, 880, 220);
	// frameCountry.setLayout(null);
	// frameCountry.add(panelCountry);
	// frameCountry.add(textCountryLabel);
	// frameCountry.add(textCountry);
	// frameCountry.add(textAdjLabel);
	// frameCountry.add(textAdjacent);
	// frameCountry.add(btnAdd);
	// frameCountry.add(btnComplete);
	// frameCountry.setSize(1000, 400);
	// frameCountry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	// frameCountry.setVisible(true);
	// desktopCreateMap.add(frameCountry);
	//
	// btnComplete.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	//
	// try {
	// frameCountry.setClosed(true);
	// } catch (PropertyVetoException e1) {
	// e1.printStackTrace();
	// }
	//
	// // jframeContinent.setVisible(true);
	// }
	// });
	//
	// }
}
