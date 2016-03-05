package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class CreatePersonTest extends AbstractServiceTest {

    protected void populate() {
        PhoneBook pb = PhoneBook.getInstance();

        Person p = new Person(pb, "João");
    }

    @Test
    public void success() {
        final String personName = "David";
        CreatePersonService service = new CreatePersonService(personName);
        service.execute();

        // check person was created
        assertTrue("user was not created", PhoneBookService.getPhoneBook().hasPerson(personName));
    }

    @Test(expected = NameAlreadyExistsException.class)
    public void unauthorizedUserCreation() {
        CreatePersonService service = new CreatePersonService("João");
        service.execute();
    }

}
