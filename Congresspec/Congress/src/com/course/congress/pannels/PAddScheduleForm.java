package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.course.congress.objects.Hall;

public class PAddScheduleForm extends JPanel {


	private JLabel sourceLabel;
	private JLabel destLabel;

	private JList<Hall> sourceList;
	private JList<Hall> destList;

	private JButton addButton;
	private JButton removeButton;
	
	private JScrollPane sourceScrollPane;
	private JScrollPane destScrollPane;
	
	public PAddScheduleForm(){
		setLayout(null);
		setBackground(Color.GRAY);
		
		Hall hall1 = new Hall(null, "Hall 1", 2, 1);
		Hall hall2 = new Hall(null, "Hall 2", 5, 1);
		List<Hall> hallList = new ArrayList<Hall>();
		hallList.add(hall1);
		hallList.add(hall2);
		
		sourceLabel = new JLabel("Choose halls:");
		sourceLabel.setBounds(10, 10, 100, 20);
		add(sourceLabel);
		sourceList = new JList<Hall>();
		DefaultListModel<Hall> model = new DefaultListModel<Hall>();
		for(Hall hall : hallList) {
		    model.addElement(hall);
		}

		sourceList.setModel(model);
		sourceList.setCellRenderer(new HallListCellRenderer());
		sourceScrollPane = new JScrollPane(sourceList);
		add(sourceScrollPane).setBounds(10, 35, 150, 100);

		addButton = new JButton("Add >>");
		addButton.setBounds(180, 50, 100, 20);
		add(addButton);

		removeButton = new JButton("<< Remove");
		removeButton.setBounds(180, 100, 100, 20);
		add(removeButton);

		destLabel = new JLabel("Your choice:");
		destLabel.setBounds(300, 10, 100, 20);
		add(destLabel);
		destList = new JList<>();
		destScrollPane = new JScrollPane(destList);
		add(destScrollPane).setBounds(300, 35, 150, 100);
	    
	}
}

//TODO: create a new class for this and move it there
class HallListCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Hall) {
        	Hall hall = (Hall)value;
            setText(hall.getName());
            setToolTipText(hall.getName());
        }
        return this;
    }
}
