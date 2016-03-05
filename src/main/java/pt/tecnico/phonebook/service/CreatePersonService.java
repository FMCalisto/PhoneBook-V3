package pt.tecnico.phonebook.service;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class CreatePersonService extends PhoneBookService {

    private String personName;

    public CreatePersonService(String name) {
        personName = name;
    }

    public final void dispatch() throws NameAlreadyExistsException {
        new Person(getPhoneBook(), personName);
    }
}
