package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;

import java.util.List;

public class ListPersonTest extends AbstractServiceTest {

    protected void populate() {
        PhoneBook pb = PhoneBook.getInstance();
        new Person(pb, "Josefina");
        new Person(pb, "Josefa");
        new Person(pb, "Joaquim");
        new Person(pb, "Joana");
        new Person(pb, "Jandira");
        new Person(pb, "Jessica");
    }

    @Test
    public void success() {
        ListPeopleService service = new ListPeopleService();
        service.execute();
	List<String> ps = service.result();

        // check person was created
        assertEquals("List with 6 Persons", 6, ps.size());
	assertEquals("First name is Jandira", "Jandira", ps.get(0));
	assertEquals("Last name is Josefina", "Josefina", ps.get(5));
	assertEquals("Third name is Joana", "Joana", ps.get(2));
	// it must be right, but all 6 should be tested ...
    }
}
