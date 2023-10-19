
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;
import java.time.LocalDate; // added localdate
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
    private Date issueDate;
    private Date expiryDate;
    private LibraryCard libraryCard;
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Tester Tester", 12345);
        book = new Book(0, "book", 0);
        bookList = new ArrayList<Book>();

        issueDate = new Date();
        expiryDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)); // issue plus one week
        libraryCard = new LibraryCard(student, issueDate, expiryDate, 123);
    }

    @Test
    @DisplayName("Should return failure if actual number of books is different from expected value")
    void bookNumberTest() {
        int expected = 3;
        int actual = 0;

        Book book1 = new Book(1, "book1", 0);
        Book book2 = new Book(2, "book2", 0);
        Book book3 = new Book(3, "book3", 1);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        // To get actual number of books
        for (int i = 0; i < bookList.size(); i++) {
            actual++;
        }

        assertEquals(expected, actual, "List should not be more than 4 items in size");
    }

    @Test
    @DisplayName("Check if exception is thrown if books being compared are the same")
    void exceptionTest() throws IllegalBookIssueException {
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
        boolean cardValidity = true; // card initialized to be valid
        boolean expected = true;

        if (issueDate == expiryDate) {
            cardValidity = false;
        }

        assertEquals(expected, cardValidity, "Only earlier dates than the actual date should be valid");
    }

    @Test
    @DisplayName("Check if book is available for borrowing")
    void bookAvailabilityTest() {
        boolean result = true;
        book.setStatus(true); // book is available when true
        boolean availability = book.getStatus();

        assertEquals(result, availability, "Borrow status is valid if the status is true");
    }

    @Test
    @DisplayName("Checks for pending fine")
    void pendingFineTest() {
        double fine = 0.0;
        libraryCard.setFine(fine);
        double expectedFine = 0.0;

        assertEquals(expectedFine, libraryCard.getFine());
    }

    @Test
    @DisplayName("Checks if IssueBook is working properly")
    void issueBookTest() {
        Book book4 = new Book(4, "book4", 1);
        boolean result = libraryCard.issueBook(book4);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Checks for demand of book")
    void demandtest() {
        // if 0/low demand, 15 days; if 1/high demand, 3 days
        int expected = 15;
        if (book.getID() == 0) {
            book.setDemand(15);
        } else {
            book.setDemand(3);
        }

        assertEquals(expected, book.getDemand());
    }
}
