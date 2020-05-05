package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testItem.txt";
    private Writer testWriter;
    LibraryCatalogue libraryCat;
    Item a;
    Item b;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        libraryCat = new LibraryCatalogue();
        a = new Book("Walk Two Moons", "Sharon Creech", Genre.Fantasy, true, LocalDate.now(), "book");
        b = new Book("Love That Dog", "Sharon Creech", Genre.Science_Fiction, true, LocalDate.now(), "book");

        libraryCat.catalogue.add(a);
        libraryCat.catalogue.add(b);


    }

    @Test
    void testWriteItems() {
        testWriter.write(a);
        testWriter.write(b);
        testWriter.close();

        // now read them back in and verify that the items have the expected values
        try {
            List<Item> items = Reader.readItems(new File(TEST_FILE));
            Item a = items.get(0);
            assertEquals("Walk Two Moons", a.getTitle());
            assertEquals("Sharon Creech", a.getAuthor());
            assertEquals(true, a.getStatus());
            assertEquals(Genre.Fantasy, a.getGenre());
            assertEquals(LocalDate.now(), a.getLastTransaction());

            Item b = items.get(1);
            assertEquals("Love That Dog", b.getTitle());
            assertEquals("Sharon Creech", b.getAuthor());
            assertEquals(true, b.getStatus());
            assertEquals(Genre.Science_Fiction, b.getGenre());
            assertEquals(LocalDate.now(), b.getLastTransaction());


        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
