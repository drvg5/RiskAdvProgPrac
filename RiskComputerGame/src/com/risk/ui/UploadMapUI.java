package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import com.risk.model.ParseMapFileModel;

public class UploadMapUI {
	static JInternalFrame jframeUpload;

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

	public void closeUpload() {
		try {
			jframeUpload.setClosed(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}

	public void showErrorMessage(int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Invalid Map!File extension is wrong", "Upload Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (i == 2) {
			JOptionPane.showMessageDialog(null, "Invalid Map! File is missing Map or Continent or Territory section",
					"Upload Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Map! File is missing Map or Continent or Territory section",
					"Upload Error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			jframeUpload.setClosed(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}

}
