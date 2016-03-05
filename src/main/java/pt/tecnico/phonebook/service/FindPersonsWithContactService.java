package pt.tecnico.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;

public class FindPersonsWithContactService extends PhoneBookService {

    private List<String> names;
    private String contact;

    public FindPersonsWithContactService(String contactName) {
	names = new ArrayList<String>();
    	contact = contactName;
    }

    public final void dispatch() { // throws ContactDoesNotExistException
	// TODO: mockup example
    }

    public final List<String> result() {
        return names;
    }
}
