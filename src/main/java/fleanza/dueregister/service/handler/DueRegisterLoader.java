package fleanza.dueregister.service.handler;

import java.util.List;

import fleanza.dueregister.model.Invoice;

/**
 * @author fleanza
 */
public interface DueRegisterLoader {

	public List<Invoice> load(List<String> lines);
}
