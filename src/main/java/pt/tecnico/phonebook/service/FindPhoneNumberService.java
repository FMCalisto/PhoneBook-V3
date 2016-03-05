package pt.tecnico.phonebook.service;

import pt.tecnico.phonebook.domain.PhoneBook;

public class FindPhoneNumberService extends PhoneBookService {

    private int phone;
    private String contact;

    public FindPhoneNumberService(String contactName) {
    	contact = contactName;
    }

    public final void dispatch() { // throws ContactDoesNotExistException
        phone = getPhoneBook().getPhoneNumberByContact(contact);
    }

    public final int result() {
        return phone;
    }
}
