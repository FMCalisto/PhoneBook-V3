package pt.tecnico.phonebook.domain;

import org.jdom2.Element;
import org.jdom2.DataConversionException;
import java.io.UnsupportedEncodingException;

import pt.tecnico.phonebook.exception.ImportDocumentException;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.InvalidEmailException;

public class EmailContact extends EmailContact_Base {
    
    public EmailContact(Person person, String name, Integer phoneNumber, String email) throws InvalidPhoneNumberException {
	init(person, name, phoneNumber);
        setEmail(email);
    }

    public EmailContact(Person person, Element xml) throws InvalidPhoneNumberException {
        xmlImport(xml);
	setPerson(person);
    }

    @Override
    public void setEmail(String email) {
        if (email.indexOf('@') < 0) {
            throw new InvalidEmailException(email);
        }
        super.setEmail(email);
    }

    public void xmlImport(Element contactElement) throws ImportDocumentException {
	super.xmlImport(contactElement);
	try {
            setEmail(new String(contactElement.getAttribute("email").getValue().getBytes("UTF-8")));
	} catch (UnsupportedEncodingException e) {
            throw new ImportDocumentException();
        }
    }

    public Element xmlExport() {
        Element element = super.xmlExport();
	element.setName("email-contact");
        element.setAttribute("email", getEmail());

        return element;
    }
}
