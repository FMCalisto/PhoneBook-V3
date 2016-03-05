package pt.tecnico.phonebook.domain;

import org.jdom2.Element;
import org.jdom2.DataConversionException;
import java.io.UnsupportedEncodingException;

import pt.tecnico.phonebook.exception.ImportDocumentException;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Contact extends Contact_Base {

    public Contact(Person person, String name, Integer phoneNumber) throws InvalidPhoneNumberException {
        setName(name);
        setPhoneNumber(phoneNumber);
	setPerson(person);
    }

    public Contact(Person person, Element xml) throws InvalidPhoneNumberException {
        xmlImport(xml);
	setPerson(person);
    }

    @Override
    public void setPhoneNumber(Integer phoneNumber) {
        if (phoneNumber <= 0) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public void setPerson(Person person) throws NameAlreadyExistsException {
        if (person == null) {
            super.setPerson(null);
            return;
        }

        person.addContact(this);
    }

    public void remove() {
        setPerson(null);
        deleteDomainObject();
    }

    public void xmlImport(Element contactElement) throws ImportDocumentException {
	try {
            setName(new String(contactElement.getAttribute("name").getValue().getBytes("UTF-8")));
	} catch (UnsupportedEncodingException e) { System.err.println(e); }
        try {
            setPhoneNumber(contactElement.getAttribute("phoneNumber").getIntValue());
        } catch (DataConversionException e) {
            throw new ImportDocumentException();
        }
    }

    public Element xmlExport() {
        Element element = new Element("contact");
        element.setAttribute("name", getName());
        element.setAttribute("phoneNumber", Integer.toString(getPhoneNumber()));

        return element;
    }
}
