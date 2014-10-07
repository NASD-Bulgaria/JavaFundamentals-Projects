package com.course.congress.main;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.course.congress.controlers.PPanelControler;
import com.course.congress.datastorage.DataStorage;

public class CongressCenterMain {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					DataStorage.initFromFile("save file.ser");
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error reading from file ! " + e);
				}
				CongressCenterForm mainPanel = PPanelControler.getMainPanel();
				mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainPanel.setSize(1200, 700);
				mainPanel.setBackground(Color.GRAY);
				mainPanel.setVisible(true);
			}
		});
	}

}
