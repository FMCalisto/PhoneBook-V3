package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.RemovePersonService;

public class RemovePerson extends PbCommand {

    public RemovePerson(Shell sh) { super(sh, "del", "delete a person (all contacts will be removed)"); }
    public void execute(String[] args) {
	if (args.length < 1)
	    throw new RuntimeException("USAGE: "+name()+" name");
	new RemovePersonService(args[0]).execute();
    }
}
