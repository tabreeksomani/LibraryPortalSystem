package ui;

import model.*;
import persistence.Reader;
import persistence.Writer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Library Portal Application
public class LibraryPortal {
    // MODIFIES: this
    // EFFECTS: initializes library catalogue
    private static final String CATALOGUE_FILE = "./data/catalogue.txt";
    private Scanner input = new Scanner(System.in);
    private LibraryCatalogue libCal;


    //Effects: Runs the library Portal
    public LibraryPortal(LibraryCatalogue libCal) {
        this.libCal = libCal;
        runLibraryPortal();


    }

    private void init() {
        LibraryCatalogue lib = new LibraryCatalogue();

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLibraryPortal() {
        String command = null;

        loadItems();

        do {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();
            processCommand(command);
        } while (!command.equals("E"));
        saveItems();
    }

    // MODIFIES: this
    // EFFECTS: loads items from CATALOGUE_FILE, if that file exists;
    // otherwise initializes catalogue

    private void loadItems() {
        try {
            List<Item> items = Reader.readItems(new File(CATALOGUE_FILE));

            for (int i = 0; i < items.size(); i++) {
                init();
                libCal.catalogue.add(items.get(i));

            }

        } catch (IOException e) {
            init();

        }
    }

    // EFFECTS: saves state of library catalogue to CATALOGUE_FILE
    private void saveItems() {
        try {
            persistence.Writer writer = new Writer(new File(CATALOGUE_FILE));
            for (int i = 0; i < libCal.catalogue.size(); i++) {
                writer.write(libCal.catalogue.get(i));
                writer.close();
            }
            System.out.println("Items saved to file " + CATALOGUE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save items to " + CATALOGUE_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }


    // EFFECTS: displays menu of options to user

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tI -> check in item");
        System.out.println("\tO -> check out item");
        System.out.println("\tR -> renew item");
        System.out.println("\tA -> add new item to catalogue");
        System.out.println("\tS -> search catalogue");
        System.out.println("\tV -> view checked out list");
        System.out.println("\tE -> Save and exit");


    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("I")) {
            checkInItem();
        } else if (command.equals("O")) {
            checkOutItem();
        } else if (command.equals("R")) {
            renewItem();
        } else if (command.equals("A")) {
            addNewItem();
        } else if (command.equals("S")) {
            searchItem();
        } else if (command.equals("V")) {
            viewCheckOutItems();
        } else if (command.equals("E")) {
            System.out.println("Exit");
        } else {
            System.out.println("Selection not valid...");
        }

    }

    private void renewItem() {
        System.out.println("Enter Title");
        String title = input.next();
        title = title.toUpperCase();
        System.out.println(libCal.renew(title));
    }

    //Modifies: this
    //Effects: Adds new item to the catalogue.
    private void addNewItem() {
        System.out.println("Enter Title");
        String title = input.next();
        title = title.toUpperCase();

        System.out.println("Enter Author");
        String author = input.next();
        author = author.toUpperCase();

        System.out.println("Enter Genre:\n" + "    \tF -> Fantasy,\n" + "    \tN -> Non_fiction,\n"
                + "    \tS -> Science_Fiction,\n" + "    \tH -> Historical_Fiction,\n" + "    \tE -> Encyclopedia,");
        String genre = input.next();
        genre = genre.toUpperCase();

        System.out.println("Available?"
                + "\tT -> True" + "\tF -> False");
        String status = input.next();
        status = status.toUpperCase();

        String type = input.next();
        type = type.toUpperCase();
        Item item;

        if (type == "BOOK") {
            item = new Book(title, author, processGenre(genre), processStatus(status), LocalDate.now(), type);

        } else {
            item = new Magazine(title, author, processGenre(genre), processStatus(status), LocalDate.now(), type);

        }
        libCal.addItem(item);

    }
    // MODIFIES: this
    // EFFECTS: processes user status

    private boolean processStatus(String status) {
        if (status.equals("T")) {
            return true;
        } else {
            return false;
        }
    }
    // MODIFIES: this
    // EFFECTS: processes user genre

    private Genre processGenre(String genre) {
        if (genre.equals("F")) {
            return Genre.Fantasy;
        } else if (genre.equals("N")) {
            return Genre.Non_fiction;
        } else if (genre.equals("S")) {
            return Genre.Science_Fiction;
        } else if (genre.equals("H")) {
            return Genre.Historical_Fiction;
        } else if (genre.equals("E")) {
            return Genre.Encyclopedia;
        } else {
            System.out.println("Selection not valid...");
            return Genre.Fantasy;
        }
    }

    //Effects: Prints output from search
    private void searchItem() {
        System.out.println("Enter Title");
        String title = input.next();
        title = title.toUpperCase();
        System.out.println(libCal.search(title));
    }

    // MODIFIES: this
    // EFFECTS: conducts a check-in transaction

    private void checkInItem() {
        System.out.println("Enter Title");
        String title = input.next();
        title = title.toUpperCase();
        System.out.println(libCal.checkIn(title));
    }

    // MODIFIES: this
    // EFFECTS: conducts a check-out transaction

    private void checkOutItem() {
        System.out.println("Enter Title");
        String title = input.next();
        title = title.toUpperCase();

        System.out.println(libCal.checkOut(title));
    }

    //Effects: Prints the list of checked out books through iteration over the list.
    private void viewCheckOutItems() {
        ArrayList list = libCal.getCheckOutList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + "\n");
        }
    }
}



