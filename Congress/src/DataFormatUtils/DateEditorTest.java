package DataFormatUtils;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

/**
 * Class for editing dates in jTable.
 * @author Yana
 *
 */
public class DateEditorTest extends DefaultCellEditor {
	JFormattedTextField ftf;
	DateFormat dateFormat;
	private Date minimum, maximum;

	public DateEditorTest(int min, int max) {
		super(new JFormattedTextField());
		ftf = (JFormattedTextField) getComponent();
		Calendar cal = Calendar.getInstance();
		minimum = cal.getTime();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		maximum = cal.getTime();
		
		// Set up the editor for the date cells.
		dateFormat = DateFormat.getDateInstance();
		DateFormatter dateFormatter = new DateFormatter(dateFormat);
		dateFormatter.setFormat(dateFormat);
		dateFormatter.setMinimum(minimum);
		dateFormatter.setMaximum(maximum);

		ftf.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		ftf.setValue(minimum);
		ftf.setHorizontalAlignment(JTextField.TRAILING);
		ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

		ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				"check");
		ftf.getActionMap().put("check", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (!ftf.isEditValid()) { // The text is invalid.
					if (userSaysRevert()) { // reverted
						ftf.postActionEvent(); // inform the editor
					}
				} else
					try { // The text is valid,
						ftf.commitEdit(); // so use it.
						ftf.postActionEvent(); // stop editing
					} catch (java.text.ParseException exc) {
					}
			}
		});
	}

	// Override to invoke setValue on the formatted text field.
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
			JFormattedTextField ftf = (JFormattedTextField) super
					.getTableCellEditorComponent(table, value, isSelected, row,
							column);
			ftf.setValue(value);
		return ftf;
	}

	public Object getCellEditorValue() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		Object o = ftf.getValue();
		if (o instanceof Date) {
			return o;
		} else {
			try {
				return dateFormat.parseObject(o.toString());
			} catch (ParseException exc) {
				System.err.println("getCellEditorValue: can't parse o: " + o);
				return null;
			}
		}
	}
	
	public boolean stopCellEditing() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		if (ftf.isEditValid()) {
			try {
				ftf.commitEdit();
			} catch (java.text.ParseException exc) {
			}

		} else { // text is invalid
			if (!userSaysRevert()) { // user wants to edit
				return false; // don't let the editor go away
			}
		}
		return super.stopCellEditing();
	}

	/**
	 * Lets the user know that the text they entered is bad. Returns true if the
	 * user elects to revert to the last good value. Otherwise, returns false,
	 * indicating that the user wants to continue editing.
	 */
	protected boolean userSaysRevert() {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = { "Edit", "Revert" };
		int answer = JOptionPane.showOptionDialog(
				SwingUtilities.getWindowAncestor(ftf),
				"The value must be an integer between " + minimum + " and "
						+ maximum + ".\n" + "You can either continue editing "
						+ "or revert to the last valid value.",
				"Invalid Text Entered", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[1]);

		if (answer == 1) { // Revert!
			ftf.setValue(ftf.getValue());
			return true;
		}
		return false;
	}
}