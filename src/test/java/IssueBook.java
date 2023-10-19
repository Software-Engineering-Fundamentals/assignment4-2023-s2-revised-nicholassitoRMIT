
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
    @Test
    @DisplayName("Should return failure if actual number of books is different from expected value")
    void bookNumberTest() {
        int expected = 4;
        int actual = 0;

        ArrayList<Book> testBookList = new ArrayList<Book>();

        Book book1 = new Book(1, "book1", 0);
        Book book2 = new Book(2, "book2", 0);
        Book book3 = new Book(3, "book3", 1);
        Book book4 = new Book(4, "book4", 1);

        testBookList.add(book1);
        testBookList.add(book2);
        testBookList.add(book3);
        testBookList.add(book4);

        // To get actual number of books
        for (int i = 0; i < testBookList.size(); i++) {
            actual++;
        }

        assertEquals(expected, actual, "List should not be more than 4 items in size");
    }

    @Test
    @DisplayName("Should return custom exception if same book is issued")
    void exceptionTest() {
        Book book1 = new Book(1, "book1", 0);
        Book book2 = new Book(2, "book1", 0);

        try {
            if (book1.getTitle().equalsIgnoreCase(book2.getTitle())) {
                throw new IllegalBookIssueException("Issue Error: The book has already been issued on the library card.");
            }
        } catch (IllegalBookIssueException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Should return failure if library card is not valid")
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
}
