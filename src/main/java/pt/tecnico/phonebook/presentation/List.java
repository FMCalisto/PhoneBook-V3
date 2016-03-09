package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.ListPeopleService;
import pt.tecnico.phonebook.service.ListPersonPhoneBook;
import pt.tecnico.phonebook.service.dto.ContactDto;

public class List extends PbCommand {

    public List(Shell sh) {
    	super(sh, "list", "list persons (or person contacts, given person name");
    }
    public void execute(String[] args) {
	if (args.length == 0) {
	    ListPeopleService lds = new ListPeopleService();
	    lds.execute();
	    for (String s: lds.result())
		System.out.println(s);
	    System.out.println("use 'list <name>' to list a person contacts");
	} else {
	    ListPersonPhoneBook lpp = new ListPersonPhoneBook(args[0]);
	    lpp.execute();
	    System.out.println("Contacts for "+args[0]);
	    for (ContactDto d: lpp.result())
		System.out.println(d.getName()+" -> "+d.getPhoneNumber()
		      + (d.getEmail().length() > 0 ? " " + d.getEmail() : ""));
	}
    }
}

