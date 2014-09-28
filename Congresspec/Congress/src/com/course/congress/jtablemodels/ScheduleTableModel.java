package com.course.congress.jtablemodels;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.course.congress.objects.Hall;

public class ScheduleTableModel extends AbstractTableModel {

	private int currentMonth;
	private int currentYear;
	private final List<Hall> hallList;
	private final String[] columnNames = new String[] {"Hall 0", "Hall 1", "Hall 2", "Hall 3"};
	private final Class[] columnClass = new Class[] {String.class, String.class, String.class, String.class};
	
	public ScheduleTableModel(List<Hall> hallList, int currentMonth, int currentYear) {
		this.hallList = hallList;
		this.currentMonth = currentMonth;
		this.currentYear = currentYear;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	/*	Hall row = hallList.get(rowIndex);
		if (0 == columnIndex) {
			return row.getName();
		} else if (1 == columnIndex) {
			return row.getCapacity();
		} else if (2 == columnIndex) {
			return row.getFloor();
		} */
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//Schedule row = hallList.get(rowIndex);
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		/*if (0 == columnIndex) {
			for (int i = 0; i < daysInMonth; i++) {
				this.setValueAt("Date " + (i + 1),i,0);
	        }
		}*/ /*else if (1 == columnIndex) {
			this.setCapacity("Event name", 0, 0);
		} else if (2 == columnIndex) {
			row.setFloor((Integer) aValue);
		} */
		 fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void addRow(Hall hall) {
		hallList.add(hall);
		fireTableRowsInserted(hallList.size() - 1, hallList.size() - 1);
	}
	
	public void removeRow(int rowIndex) {
		hallList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}

}
