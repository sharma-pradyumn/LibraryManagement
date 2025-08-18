import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Library implements MemberLibraryAccess {
    private HashMap<Long, Book> bookMap; // BookId to Book
    private HashMap<Long, ArrayList<BookIssueRecord>> currentlyIssuedMemberMap; // memberId to list of BookIssueRecord
    private HashMap<Long, LinkedList<Member>> reservedBookMemberMap; // bookId to Queue of members who reserved it

    public boolean borrowBook(String bookTitle) {
        return false;
    }

    public boolean returnBook(Book book) {
        return false;
    }

    public boolean isBookNameAvailable(String bookTitle) {
        return false;
    }

}

