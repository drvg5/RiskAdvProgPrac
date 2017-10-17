package com.map.editor;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
	int i = 0;
	private static final long serialVersionUID = 1L;

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value.equals("Configure")) {

			i++;
			// JOptionPane.showMessageDialog(button, "Column with Value: " +
			// System.out.println(table.getModel());
			System.out.println("Count:" + i);
			String strContinent = (String) table.getModel().getValueAt(row, column - 1);
			System.out.println(strContinent);
			// CreateMap.addCountry(strContinent);
			// CreateMap.AddCountry(strContinent);
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
