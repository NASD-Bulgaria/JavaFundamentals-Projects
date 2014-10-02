package com.course.congress.main;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.course.congress.controlers.PPanelControler;

public class CongressCenterMain {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CongressCenterForm mainPanel = PPanelControler.getMainPanel();
				mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainPanel.setSize(1200, 700);
				mainPanel.setBackground(Color.GRAY);
				mainPanel.setVisible(true);
			}
		});
	}

}
