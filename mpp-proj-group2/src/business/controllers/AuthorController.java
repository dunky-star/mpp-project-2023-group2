package business.controllers;

import business.Author;
import business.interfaces.AuthorInterface;
import dataaccess.DataAccessFacade;
import dataaccess.DataAccess;

import java.util.List;

public class AuthorController implements AuthorInterface {

    @Override
    public List<Author> getAllAuthor() {
        DataAccess da = new DataAccessFacade();
        return da.readAuthorsMap().values().stream().toList();
    }

    @Override
    public List<Author> getAllAuthorByBook(String ISBN) {
        return null;
    }
}
