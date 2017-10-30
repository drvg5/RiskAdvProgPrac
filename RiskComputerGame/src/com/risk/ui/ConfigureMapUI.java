package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
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

import com.risk.model.ConfigureMapModel;

/**
 * <h1>Controller Class for Creating Map</h1>
 * 
 * This class consists methods to Configure the User Interface and send data to
 * the model.
 * 
 * @author Khashyap
 * @author drvg5 - modified class to implement Modified MVC architecture
 * 
 */
public class ConfigureMapUI {

	static JInternalFrame jframeContinent = new JInternalFrame();
	ConfigureMapModel configureMapModel;

	public void createContinentandCountry(JDesktopPane desktop) {

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
		btnEdit.setBounds(490, 450, 270, 25);

		tableContinent.setModel(modelContinent);

		// Button to add Continent and Country. All Validations are handled
		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				configureMapModel = new ConfigureMapModel();
				String[] data = { textContinent.getText().toLowerCase().trim(),
						textCountry.getText().toLowerCase().trim(),
						textContinentControlValue.getText().toLowerCase().trim() };

				String[] dataReturned = configureMapModel.addContinentsAndCountries(data);
				modelContinent.addRow(new Object[] { dataReturned[0], dataReturned[1], dataReturned[2] });
				textCountry.setText(null);
				textContinentControlValue.setEnabled(false);

			}
		});

		// Listen for changes in the Continent value and enables ControlContinentValue
		// Text Box
		textContinent.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				textContinentControlValue.setText(null);
				textContinentControlValue.setEnabled(true);
			}

			public void insertUpdate(DocumentEvent e) {
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
				String[] data = { concatStringToDelete, strtextContinentToChange, strtextCountryToChange,
						strtextContinentToChangeCapitalize, strtextCountryToChangeCapitalize,
						strtextContinentToChangeCapitalize };
				// call to model method
				configureMapModel.updateOnClick(data);

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
					StringJoiner joinerToUpdateHashMap = new StringJoiner(",");
					joinerToUpdateHashMap.add(strContinentToUpdateCapitalize).add(strCountryToUpdateCapitalize);
					String concatString = joinerToUpdateHashMap.toString();
					String[] data = { concatString, strContinentToUpdateCapitalize, strContinentControlValueToUpdate,
							strContinentToUpdate, strCountryToUpdate, strContinentToUpdateCapitalize,
							strCountryToUpdateCapitalize };
					// call to model method
					configureMapModel.updateOnClickOfButton(data);

					textCountry.setText(null);
					textContinentControlValue.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to Update", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// Button to call save to file method in Save Map class

		btnConfigureAdj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeContinent.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				configureMapModel.inputForAdjacency(desktop);
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

}
