import java.util.List;

public class Admin extends Person {
    private Library library;

    public Admin(String n, String e) {
        super(n, e);
    }

    public void joinLibrary(Library l) {
        this.library = l;
        l.addAdmin(this);
    }

    public void addBook(Book book) {
        if (library == null) {
            throw new IllegalStateException("Admin is not part of any library");
        }
        library.addBook(book);
    }
    
}
