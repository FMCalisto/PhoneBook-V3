package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.ExportPhoneBookService;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Export extends PbCommand {

    public Export(Shell sh) { super(sh, "export", "xml export a list of persons contacts.\n\tUSAGE: export [filename [name1 name2 ...]]"); }
    public void execute(String[] args) {
	ExportPhoneBookService e = new ExportPhoneBookService();
	if (args.length > 0) {
	    for (String s: Arrays.copyOfRange(args, 1, args.length))
	        e.add(s);
	}
	e.execute();
	try {
	    Format form = Format.getPrettyFormat();
	    form.setEncoding("UTF-8");
	    XMLOutputter xmlOutput = new XMLOutputter(form);
	    if (args.length == 0)
		System.out.println(xmlOutput.outputString(e.result()));
	    else
		xmlOutput.output(e.result(), new FileWriter(args[0]));
	} catch (IOException ex) { throw new RuntimeException(ex); }
    }
}
