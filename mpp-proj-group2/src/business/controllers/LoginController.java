package business.controllers;

import business.Group2Exception;
import business.interfaces.LoginInterface;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.util.HashMap;

public class LoginController implements LoginInterface {

    public void login(String id, String password) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if(!map.containsKey(id)) {
            throw new Group2Exception("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if(!passwordFound.equals(password)) {
            throw new Group2Exception("Password incorrect");
        }
        SystemController.currentAuth = map.get(id).getAuthorization();


    }
}
