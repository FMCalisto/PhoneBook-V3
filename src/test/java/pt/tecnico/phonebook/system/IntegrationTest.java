package pt.tecnico.phonebook.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import pt.tecnico.phonebook.domain.PhoneBook; // Mockup
import pt.tecnico.phonebook.service.*;
import pt.tecnico.phonebook.service.dto.*;
import pt.tecnico.phonebook.exception.*;

@RunWith(JMockit.class)
public class IntegrationTest extends AbstractServiceTest {

    private static final List<String> names = new ArrayList<String>();
    private static final String p1 = "Tiago", p2 = "Miguel", p3 = "Xana";
    private static final String p4 = "António", p5 = "Ana";
    private static final String importFile = "other.xml";
    private static final int phone2 = 345678900, phone5 = 935667654;

    protected void populate() { // populate mockup
	names.add(p2);
	names.add(p4);
    }

    @Test
    public void success() throws Exception {

        new CreatePersonService(p1).execute();

	new CreateContactService(p1, p5, phone5).execute();
	new CreateContactService(p1, p2, phone2).execute();

	ListPersonPhoneBook lc = new ListPersonPhoneBook(p1);
	lc.execute();
	System.out.println(p1);
	for (ContactDto dto: lc.result())
	    System.out.println("\t" + dto.getName() + " -> " + dto.getPhoneNumber());
	assertEquals(lc.result().size(), 2);

	ClassLoader loader = getClass().getClassLoader();
	File file = new File(loader.getResource(importFile).getFile());
	Document doc = (Document)new SAXBuilder().build(file);
	new ImportPhoneBookService(doc).execute();

	ListPeopleService lp = new ListPeopleService();
	lp.execute();
	System.out.print("Persons:");
	for (String name: lp.result())
	    System.out.print("\t" + name);
	System.out.println(".");
	assertEquals(lp.result().size(), 6); // Tiago, António, Miguel, Xana, Sofia

        new RemoveContactService(p1, p2).execute();
	lc = new ListPersonPhoneBook(p1);
	lc.execute();
	assertEquals(lc.result().size(), 1);

        new RemovePersonService(p1).execute();
	lp = new ListPeopleService();
	lp.execute();
	assertEquals(lp.result().size(), 5); // António, Miguel, Xana, Sofia

	ExportPhoneBookService exp = new ExportPhoneBookService();
	exp.execute();
	// exported the same number of persons!
	assertEquals(doc.getRootElement().getChildren().size(),
		     exp.result().getRootElement().getChildren().size());

        new MockUp<FindPersonsWithContactService>() {
	  @Mock
	  List<String> result() { return names; }
	};
        FindPersonsWithContactService s = new FindPersonsWithContactService(p3);
        s.execute();
        assertEquals(s.result().size(), 2);

        new MockUp<PhoneBook>() {
	  @Mock
          int getPhoneNumberByContact(String p2) { return phone2; }
        };
        FindPhoneNumberService pn = new FindPhoneNumberService(p2);
        pn.execute();
        assertEquals(pn.result(), phone2);
    }
}
