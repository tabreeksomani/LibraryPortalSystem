package model;

import gui.Menu;

import java.time.LocalDate;

public class Book extends Item {
    LibraryCatalogue libraryCatalogue = Menu.getInstanceOf();

    public Book(String title, String author, Genre genre, boolean status, LocalDate lastTransaction, String type) {
        super(title, author, genre, status, lastTransaction, type);
    }


    @Override
    public String getItemInfo() {

        return super.getItemInfo() + "\r\n Item Type: " + this.getType() + "\r\n Available: " + this.getStatus()
              +  "\r\n Issuable: Yes";
    }


    @Override
    public void issueItem() {

        libraryCatalogue.checkOut(this.getTitle());


    }
}
