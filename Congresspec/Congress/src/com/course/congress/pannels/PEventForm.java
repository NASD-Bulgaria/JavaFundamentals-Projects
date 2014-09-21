package com.course.congress.pannels;

import java.awt.Color;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.jdesktop.swingx.table.DatePickerCellEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.course.congress.controlers.PPanelControler;
import com.course.congress.jtablemodels.DateValueRenderer;
import com.course.congress.jtablemodels.EventTableModel;
import com.course.congress.objects.Event;

public class PEventForm extends JPanel {

	private JScrollPane scrollPane;

	private JLabel name;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel type;
	private JLabel description;
	private JLabel promotionalMaterials;

	private JTextField eventName;
	private JTextField eventType;
	private JTextArea eventDescription;
	// set the equipment chooser
	// set the way that u want to choose the arrangement of the hall
	// set the way to select if promotional materials are selected and the way
	// that u want to choose them

	private JButton addEvent;
	private JButton removeEvent;
	private JButton equipment;
	private JButton hallArrangement;
	
	private JDatePickerImpl datePickerStartDate;
	private UtilDateModel dateModelStartDate;
	private JDatePanelImpl datePanelStartDate;
	
	private JDatePickerImpl datePickerEndDate;
	private UtilDateModel dateModelEndDate; 
	private JDatePanelImpl datePanelEndDate; 

	private JTable eventsTable;
	private EventTableModel eventTableModel;

	public PEventForm() {

		setLayout(null);
		setSize(1000, 1000);
		setBackground(Color.GRAY);

		// name
		name = new JLabel("Name");
		name.setBounds(10, 10, 40, 20);
		add(name);
		eventName = new JTextField();
		eventName.setBounds(50, 10, 130, 20);
		add(eventName);

		startDate = new JLabel("Start Date");
		startDate.setBounds(190, 10, 60, 20);
		add(startDate);
		dateModelStartDate = new UtilDateModel();
		dateModelStartDate.setDate(2014, 8, 24);
		dateModelStartDate.setSelected(true);
		datePanelStartDate = new JDatePanelImpl(dateModelStartDate);
		datePickerStartDate = new JDatePickerImpl(datePanelStartDate);
		datePickerStartDate.setBounds(255, 10, 130, 20);
		add(datePickerStartDate);

		endDate = new JLabel("End Date");
		endDate.setBounds(395, 10, 60, 20);
		add(endDate);
		dateModelEndDate = new UtilDateModel();
		dateModelEndDate.setDate(2014, 8, 24);
		dateModelEndDate.setSelected(true);
		datePanelEndDate = new JDatePanelImpl(dateModelEndDate);
		datePickerEndDate = new JDatePickerImpl(datePanelEndDate);
		datePickerEndDate.setBounds(450, 10, 130, 20);
		add(datePickerEndDate);

		type = new JLabel("Type");
		type.setBounds(590, 10, 40, 20);
		add(type);
		eventType = new JTextField();
		eventType.setBounds(620, 10, 145, 20);
		add(eventType);

		description = new JLabel("Description");
		description.setBounds(10, 40, 70, 20);
		add(description);
		eventDescription = new JTextArea();
		JScrollPane sp = new JScrollPane(eventDescription); 
		sp.setBounds(80, 40, 685, 40);
		add(sp);

		addEvent = new JButton("Add Event");
		addEvent.setBounds(800, 10, 120, 40);
		add(addEvent);

		//this button is not needed since the table is updated by double click in a cell.
		/*editEvent = new JButton("Edit Event");
		editEvent.setBounds(140, 520, 120, 40);
		add(editEvent);*/

		removeEvent = new JButton("Remove Event");
		removeEvent.setBounds(800, 460, 120, 40);
		add(removeEvent);

		equipment = new JButton("Add Equipment");
		equipment.setBounds(800, 100, 120, 40);
		add(equipment);

		hallArrangement = new JButton("Arangement");
		hallArrangement.setBounds(800, 150, 120, 40);
		add(hallArrangement);

		// Creating editable jTable for events with dummy data
		Event row1 = new Event(null,"Party", 1, new Date(), new Date(), "party",
				"Boss b-day", null, null, null);
		// build the list
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(row1);

		// create the model
		eventTableModel = new EventTableModel(eventList);
		// create the table
		eventsTable = new JTable(eventTableModel);
		setTableAlignment(eventsTable);
		eventsTable.setFillsViewportHeight(true);
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		eventsTable.setDefaultRenderer(Date.class, new DateValueRenderer());
		eventsTable.setDefaultEditor(Date.class, new DatePickerCellEditor(df));
		scrollPane = new JScrollPane(eventsTable);
		add(scrollPane).setBounds(10, 100, 755, 400);

		// events and actions
		equipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PEquipment pEquipment = new PEquipment();
				PPanelControler.showNextPanel(pEquipment);

			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String eventNameText = eventName.getText();
				Date startDateText = (Date) datePickerStartDate.getModel().getValue();
				Date endDateText = (Date) datePickerEndDate.getModel().getValue();
				int durationText = (int) ((endDateText.getTime() - startDateText.getTime()) / (1000 * 60 * 60 * 24));
				//checking if end date is after start date
				if (endDateText.after(startDateText)){
					String type = eventType.getText();
					String description = eventDescription.getText();
					Event event = new Event(null, eventNameText, durationText, startDateText, endDateText, type, description, null, null, null);
					eventTableModel.addRow(event);
				} else {
					JOptionPane.showMessageDialog(null, "End date must be after start date!");
				}
				//TODO: clear fields after data is imported
				//TODO: check for empty textboxes
			}
		});
		
		removeEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check for selected row first
		        if (eventsTable.getSelectedRow() != -1) {
		        	eventTableModel.removeRow(eventsTable.getSelectedRow());
		        } else {
		        	JOptionPane.showMessageDialog(null, "Please select event from the table first.");
		        }
			}
		});
		
		eventsTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					System.out.println("Cell "
							+ e.getFirstRow()
							+ ", "
							+ e.getColumn()
							+ " changed. The new value: "
							+ eventsTable.getModel().getValueAt(
									e.getFirstRow(), e.getColumn()));
					int row = e.getFirstRow();
					int column = e.getColumn();
					if (column == 2 || column == 3) {
						TableModel model = eventsTable.getModel();
						Date startDate = ((Date) model.getValueAt(row, 2));
						Date endDate = ((Date) model.getValueAt(row, 3));
						int days = (int) ((endDate.getTime() - startDate
								.getTime()) / (1000 * 60 * 60 * 24));
						model.setValueAt(days, row, 1);
					}
				}
			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
	}
	
	/**
	 * Sets alignment to the headers and the content in each cell.
	 * @param table
	 */
	public void setTableAlignment(JTable table) {
		// table header alignment
		/*JTableHeader header = table.getTableHeader();
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		header.setDefaultRenderer(renderer);
		renderer.setHorizontalAlignment(JLabel.CENTER);*/

		// table content alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.LEFT);
		//sets the alignment for integer columns only
		table.setDefaultRenderer(Integer.class, centerRenderer);
	}
	
}
