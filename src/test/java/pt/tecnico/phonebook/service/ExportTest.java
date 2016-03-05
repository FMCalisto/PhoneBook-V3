package pt.tecnico.phonebook.service;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Attribute;

public class ExportTest extends AbstractServiceTest {

    protected void populate() {
        PhoneBook pb = PhoneBook.getInstance();
	Person p = new Person(pb, "Xico");
	new Contact(p, "Manel", 333333333);
	new Contact(p, "Maria", 666666666);
	new Contact(p, "Xana", 963456789);
	p = new Person(pb, "Xana");
	new Contact(p, "Manel", 333333333);
	new Contact(p, "Xico", 911919191);
    }

    @Test
    public void success() throws Exception {
        ExportPhoneBookService service = new ExportPhoneBookService();
        service.execute();
	Document doc = service.result();

        // check exported data
	Element e = doc.getRootElement();
        assertEquals("exported 2 Persons", 2, e.getChildren().size());
	for (Element p: e.getChildren("person")) {
	    String name = p.getAttribute("name").getValue();
	    Element contacts = p.getChild("contacts");
	    if (name.equals("Xico")) {
		assertEquals("Xico has 3 Contacts", 3, contacts.getChildren().size());
	    } else if (name.equals("Xana")) {
		assertEquals("Xana has 2 Contacts", 2, contacts.getChildren().size());
	    } else fail("invalid Person name.");
	}
	// it must be right, but more checks can be made ...
    }
}
