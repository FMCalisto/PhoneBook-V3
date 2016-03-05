package pt.tecnico.phonebook.exception;

public class InvalidPhoneNumberException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    private int phoneNumber;

    public InvalidPhoneNumberException(int phoneNumber) {
        phoneNumber = phoneNumber;
    }

    public int getInvalidPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getMessage() {
        return "Invalid phone number format: " + phoneNumber;
    }
}
