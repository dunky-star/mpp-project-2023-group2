package business.interfaces;

import business.CheckoutRecord;
import business.LibraryMember;
import business.Group2Exception;

import java.util.List;

public interface MemberInterface {

    public List<LibraryMember> getAllLibraryMember();
    public LibraryMember createLibraryMember(String memberID,
                                             String firstName,
                                             String lastName,
                                             String street,
                                             String city,
                                             String zip,
                                             String state,
                                             String tel) throws Group2Exception;

    public CheckoutRecord getRecord(String memberID) throws Group2Exception;
}
