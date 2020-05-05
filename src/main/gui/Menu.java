package gui;

import model.*;
import persistence.Reader;
import persistence.Writer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.Vector;

//This class has the responsibility of inserting initial data.
public class Menu extends JFrame {

    private static LibraryCatalogue cat;
    private JPanel contentPane;
    JButton btnSave;
    JButton reload;
    Vector<Vector> rowData;
    Vector<String> colNames;
    Vector<String> vect;
    static JTable table;
    JScrollPane scrollPane;
    static DefaultTableModel model;
    List<Item> items;
    private static final String CATALOGUE_FILE = "./data/catalogue.txt";
    JButton addBook;
    JSeparator separator;
    UpdateTableData utd;
    InformationRetrieval ir;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu frame = new Menu();
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
    public Menu() {

        if (cat == null) {
            cat = new LibraryCatalogue();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 820, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        utd = new UpdateTableData();
        contentPane.add(utd.checkOut);
        contentPane.add(utd.checkIn);
        contentPane.add(utd.renew);
        ir = new InformationRetrieval();
        contentPane.add(ir.moreInfo);
        contentPane.add(ir.checkedOutBooks);
        contentPane.add(ir.searchCatalogue);
        contentPane.add(ir.searchResults);
        contentPane.add(ir.searchBar);

        actions();
        colAdd();

        model = new MyTableModel(rowData, colNames);
        table = new JTable(model);
        constructTable();
    }
//adds columns to table

    private void colAdd() {
        colNames = new Vector();
        colNames.add("Title");
        colNames.add("Author");
        colNames.add("Genre");
        colNames.add("Available?");
        colNames.add("Last Transaction");
        colNames.add("Type");
    }

    private void actions() {
        addBooks();

        separator = new JSeparator();
        separator.setBounds(83, 118, 1, 46);
        contentPane.add(separator);
        save();

        reload();
    }

    // adds each new added item to the table.
    private void constructTable() {
        scrollPane = new JScrollPane();
        rowData = new Vector<>(cat.catalogue.size());


        for (int i = 0; i < cat.catalogue.size(); i++) {
            vect = new Vector<>();
            String title = cat.catalogue.get(i).getTitle();
            String author = cat.catalogue.get(i).getAuthor();
            String genre = cat.catalogue.get(i).getGenre().toString();
            String status = Boolean.toString(cat.catalogue.get(i).getStatus());
            String lastTransaction = cat.catalogue.get(i).getLastTransaction().toString();


            vect.add(title);
            vect.add(author);
            vect.add(genre);
            vect.add(status);
            vect.add(lastTransaction);
            rowData.add(i, vect);
        }
        table.setBounds(116, 181, 1, 1);
        contentPane.add(table);
        scrollPane.setBounds(20, 149, 763, 241);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(table);
    }

    // instantiates a reload jbutton
    private void reload() {
        reload = new JButton("Reload");
        reload.setBounds(503, 457, 135, 23);
        contentPane.add(reload);
        reload.addMouseListener(new MouseAdapter() {
            @Override
            //mouse event for reload button that reloads the data on the text file
            public void mouseClicked(MouseEvent e) {
                loadItems();
                for (int i = 0; i < cat.catalogue.size(); i++) {
                    Vector<String> b = new Vector();
                    b.add(cat.catalogue.get(i).getTitle());
                    b.add(cat.catalogue.get(i).getAuthor());
                    b.add(cat.catalogue.get(i).getGenre().toString());
                    b.add(Boolean.toString(cat.catalogue.get(i).getStatus()));
                    b.add(cat.catalogue.get(i).getLastTransaction().toString());
                    b.add(cat.catalogue.get(i).getType());
                    model.addRow(b);
                }
                table.revalidate();
            }
        }
        );
        playSound();


    }
    //Adds the sound played when jbutton reload is clicked

    private void playSound() {
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InputStream in;
                try {

                    in = new FileInputStream(new File("src/button1.wav"));
                    AudioStream audios = new AudioStream(in);
                    AudioPlayer.player.start(audios);
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(null, f);
                }
            }
        });
    }
//Saves items in the catalogue to text file

    private void save() {

        btnSave = new JButton("Save");
        btnSave.setBounds(648, 457, 135, 23);
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    Writer writer = new Writer(new File(CATALOGUE_FILE));
                    for (int i = 0; i < cat.catalogue.size(); i++) {
                        writer.write(cat.catalogue.get(i));
                    }
                    writer.close();
                } catch (FileNotFoundException x) {
                    //if file not found
                } catch (UnsupportedEncodingException x) {
                    x.printStackTrace();
                    // this is due to a programming error
                }
            }
        });
        contentPane.add(btnSave);


    }

//Search functionality when enter is pressed works on the search bar, clicking search jbutton also performs search

    public static LibraryCatalogue getInstanceOf() {
        if (cat == null) {
            cat = new LibraryCatalogue();
        }
        return cat;
    }


    //Add book button
    private void addBooks() {
        addBook = new JButton("Add Book");
        getRootPane().setDefaultButton(addBook);
        addBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddItemPopUp popUp = new AddItemPopUp();
                popUp.setVisible(true);
            }
        });

        keyList();

        addBook.setBounds(20, 11, 135, 23);
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        contentPane.setLayout(null);
        contentPane.add(addBook);

    }
// Button activated by pressing n key

    private void keyList() {
        addBook.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char x = e.getKeyChar();
                if (x == 'n') {
                    AddItemPopUp popUp = new AddItemPopUp();
                    popUp.setVisible(true);

                }
            }
        });
    }


    //loads items from catalogue.
    public void loadItems() {
        try {
            items = Reader.readItems(new File(CATALOGUE_FILE));

            for (int i = 0; i < items.size(); i++) {
                cat.catalogue.add(items.get(i));
            }
        } catch (IOException z) {
            JOptionPane.showMessageDialog(null, z);
        }
    }
}
