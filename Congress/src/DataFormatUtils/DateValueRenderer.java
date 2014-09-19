package DataFormatUtils;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Rendering dates in jTable.
 * @author Yana
 *
 */
public class DateValueRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yy");

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (value instanceof Date) {
				value = f.format(value);
			}
			return super.getTableCellRendererComponent(table, value, isSelected,
	                hasFocus, row, column);
		}
}
