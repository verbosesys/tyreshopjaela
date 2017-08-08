/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tyre_shop_jaela;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nalaranga
 */
public class CustomRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 6703872492730589499L;
    final Color col = new Color(102, 102, 102);
    final Color col1 = new Color(66,165,245);
    final Color colBG = new Color(252, 252, 252);
    String date = "";

    public CustomRenderer(String nowDate) {
        if (Integer.parseInt(nowDate) < 10) {
            String srt[] = nowDate.split("");
            date = srt[1];
        } else {
            date = nowDate;
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.getValueAt(row, column).equals(date)) {
            cellComponent.setForeground(Color.WHITE);
            cellComponent.setBackground(col1);
        } else {
            cellComponent.setForeground(col);
            cellComponent.setBackground(colBG);
        }
        return cellComponent;
    }
}