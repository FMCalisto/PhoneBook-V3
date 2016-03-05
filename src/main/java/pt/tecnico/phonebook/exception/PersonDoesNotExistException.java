package pt.tecnico.phonebook.exception;

public class PersonDoesNotExistException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    private String personName;

    public PersonDoesNotExistException(String personName) {
        personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    @Override
    public String getMessage() {
        return "Person " + personName + " does not exist";
    }
}
