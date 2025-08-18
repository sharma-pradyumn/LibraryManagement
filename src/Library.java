import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Library implements MemberLibraryAccess {
    private final HashMap<Long, Book> bookMap = new HashMap<>(); // BookId to Book
    private final HashMap<String, ArrayList<Long>> bookNameMap = new HashMap<>(); // bookName to bookIds

    private final HashMap<Long, ArrayList<BookIssueRecord>> currentlyIssuedMemberMap = new HashMap<>(); // memberId to list of BookIssueRecord
    private final HashMap<String, LinkedList<Member>> reservedBookMemberMap = new HashMap<>(); // bookId to Queue of members who reserved it

    private final HashMap<Long, BookIssueRecord> issuedBookMap = new HashMap<>(); // bookId → current issue record

    private final HashMap<Long, Member> memberMap = new HashMap<>(); // memberId -> member for all members of the Lib

    public Book borrowBook(String bookTitle, Long memberId) {

        // check if member is associated with this library
        if (isMemberNotInLibrary(memberId)) {
            throw new IllegalArgumentException("Cannot borrow book, " + memberId + " not a member of library");
        }
        ArrayList<Long> bookIds = bookNameMap.get(bookTitle);
        if (bookIds == null) {
            System.out.println("No copies found for " + bookTitle);
            return null;
        }
        for (Long bookId : bookIds) {
            if (issuedBookMap.get(bookId) == null) {
                BookIssueRecord issueRecord = new BookIssueRecord(LocalDate.now(), bookId, memberId);
                currentlyIssuedMemberMap.computeIfAbsent(memberId, k -> new ArrayList<>()).add(issueRecord);
                issuedBookMap.put(bookId, issueRecord);
                return bookMap.get(bookId);
            }
        }
        return null;
    }

    public void addReservation(String bookName, Member member) {

        // check if member is associated with this library
        if (isMemberNotInLibrary(member.getId())) {
            throw new IllegalArgumentException("Cannot reserve book, " + member.getId() + " not a member of library");
        }

        if (isBookNameAvailable(bookName)) {
            System.out.println("Book " + bookName + " is available for borrowing");
            return;
            // should we automatically borrow book here
        }
        LinkedList<Member> queue = reservedBookMemberMap.computeIfAbsent(bookName, k -> new LinkedList<>());
        if (queue.stream().noneMatch(m -> m.getId() == member.getId())) {
            queue.add(member);
        } else {
            System.out.println("Member already added for reservation");
        }
    }

    public void returnBook(Book book) {
        long returnedBookId = book.getBookId();
        BookIssueRecord issuedRecord = issuedBookMap.get(returnedBookId);

        if (issuedRecord == null) {
            System.out.println("Returning " + book + " was not borrowed");
            return;
        }
        issuedBookMap.remove(returnedBookId);
        long borrowedMember = issuedRecord.getMemberId();

        ArrayList<BookIssueRecord> memberIssuedrecord = currentlyIssuedMemberMap.get(borrowedMember);

        if (memberIssuedrecord == null) {
            System.out.println("Borrowed book " + book + " was not found in Member " + borrowedMember);
            return;
        }
        memberIssuedrecord.removeIf(record -> record.getBookId() == returnedBookId);

        LinkedList<Member> reservedMembers = reservedBookMemberMap.get(book.getName());
        if (reservedMembers != null) {
            Member nextInQueue = null;
            while (!reservedMembers.isEmpty()) {
                nextInQueue = reservedMembers.pop();
                if (isMemberNotInLibrary(nextInQueue.getId())) {
                    System.out.println("Skipping " + nextInQueue + " as left library");
                } else {
                    break;
                }
            }

            if (nextInQueue == null) {
                return;
            }
            Book allocated = borrowBook(book.getName(), nextInQueue.getId());
            if (allocated != null) {
                System.out.println("Book " + book.getName() + " automatically issued to " + nextInQueue.getId());
            } else {
                // put them back if borrow failed
                reservedMembers.addFirst(nextInQueue);
            }
        }
    }

    public void joinLibrary(Member member) {
        if (memberMap.get(member.getId()) != null) {
            System.out.println("Member " + member + " already joined the library");
            return;
        }
        memberMap.put(member.getId(), member);
    }

    public void removeLibrary(Member member) {
        ArrayList<BookIssueRecord> issuedBooks = currentlyIssuedMemberMap.get(member.getId());
        if (issuedBooks != null && !issuedBooks.isEmpty()) {
            throw new IllegalArgumentException("Member cannot leave, as they have issued books " + issuedBooks);
        }
        memberMap.remove(member.getId());
    }

    public boolean isMemberNotInLibrary(Long memberId) {
        return memberMap.get(memberId) == null;
    }

    public boolean isBookNameAvailable(String bookTitle) {
        ArrayList<Long> bookIds = bookNameMap.get(bookTitle);
        if (bookIds == null) {
            System.out.println("No copies found for " + bookTitle);
            return false;
        }
        for (Long bookId : bookIds) {
            if (issuedBookMap.get(bookId) == null) {
                return true;
            }
        }
        return false;
    }

    public void addBook(Book book) {
        long bookId = book.getBookId();
        String bookName = book.getName();

        if (bookMap.containsKey(bookId)) {
            throw new IllegalArgumentException("Book with ID " + bookId + " already exists in the library.");
        }

        // put book into main bookMap
        bookMap.put(bookId, book);

        // put bookId into the list of IDs for this bookName
        bookNameMap
                .computeIfAbsent(bookName, k -> new ArrayList<>())
                .add(bookId);
    }

    public List<BookIssueRecord> getCurrentlyIssuedBooks(Long memberId) {
        return currentlyIssuedMemberMap.get(memberId);
    }
}

