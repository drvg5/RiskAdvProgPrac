package com.risk.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.risk.model.ReinforcementPhase;
import com.risk.ui.LoadGame;
import com.risk.ui.MainMenu;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test class containing a Test Suite comprising of test cases for unit testing
 * the application
 * 
 * 
 * 
 * @author drvg5
 * @see TestCase
 *
 */
public class RiskTest extends TestCase {

	private MainMenu menuObj;
	private LoadGame loadObj;
	JFrame dummyJframe;

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(RiskTest.class);
	}

	protected void setUp() {
		menuObj = new MainMenu();
		loadObj = new LoadGame();
	}

	public void testValidityOfMapFile() {
		// setting up a dummy JFrame
		dummyJframe = new JFrame("Upload");
		dummyJframe.setLayout(null);
		dummyJframe.setSize(300, 300);
		dummyJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton buttonSelectFile = new JButton("Upload");
		buttonSelectFile.setName("Upload");
		buttonSelectFile.setBounds(100, 100, 100, 30);
		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(100, 150, 100, 30);
		dummyJframe.add(buttonSelectFile);
		dummyJframe.add(buttonCloseUpload);

		// testing instance created
		assertNotNull(menuObj);

		// event handling for the button
		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// call made to check file validity
				assertTrue(loadObj.uploadProcess());
			}
		});
		dummyJframe.setVisible(true);

	}
	
	public void testCalcReinforcementArmies()
	{
		int armies = ReinforcementPhase.calculateReinforcement("1");
		assertEquals(armies,ReinforcementPhase.calculateReinforcement("1"));
	}

}