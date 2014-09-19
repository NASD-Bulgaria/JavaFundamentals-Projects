package events;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * This class is used for validating data in events jTable.
 * @author Yana
 *
 */
public class EventsTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<Event> eventList;

	private final String[] columnNames = new String[] {"Name", "Duration", "Start Date", "End Date", "Type", "Description"};
	
	private final Class[] columnClass = new Class[] { String.class, Integer.class, Date.class, Date.class, 
			String.class,  String.class};

	public EventsTableModel(List<Event> eventList) {
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
		
	}
	
}