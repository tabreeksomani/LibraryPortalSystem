package ui;

import model.LibraryCatalogue;

public class Main {
    private static LibraryCatalogue libCal;

    public static void main(String[] args) {
        new LibraryPortal(libCal);
    }

    public static void setLibCal(LibraryCatalogue libCal) {
        Main.libCal = libCal;
    }
}
