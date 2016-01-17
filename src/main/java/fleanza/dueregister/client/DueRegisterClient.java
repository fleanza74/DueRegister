package fleanza.dueregister.client;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fleanza.dueregister.service.DueRegisterService;
import fleanza.dueregister.service.DueRegisterServiceBuilder;
import fleanza.dueregister.service.handler.DueRegisterLoader;
import fleanza.dueregister.service.handler.DueRegisterSorter;
import fleanza.dueregister.service.handler.DueRegisterValidator;
import fleanza.dueregister.service.handler.DueRegisterWriter;
import fleanza.dueregister.service.handler.impl.DueRegisterArgsValidator;
import fleanza.dueregister.service.handler.impl.DueRegisterIssueDateEnhancer;
import fleanza.dueregister.service.handler.impl.DueRegisterSelectionSorter;
import fleanza.dueregister.service.handler.impl.DueRegisterTokenLoader;
import fleanza.dueregister.service.handler.impl.DueRegisterTokenParser;
import fleanza.dueregister.service.handler.impl.DueRegisterTokenWriter;

/**
 * @author fleanza
 */
public class DueRegisterClient {

	private static String		header	= "DueRegister\n\n";
	private static String		footer	= "\nPlease report issues at https://github.com/fleanza74/DueRegister/issues/new\n";

	private static Options	options;

	public DueRegisterClient() {
	}

	private static Options options() {
		if(options == null) {
			synchronized(DueRegisterClient.class) {
				if(options == null) {
					Option i = Option.builder("in").argName("filename").desc("Input filename").hasArg().required(true).build();
					Option o = Option.builder("out").argName("filename").desc("Output filename").hasArg().required(true).build();
					Option h = Option.builder("h").desc("Write help").hasArg(false).required(false).build();
					options = new Options();
					options.addOption(i);
					options.addOption(o);
					options.addOption(h);
				}
			}
		}
		return options;
	}

	private static void help(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(80,"java -jar dueregister.jar",header,options,footer,true);
	}

	private void execute(String in, String out) throws IOException {

		DueRegisterValidator v = new DueRegisterArgsValidator();
		DueRegisterLoader l = new DueRegisterTokenLoader(new DueRegisterTokenParser(";"),
				new DueRegisterIssueDateEnhancer());
		DueRegisterSorter s = new DueRegisterSelectionSorter();
		DueRegisterWriter w = new DueRegisterTokenWriter(";");

		DueRegisterService service = DueRegisterServiceBuilder.create().validator(v).loader(l).sorter(s).writer(w).build();
		service.execute(in,out);
	}

	public void run(String[] args) throws ParseException, IOException {

		CommandLine cmd = new DefaultParser().parse(options(),args);
		if(cmd.hasOption("h")) {
			System.out.println();
			help(options());
		}
		else {
			execute(cmd.getOptionValue("in"),cmd.getOptionValue("out"));
		}
	}

	public static void main(String[] args) {
		try {
			DueRegisterClient client = new DueRegisterClient();
			client.run(args);
			System.out.println("\nProcess terminated OK.");
			System.exit(0);
		}
		catch(Exception ex) {
			System.out.println();
			System.out.println(ex.getMessage());
			help(options());
			System.out.println();
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
