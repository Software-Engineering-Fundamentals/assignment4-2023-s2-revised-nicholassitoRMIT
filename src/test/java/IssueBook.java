
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;
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
	@BeforeEach
    public void setUp() {
        // adding 4 books to the book list, should be valid
        ArrayList<Book> testBookList = new ArrayList<Book>();

        Book book1 = new Book(1, "book1", 0);
        Book book2 = new Book(2, "book2", 0);
        Book book3 = new Book(3, "book3", 1);
        Book book4 = new Book(4, "book4", 1);

        testBookList.add(book1);
        testBookList.add(book2);
        testBookList.add(book3);
        testBookList.add(book4);

        for (int i = 0; i < 4; i++) {
            System.out.println(testBookList.get(i).getTitle());
        }
    }
	
    @Test
    @DisplayName("Test for book number validity")
    public void bookNumberTest() {
        System.out.println("test");
    }
}
