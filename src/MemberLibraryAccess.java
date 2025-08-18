public interface MemberLibraryAccess {

    public Book borrowBook(String bookTitle, Long memberId);

    void returnBook(Book book);

    boolean isBookNameAvailable(String bookTitle);

}
