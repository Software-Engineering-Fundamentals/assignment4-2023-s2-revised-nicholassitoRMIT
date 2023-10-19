
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate; // changed to localdate
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
// importing array list function 
import java.util.ArrayList;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {
    private Book book;
    private ArrayList<Book> bookList; 

    @BeforeEach
    void setUp() {
        book = new Book(0, "book", 0);
        bookList = new ArrayList<Book>();
    }

    @Test
    @DisplayName("Should return failure if actual number of books is different from expected value")
    void bookNumberTest() {
        int expected = 4;
        int actual = 0;

        Book book1 = new Book(1, "book1", 0);
        Book book2 = new Book(2, "book2", 0);
        Book book3 = new Book(3, "book3", 1);
        Book book4 = new Book(4, "book4", 1);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);

        // To get actual number of books
        for (int i = 0; i < bookList.size(); i++) {
            actual++;
        }

        assertEquals(expected, actual, "List should not be more than 4 items in size");
    }

    @Test
    @DisplayName("Check if exception is thrown if books being compared are the same")
    void exceptionTest() {
        Book book1 = new Book(1, "book1", 0);
        Book book2 = new Book(1, "book1", 0);

        try {
            if (book1.getID() == book2.getID()) {
                throw new IllegalBookIssueException("Issue Error: The book has already been issued on the library card.");
            }
        } catch (IllegalBookIssueException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Check if library card is valid")
    void libraryCardValidityTest() {
        LocalDate currentDate = LocalDate.now();
        LocalDate cardDate = currentDate.minusDays(1);
        boolean cardValidity = true; // card is assumed to be true at the start

        // Card should be invalid if date is the same
        if ((currentDate.compareTo(cardDate) == 0) || (currentDate.compareTo(cardDate) > 0)){
            cardValidity = false;
        }

        assertEquals((currentDate.equals(cardDate)), cardValidity, "Only earlier dates than the actual date should be valid");
    }

    @Test
    @DisplayName("Check if book is available for borrowing")
    void bookAvailabilityTest() {
        boolean result = true;
        book.setStatus(true); // book is available when true
        boolean availability = book.getStatus();

        assertEquals(result, availability, "Borrow status is valid if the status is true");
    }

    
}
