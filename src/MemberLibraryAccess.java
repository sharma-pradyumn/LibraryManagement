public interface MemberLibraryAccess {

    public boolean borrowBook(String bookTitle);

    public boolean returnBook(Book book);

    public boolean isBookNameAvailable(String bookTitle);

}
