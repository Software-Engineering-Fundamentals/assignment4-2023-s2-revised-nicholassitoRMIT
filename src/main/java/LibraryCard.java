
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	   this.ExpiryDate = ExpiryDate;
	   this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    

    /**
     * Issue a new book
     * @param Book: book to borrow 
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book){
        int i = 0;
        List<Book> booklist = getBooks();
        int numBorrowed = 0;
        // booleans initialized to false
        boolean sameBook = false; 
        boolean borrowStatus = false;
        boolean pendingFine = false;
        double fine = getFine();
        int demand = book.getDemand();

        // To iterate along the book list and throw exception if same book
        while (booklist.size() > i) {
            try {
                if(booklist.get(i).getID() == book.getID()) {
                    sameBook = true;
                    throw new IllegalBookIssueException("Issue Error: The book has already been issued on the library card.");
                } 
            } catch (IllegalBookIssueException exception) {
                System.out.println(exception);
            }

            numBorrowed++;
            i++;
        }

        // Returns false if same book is present
        if (sameBook == true) {
            return false;
        }

        // Number of books should not be greater than 4
        if (numBorrowed > 4) {
            return false;
        }

        // Check if library card is still valid
        Date expiryDate = getExpiryDate();
        Date currentDate = new Date();
        boolean cardValidity = false;

        if (expiryDate.compareTo(currentDate) > 0) { // If expiry date is after current date, it is valid; Otherwise, it is invalid
            cardValidity = true;
        }

        // Returns false if card is not valid
        if (cardValidity == false) {
            return false;
        }

        // Check for borrow status
        if (book.getStatus()) { // book is available
            borrowStatus = true;
        }

        // Returns false if book is not available for borrowing
        if (borrowStatus == false) {
            return false;
        }

        // Check for status of fine
        if (fine > 0) {
            pendingFine = true;
        }

        // Returns false if there is a pending fine
        if (pendingFine) {
            return false;
        }

        
        
        
        // if none of the booleans give a false return value/i.e. all valid, return true to issue book
    	if (sameBook == false && cardValidity == true && borrowStatus == true && pendingFine == false) {
            // Setting days issued; only works if book can be issued
            if (demand == 0) {
                book.setDays(15);
            } else {
                book.setDays(3);
            } 
        }

        return true;
    }

    


}
