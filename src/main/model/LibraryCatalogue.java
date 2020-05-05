package model;

import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;


//Represents a library catalogue that holds an array list of item called catalogue
// and an array list of checked out books.
public class LibraryCatalogue {
    public ArrayList<Item> catalogue;
    public static ArrayList<String> checkedOutBooks;


    //Effects: Creates an empty catalogue
    public LibraryCatalogue() {
        catalogue = new ArrayList<Item>();
    }

    public ArrayList getFullList() {
        return catalogue;
    }

    //Modifies: catalogue
    //Effects: Add item as the last item on the catalogue.
    public void addItem(Item i) {
        catalogue.add(i);
    }

    //Requires: Catalogue must contain item with the title, and the item must be checked out.
    //Modifies: this
    // Effects: Change status of item and last transaction date in catalogue.
    public String checkOut(String title) {
        for (Item item : catalogue) {
            if ((item.getTitle().equals(title))) {
                if (!item.getStatus()) {
                    return "Item is not available.";
                } else {
                    item.setStatus(false);
                    item.setLastTransaction(java.time.LocalDate.now());
                    return item.getTitle() + " has been checked out successfully.";
                }
            }
        }
        return "item not found";
    }

    //Requires: Catalogue must contain item with the title, and the item must be available (Status).
    //Modifies: this
    // Effects: Updates status of item and last transaction date in catalogue.
    public String checkIn(String title) {
        int i = 0;
        for (Item item : catalogue) {
            if ((item.getTitle().equals(title))) {
                item.setStatus(true);
                item.setLastTransaction(java.time.LocalDate.now());
                return "Checked in successfully.";
            }
            i++;
        }
        return "Item not found.";
    }

    //Requires: item must exist in catalogue and must be checked out.
    //Modifies: this
    //Effects: Updates last transaction date
    public String renew(String title) {
        int i = 0;
        for (Item item : catalogue) {
            if ((item.getTitle().equals(title))) {
                if (item.getStatus()) {
                    return "Item must be checked out first.";
                } else {
                    item.setLastTransaction(java.time.LocalDate.now());
                    return "Renewal Successful.";
                }
            }
            i++;
        }
        return "Item not found.";
    }

    public Item returnItemSearch(String title, String type) {
        Item s = null;
        for (Item item : catalogue) {
            if ((item.getTitle().equals(title))) {
                if (item.getType().equals(type)) {
                    s = item;
                }
            }

        }
        return s;

    }

    //Effects: Determines if an item exists in a catalogue.
    public String search(String title) {
        for (Item item : catalogue) {
            if ((item.getTitle().equals(title))) {
                if (item.getStatus()) {
                    return "Available: " + item.getTitle() + " by " + item.getAuthor() + ": " + item.getGenre();
                } else {
                    return "Not Available: " + item.getTitle() + " by " + item.getAuthor() + ": " + item.getGenre();
                }
            }
        }
        return "Item not found";
    }

    //Effects: Produces list of checked out items using each item's status in a for-loop
    public ArrayList getCheckOutList() {
        checkedOutBooks = new ArrayList<String>();

        int i = 0;
        for (Item item : catalogue) {
            if (!item.getStatus()) {
                checkedOutBooks.add(item.getTitle());
            }
            i++;
        }
        return checkedOutBooks;
    }


}





