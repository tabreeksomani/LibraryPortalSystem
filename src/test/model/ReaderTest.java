package model;

import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    @Test
    void testParseItemsFile1() {
        try {
            List<Item> items = Reader.readItems(new File("./data/testItemFile1.txt"));
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

    @Test
    void testParseItemsFile2() {
        try {
            List<Item> items = Reader.readItems(new File("./data/testItemFile2.txt"));
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

            Item c = items.get(2);
            assertEquals(Genre.Historical_Fiction, c.getGenre());

            Item d = items.get(3);
            assertEquals(Genre.Non_fiction, d.getGenre());

            Item e = items.get(4);
            assertEquals(Genre.Encyclopedia, e.getGenre());

            Item f = items.get(5);
            assertEquals(Genre.Fantasy, f.getGenre());
        } catch (IOException e) {
          fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readItems(new File("./path/does/not/exist/testItem.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}