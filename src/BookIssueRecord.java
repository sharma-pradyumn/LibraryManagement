import java.time.LocalDate;

public class BookIssueRecord {
    private static final int BORROW_PERIOD_DAYS = 10;
    private final LocalDate issuedDate;
    private final LocalDate dueDate;
    private final long bookId;
    private final long memberId;
    private final String bookName; // <-- added


    public BookIssueRecord(LocalDate _issueDate, long _bookId, long _memberId, String _bookName) {
        issuedDate = _issueDate;
        bookId = _bookId;
        memberId = _memberId;
        bookName = _bookName;
        dueDate = issuedDate.plusDays(BORROW_PERIOD_DAYS);
    }

    public long getBookId() {
        return bookId;
    }

    public long getMemberId() {
        return memberId;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
