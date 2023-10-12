package business.controllers;

import business.*;
import business.Group2Exception;
import business.interfaces.BookInterface;
import dataaccess.DataAccessFacade;
import dataaccess.DataAccess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BookController implements BookInterface {

    @Override
    public List<Book> getAllBook() {
        DataAccess da = new DataAccessFacade();
        return da.readBooksMap().values().stream().toList();
    }

    @Override
    public Book getBookById(String isbn) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> map = da.readBooksMap();
        Book book = map.get(isbn);
        if (book == null) {
            throw new Group2Exception("This ISBN does not exist in the system");
        }
        return book;
    }

    @Override
    public Book addBook(String ISBN, String title, int maxCheckoutLength, List<Author> authors, List<BookCopy> copies) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> map = da.readBooksMap();
        if (map.containsKey(ISBN)) {
            throw new Group2Exception("This ISBN existed in the system");
        }
        Book book = new Book(ISBN,title,maxCheckoutLength,authors, copies.toArray(new BookCopy[0]));
        map.put(ISBN,book);
        da.updateBook(book);
        return book;
    }

    @Override
    public BookCopy addBookCopy(Book book) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> map = da.readBooksMap();
        if (!map.containsKey(book.getIsbn())) {
            throw new Group2Exception("This ISBN does not exist in the system");
        }
        BookCopy bookCopy = book.addCopy();
        da.updateBook(book);
        return bookCopy;
    }

    @Override
    public void updateBookCopyStatus(Book book, BookCopy bookCopy, boolean newStatus) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> map = da.readBooksMap();
        if (!map.containsKey(book.getIsbn())) {
            throw new Group2Exception("This ISBN does not exist in the system");
        }
        Book refBook = map.get(book.getIsbn());
        BookCopy refBookCopy = refBook.getCopies()[bookCopy.getCopyNum()];
        refBookCopy.setAvailable(newStatus);

        da.updateBook(refBook);
    }

    @Override
    public Book checkout(String memberID, String isbn) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> memberMap = da.readMemberMap();
        if (!memberMap.containsKey(memberID)) {
            throw new Group2Exception("MemberId not found");
        }


        HashMap<String, Book> bookHashMap = da.readBooksMap();
        if (!bookHashMap.containsKey(isbn)) {
            throw new Group2Exception("Book not found");
        }
        Book book = bookHashMap.get(isbn);
        if (!book.isAvailable()) {
            throw new Group2Exception("Book copy not available");
        }
        BookCopy copy = book.getNextAvailableCopy();
        LibraryMember member = memberMap.get(memberID);

        if (member.getCheckoutRecord() == null) {
            member.setCheckoutRecord(new CheckoutRecord());
        }

        copy.changeAvailability();
        member.getCheckoutRecord()
                .addRecordEntry(new CheckoutRecordEntry(copy));
        da.saveNewRecord(member.getCheckoutRecord());
        da.updateBook(book);
        da.saveNewMember(member);
        return book;
    }

    @Override
    public Book addBookCopyByISBN(String isbn) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> map = da.readBooksMap();
        Book book = map.get(isbn);
        if (book == null) {
            throw new Group2Exception("This ISBN does not exist in the system");
        }
        book.addCopy();
        da.updateBook(book);
        return book;
    }

    @Override
    public HashMap<BookCopy, LibraryMember> find(String isbn) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> map = da.readBooksMap();
        if (!map.containsKey(isbn)) {
            throw new Group2Exception("This ISBN does not exist in the system");
        }

        HashMap<BookCopy,LibraryMember> bookCopyLibraryMemberHashMap = new HashMap<>();
        Book book = map.get(isbn);
        List<BookCopy> bookCopyList = Arrays.asList(book.getCopies());

        HashMap<String, LibraryMember> memberMap = da.readMemberMap();
        for (LibraryMember member: memberMap.values()) {
            List<CheckoutRecordEntry> entries = member.getCheckoutRecord().getRecordEntries();
            for (int i = 0; i < entries.size(); i++) {
                BookCopy copy = entries.get(i).getBookCopy();
                if (bookCopyList.contains(copy)) {
                    bookCopyLibraryMemberHashMap.put(copy,member);
                }
            }
        }
        if (bookCopyLibraryMemberHashMap.isEmpty()) {
            bookCopyLibraryMemberHashMap.put(bookCopyList.get(0),null);
        }
        return bookCopyLibraryMemberHashMap;
    }
}
