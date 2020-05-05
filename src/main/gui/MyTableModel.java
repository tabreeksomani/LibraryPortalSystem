package gui;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MyTableModel extends DefaultTableModel {

    public MyTableModel(Vector tableData, Vector colNames) {
        super(tableData, colNames);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }
}