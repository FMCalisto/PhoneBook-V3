package pt.tecnico.phonebook.service.dto;

public class ContactDto implements Comparable<ContactDto> {

    private String name;
    private int phoneNumber;
    private String email;

    public ContactDto(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
	this.email = "";
    }

    public ContactDto(String name, int phoneNumber, String email) {
        this(name, phoneNumber);
	this.email = email;
    }

    public final String getName() {
        return this.name;
    }

    public final int getPhoneNumber() {
        return this.phoneNumber;
    }

    public final String getEmail() {
        return this.email;
    }

    @Override
    public int compareTo(ContactDto other) {
	return getName().compareTo(other.getName());
    }
}
