package fleanza.dueregister.service.handler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fleanza.dueregister.model.Invoice;

/**
 * @author fleanza
 */
public interface DueRegisterWriter {

	public void write(File fout, List<Invoice> invoices) throws IOException;
}
