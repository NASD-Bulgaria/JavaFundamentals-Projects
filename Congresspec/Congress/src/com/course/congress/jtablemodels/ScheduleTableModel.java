package com.course.congress.jtablemodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;

public class ScheduleTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private int currentMonth;
	private int currentYear;
	private int daysInMonth;
	private HashMap<String, ArrayList<Event>> schedulesMap;
	private Hall[] halls;
	private final String[] columnNames;

	public ScheduleTableModel(Hall[] halls, HashMap<String, ArrayList<Event>> schedulesMap, int currentMonth,
			int currentYear) {
		this.halls = halls;
		this.schedulesMap = schedulesMap;
		this.currentMonth = currentMonth;
		this.currentYear = currentYear;
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
		this.daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.columnNames = new String[halls.length];
	}

	@Override
	public String getColumnName(int column) {
		for (int i = 0; i < halls.length; i++) {
			columnNames[i] = halls[i].getName();
		}
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.daysInMonth;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<Event> eventsPerHall = schedulesMap.get(columnNames[columnIndex]);
		
		for (int i = 0; i <= this.daysInMonth; i++) {
			if (eventsPerHall != null) {
				for (Event event : eventsPerHall) {
					Date eventStartDate = event.getStartDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(eventStartDate);
					int startDay = cal.get(Calendar.DAY_OF_MONTH);
					int eventMonth = cal.get(Calendar.MONTH);
					int eventYear = cal.get(Calendar.YEAR);
					if ((rowIndex + 1) == startDay && this.currentMonth == eventMonth && this.currentYear == eventYear) {
						return event.getName();
					} else {
						return "";
					}
				}
			} else {
				return null;
			}
		}
		return null;
			
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Schedule row = hallList.get(rowIndex);
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

		/*
		 * if (0 == columnIndex) { for (int i = 0; i < daysInMonth; i++) {
		 * this.setValueAt("Date " + (i + 1),i,0); } }
		 *//*
			 * else if (1 == columnIndex) { this.setCapacity("Event name", 0,
			 * 0); } else if (2 == columnIndex) { row.setFloor((Integer)
			 * aValue); }
			 */
		fireTableCellUpdated(rowIndex, columnIndex);
	}

}
