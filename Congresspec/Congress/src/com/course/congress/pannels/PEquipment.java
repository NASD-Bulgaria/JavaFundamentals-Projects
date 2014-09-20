package com.course.congress.pannels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.course.congress.controlers.PPanelControler;


public class PEquipment extends JPanel{

	private static final long serialVersionUID = 1L;

	private JLabel michrophoneLabel;
	private JLabel soundSystemLabel;
	private JLabel mobileScreenLabel;
	private JLabel additionalLightingLabel;
	private JLabel computerLabel;
	private JLabel projectorLabel;
	
	private JTextField michrophones;
	private JCheckBox soundSystem;
	private JTextField mobileScreens;
	private JCheckBox additionalLighting;
	private JTextField computer;
	private JTextField projector;
	
	private JButton request;
	private JButton cancel;
	
	
	PEquipment()
	{
		setBackground(Color.GRAY);
		setSize(200,300);
		setLayout(null);
		
		
		
		michrophoneLabel = new JLabel("Microphones");
		michrophoneLabel.setBounds(10, 10, 90, 20);
		add(michrophoneLabel);
		michrophoneLabel.setForeground(Color.WHITE);
		
		michrophones = new JTextField();
		michrophones.setBounds(100,10,150,20);
		add(michrophones);
		
		soundSystemLabel = new JLabel("Sound System");
		soundSystemLabel.setBounds(10, 40, 150, 20);
		add(soundSystemLabel);
		soundSystemLabel.setForeground(Color.WHITE);
		
		soundSystem = new JCheckBox();
		soundSystem.setBounds(100, 43, 20, 15);
		add(soundSystem);
		soundSystem.setBackground(Color.GRAY);
		
		mobileScreenLabel = new JLabel("Mobile Screens");
		mobileScreenLabel.setBounds(10, 70, 150, 20);
		add(mobileScreenLabel);
		mobileScreenLabel.setForeground(Color.WHITE);
		
		mobileScreens = new JTextField();
		mobileScreens.setBounds(100, 70, 150, 20);
		add(mobileScreens);
		
		additionalLightingLabel = new JLabel("Additional light");
		additionalLightingLabel.setBounds(10, 100, 90, 20);
		add(additionalLightingLabel);
		additionalLightingLabel.setForeground(Color.WHITE);
		
		additionalLighting = new JCheckBox();
		additionalLighting.setBounds(100, 103, 20, 15);
		add(additionalLighting);
		additionalLighting.setBackground(Color.GRAY);
		
		computerLabel = new JLabel("Computers");
		computerLabel.setBounds(10, 130, 90, 20);
		add(computerLabel);
		computerLabel.setForeground(Color.WHITE);
		
		computer = new JTextField();
		computer.setBounds(100, 130, 150, 20);
		add(computer);
		
		projectorLabel = new JLabel("Projectors");
		projectorLabel.setBounds(10, 160, 90, 20);
		add(projectorLabel);
		projectorLabel.setForeground(Color.WHITE);
		
		projector = new JTextField();
		projector.setBounds(100, 160, 150, 20);
		add(projector);
		
		request = new JButton("Request");
		request.setBounds(10, 190, 100, 25);
		add(request);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(150, 190, 100, 25);
		add(cancel);
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PPanelControler.showPrevPanel();
			}
		});
		
	}
	
}
