package pt.tecnico.phonebook.service;

import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class RemovePersonService extends PhoneBookService {

    private String personName;

    public RemovePersonService(String name) {
        personName = name;
    }

    public final void dispatch() throws PersonDoesNotExistException {
        getPerson(personName).remove();
    }
}
