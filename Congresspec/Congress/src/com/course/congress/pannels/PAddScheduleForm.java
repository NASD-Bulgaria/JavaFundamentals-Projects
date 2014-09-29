package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.course.congress.jtablemodels.ScheduleDatesTableModel;
import com.course.congress.jtablemodels.ScheduleTableModel;
import com.course.congress.objects.Hall;

public class PAddScheduleForm extends JPanel {

	private JLabel hallNameLabel;
	private JLabel dateLabel;
	private JLabel eventNameLabel;
	private JLabel scheduleMonthLabel;

	private JComboBox hallCombo;
	private JComboBox eventCombo;

	private JButton prevMonth;
	private JButton nextMonth;

	private JDatePickerImpl datePicker;
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;

	private DefaultTableModel model;
	private JTable table;
	private JTable headerTable;
	private JScrollPane scrollPane;

	private int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
	private int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

	public PAddScheduleForm() {
		setLayout(null);
		setBackground(Color.GRAY);

		Hall hall1 = new Hall(null, "Hall 1", 2, 1);
		Hall hall2 = new Hall(null, "Hall 2", 5, 1);
		List<Hall> hallList = new ArrayList<Hall>();
		hallList.add(hall1);
		hallList.add(hall2);

		hallNameLabel = new JLabel("Hall");
		hallNameLabel.setBounds(10, 10, 30, 20);
		add(hallNameLabel);
		hallCombo = new JComboBox(new DefaultComboBoxModel(hallList.toArray()));
		hallCombo.insertItemAt("Please choose hall..", 0);
		hallCombo.setSelectedIndex(0);
		hallCombo.setMaximumRowCount(3);
		hallCombo.setBounds(40, 10, 140, 20);
		add(hallCombo);

		dateLabel = new JLabel("Choose date");
		dateLabel.setBounds(200, 10, 100, 20);
		add(dateLabel);
		dateModel = new UtilDateModel();
		dateModel.setDate(2014, 8, 24);
		dateModel.setSelected(false);
		datePanel = new JDatePanelImpl(dateModel);
		datePanel.setEnabled(false);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(280, 10, 130, 20);
		datePicker.setEnabled(false);
		add(datePicker);

		eventNameLabel = new JLabel("Event");
		eventNameLabel.setBounds(430, 10, 50, 20);
		add(eventNameLabel);
		eventCombo = new JComboBox();
		eventCombo.setEnabled(false);
		eventCombo.setMaximumRowCount(3);
		eventCombo.setBounds(470, 10, 120, 20);
		add(eventCombo);

		prevMonth = new JButton("<< " + getPrevMonth(currentMonth));
		prevMonth.setBounds(10, 70, 130, 20);
		add(prevMonth);

		scheduleMonthLabel = new JLabel("Schedule : " + getCurrentMonth(currentMonth) +" "+ currentYear);
		scheduleMonthLabel.setBounds(210, 70, 180, 20);
		add(scheduleMonthLabel);

		nextMonth = new JButton(getNextMonth(currentMonth) + " >>");
		nextMonth.setBounds(460, 70, 130, 20);
		add(nextMonth);

		table = new JTable(new ScheduleTableModel(hallList, currentMonth,
				currentYear));

		List<String> dates = new ArrayList<String>();
		for (int i = 0; i < getDaysOfMonth(currentMonth, currentYear); i++) {
			dates.add("Date " + (i + 1));
		}
		headerTable = new JTable();
		changeHeaderTableProperties(new ScheduleDatesTableModel(dates));

		scrollPane = new JScrollPane(table);
		scrollPane.setRowHeaderView(headerTable);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		add(scrollPane).setBounds(10, 90, 580, 530);

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
						+ getCurrentMonth(currentMonth)  + " " + currentYear);
				prevMonth.setText("<< " + getPrevMonth(currentMonth));
				nextMonth.setText(getNextMonth(currentMonth) + " >>");

				// change the displayed dates
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}				
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(new ArrayList<Hall>(), currentMonth, currentYear));
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
						+ getCurrentMonth(currentMonth)  + " " + currentYear);
				prevMonth.setText("<< " + getPrevMonth(currentMonth));
				nextMonth.setText(getNextMonth(currentMonth) + " >>");

				// change the displayed dates
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}				
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(new ArrayList<Hall>(), currentMonth, currentYear));
			}
		});
		
		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date newDate = (Date) datePicker.getModel().getValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(newDate);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				
				currentMonth = month;
				currentYear = year;
				
				scheduleMonthLabel.setText("Schedule : "
						+ getCurrentMonth(currentMonth)  + " " + currentYear);
				prevMonth.setText("<< " + getPrevMonth(currentMonth));
				nextMonth.setText(getNextMonth(currentMonth) + " >>");
				// change the displayed dates
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}				
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(new ArrayList<Hall>(), currentMonth, currentYear));
			}
		});
	}

	private void changeHeaderTableProperties(ScheduleDatesTableModel scheduleDatesTableModel) {
			headerTable.setModel(scheduleDatesTableModel);
			headerTable.setShowGrid(false);
			headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			headerTable.setPreferredScrollableViewportSize(new Dimension(50, 0));
			headerTable.getColumnModel().getColumn(0).setPreferredWidth(50);
			headerTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable x,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {

					Component component = table.getTableHeader().getDefaultRenderer()
							.getTableCellRendererComponent(table, value,
									false, false, -1, -2);
					((JLabel) component)
							.setHorizontalAlignment(SwingConstants.CENTER);
					return component;
				}
			});
	}

	private String getCurrentMonth(int monthIndex) {
		String monthString = new DateFormatSymbols().getMonths()[monthIndex];
		return monthString;
	}

	private String getPrevMonth(int monthIndex) {
		if (monthIndex == 0) {
			monthIndex = 12;
		}
		return new DateFormatSymbols().getMonths()[monthIndex - 1];
	}

	private String getNextMonth(int monthIndex) {
		if (monthIndex == 11) {
			monthIndex = -1;
		}
		return new DateFormatSymbols().getMonths()[monthIndex + 1];

	}

	private int getDaysOfMonth(int monthIndex, int year) {
		Calendar mycal = new GregorianCalendar(year, monthIndex, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;

	}
}
