package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ModifiedJTable extends JTable{
	
	//members
	JTableHeader header = this.getTableHeader();
	protected Font font_1 = new Font("Elephant", Font.PLAIN, 14);
	protected Font font_2 = new Font("Courier", Font.PLAIN, 20);
	
	/*
	 * ModifiedJTable(TabelModel dm) takes in a TableModel and sets up
	 * 			the table with specifics
	 */
	ModifiedJTable(TableModel dm){
		super(dm);
		//setBorder(null);
		setShowVerticalLines(false);
		setShowGrid(false);
		setShowHorizontalLines(false);
		setFont(font_2);
		setForeground(new Color(240, 255, 240));
		
		header.setBackground(new Color(173,216,230));
		header.setForeground(new Color(47, 52, 64));
		header.setFont(font_1);
		header.setBorder(new LineBorder(new Color(173,216,230), 2));
		header.setOpaque(false);
		
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setOpaque(false);
		((DefaultTableCellRenderer)this.getDefaultRenderer(Object.class)).setOpaque(false);
	}
	
	/*
	 * updateRowHeights() sets up the rows evenly
	 */
	public void updateRowHeights()
	{
	    try
	    {
	        for (int row = 0; row < this.getRowCount(); row++)
	        {
	            int rowHeight = this.getRowHeight();

	            for (int column = 0; column < this.getColumnCount(); column++)
	            {
	                Component comp = this.prepareRenderer(this.getCellRenderer(row, column), row, column);
	                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	            }

	            this.setRowHeight(row, rowHeight);
	        }
	    }
	    catch(ClassCastException e) {}
	}
}
