import java.time.LocalDate;

public class BookIssueRecord {
    private static final int BORROW_PERIOD_DAYS = 10;
    private final LocalDate issuedDate;
    private final LocalDate dueDate;
    private final long bookId;
    private final long memberId;

    public BookIssueRecord(LocalDate _issueDate, long _bookId, long _memberId) {
        issuedDate = _issueDate;
        bookId = _bookId;
        memberId = _memberId;
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

    public LocalDate getDueDate() {
        return dueDate;
    }
}
