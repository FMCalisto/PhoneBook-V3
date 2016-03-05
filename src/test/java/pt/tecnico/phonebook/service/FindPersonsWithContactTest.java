package pt.tecnico.phonebook.service;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.exception.PhoneBookException;
import pt.tecnico.phonebook.exception.ContactDoesNotExistException;

@RunWith(JMockit.class)
public class FindPersonsWithContactTest extends AbstractServiceTest {

    private static final List<String> names = new ArrayList<String>();
    private static final String contact = "Joana";
    private static final String person1 = "Xana", person2 = "Miguel";
    private static final int phone = 123456789;

    private FindPersonsWithContactService service;

    protected void populate() {
        PhoneBook pb = PhoneBook.getInstance();
	new Contact(new Person(pb, person1), contact, phone);
	new Contact(new Person(pb, person2), contact, phone);

	// populate mockup
	names.add(person1);
	names.add(person2);
    }

    @Test
    public void success() {
        new MockUp<FindPersonsWithContactService>() {
	  @Mock
	  List<String> result() { return names; }
	};

        service = new FindPersonsWithContactService(contact);
        service.execute();
        assertEquals(service.result().size(), 2);
        assertTrue(service.result().contains(person1)); // person1 is final
        assertTrue(service.result().contains(person2)); // person2 is final
    }

    @Test(expected = ContactDoesNotExistException.class)
    public void findNonexistingContact() throws ContactDoesNotExistException {
	final String nonexisting = "Ana";

        new MockUp<FindPersonsWithContactService>() {
	  @Mock
	  void dispatch() throws PhoneBookException {
	    throw new ContactDoesNotExistException(nonexisting); }
	};

        new FindPersonsWithContactService(nonexisting).execute();
    }
}
