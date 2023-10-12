package business.controllers;

import business.Address;
import business.CheckoutRecord;
import business.LibraryMember;
import business.Group2Exception;
import business.interfaces.MemberInterface;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.HashMap;
import java.util.List;

public class MemberController implements MemberInterface {
    @Override
    public LibraryMember createLibraryMember(String memberID,
                                    String firstName,
                                    String lastName,
                                    String street,
                                    String city,
                                    String zip,
                                    String state,
                                    String tel) throws Group2Exception {

        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> map = da.readMemberMap();
        if (map.containsKey(memberID)) {
            throw new Group2Exception("This memberID exist in the system");
        }
        Address address = new Address(street,city,state,zip);
        LibraryMember member = new LibraryMember(memberID,firstName,lastName,tel,address);
        da.saveNewMember(member);
        return member;
    }

    @Override
    public CheckoutRecord getRecord(String memberID) throws Group2Exception {
        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> map = da.readMemberMap();
        if (!map.containsKey(memberID)) {
            throw new Group2Exception("This memberID does not exist in the system");
        }
        return map.get(memberID).getCheckoutRecord();
    }

    @Override
    public List<LibraryMember> getAllLibraryMember() {
        DataAccess da = new DataAccessFacade();
        return da.readMemberMap().values().stream().toList();
    }
}
