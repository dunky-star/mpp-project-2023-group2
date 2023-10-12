package business.interfaces;

import business.Author;

import java.util.List;

public interface AuthorInterface {
	public List<Author> getAllAuthor();

	public List<Author> getAllAuthorByBook(String ISBN);
}
