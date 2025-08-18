import java.util.List;

public interface MemberLibraryAccess {

    Book borrowBook(String bookTitle, Long memberId);

    void returnBook(Book book);

    boolean isBookNameAvailable(String bookTitle);

    void addReservation(String bookName, Member member);

    void joinLibrary(Member member);

    void removeLibrary(Member member);

    List<BookIssueRecord> getCurrentlyIssuedBooks(Long memberId);
}
