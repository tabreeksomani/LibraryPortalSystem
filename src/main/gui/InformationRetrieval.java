package gui;

import model.Item;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static gui.CheckedOutBooks.myModel;

//This class is responsible for retrieving existing information.

public class InformationRetrieval {
    String selTitle;
    Item selItem;
    JButton moreInfo;
    String selType;
    JButton checkedOutBooks;
    JTextField searchBar;
    JTextField searchResults;
    JButton searchCatalogue;

    public InformationRetrieval() {
        moreInfo = new JButton("More Information ");
        moreInformation();
        checkedOutBooks = new JButton("Checked Out List");
        checkedOut();
        searchBar = new JTextField();
        searchResults = new JTextField();
        searchCatalogue = new JButton("Search Catalogue");
        search();
    }

    //Implements the moreInformation call
    private void moreInformation() {
        moreInfo.setBounds(350, 457, 135, 23);
        moreInfo.addMouseListener(new MouseAdapter() {
            @Override
            //mouse event for reload button that reloads the data on the text file
            public void mouseClicked(MouseEvent e) {
                if (!Menu.table.getSelectionModel().isSelectionEmpty()) {
                    int sel = Menu.table.getSelectedRow();
                    selTitle = Menu.table.getValueAt(sel, 0).toString();
                    selType = Menu.table.getValueAt(sel, 5).toString();
                    selItem = Menu.getInstanceOf().returnItemSearch(selTitle, selType);
                    JOptionPane.showMessageDialog(null, selItem.getItemInfo());
                }
            }
        });
    }

    //Creates a new window for checkedOutBooks on mouse click
    private void checkedOut() {
        checkedOutBooks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CheckedOutBooks checkedOut = new CheckedOutBooks();
                checkedOut.setVisible(true);
            }
        });
        checkedOutBooks.setBounds(181, 11, 135, 23);

    }

    //Retrieves checked out list as JList
    public static void retrieveCheckedOut(JList list) {
        ArrayList checkedOutBooks = new ArrayList();
        checkedOutBooks = Menu.getInstanceOf().getCheckOutList();
        for (int i = 0; i < checkedOutBooks.size(); i++) {
            myModel.addElement(checkedOutBooks.get(i).toString());
        }
    }

    private void search() {
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String bookTitle = searchBar.getText();
                    searchResults.setText(Menu.getInstanceOf().search(bookTitle));

                }
            }
        });
        searchCatalogue.setBounds(648, 73, 135, 23);
        searchResults.setText("Search Results");
        searchResults.setBounds(20, 102, 608, 20);
        searchResults.setColumns(10);
        searchBar.setBounds(20, 74, 608, 20);
        searchBar.setColumns(10);
        searchCatalogue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String bookTitle = searchBar.getText();
                searchResults.setText(Menu.getInstanceOf().search(bookTitle));


            }
        });
    }
}
