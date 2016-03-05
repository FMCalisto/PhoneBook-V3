package pt.tecnico.phonebook.presentation;

public abstract class PbCommand extends Command {
  public PbCommand(Shell sh, String n) { super(sh, n); }
  public PbCommand(Shell sh, String n, String h) { super(sh, n, h); }
}
