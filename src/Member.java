import java.util.List;

public class Member extends Person {
    private Library library;

    public Member(String n, String e) {
        super(n, e);
    }

    public void joinLibrary(Library l) {
        this.library = l;
        l.joinLibrary(this);
    }

    public void leaveLibrary() {
        if (library != null) {
            library.removeLibrary(this);
            library = null;
        }
    }

    public Book borrowBook(String title) {
        if (library == null) {
            throw new IllegalStateException("Member is not part of any library");
        }
        return library.borrowBook(title, getId()); // assume Person has getId()
    }

    public void returnBook(Book book) {
        if (library == null) {
            throw new IllegalStateException("Member is not part of any library");
        }
        library.returnBook(book);
    }

    public List<BookIssueRecord> getBorrowedBooks() {
        if (library == null) {
            return List.of();
        }
        return library.getCurrentlyIssuedBooks(getId());
    }

    public void reserveBook(String bookName) {
        if (library == null) {
            throw new IllegalStateException("Member is not part of any library");
        }
        library.addReservation(bookName, this);
    }
}
