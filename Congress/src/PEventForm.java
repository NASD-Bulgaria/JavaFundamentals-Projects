
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class PEventForm extends JPanel {

	private JScrollPane scrollPane;
	
	private JLabel name;
	private JLabel duration;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel type;
	private JLabel description;
	private JLabel equipment;
	private JLabel arrangement;
	private JLabel promotionalMaterials;
	
	private JTextField eventName;
	private JTextField eventDuration;
	private JTextField eventStartDate;
	private JTextField eventEndDate;
	private JTextField eventType;
	private JTextArea eventDescription;
	//set the equipment chooser
	//set the way that u want to choose the arrangement of the hall
	//set the way to select if promotional materials are selected and the way that u want to choose them
	
	private JButton addEvent;
	private JButton editEvent;
	private JButton removeEvent;
	
	private JTable eventsTable;
	private String[] eventTableColumnNames = {"Name", "Duration", "Start Date", "End Date", "Type", "Description"};
	
	//table dummy data, should be read from file ?!
	private Object[][] data = {
			{"Party", "1 day", "15.09", "15.09", "party", "Boss b-day"},
	};
	
	PEventForm() {
		
		setLayout(null);
		setSize(950, 650);
		setBackground(Color.GRAY);
		
		//name
		name = new JLabel("Name");
		name.setBounds(10, 10, 40, 20);
		add(name);
		eventName = new JTextField();
		eventName.setBounds(50, 10, 130, 20);
		add(eventName);
		
		duration = new JLabel("Duration");
		duration.setBounds(190, 10, 50, 20);
		add(duration);
		eventDuration = new JTextField();
		eventDuration.setBounds(245, 10, 130, 20);
		add(eventDuration);

		startDate = new JLabel("Start Date");
		startDate.setBounds(385, 10, 60, 20);
		add(startDate);
		eventStartDate = new JTextField();
		eventStartDate.setBounds(445, 10, 130, 20);
		add(eventStartDate);
		
		endDate = new JLabel("End Date");
		endDate.setBounds(585, 10, 60, 20);
		add(endDate);
		eventEndDate = new JTextField();
		eventEndDate.setBounds(640, 10, 130, 20);
		add(eventEndDate);
		
		type = new JLabel("Type");
		type.setBounds(10, 40, 40, 20);
		add(type);
		eventType = new JTextField();
		eventType.setBounds(50, 40, 130, 20);
		add(eventType);
		
		description = new JLabel("Description");
		description.setBounds(190, 40, 70, 20);
		add(description);
		eventDescription = new JTextArea();
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		eventDescription.setBorder(BorderFactory.createCompoundBorder(border, 
		            BorderFactory.createEmptyBorder(265, 40, 505, 20)));
		eventDescription.setBounds(265, 40, 505, 20);
		add(eventDescription);
		
		addEvent = new JButton("Add Event");
		addEvent.setBounds(800, 100, 120, 40);
		add(addEvent);
		
		editEvent = new JButton("Edit Event");
		editEvent.setBounds(800, 200, 120, 40);
		add(editEvent);
		
		removeEvent = new JButton("Remove Event");
		removeEvent.setBounds(800, 300, 120, 40);
		add(removeEvent);
		
		eventsTable = new JTable(new DefaultTableModel(data, eventTableColumnNames));
		eventsTable.setFillsViewportHeight(true);
		//eventsTable.setEnabled(false);
		scrollPane = new JScrollPane(eventsTable);
		add(scrollPane).setBounds(10, 100, 755, 500);
		
		//events and actions
		
		
	}
}
