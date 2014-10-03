package com.course.congress.jtablemodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jdesktop.swingx.calendar.DaySelectionModel;

import com.course.congress.datastorage.DataStorage;
import com.course.congress.objects.Equipment;
import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.objects.HallArrangement;
import com.course.congress.objects.Schedule;

public class ScheduleTableModel extends AbstractTableModel {

	private int currentMonth;
	private int currentYear;
	private int daysInMonth;
	private HashMap<String, ArrayList<Event>> schedulesMap;
	private Hall[] halls = DataStorage.getHalls();
	// private Hall[] halls = new Hall[5];
	private final String[] columnNames = new String[halls.length];

	public ScheduleTableModel(Hall[] halls, int currentMonth,
			int currentYear) {
		this.halls = halls;
		this.currentMonth = currentMonth;
		this.currentYear = currentYear;
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
		this.daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
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
//		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
//		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return this.daysInMonth;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//create dummy data
		ArrayList<Equipment> listEquipment = new ArrayList<>();
		HallArrangement ha = new HallArrangement("");
		Event event1 = new Event(1, "name1", 1, new Date(), new Date(), "", "", "", listEquipment, ha);
		Event event2 = new Event(2, "name2", 1, new Date(), new Date(), "", "", "", listEquipment, ha);
		ArrayList<Event> dummyEvent = new ArrayList<>();
		dummyEvent.add(event1);
		dummyEvent.add(event2);
		
		schedulesMap = new HashMap<>();
		schedulesMap.put("fdgdfgdfg", dummyEvent);
		schedulesMap.put("Hall 1", dummyEvent);
		
		
		ArrayList<Event> eventsPerHall = schedulesMap.get(columnNames[columnIndex]);
		
		for (int i = 0; i <= this.daysInMonth; i++) {
			if (eventsPerHall != null) {
				for (Event event : eventsPerHall) {
					Date eventStartDate = event.getStartDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(eventStartDate);
					int startDay = cal.get(Calendar.DAY_OF_MONTH);
					if ((rowIndex + 1) == startDay) {
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

	/*public void addRow(Hall hall) {
		hallList.add(hall);
		fireTableRowsInserted(hallList.size() - 1, hallList.size() - 1);
	}

	public void removeRow(int rowIndex) {
		hallList.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}*/

}
