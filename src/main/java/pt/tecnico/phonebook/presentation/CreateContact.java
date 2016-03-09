package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.CreateContactService;

public class CreateContact extends PbCommand {

    public CreateContact(Shell sh) { super(sh, "add", "add a contact to person"); }
    public void execute(String[] args) {
	if (args.length < 3)
	    throw new RuntimeException("USAGE: "+name()+" <person> <contact> <phonenumber> [<email>]");
	if (args.length > 3)
	    new CreateContactService(args[0], args[1], Integer.parseInt(args[2]), args[3]).execute();
	else
	    new CreateContactService(args[0], args[1], Integer.parseInt(args[2])).execute();
    }
}
