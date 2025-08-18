import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- Setup library and admin ---
        Library myLibrary = new Library();
        Admin admin = new Admin("Alice", "alice@example.com");
        admin.joinLibrary(myLibrary);

        // --- Admin adds books ---
        Book book1 = new Book("Java Programming", "ISBN001", "Author A", "Programming");
        Book book2 = new Book("Python Programming", "ISBN002", "Author B", "Programming");
        Book book3 = new Book("Java Programming", "ISBN003", "Author A", "Programming"); // second copy

        admin.addBook(book1);
        admin.addBook(book2);
        admin.addBook(book3);

        System.out.println("\nBooks added to library.\n");

        // --- Members join library ---
        Member member1 = new Member("Bob", "bob@example.com");
        Member member2 = new Member("Charlie", "charlie@example.com");
        Member member3 = new Member("Diana", "diana@example.com");

        member1.joinLibrary(myLibrary);
        member2.joinLibrary(myLibrary);
        member3.joinLibrary(myLibrary);

        System.out.println("\nMembers joined the library.\n");

        // --- Member 1 borrows a book ---
        Book borrowed1 = member1.borrowBook("Java Programming");
        System.out.println(member1.getName() + " borrowed: " + borrowed1.getName());

        // --- Member 2 borrows the same book (gets the second copy) ---
        Book borrowed2 = member2.borrowBook("Java Programming");
        System.out.println(member2.getName() + " borrowed: " + borrowed2.getName());

        // --- Member 3 tries to borrow "Java Programming" (all copies borrowed) ---
        Book borrowed3 = member3.borrowBook("Java Programming");
        if (borrowed3 == null) {
            System.out.println(member3.getName() + " could not borrow Java Programming (all copies issued).");
        }

        // --- Member 3 reserves "Java Programming" ---
        member3.reserveBook("Java Programming");
        System.out.println(member3.getName() + " reserved Java Programming.\n");

        // --- Member 1 returns their book ---
        member1.returnBook(borrowed1);
        System.out.println(member1.getName() + " returned: " + borrowed1.getName() + "\n");

        // --- Check borrowed books for each member ---
        System.out.println("Current borrowed books:");
        for (Member m : List.of(member1, member2, member3)) {
            List<BookIssueRecord> books = m.getBorrowedBooks();
            if (books == null || books.isEmpty()) {
                System.out.println(m.getName() + " has borrowed: None");
            } else {
                System.out.println(m.getName() + " has borrowed: " +
                        books.stream()
                                .map(record -> record.getBookName())
                                .toList());
            }
        }
    }
}
