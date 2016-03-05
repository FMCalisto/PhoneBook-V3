package pt.tecnico.phonebook.service;

import java.io.File;
import java.io.IOException;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import pt.tecnico.phonebook.exception.ImportDocumentException;

public class ImportPhoneBookService extends PhoneBookService {
    private final Document doc;

    public ImportPhoneBookService(Document doc) {
        this.doc = doc;
    }

    @Override
    protected void dispatch() throws ImportDocumentException {
        getPhoneBook().xmlImport(doc.getRootElement());
    }
}
