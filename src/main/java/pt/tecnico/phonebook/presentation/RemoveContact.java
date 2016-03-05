package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.RemoveContactService;

public class RemoveContact extends PbCommand {

    public RemoveContact(Shell sh) { super(sh, "rm", "remove the contact of person"); }
    public void execute(String[] args) {
	if (args.length < 2)
	    throw new RuntimeException("USAGE: "+name()+" <person> <contact>");
	new RemoveContactService(args[0], args[1]).execute();
    }
}
