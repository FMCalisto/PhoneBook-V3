package pt.tecnico.phonebook.service;

import java.util.Set;
import java.util.HashSet;
import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.ExportDocumentException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class ExportPhoneBookService extends PhoneBookService {
    private Document doc;
    private Set<String> names;

    public ExportPhoneBookService() {
        names = new HashSet<String>();
    }

    public void add(String personName) {
        names.add(personName);
    }

    @Override
    protected void dispatch() throws ExportDocumentException, PersonDoesNotExistException {
	if (names.size() == 0) // export all
	    doc = getPhoneBook().xmlExport();
	else {
	    Element root = new Element("phonebook");
	    doc = new Document(root);
	    for (String s: names)
		root.addContent(getPerson(s).xmlExport());
	}
    }

    public final Document result() {
        return doc;
    }
}
