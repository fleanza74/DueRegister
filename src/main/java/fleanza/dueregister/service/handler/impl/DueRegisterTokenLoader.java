package fleanza.dueregister.service.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fleanza.dueregister.exception.DueRegisterException;
import fleanza.dueregister.model.Invoice;
import fleanza.dueregister.service.handler.DueRegisterEnhancer;
import fleanza.dueregister.service.handler.DueRegisterLoader;
import fleanza.dueregister.service.handler.DueRegisterParser;

/**
 * @author fleanza
 */
public class DueRegisterTokenLoader implements DueRegisterLoader {

	private DueRegisterParser		parser		= null;
	private DueRegisterEnhancer	enhancer	= null;

	public DueRegisterTokenLoader(DueRegisterParser aParser, DueRegisterEnhancer anEnhancer) {
		parser = aParser;
		enhancer = anEnhancer;
	}

	@Override
	public List<Invoice> load(List<String> lines) {

		if(lines == null || lines.isEmpty()) {
			throw new DueRegisterException("Error loading lines. Lines list NULL or EMPTY");
		}

		List<Invoice> invoices = new ArrayList<Invoice>(0);

		int row = 1;
		Iterator<String> it = lines.iterator();
		while(it.hasNext()) {
			try {
				Invoice invoice = parser.parse(it.next());
				enhancer.enhance(invoice);
				invoices.add(invoice);
				row++;
			}
			catch(Exception ex) {
				String msg = String.format("Error loading lines at row %d. " + ex.getMessage(),row);
				throw new DueRegisterException(msg,ex);
			}
		}

		return invoices;
	}
}
