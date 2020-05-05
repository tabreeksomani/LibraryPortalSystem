package model;

import java.time.LocalDate;

public class Magazine extends Item {
    public Magazine(String title, String author, Genre genre, boolean status, LocalDate lastTransaction, String type) {
        super(title, author, genre, status, lastTransaction, type);
    }

    @Override
    public String getItemInfo() {
        return super.getItemInfo() + "\r\n Item Type: " + this.getType() + "\r\n Issuable: No";
    }


    @Override
    public void issueItem() throws ItemNotIssuableException {
        throw new ItemNotIssuableException();
    }
}






