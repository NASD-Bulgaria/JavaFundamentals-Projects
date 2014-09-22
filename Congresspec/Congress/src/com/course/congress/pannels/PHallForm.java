package com.course.congress.pannels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import com.course.congress.jtablemodels.HallTableModel;
import com.course.congress.objects.Hall;

public class PHallForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel name;
	private JLabel capacity;
	private JLabel floor;

	private JTextField hallName;
	private JFormattedTextField hallCapacity;
	private JComboBox hallFloor;

	private JButton addHall;
	private JButton editHall;
	private JButton removeHall;

	private JTable hallsTable;
	private HallTableModel hallTableModel;
	private JScrollPane scrollPane;

	private static final Integer[] floors = { 1, 2, 3, 4, 5 };

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
		hallCapacity = new JFormattedTextField();
		hallCapacity.setValue(new Integer(150));
		hallCapacity.setColumns(10);
		hallCapacity.setBounds(260, 10, 80, 20);
		add(hallCapacity);

		floor = new JLabel("Floor");
		floor.setBounds(350, 10, 50, 20);
		add(floor);
		hallFloor = new JComboBox(floors);
		hallFloor.setMaximumRowCount(3);
		hallFloor.setBounds(400, 10, 80, 20);
		add(hallFloor);

		// Creating editable jTable for halls with dummy data
		Hall row1 = new Hall(null, "Hall 1", 2, 1);
		Hall row2 = new Hall(null, "Hall 2", 5, 1);
		// build the list
		List<Hall> hallList = new ArrayList<Hall>();
		hallList.add(row1);
		hallList.add(row2);

		// create the model
		hallTableModel = new HallTableModel(hallList);
		// create the table
		hallsTable = new JTable(hallTableModel);
		setTableAlignment(hallsTable);
		hallsTable.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(hallsTable);
		add(scrollPane).setBounds(10, 60, 470, 500);

		addHall = new JButton("Add Hall");
		addHall.setBounds(500, 10, 110, 40);
		add(addHall);

		/*editHall = new JButton("Edit Hall");
		editHall.setBounds(580, 200, 110, 40);
		add(editHall);*/

		removeHall = new JButton("Remove Hall");
		removeHall.setBounds(500, 520, 110, 40);
		add(removeHall);
		
		// events and actions
		addHall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hallNameText = hallName.getText();
				int capacityText = ((Number)hallCapacity.getValue()).intValue();
				int floorText = (Integer) hallFloor.getSelectedItem();
				//validate hall fields
				if (hallNameText == "" || hallNameText.isEmpty() || hallNameText == null) {
					JOptionPane.showMessageDialog(null, "Please enter hall name.");
				} else if (capacityText <= 0 ) {
					JOptionPane.showMessageDialog(null, "Hall capacity must be bigger than 0.");
				} else {
					Hall hall = new Hall(null, hallNameText, capacityText, floorText);
					hallTableModel.addRow(hall);
					//clear fields
					hallName.setText("");
					hallCapacity.setValue(new Integer(150));
					hallFloor.setSelectedIndex(0);
				}
			}
		});
		
		removeHall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (hallsTable.getSelectedRow() != -1) {
					 hallTableModel.removeRow(hallsTable.getSelectedRow());
			        } else {
			        	JOptionPane.showMessageDialog(null, "Please select hall from the table first.");
			        }
			}
		});
	}

	private void setTableAlignment(JTable hallsTable2) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		hallsTable.setDefaultRenderer(Integer.class, centerRenderer);

	}
}
