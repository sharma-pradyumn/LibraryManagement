public class Book {
    private static long nextBookId = 0;
    private final long bookId;
    private final String Name, ISBN, author, subject;

    public Book(String n, String i, String a, String s) {
        Name = n;
        ISBN = i;
        author = a;
        subject = s;
        bookId = nextBookId;
        nextBookId++;
    }

    public String getName() {
        return Name;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public long getBookId() {
        return bookId;
    }
}
