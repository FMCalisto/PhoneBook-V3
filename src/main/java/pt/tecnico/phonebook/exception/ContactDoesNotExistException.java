package pt.tecnico.phonebook.exception;

public class ContactDoesNotExistException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    private String contactName;

    public ContactDoesNotExistException(String contactName) {
        contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    @Override
    public String getMessage() {
        return "Contact " + contactName + " does not exist";
    }
}
