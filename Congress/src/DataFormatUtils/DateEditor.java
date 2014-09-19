package DataFormatUtils;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableCellEditor;

/**
 * Class for editing dates.
 * @author Yana
 *
 */
public class DateEditor extends AbstractCellEditor implements TableCellEditor {

	private Object cellEditorValue;

	@Override
	public Object getCellEditorValue() {
		return this.cellEditorValue;
	}

	public void setCellEditorValue(Object value) {
		this.cellEditorValue = value;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int col) {

		JComponent component = new JTextField();
		//final JTextField textField = new JTextField();
		if (value instanceof Date) {
			final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
			//textField.setText(dateFormat.format(value));
			((JTextField)component).setText(dateFormat.format(value));
			((JTextField)component).addCaretListener(new CaretListenerForDate(this, ((JTextField)component)));
			//c.add(textField);
		}
		return component;
	}

}

class CaretListenerForDate implements CaretListener {
	private JTextField textField;
	private DateEditor dateEditor;

	public CaretListenerForDate(DateEditor dateEditor, JTextField textField) {
		this.textField = textField;
		this.dateEditor = dateEditor;
	}

	public void caretUpdate(CaretEvent e) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		try {
			dateEditor.setCellEditorValue(dateFormat.parse(textField.getText()));
		} catch (ParseException e1) {
			System.err.println(String.format(
					"Worng date format! [%s] Error is [%s]",
					textField.getText(), e1.getMessage()));
		}

	}
}
