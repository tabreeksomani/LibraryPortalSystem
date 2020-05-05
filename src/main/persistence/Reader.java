package persistence;

import model.Book;
import model.Genre;
import model.Item;
import model.Magazine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read items data from a file
public class Reader {
    public static final String DELIMITER = ",";
    private static Reader reader = new Reader();

    // EFFECTS: returns a list of items parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Item> readItems(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }


    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of items parsed from list of strings
    // where each string contains data for one item
    private static List<Item> parseContent(List<String> fileContent) {
        List<Item> items = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            items.add(parseItem(lineComponents));
        }

        return items;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 5 where element 0 represents the
    // title of the item to be constructed, element 1 represents
    // the author, elements 2 represents the genre and element 3 represents
    // the status of the item to be constructed, and element 5 represents localdate
    // when the item was created
    // EFFECTS: returns an item constructed from components
    private static Item parseItem(List<String> components) {
        String title = components.get(0);
        String author = components.get(1);
        String genreS = (components.get(2));
        Genre genre = assignGenre(genreS);


        boolean status = Boolean.parseBoolean(components.get(3));
        LocalDate lastTransaction = LocalDate.parse(components.get(4));
        String type = components.get(5);
        if (type.equals("book")) {
            return new Book(title, author, genre, status, lastTransaction, type);
        } else {
            return new Magazine(title, author, genre, true, lastTransaction, type);
        }
    }

    public static Genre assignGenre(String genreS) {
        if (genreS.equals("Fantasy")) {
            return Genre.Fantasy;
        } else if (genreS.equals("Non_Fiction")) {
            return Genre.Non_fiction;
        } else if (genreS.equals("Science_Fiction")) {
            return Genre.Science_Fiction;
        } else if (genreS.equals("Historical_Fiction")) {
            return Genre.Historical_Fiction;
        } else if (genreS.equals("Encyclopedia")) {
            return Genre.Encyclopedia;
        }
        return Genre.Fantasy;
    }
}



