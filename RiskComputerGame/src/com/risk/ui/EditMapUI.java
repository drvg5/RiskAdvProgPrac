package com.risk.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FilenameUtils;

public class EditMapUI {


	static JInternalFrame jframeContinent = new JInternalFrame();
	static DefaultTableModel modelToEdit = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List", "Control Value" }, 0);
	static HashMap<String, List<String>> continentHashMapToEdit = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMapToEdit = new HashMap<String, Integer>();
	static List<String> continentListToEdit = new ArrayList<String>();
	static String UploadFileName;
	static List<String> checkDuplicates = new ArrayList<String>();

	/**
	 * <p>
	 * This method is used to fetch text or map file from local folder to Edit User
	 * can edit Continents, Countries and adjacency links between Countries. File is
	 * already loaded in Default local folder location User can select from existing
	 * files
	 * 
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 * 
	 */

	public static void fetchMap(final JDesktopPane desktop) {
		final JInternalFrame jframeUpload = new JInternalFrame("Upload");
		jframeUpload.setLayout(null);
		jframeUpload.setSize(300, 300);
		jframeUpload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton buttonSelectFile = new JButton("Upload");
		buttonSelectFile.setBounds(100, 100, 100, 30);
		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(100, 150, 100, 30);
		jframeUpload.add(buttonSelectFile);
		jframeUpload.add(buttonCloseUpload);
		jframeUpload.setVisible(true);
		desktop.add(jframeUpload);

		// Button to upload file and populate in Default Model.
		// This will call Edit Map method after fetching data

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				boolean checkDuplicate;
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(10, 20, 10, 10);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {}
			}
		});

		buttonCloseUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeUpload.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * <p>
	 * This method is used to Edit Map attributes like Continents, Countries and
	 * adjacency links After update, changes is saved to same text files which is
	 * loaded initially
	 * 
	 * @param continentHashMap
	 *            Contains all attribute values
	 * 
	 * @param continentControlValueHashMap
	 *            Contains Continent and its control value.
	 * 
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 * 
	 */

	public static void editMap(final HashMap<String, List<String>> continentHashMap,
			HashMap<String, Integer> continentControlValueHashMap, JDesktopPane desktop) {

		continentHashMapToEdit = continentHashMap;
		continentControlValueHashMapToEdit = continentControlValueHashMap;
		List<String> listContinentToCheck = new ArrayList<String>();
		final List<String> listCountryToCheck = new ArrayList<String>();
		final JTable tableContinent = new JTable();
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
		JButton btnAddAll = new JButton("Add");
		btnAddAll.setBounds(100, 400, 100, 25);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(200, 400, 100, 25);
		JButton btnDeleteContinent = new JButton("Delete Continent");
		btnDeleteContinent.setBounds(570, 270, 180, 25);
		JButton btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setBounds(570, 310, 180, 25);
		JButton btnDeleteAdj = new JButton("Delete Adjacency");
		btnDeleteAdj.setBounds(570, 350, 180, 25);
		JButton btnSaveEditedMap = new JButton("Save");
		btnSaveEditedMap.setBounds(570, 390, 180, 25);


		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean checkCountry = false;
				String strContinentToAdd = textContinentToEdit.getText().trim().toLowerCase();
				String strCountryToAdd = textCountryToEdit.getText().trim().toLowerCase();
				String strAdjListToAdd = textAdjListToEdit.getText().toLowerCase();
				String strControlValueToAdd = textContinentControlValueToEdit.getText().trim();
				
			}
		});

		// Button to Delete Continent. Selects value in row and deletes continent
		// completely

		btnDeleteAdj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnDeleteContinent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableContinent.getSelectedRow();
				String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
				List<String> ToDelete = new ArrayList<String>();
				
			}
		});

		// Button to Delete Country. Selects value in row and deletes Country and its
		// adjacency completely

		btnDeleteCountry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableContinent.getSelectedRow();
				String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
				String strtextCountryToDelete = modelToEdit.getValueAt(row, 1).toString();
				String Key = strtextContinentToDelete + "," + strtextCountryToDelete;
			}
		});

		// Button to save all edited changes to same Map file

		btnSaveEditedMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				FileWriter fstream;
				BufferedWriter out;
				List<String> continentListToPrint = new ArrayList<String>();
				String textFileName;
				textFileName = UploadFileName;
				String textFileNameToShow = textFileName.split("\\.", 2)[0];
				try {

					fstream = new FileWriter("C:/Users/Khashyap/Documents/Maps/" + textFileName);
					out = new BufferedWriter(fstream);
					out.write("[Map]");
					out.write(System.getProperty("line.separator"));
					out.write(System.getProperty("line.separator"));
					out.write("[Continents]");
					out.write(System.getProperty("line.separator"));
					for (Map.Entry<String, Integer> temp : continentControlValueHashMapToEdit.entrySet()) {

						continentListToPrint.add(temp.getKey());
						out.write(temp.getKey() + "=" + temp.getValue());
						out.write(System.getProperty("line.separator"));
					}
					out.write(System.getProperty("line.separator"));
					out.write("[Territories]");
					out.write(System.getProperty("line.separator"));
					for (String obj : continentListToPrint) {
						for (Map.Entry<String, List<String>> iterate : continentHashMapToEdit.entrySet()) {
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
				jframeContinent.setVisible(false);
			}
		});

		tableContinent.setModel(modelToEdit);
		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 250);
		jframeContinent = new JInternalFrame("Add Continent");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(labelContinentToEdit);
		jframeContinent.add(labelCountryToEdit);
		jframeContinent.add(labelContinentControlValueToEdit);
		jframeContinent.add(labelAdjListToEdit);
		jframeContinent.add(labelAdjMessageToEdit);
		jframeContinent.add(textContinentToEdit);
		jframeContinent.add(textCountryToEdit);
		jframeContinent.add(textContinentControlValueToEdit);
		jframeContinent.add(textAdjListToEdit);
		jframeContinent.add(btnAddAll);
		jframeContinent.add(btnUpdate);
		jframeContinent.add(btnDeleteContinent);
		jframeContinent.add(btnDeleteCountry);
		jframeContinent.add(btnDeleteAdj);
		jframeContinent.add(btnSaveEditedMap);
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);
	}

	/**
	 * <p>
	 * This method is used to reload Data model after changes in HashMap value
	 * 
	 */

	public static void reloadModel() {
		modelToEdit.setRowCount(0);
		List<String> tempList = new ArrayList<String>();
		for (Map.Entry<String, Integer> temp : continentControlValueHashMapToEdit.entrySet()) {

			tempList.add(temp.getKey());

		}

		for (String obj : tempList) {

			for (Map.Entry<String, List<String>> entry : continentHashMapToEdit.entrySet()) {
				String strKey = entry.getKey();
				String[] strKeyArrayToEdit = strKey.split(",");
				if (obj.equals(strKeyArrayToEdit[0])) {
					String printWithoutBraces = entry.getValue().toString().replaceAll("(^\\[|\\s|\\]$)", "");
					modelToEdit.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces,continentControlValueHashMapToEdit.get(strKeyArrayToEdit[0]) });
				}

			}
		}
	}

	public static boolean findDuplicates(List<String> listContainingDuplicates) {
		final Set<String> set = new HashSet<String>();
		boolean sendToValidate = false;
		for (String str : listContainingDuplicates) {
			if (!set.add(str)) {
				sendToValidate = true;
			}
		}
		return sendToValidate;
	}


}
