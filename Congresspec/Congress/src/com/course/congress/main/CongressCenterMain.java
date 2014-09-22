package com.course.congress.main;

import java.awt.Color;

import javax.swing.JFrame;
import com.course.congress.controlers.PPanelControler;

public class CongressCenterMain {

	public static void main(String[] args) {
		CongressCenterForm mainPanel = PPanelControler.getMainPanel();
	    mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainPanel.setSize(1200, 700);
	    mainPanel.setBackground(Color.GRAY);
	    mainPanel.setVisible(true);
	}

}
