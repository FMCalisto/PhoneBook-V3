package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.service.dto.ContactDto;

import java.util.List;

public class ListContactTest extends AbstractServiceTest {

    protected void populate() {
        PhoneBook pb = PhoneBook.getInstance();
        Person p = new Person(pb, "Ana");
        new Contact(p, "António", 444444444);
        new Contact(p, "Abel", 555555555);
        new Contact(p, "Beatriz", 777777777);
        new Contact(p, "Bruno", 999999999);
        new Contact(p, "Zélia", 222222222);
        new Contact(p, "Zacarias", 666666666);
    }

    @Test
    public void success() {
        ListPersonPhoneBook service = new ListPersonPhoneBook("Ana");
        service.execute();
	List<ContactDto> cs = service.result();

        // check contact listing
        assertEquals("List with 6 Contacts", 6, cs.size());
	assertEquals("First name is Abel", "Abel", cs.get(0).getName());
	assertEquals("Last name is Zélia", "Zélia", cs.get(5).getName());
	assertEquals("Third name is Beatriz", "Beatriz", cs.get(2).getName());
	assertEquals("Third phoneNumber is 777777777", 777777777, cs.get(2).getPhoneNumber());
	// it must be right, but all 6 should be tested ...
    }
}
