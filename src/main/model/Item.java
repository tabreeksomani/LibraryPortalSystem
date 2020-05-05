package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

//Represents an item having a title, author, genre, status, and last transaction date.

public abstract class Item implements Saveable, IIssuableItem {

    String title;
    String author;
    Genre genre;
    boolean status;
    LocalDate lastTransaction;
    String type;

    // EFFECTS: item has given title, author, genre, status, and current date.
    public Item(String title, String author, Genre genre, boolean status, LocalDate lastTransaction, String type) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.lastTransaction = lastTransaction;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getLastTransaction() {
        return lastTransaction;
    }

    public String getType() {
        return type;
    }

    public void setStatus(boolean available) {
        status = available;

    }

    public void setLastTransaction(LocalDate date) {
        lastTransaction = date;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(title);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(author);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(genre);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(status);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(lastTransaction);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(type);

    }


    public String getItemInfo() {
        return "Name: " + this.getTitle() + "\r\n Author Name: " + this.getAuthor();
    }

}

