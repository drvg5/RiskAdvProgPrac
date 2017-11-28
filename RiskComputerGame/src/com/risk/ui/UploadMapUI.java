package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.risk.model.ParseMapFileModel;

/**
 * <h1>Upload MapUI</h1>
 * <p>
 * <b>This class consists method to receive file input from user and send for parsing</b>
 * <p>
 * 
 * @author Khashyap
 * @version 1.0
 */


public class UploadMapUI {
	static JInternalFrame jframeUpload;
	
	
	/**
	 * <p>
	 * This method is used to load map file from local folder to play game
	 * 
	 * @author Khashyap 
	 * @param desktop
	 *            To bind the InternalFrame with Main window frame
	 * 
	 */

	public static void LoadMap(JDesktopPane desktop) {

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

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(10, 20, 30, 100);
				int returnValue = fileChooser.showOpenDialog(null);
				new ParseMapFileModel().getMapFile(returnValue, fileChooser.getSelectedFile());
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
	 * This method is used to close upload button
	 * 
	 */
	
	public void closeUpload() {
		try {
			jframeUpload.setClosed(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}
	
	/**
	 
	 * This methods displays all error messages to User
	 * 
	 * @author Dhruv
	 * @param i number for particular error message 
	 */

	public void showErrorMessageForUpload(int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Invalid Map!File extension is wrong", "Upload Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (i == 2) {
			JOptionPane.showMessageDialog(null, "Invalid Map! File is missing Map or Continent or Territory section",
					"Upload Error", JOptionPane.ERROR_MESSAGE);
		} 
		
		else if (i == 3)
		{
			JOptionPane.showMessageDialog(null,
					"Invalid Map! Not a connected graph between Continents",
					"Adjacency Error", JOptionPane.ERROR_MESSAGE);
		}
		
		else if (i == 4)
		{
			JOptionPane.showMessageDialog(null,
					"Invalid Map! Not a connected graph between Territories in Continent",
					"Adjacency Error", JOptionPane.ERROR_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Invalid Map! Duplicate Countries",
					"Load Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
