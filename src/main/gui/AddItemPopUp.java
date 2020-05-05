package gui;

import com.sun.glass.ui.Clipboard;
import model.Book;
import model.Genre;
import model.Item;
import model.Magazine;


import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Vector;


public class AddItemPopUp extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    JButton btnAdd;
    JRadioButtonMenuItem nonfiction;
    private JTextField textField2;
    private Item item;
    JRadioButtonMenuItem encyclopedia;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    String author;
    String titleOfBook;
    Boolean available;
    JLabel authorLabel;
    Genre genre;
    JRadioButtonMenuItem fantasy;
    LocalDate lastTransaction;
    JCheckBoxMenuItem checkBoxMenuItem;
    JPopupMenu popupMenu;
    JRadioButton bookButton;
    JLabel genreLabel;
    JLabel statusLabel;
    JLabel titleLabel;
    ButtonGroup group;
    JRadioButtonMenuItem science;
    JSeparator separator;
    JRadioButtonMenuItem history;
    String type;

    @SuppressWarnings("checkstyle:MemberName")


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddItemPopUp frame = new AddItemPopUp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * Create the frame.
     */
    public AddItemPopUp() {
        setup();
        partOne();
        partTwo();
        partThree();

        //Instantiates button for add book (to submit values for fields)
        btnAdd = new JButton("Add Book");
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (type == "book") {
                    item = new Book(titleOfBook, AddItemPopUp.this.author, AddItemPopUp.this.genre,
                            available, lastTransaction, type);
                } else {
                    item = new Magazine(titleOfBook, AddItemPopUp.this.author, AddItemPopUp.this.genre,
                            true, lastTransaction, type);
                }

                addBook(item);
            }
        });
        partFour();


        btnAdd.setBounds(361, 271, 89, 23);
        contentPane.add(btnAdd);

    }


    //add new row to the table when book is added
    private static void addBook(Item i) {
        Menu.getInstanceOf().addItem(i);
        Vector<String> b = new Vector();
        b.add(i.getTitle());
        b.add(i.getAuthor());
        b.add(i.getGenre().toString());
        b.add(Boolean.toString(i.getStatus()));
        b.add(i.getLastTransaction().toString());
        b.add(i.getType());
        Menu.model.addRow(b);
    }


    //Add button assigns values from text fields
    private void partFour() {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddItemPopUp.this.author = textField.getText();
                titleOfBook = textField2.getText();
                available = checkBoxMenuItem.getState();
                AddItemPopUp.this.genre = processGenre();
                lastTransaction = LocalDate.now();
                type = determineType();
            }

            private String determineType() {
                if (bookButton.isSelected()) {
                    return "book";
                } else {
                    {
                        return "magazine";
                    }
                }
            }
//uses jbutton selection to assign genre

        });
    }

    private Genre processGenre() {
        if (fantasy.isSelected()) {
            return Genre.Fantasy;
        } else if (nonfiction.isSelected()) {
            return Genre.Non_fiction;
        } else if (encyclopedia.isSelected()) {
            return Genre.Encyclopedia;
        } else if (science.isSelected()) {
            return Genre.Science_Fiction;
        } else {
            return Genre.Historical_Fiction;
        }
    }

    private void setup() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 516, 344);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        popupMenu = new JPopupMenu();
        popupMenu.setBounds(0, 0, 32, 33);
        addPopup(contentPane, popupMenu);

    }
//Creates jradio buttons and adds them to button group

    private void partThree() {
        encyclopedia = new JRadioButtonMenuItem("Encyclopedia");
        group.add(encyclopedia);
        encyclopedia.setBounds(296, 127, 114, 26);
        contentPane.add(encyclopedia);

        bookButton = new JRadioButton("Book");
        bookButton.setBounds(76, 210, 109, 23);
        contentPane.add(bookButton);
        buttonGroup.add(bookButton);


        JRadioButton magazineButton = new JRadioButton("Magazine");
        buttonGroup.add(magazineButton);
        magazineButton.setBounds(187, 210, 109, 23);
        contentPane.add(magazineButton);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBounds(10, 214, 46, 14);
        contentPane.add(typeLabel);

        science = new JRadioButtonMenuItem("Science");
        group.add(science);
        science.setBounds(225, 164, 114, 26);
        contentPane.add(science);

        history = new JRadioButtonMenuItem("History");
        group.add(history);
        history.setBounds(123, 164, 105, 26);
        contentPane.add(history);
    }

    //Creates labels, textfields and checkbox
    private void partOne() {
        authorLabel = new JLabel("Author");
        authorLabel.setBounds(10, 44, 46, 14);
        contentPane.add(authorLabel);

        genreLabel = new JLabel("Genre");
        genreLabel.setBounds(10, 127, 46, 14);
        contentPane.add(genreLabel);

        statusLabel = new JLabel("Status");
        contentPane.add(statusLabel);

        textField = new JTextField();
        textField.setBounds(66, 41, 344, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.setBounds(66, 82, 344, 20);
        contentPane.add(textField2);

        checkBoxMenuItem = new JCheckBoxMenuItem("Available");
        checkBoxMenuItem.setBounds(67, 268, 137, 26);
        contentPane.add(checkBoxMenuItem);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(10, 85, 46, 14);
        contentPane.add(titleLabel);


    }


    private void partTwo() {
        fantasy = new JRadioButtonMenuItem("Fantasy");
        fantasy.setBounds(176, 127, 114, 26);
        nonfiction = new JRadioButtonMenuItem("Non Fiction");
        nonfiction.setBounds(66, 127, 105, 26);
        contentPane.add(fantasy);
        contentPane.add(nonfiction);

        separator = new JSeparator();
        separator.setBounds(205, 136, -20, 2);
        contentPane.add(separator);

        group = new ButtonGroup();
        group.add(fantasy);
        group.add(nonfiction);
    }


    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

}



