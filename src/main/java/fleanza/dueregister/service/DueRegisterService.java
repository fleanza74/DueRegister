package fleanza.dueregister.service;

import static org.apache.commons.io.FileUtils.readLines;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fleanza.dueregister.service.handler.DueRegisterLoader;
import fleanza.dueregister.service.handler.DueRegisterSorter;
import fleanza.dueregister.service.handler.DueRegisterValidator;
import fleanza.dueregister.service.handler.DueRegisterWriter;

/**
 * @author fleanza
 */
public class DueRegisterService {

	private DueRegisterValidator	validator	= null;
	private DueRegisterLoader			loader		= null;
	private DueRegisterSorter			sorter		= null;
	private DueRegisterWriter			writer		= null;

	public DueRegisterService(DueRegisterValidator aValidator, DueRegisterLoader aLoader, DueRegisterSorter aSorter,
			DueRegisterWriter aWriter) {

		validator = aValidator;
		loader = aLoader;
		sorter = aSorter;
		writer = aWriter;
	}

	public void execute(String in, String out) throws IOException {

		File fin = new File(in);
		File fout = new File(out);
		validator.validate(fin,fout);
		List<String> lines = readLines(fin);
		writer.write(fout,sorter.sort(loader.load(lines)));
	}
}
