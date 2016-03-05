package pt.tecnico.phonebook.exception;

public class ImportDocumentException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    public ImportDocumentException() {
        super("Error in importing person from XML");
    }
}
