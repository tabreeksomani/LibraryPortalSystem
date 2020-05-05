package model;

import gui.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

class TestForLibraryCatalogue {
    LibraryCatalogue cat;
    Item a;
    Item b;

    @BeforeEach
    void setup() {
        cat = new LibraryCatalogue();
        a = new Book("Walk Two Moons", "Sharon Creech", Genre.Fantasy, true, LocalDate.now(), "book");
        b = new Magazine("Love That Dog", "Sharon Creech", Genre.Fantasy, true, LocalDate.now(), "magazine");


        cat.addItem(a);

    }

    @Test
    void bookClassTest() throws IIssuableItem.ItemNotIssuableException {
        Menu.getInstanceOf().addItem(a);
        try {
            a.issueItem();
            assertFalse(a.getStatus());
        } catch (IIssuableItem.ItemNotIssuableException e) {
            fail("Should not have been thrown!");
        }
        assertEquals("Name: Walk Two Moons\r\n Author Name: Sharon Creech\r\n Item Type: book"
                + "\r\n Available: false\r\n Issuable: Yes", a.getItemInfo());

    }

    @Test
    void magazineClassTest() throws IIssuableItem.ItemNotIssuableException {
        try {
            b.issueItem();
            fail("Exception should have been thrown!");
        } catch (IIssuableItem.ItemNotIssuableException e) {
            //as expected
        }
        assertEquals("Name: Love That Dog\r\n Author Name: Sharon Creech\r\n Item Type: magazine"
                + "\r\n Issuable: No", b.getItemInfo());

    }


    @Test
    void testForAddItem() {
        assertEquals(1, cat.getFullList().size());
    }

    @Test
    void testForCheckOut() {
        cat.checkOut("Walk Two Moons");

        assertFalse(a.getStatus());
    }

    @Test
    void testForCheckInFailed() {
        assertEquals("Item not found.", cat.checkIn("Tomorrow"));
    }

    @Test
    void testForRenewNotFound() {
        assertEquals("Item not found.", cat.renew("Tomorrow"));
    }

    @Test
    void testForCheckIn() {
        a.setStatus(false);
        cat.checkIn("Walk Two Moons");

        assertTrue(a.getStatus());

    }

    @Test
    void testForReturnSearchItem() {
        assertEquals(a, cat.returnItemSearch("Walk Two Moons", "book"));
    }

    @Test
    void testForReturnSearchItemNotFound() {
        assertEquals(null, cat.returnItemSearch("a", "book"));
        assertEquals(null, cat.returnItemSearch("Walk Two Moons", "magazine"));

    }

    @Test
    void testForCheckOutItemNotExisting() {
        assertEquals("item not found", cat.checkOut("a"));
    }

    @Test
    void testForRenew() {
        cat.checkOut("Walk Two Moons");
        cat.renew("Walk Two Moons");

        assertEquals(LocalDate.now(), a.getLastTransaction());
    }

    @Test
    void testForSearchAvailable() {

        assertEquals("Available: Walk Two Moons by Sharon Creech: Fantasy", cat.search("Walk Two Moons"));

    }

    @Test
    void testForSearchNotAvailable() {
        cat.checkOut("Walk Two Moons");

        assertEquals("Not Available: Walk Two Moons by Sharon Creech: Fantasy", cat.search("Walk Two Moons"));

    }

    @Test
    void testForSearchNotFound() {
        assertEquals("Item not found", cat.search("Walk Moon"));
    }

    @Test
    void testForGetCheckedOutList() {
        cat.checkOut("Walk Two Moons");

        assertEquals(1, cat.getCheckOutList().size());
    }

    @Test
    void testForGetCheckedOutListEmpty() {


        assertEquals(0, cat.getCheckOutList().size());
    }

    @Test
    void testForItemNotAvailableToCheckOut() {
        cat.checkOut("Walk Two Moons");

        assertEquals("Item is not available.", cat.checkOut("Walk Two Moons"));
    }

    @Test
    void testForRenewCheckedInItem() {
        assertEquals("Item must be checked out first.", cat.renew("Walk Two Moons"));
    }


}