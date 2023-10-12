package business.interfaces;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;
import business.Group2Exception;

import java.util.HashMap;
import java.util.List;

public interface BookInterface {
	public List<Book> getAllBook();

	public Book getBookById(String isbn) throws Group2Exception;

	public Book addBook(String ISBN,
						String title,
						int maxCheckoutLength,
						List<Author> authors,
						List<BookCopy> copies) throws Group2Exception;

	public BookCopy addBookCopy(Book book) throws Group2Exception;

	public void updateBookCopyStatus(Book book, BookCopy bookCopy, boolean newStatus) throws Group2Exception;

	public Book checkout(String memberID, String isbn) throws Group2Exception;

	Book addBookCopyByISBN(String isbn) throws Group2Exception;

	public HashMap<BookCopy, LibraryMember> find(String isbn) throws Group2Exception;

}
