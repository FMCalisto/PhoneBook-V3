package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Verifications;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.ContactDoesNotExistException;

@RunWith(JMockit.class)
public class FindPhoneNumberTest extends AbstractServiceTest {

    private static final String contact = "Joana";
    private static final int phoneNumber = 999999999;

    @Mocked
    private PhoneBook pb;

    protected void populate() {
        pb = PhoneBook.getInstance();
    }

    @Test
    public void success() {
	new Expectations() {
            {
                pb.getPhoneNumberByContact(contact);
                result = phoneNumber;
            }
        };
        FindPhoneNumberService service = new FindPhoneNumberService(contact);
        service.execute();
        assertEquals(service.result(), phoneNumber);
    }

    @Test
    public void methodIsCalled() {
        FindPhoneNumberService service = new FindPhoneNumberService(contact);
        service.execute();
	// service.result() will return 0
	new Verifications() {
            {
                pb.getPhoneNumberByContact(contact);
            }
        };
    }

    @Test(expected = ContactDoesNotExistException.class)
    public void findNonexistingContact() throws ContactDoesNotExistException {
	new Expectations() {
            {
                pb.getPhoneNumberByContact(contact);
                result = new ContactDoesNotExistException(contact);
            }
        };
        FindPhoneNumberService service = new FindPhoneNumberService(contact);
        service.execute();
    }
}
