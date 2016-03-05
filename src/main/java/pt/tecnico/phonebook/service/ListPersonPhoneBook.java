package pt.tecnico.phonebook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;
import pt.tecnico.phonebook.service.dto.ContactDto;

public class ListPersonPhoneBook extends PhoneBookService {

    private List<ContactDto> phonebook;
    private String personName;

    public ListPersonPhoneBook(String personName) {
        this.personName = personName;
    }

    public final void dispatch() throws PersonDoesNotExistException {
        Person person = getPerson(this.personName);
        phonebook = new ArrayList<ContactDto>();

        for (Contact c : person.getContactSet()) {
            phonebook.add(new ContactDto(c.getName(), c.getPhoneNumber()));
        }

        Collections.sort(phonebook);
    }

    public final List<ContactDto> result() {
        return phonebook;
    }
}
