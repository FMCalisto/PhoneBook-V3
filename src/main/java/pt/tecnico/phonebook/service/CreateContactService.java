package pt.tecnico.phonebook.service;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.EmailContact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class CreateContactService extends PhoneBookService {

    private String personName;
    private String contactName;
    private int phoneNumber;
    private String contactEmail;

    public CreateContactService(String personName, String contactName, int phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.personName = personName;
        this.contactEmail = "";
    }

    public CreateContactService(String personName, String contactName, int phoneNumber, String email) {
        this(personName, contactName, phoneNumber);
        this.contactEmail = email;
    }

    @Override
    public final void dispatch() throws NameAlreadyExistsException, InvalidPhoneNumberException {
	if (contactEmail.length() == 0)
	    new Contact(getPerson(personName), contactName, phoneNumber);
	else
	    new EmailContact(getPerson(personName), contactName, phoneNumber, contactEmail);
    }
}
