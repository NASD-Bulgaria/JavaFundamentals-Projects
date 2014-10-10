package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.course.congress.datastorage.DataStorage;
import com.course.congress.jtablemodels.ScheduleDatesTableModel;
import com.course.congress.jtablemodels.ScheduleTableModel;
import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.utils.DateUtils;

public class PAddScheduleForm extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel hallNameLabel;
	private JLabel dateLabel;
	private JLabel eventNameLabel;
	private JLabel scheduleMonthLabel;
	private JLabel usermessagesLabel;

	private JComboBox<String> hallCombo;
	private JComboBox<Serializable> eventCombo;

	private JButton prevMonth;
	private JButton nextMonth;
	private JButton buttonSave;	

	private JDatePickerImpl datePicker;
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;

	private JTable table;
	private JTable headerTable;
	private JScrollPane scrollPane;

	private int currentMonth;
	private int currentYear;
	private int currentDay;

	private Hall[] halls;
	private Event[] events;
	private HashMap<String, ArrayList<Event>> schedulesMap;
	
	private List<Event> eventsPerHall;
	private List<Event> existingEvent;

	public PAddScheduleForm() {
		setLayout(null);
		setBackground(Color.GRAY);

		currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		events = DataStorage.getEvents();
		halls = DataStorage.getHalls();
		schedulesMap = DataStorage.getSchedule() != null ? DataStorage.getSchedule() : new HashMap<String, ArrayList<Event>>();
		existingEvent = new ArrayList<Event>();
		
		hallNameLabel = new JLabel("Hall");
		hallNameLabel.setBounds(10, 10, 30, 20);
		add(hallNameLabel);
		hallCombo = new JComboBox(new DefaultComboBoxModel(halls));
		hallCombo.insertItemAt("Please choose hall..", 0);
		hallCombo.setSelectedIndex(0);
		hallCombo.setMaximumRowCount(3);
		hallCombo.setBounds(40, 10, 140, 20);
		add(hallCombo);

		dateLabel = new JLabel("Choose date");
		dateLabel.setBounds(200, 10, 100, 20);
		add(dateLabel);
		dateModel = new UtilDateModel();
		dateModel.setDate(currentYear, currentMonth, currentDay);
		dateModel.setSelected(false);
		datePanel = new JDatePanelImpl(dateModel);
		datePanel.setEnabled(false);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setEnabled(false);
		datePicker.setBounds(280, 10, 130, 20);
		add(datePicker);

		eventNameLabel = new JLabel("Event");
		eventNameLabel.setBounds(430, 10, 50, 20);
		add(eventNameLabel);
		eventCombo = new JComboBox(new DefaultComboBoxModel(existingEvent.toArray()));
		eventCombo.setEnabled(false);
		eventCombo.setMaximumRowCount(3);
		eventCombo.setBounds(470, 10, 120, 20);
		add(eventCombo);
		
		buttonSave = new JButton("Edit");
		buttonSave.setBounds(600, 10, 100, 20);
		buttonSave.setEnabled(false);
		add(buttonSave);

		prevMonth = new JButton("<< " + DateUtils.getPrevMonth(currentMonth));
		prevMonth.setBounds(10, 70, 130, 20);
		add(prevMonth);

		scheduleMonthLabel = new JLabel("Schedule : "
				+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
		scheduleMonthLabel.setBounds(290, 70, 180, 20);
		add(scheduleMonthLabel);

		nextMonth = new JButton(DateUtils.getNextMonth(currentMonth) + " >>");
		nextMonth.setBounds(570, 70, 130, 20);
		add(nextMonth);
		
		usermessagesLabel = new JLabel("Message to the user");
		usermessagesLabel.setBounds(770, 10, 150, 20);
		add(usermessagesLabel);

		table = new JTable(new ScheduleTableModel(halls, schedulesMap,currentMonth,
				currentYear));

		List<String> dates = new ArrayList<String>();
		for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
			dates.add("Date " + (i + 1));
		}
		headerTable = new JTable();
		changeHeaderTableProperties(new ScheduleDatesTableModel(dates));

		scrollPane = new JScrollPane(table);
		scrollPane.setRowHeaderView(headerTable);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		add(scrollPane).setBounds(10, 90, 690, 540);
		
		prevMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentMonth == 0) {
					currentMonth = 11;
					currentYear = currentYear - 1;
				} else {
					currentMonth = currentMonth - 1;
				}
				scheduleMonthLabel.setText("Schedule : "
						+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
				prevMonth.setText("<< " + DateUtils.getPrevMonth(currentMonth));
				nextMonth.setText(DateUtils.getNextMonth(currentMonth) + " >>");

				// change the displayed dates
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth,
						currentYear));
			}

		});

		nextMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentMonth == 11) {
					currentMonth = 0;
					currentYear = currentYear + 1;
				} else {
					currentMonth = currentMonth + 1;
				}
				scheduleMonthLabel.setText("Schedule : "
						+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
				prevMonth.setText("<< " + DateUtils.getPrevMonth(currentMonth));
				nextMonth.setText(DateUtils.getNextMonth(currentMonth) + " >>");

				// change the displayed dates in the jTable
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth,
						currentYear));
			}
		});

		hallCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Hall selectedHall = null;
				try {
					selectedHall = (Hall) hallCombo.getSelectedItem();
				} catch (ClassCastException ce) {
					hallCombo.setSelectedIndex(1);;
					selectedHall = (Hall) hallCombo.getSelectedItem();
				}
				String hallName = selectedHall.getName();
				eventsPerHall = schedulesMap.get(hallName);
				
				//clear selected values for the other combos
				dateModel.setSelected(false);
				eventCombo.setSelectedIndex(-1);
				eventCombo.setEnabled(false);
				buttonSave.setEnabled(false);
			}
		});

		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date newDate = (Date) datePicker.getModel().getValue();
				newDate = DateUtils.returnDateWithoutTime(newDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(newDate);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);

				currentMonth = month;
				currentYear = year;

				//if there are events assigned to the selected hall
				if (eventsPerHall != null) {
					for (Event event : eventsPerHall) {
						Date eventStartDate = event.getStartDate();
						//if there are events assigned to the hall on the selected date
						if (eventStartDate.equals(newDate)) {
							eventCombo.removeAllItems();
							eventCombo.addItem(event);
							eventCombo.setSelectedIndex(0);
							eventCombo.setEnabled(false);
							buttonSave.setText("Edit");
							buttonSave.setEnabled(true);
							break;
						} else {
							//Show possible events for the selected date and hall
							getPossibleEventsForDate(newDate);
						}
					}
				} else {
					getPossibleEventsForDate(newDate);
				}

				scheduleMonthLabel.setText("Schedule : "
						+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
				prevMonth.setText("<< " + DateUtils.getPrevMonth(currentMonth));
				nextMonth.setText(DateUtils.getNextMonth(currentMonth) + " >>");

				// change the displayed dates in the jTable
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(halls, schedulesMap,currentMonth,
						currentYear));
			}
		});
		
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttonSave.getText().equalsIgnoreCase("edit")) {
					Date selectedDate = (Date) datePicker.getModel().getValue();
					selectedDate = DateUtils.returnDateWithoutTime(selectedDate);
					getPossibleEventsForDate(selectedDate);
				} else if (buttonSave.getText().equalsIgnoreCase("save")) {
					Hall selectedHall = (Hall) hallCombo.getSelectedItem();
					String hallName = selectedHall.getName();
					Event selectedEvent = null;
					try {
						selectedEvent = (Event) eventCombo.getSelectedItem();
					} catch (ClassCastException ce) {
						selectedEvent = eventsPerHall.get(0);
						DataStorage.removeSchedule(hallName, selectedEvent);
						System.out.println("Event detached.");
						table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth,
								currentYear));
						return;
					}
					int duration = selectedEvent.getDuration();
					if (duration > 1) {
						while (duration == 1) {
							Object cellValue = table.getModel().getValueAt(7, 0);
							if (cellValue == null) {
								duration--;
							} else {
								JOptionPane.showMessageDialog(null, "The hall is occupied for the selected period!");
								return;
							}
						}
					}
					DataStorage.addNewSchedule(hallName, selectedEvent);
					table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth, currentYear));
				}
			}
		});
	}

	private void changeHeaderTableProperties(
			ScheduleDatesTableModel scheduleDatesTableModel) {
		headerTable.setModel(scheduleDatesTableModel);
		headerTable.setShowGrid(false);
		headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		headerTable.setPreferredScrollableViewportSize(new Dimension(50, 0));
		headerTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		headerTable.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable x,
							Object value, boolean isSelected, boolean hasFocus,
							int row, int column) {

						Component component = table
								.getTableHeader()
								.getDefaultRenderer()
								.getTableCellRendererComponent(table, value,
										false, false, -1, -2);
						((JLabel) component)
								.setHorizontalAlignment(SwingConstants.CENTER);
						return component;
					}
				});
	}
	
	/**
	 * Fills the combobox with the possible events for the selected date and hall.
	 * @param selectedDate
	 */
	private void getPossibleEventsForDate(Date selectedDate){
		Hall selectedHall = (Hall) hallCombo.getSelectedItem();
		String selectedHallName = selectedHall.getName();
		ArrayList<Event> allAssignedEvents = new ArrayList<Event>();
		eventCombo.removeAllItems();
		
		if (!schedulesMap.isEmpty()) {
			for (Map.Entry<String, ArrayList<Event>> entry : schedulesMap.entrySet()) {
				ArrayList<Event> assignedEventsPerHall = entry.getValue();
				for (Event event : assignedEventsPerHall) {
					allAssignedEvents.add(event);
				}
			}
		}
		
		for (int i = 0; i < events.length; i++) {
			if (events[i].getStartDate().equals(selectedDate)) {
				if (!schedulesMap.isEmpty()) {
					for (Map.Entry<String, ArrayList<Event>> entry : schedulesMap.entrySet()) {
						String hallName = entry.getKey();
						ArrayList<Event> assignedEventsPerHall = entry.getValue();
						
						// if event is assigned to a hall, don't add it to the
						// possible events
						if (!allAssignedEvents.contains(events[i])) {
							eventCombo.addItem(events[i]);
							break;
						} else if (assignedEventsPerHall.contains(events[i])
								&& hallName.equalsIgnoreCase(selectedHallName)) {
							eventCombo.addItem(events[i]);
						}
					}
					
				} else {
					eventCombo.addItem(events[i]);
				}
			}
		}
		if (eventCombo.getItemCount() > 0) {
			eventCombo.insertItemAt("Please choose..", 0);
			eventCombo.setSelectedIndex(0);
			eventCombo.setEnabled(true);
			buttonSave.setText("Save");
			buttonSave.setEnabled(true);
		} else {
			eventCombo.insertItemAt("No events..", 0);
			eventCombo.setSelectedIndex(0);
			eventCombo.setEnabled(false);
			buttonSave.setEnabled(false);
		}
	}
}
