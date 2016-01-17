package fleanza.dueregister.service.handler.impl;

import static fleanza.dueregister.util.DueRegisterHelper.toStringList;
import static org.apache.commons.io.FileUtils.writeLines;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fleanza.dueregister.model.Invoice;
import fleanza.dueregister.service.handler.DueRegisterWriter;

/**
 * @author fleanza
 */
public class DueRegisterTokenWriter implements DueRegisterWriter {

	private String TOKEN = null;

	public DueRegisterTokenWriter(String aToken) {
		TOKEN = aToken;
	}

	@Override
	public void write(File fout, List<Invoice> invoices) throws IOException {
		writeLines(fout,toStringList(TOKEN,invoices));
	}
}
