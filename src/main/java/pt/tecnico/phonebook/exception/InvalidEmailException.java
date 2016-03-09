package pt.tecnico.phonebook.exception;

public class InvalidEmailException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    private String email;

    public InvalidEmailException(String email) {
        this.email = email;
    }

    public String getInvalidEmail() {
        return email;
    }

    @Override
    public String getMessage() {
        return "Invalid email number format: " + email;
    }
}
