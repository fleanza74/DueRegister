package fleanza.dueregister.service.handler;

import fleanza.dueregister.model.Invoice;

/**
 * @author fleanza
 */
public interface DueRegisterParser {

	public Invoice parse(String line);
}
