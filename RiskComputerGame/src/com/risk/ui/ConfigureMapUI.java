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

	/** Frame to display data to user*/
	static JInternalFrame jframeContinent = new JInternalFrame();
	
	/**Object creation for ConfigureMapModel class */
	ConfigureMapModel configureMapModel;
	
	/**
	 * This method consists fetching data from user input and send data to
	 * the model.
	 *
	 * @author Khashyap
	 * @author drvg5 - modified method to implement Modified MVC architecture
	 * @param desktop To bind the InternalFrame with Main window frame
	 */

	public void createContinentandCountry(final JDesktopPane desktop) {

		final JTable tableContinent = new JTable();
		final DefaultTableModel modelContinent = new DefaultTableModel(
				new Object[] { "Continent List", "Country", "Continent Control Value" }, 0);
		JLabel labelContinent = new JLabel("Continent");
		labelContinent.setBounds(20, 390, 60, 25);
		final JTextField textContinent = new JTextField();
		textContinent.setBounds(150, 390, 100, 25);
		JLabel labelCountry = new JLabel("Country");
		labelCountry.setBounds(280, 390, 60, 25);
		final JTextField textCountry = new JTextField();
		textCountry.setBounds(350, 390, 100, 25);
		JLabel labelContinentControlValue = new JLabel(
				"<html>Control Value(<font color='blue'>Alter while changing Continent</font>)</html>");
		labelContinentControlValue.setBounds(460, 390, 380, 25);
		final JTextField textContinentControlValue = new JTextField();
		textContinentControlValue.setBounds(730, 390, 50, 25);
		JButton btnAddAll = new JButton("Add to Map");
		btnAddAll.setBounds(20, 450, 150, 25);
		final JButton btnConfigureAdj = new JButton("Add Adjacency after Configuring");
		btnConfigureAdj.setBounds(180, 450, 300, 25);
		btnConfigureAdj.setEnabled(false);;
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
				if(!(dataReturned[1]==null))
				{
					modelContinent.addRow(new Object[] { dataReturned[0], dataReturned[1], dataReturned[2] });
					textCountry.setText(null);
					textContinentControlValue.setEnabled(false);	
					btnConfigureAdj.setEnabled(true);;

				}
				

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
					configureMapModel.inputForAdjacency(desktop);

				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
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

	
	/**
	 * This methods displays all error messages to User.
	 *
	 * @author Dhruv
	 * @param i  number for particular error message
	 */
	
	public void showErrorMessageForSaving(int i) {
		if (i==1) {
			JOptionPane.showMessageDialog(null, "Oops!Please enter values", "Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (i==2) {
			JOptionPane.showMessageDialog(null, "Only Alphabets are allowed", "Error in Continent and Country",
					JOptionPane.ERROR_MESSAGE);
		}

		else if (i==3) {
			JOptionPane.showMessageDialog(null, "Invalid Map! Country already exists in Country List",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (i==4) {

			JOptionPane.showMessageDialog(null, "Invalid Map! Continent already exists in Country List",
					"Error", JOptionPane.ERROR_MESSAGE);

		}

		else if (i==5) {
			JOptionPane.showMessageDialog(null, "Invalid Map! Country already exists in Continent List",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (i==6) {
			JOptionPane.showMessageDialog(null, "Invalid Map! Continent and Country name cant be same", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		else if (i==7) {
			JOptionPane.showMessageDialog(null, "Only Numbers are  allowed", "Error in Control Value",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
