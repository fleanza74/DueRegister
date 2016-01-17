package fleanza.dueregister.service.handler;

import java.util.List;

import fleanza.dueregister.model.Invoice;

/**
 * @author fleanza
 */
public interface DueRegisterSorter {

	public List<Invoice> sort(List<Invoice> source);
}
