package pt.tecnico.phonebook.domain;

import org.jdom2.Element;
import java.io.UnsupportedEncodingException;

import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Person extends Person_Base {

    public Person(PhoneBook pb, String name) {
        super();
        setName(name);
	setPhoneBook(pb);
    }

    public Person(PhoneBook pb, Element xml) {
        super();
        xmlImport(xml);
	setPhoneBook(pb);
    }

    @Override
    public void setPhoneBook(PhoneBook pb) {
        if (pb == null)
            super.setPhoneBook(null);
        else
            pb.addPerson(this);
    }

    @Override
    public void addContact(Contact contactToBeAdded) throws NameAlreadyExistsException {
        if (hasContact(contactToBeAdded.getName()))
            throw new NameAlreadyExistsException(contactToBeAdded.getName());

        super.addContact(contactToBeAdded);
    }

    public Contact getContactByName(String name) {
        for (Contact contact: getContactSet())
            if (contact.getName().equals(name))
                return contact;
        return null;
    }

    public boolean hasContact(String contactName) {
        return getContactByName(contactName) != null;
    }

    public void remove() {
        for (Contact c: getContactSet())
            c.remove();
        setPhoneBook(null);
        deleteDomainObject();
    }

    public void xmlImport(Element personElement) {
        // clear current phone book
        for (Contact c: getContactSet())
            c.remove();

	try {
            setName(new String(personElement.getAttribute("name").getValue().getBytes("UTF-8")));
	} catch (UnsupportedEncodingException e) { System.err.println(e); }
        Element contacts = personElement.getChild("contacts");

        for (Element contactElement: contacts.getChildren("contact"))
            new Contact(this, contactElement);
    }

    public Element xmlExport() {
        Element element = new Element("person");
        element.setAttribute("name", getName());

        Element contactsElement = new Element("contacts");
        element.addContent(contactsElement);

        for (Contact c: getContactSet())
            contactsElement.addContent(c.xmlExport());

        return element;
    }
}
