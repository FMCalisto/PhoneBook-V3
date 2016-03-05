package pt.tecnico.phonebook.presentation;
import pt.tecnico.phonebook.service.ImportPhoneBookService;
import java.io.File;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class Import extends PbCommand {

    public Import(Shell sh) { super(sh, "import", "import phonebook contacts. (use ./locaFile or resourceFile)"); }
    public void execute(String[] args) {
	if (args.length < 1)
	    throw new RuntimeException("USAGE: "+name()+" filename");
	try {
	    SAXBuilder builder = new SAXBuilder();
	    File file;
	    if (args[0].startsWith(".")) file = new File(args[0]);
	    else file = resourceFile(args[0]);
            Document doc = (Document)builder.build(file);
	    new ImportPhoneBookService(doc).execute();
	} catch (Exception e) { throw new RuntimeException(e); }
    }

    public File resourceFile(String filename) {
	 log.trace("Resource: "+filename);
         ClassLoader classLoader = getClass().getClassLoader();
         if (classLoader.getResource(filename) == null) return null;
         return new java.io.File(classLoader.getResource(filename).getFile());
    }
}
