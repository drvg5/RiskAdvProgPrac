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

import com.risk.model.EditMapModel;
import com.risk.model.ParseMapFileModel;

public class EditMapUI {

	static JInternalFrame jframeContinent = new JInternalFrame();
	static JInternalFrame jframeUpload;
	static DefaultTableModel modelToEdit = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List", "Control Value" }, 0);
	static HashMap<String, List<String>> continentHashMapToEdit = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMapToEdit = new HashMap<String, Integer>();
	static List<String> continentListToEdit = new ArrayList<String>();
	static String UploadFileName;
	static List<String> checkDuplicates = new ArrayList<String>();
	static JDesktopPane desktopUploadForEdit;
	static EditMapModel editMapModel = new EditMapModel();

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
		desktopUploadForEdit = desktop;
 		jframeUpload = new JInternalFrame("Upload");
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
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(10, 20, 10, 10);
				int returnValue = fileChooser.showOpenDialog(null);
				editMapModel.getMapFile(returnValue, fileChooser.getSelectedFile());
				UploadFileName = fileChooser.getSelectedFile().getName();
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

	public void editMap(final HashMap<String, List<String>> continentHashMap,
			HashMap<String, Integer> continentControlValueHashMap) {

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
		btnUpdate.setVisible(false);
		JButton btnDeleteContinent = new JButton("Delete Continent");
		btnDeleteContinent.setBounds(570, 270, 180, 25);
		JButton btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setBounds(570, 310, 180, 25);
		JButton btnDeleteAdj = new JButton("Delete Adjacency");
		btnDeleteAdj.setBounds(570, 350, 180, 25);
		JButton btnUpdateAll = new JButton("Click here to Update after all Edits");
		btnUpdateAll.setBounds(280, 430, 320, 25);

		reloadModel();

		for (Map.Entry<String, List<String>> hash : continentHashMapToEdit.entrySet()) {
			String stringKeyToCheck = hash.getKey();
			String[] strKeyArrayToCheck = stringKeyToCheck.split(",");
			listContinentToCheck.add(strKeyArrayToCheck[0]);
			listCountryToCheck.add(strKeyArrayToCheck[1]);
		}

		// Button to Add All values to existing hashMap

		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean checkCountry = false;
				String strContinentToAdd = textContinentToEdit.getText().trim().toLowerCase();
				String strCountryToAdd = textCountryToEdit.getText().trim().toLowerCase();
				String strAdjListToAdd = textAdjListToEdit.getText().toLowerCase();
				String strControlValueToAdd = textContinentControlValueToEdit.getText().trim();

				if ((strContinentToAdd.isEmpty()) || (strCountryToAdd.isEmpty()) || (strAdjListToAdd.isEmpty())
						|| (strControlValueToAdd.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Oops!Please enter values", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else if (!(strControlValueToAdd.matches("^[0-9]+$"))) {
					JOptionPane.showMessageDialog(null, "Only Numbers are  allowed", "Error in Control Value",
							JOptionPane.ERROR_MESSAGE);
				}

				else {

					String strContinentCapitalize = strContinentToAdd.substring(0, 1).toUpperCase()
							+ strContinentToAdd.substring(1);
					String strCountryCapitalize = strCountryToAdd.substring(0, 1).toUpperCase()
							+ strCountryToAdd.substring(1);
					List<String> listAdjCountryToAdd = new ArrayList<String>();
					List<String> listAdjCountrytoAddCapitalize = new ArrayList<String>();
					listAdjCountryToAdd = new ArrayList<String>(Arrays.asList(strAdjListToAdd.split(",")));
					for (String capital : listAdjCountryToAdd) {
						capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
						listAdjCountrytoAddCapitalize.add(capital);
					}
					int continetControlValue = Integer.parseInt(strControlValueToAdd);

					if (!(listCountryToCheck.isEmpty())) {
						for (String str : listCountryToCheck) {
							if (str.equals(strCountryCapitalize)) {
								checkCountry = true;
							}
						}
					}

					if (checkCountry) {
						JOptionPane.showMessageDialog(null,
								"Invalid Map!" + strCountryCapitalize + " already exists in Country List", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					else {

						continentControlValueHashMapToEdit.put(strContinentCapitalize, continetControlValue);

						// call to model
						continentHashMapToEdit = editMapModel.addDataEnteredForEditMap(strContinentCapitalize,
								strCountryCapitalize, continetControlValue, listAdjCountrytoAddCapitalize,
								listCountryToCheck, continentHashMapToEdit);
						reloadModel();

						textCountryToEdit.setText(null);
						textAdjListToEdit.setText(null);
						textContinentControlValueToEdit.setText(null);
						textContinentToEdit.setText(null);
					}
				}
			}
		});

		// Button to Delete Adjacency.

		btnDeleteAdj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableContinent.getSelectedRow();
				if (row > -1) {
					String ToDelete = new String();
					List<String> listAdjCountryToRemove = new ArrayList<String>();
					List<String> listAdjCountryToRemoveCapitalize = new ArrayList<String>();
					ToDelete = JOptionPane.showInputDialog("Please specify links to delete");
					listAdjCountryToRemove = new ArrayList<String>(Arrays.asList(ToDelete.split(",")));
					for (String capital : listAdjCountryToRemove) {
						capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
						listAdjCountryToRemoveCapitalize.add(capital);
					}
					String conti = modelToEdit.getValueAt(row, 0).toString();
					String countr = modelToEdit.getValueAt(row, 1).toString();
					continentHashMapToEdit = editMapModel.deleteAdjacency(conti, countr,
							listAdjCountryToRemoveCapitalize, continentHashMapToEdit);
					reloadModel();
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to Delete", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Button to Delete Continent. Selects value in row and deletes continent
		// completely

		btnDeleteContinent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableContinent.getSelectedRow();
				String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
				if (row > -1) {
					continentHashMapToEdit = editMapModel.deleteContinent(strtextContinentToDelete,
							continentHashMapToEdit);
					continentControlValueHashMapToEdit.remove(strtextContinentToDelete);
					textContinentToEdit.setText(null);
					textCountryToEdit.setText(null);
					textAdjListToEdit.setText(null);
					textContinentControlValueToEdit.setText(null);
					reloadModel();
				} else {

					JOptionPane.showMessageDialog(null, "Please select a row to Delete", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// Button to Delete Country. Selects value in row and deletes Country and its
		// adjacency completely

		btnDeleteCountry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableContinent.getSelectedRow();
				if (row > -1) {
					String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
					String strtextCountryToDelete = modelToEdit.getValueAt(row, 1).toString();
					continentHashMapToEdit = editMapModel.deleteCountry(strtextContinentToDelete,
							strtextCountryToDelete, continentHashMapToEdit);
					reloadModel();
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to Delete", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Button to save all edited changes to same Map file

		btnUpdateAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jframeContinent.setVisible(false);
				new SaveMapUponEditUI().updateAndSave(continentHashMapToEdit, continentControlValueHashMapToEdit, desktopUploadForEdit,
						UploadFileName);
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
		jframeContinent.add(btnUpdateAll);
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktopUploadForEdit.add(jframeContinent);
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
					modelToEdit.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces,
							continentControlValueHashMapToEdit.get(strKeyArrayToEdit[0]) });
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

	public void closeUploadForEdit() {
		try {
			jframeUpload.setClosed(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}

	public void showUploadForEdit() {
		jframeUpload.setVisible(true);

	}

}
