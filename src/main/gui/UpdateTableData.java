package gui;

import model.IIssuableItem;
import model.Item;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

//This class is responsible for the calls to update already existing data in the table.

public class UpdateTableData {

    JButton checkIn;
    JButton checkOut;
    JButton renew;
    String selTitle;
    Item selItem;
    String selType;
    private int sel;

    public UpdateTableData() {
        checkOut = new JButton("Check Out");
        checkOut();
        checkIn = new JButton("Check In");
        checkIn();
        renew = new JButton("Renew");
        renew();

    }

    //Performs renewal of Books, modifies last transaction in table.
    private void renew() {
        renew.setBounds(648, 11, 135, 23);
        renew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getValues("false");
                if (selTitle != null) {
                    Menu.getInstanceOf().renew(selTitle);
                    modify("false");
                }
            }
        });
    }

    //Performs check In of Books, changes last transaction and status in data table.
    private void checkIn() {
        checkIn.setBounds(342, 11, 135, 23);
        checkIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getValues("false");
                if (selTitle != null) {
                    Menu.getInstanceOf().checkIn(selTitle);
                    modify("true");
                }
            }
        });
    }
    //Performs check Out of Books, changes last transaction and status in data table.

    private void checkOut() {
        checkOut.setBounds(493, 11, 135, 23);
        checkOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getValues("true");
                if (selTitle != null) {
                    selType = Menu.table.getValueAt(sel, 5).toString();
                    selItem = Menu.getInstanceOf().returnItemSearch(selTitle, selType);
                    try {
                        selItem.issueItem();
                        modify("false");

                    } catch (IIssuableItem.ItemNotIssuableException in) {
                        JOptionPane.showMessageDialog(null, "Magazine cannot be checked out",
                                "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }


    private void getValues(String bool) {
        selTitle = null;
        if (!Menu.table.getSelectionModel().isSelectionEmpty()) {
            sel = Menu.table.getSelectedRow();
            if (Menu.table.getValueAt(sel, 3).equals(bool)) {
                selTitle = Menu.table.getValueAt(sel, 0).toString();

            }
        }
    }

    private void modify(String val) {
        Menu.table.setValueAt(LocalDate.now().toString(), sel, 4);
        Menu.table.setValueAt(val, sel, 3);
        Menu.model.fireTableDataChanged();
    }
}
