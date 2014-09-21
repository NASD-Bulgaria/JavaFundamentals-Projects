package com.course.congress.jtablemodels;

import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.course.congress.objects.Event;

/**
 * This class is used for validating data in events jTable.
 *
 */
public class EventTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private final List<Event> eventList;
	private final String[] columnNames = new String[] {"Name", "Duration (# of days)", "Start Date", "End Date", "Type", "Description"};
	private final Class[] columnClass = new Class[] { String.class, Integer.class, Date.class, Date.class, String.class,  String.class};

	public EventTableModel(List<Event> eventList) {
		this.eventList = eventList;
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
		return eventList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Event row = eventList.get(rowIndex);
		if (0 == columnIndex) {
			return row.getName();
		} else if (1 == columnIndex) {
			return row.getDuration();
		} else if (2 == columnIndex) {
			return row.getStartDate();
		} else if (3 == columnIndex) {
			return row.getEndDate();
		} else if(4 == columnIndex){
			return row.getType();
		} else if(5 == columnIndex){
			return row.getDescription();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex==1){
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Event row = eventList.get(rowIndex);
		if (0 == columnIndex) {
			row.setName((String) aValue);
		} else if (1 == columnIndex) {
			row.setDuration((Integer) aValue);
		} else if (2 == columnIndex) {
			row.setStartDate((Date) aValue);
		} else if (3 == columnIndex) {
			row.setEndDate((Date) aValue);
		} else if (4 == columnIndex){
			row.setType((String) aValue);
		} else if (5 == columnIndex){
			row.setDescription((String) aValue);
		}
		 fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void addRow(Event event) {
		eventList.add(event);
		fireTableRowsInserted(eventList.size() - 1, eventList.size() - 1);
	}
	
	public void removeRow(int rowIndex) {
	    //Event rowToRemove = eventList.get(rowIndex);
	    eventList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}
}
