package pt.tecnico.phonebook.presentation;

public class PbShell extends Shell {

  public static void main(String[] args) throws Exception {
    PbShell sh = new PbShell();
    sh.execute();
  }

  public PbShell() { // add commands here
    super("PhoneBook");
    new CreatePerson(this);
    new CreateContact(this);
    new RemovePerson(this);
    new RemoveContact(this);
    new List(this);
    new Import(this);
    new Export(this);
  }
}
