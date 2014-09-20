package com.course.congress.pannels;

import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PHallForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel name;
	private JLabel capacity;
	private JLabel floor;

	private JTextField hallName;
	private JTextField hallCapacity;
	private JComboBox hallFloor;

	private JButton addHall;
	private JButton editHall;
	private JButton removeHall;

	private JTable halls;

	private static final Integer[] floors = { 1, 2, 3, 4, 5 };
	private static final String[] columnNames = { "Name", "Capacity", "Floor" };

	public PHallForm() {
		setLayout(null);
		setBackground(Color.GRAY);
		name = new JLabel("Name");
		name.setBounds(10, 10, 40, 20);
		add(name);
		hallName = new JTextField();
		hallName.setBounds(50, 10, 130, 20);
		hallName.setVisible(true);
		 add(hallName);

		capacity = new JLabel("Capacity");
		capacity.setBounds(200, 10, 50, 20);
		 add(capacity);
		hallCapacity = new JTextField();
		hallCapacity.setBounds(260, 10, 80, 20);
		hallCapacity.setText(new Random().nextInt() + "");
		 add(hallCapacity);

		floor = new JLabel("Floor");
		floor.setBounds(350, 10, 50, 20);
		 add(floor);
		hallFloor = new JComboBox(floors);
		hallFloor.setMaximumRowCount(3);
		hallFloor.setBounds(400, 10, 80, 20);
		 add(hallFloor);

		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 1); // should
																				// rewrite
																				// the
																				// whole
																				// table
																				// logic
		halls = new JTable(tableModel);
		halls.setBounds(10, 60, 500, 500);
		 add(halls);

		addHall = new JButton("Add Hall");
		addHall.setBounds(580, 100, 110, 40);
		 add(addHall);

		editHall = new JButton("Edit Hall");
		editHall.setBounds(580, 200, 110, 40);
		 add(editHall);

		removeHall = new JButton("Remove Hall");
		removeHall.setBounds(580, 300, 110, 40);
		 add(removeHall);
	}
}
