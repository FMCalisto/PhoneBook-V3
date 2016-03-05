package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.CreatePersonService;

public class CreatePerson extends PbCommand {

    public CreatePerson(Shell sh) { super(sh, "new", "add a new person"); }
    public void execute(String[] args) {
	if (args.length < 1)
	    throw new RuntimeException("USAGE: "+name()+" name");
	new CreatePersonService(args[0]).execute();
    }
}
