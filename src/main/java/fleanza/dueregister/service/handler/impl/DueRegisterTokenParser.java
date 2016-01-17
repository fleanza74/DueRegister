package fleanza.dueregister.service.handler.impl;

import static fleanza.dueregister.util.DueRegisterHelper.parseDate;

import fleanza.dueregister.exception.DueRegisterException;
import fleanza.dueregister.model.Invoice;
import fleanza.dueregister.model.Invoice.PayMode;
import fleanza.dueregister.service.handler.DueRegisterParser;

/**
 * @author fleanza
 */
public class DueRegisterTokenParser implements DueRegisterParser {

	private String TOKEN = null;

	public DueRegisterTokenParser(String aToken) {
		TOKEN = aToken;
	}

	@Override
	public Invoice parse(String line) {

		String[] tokens = line.split(TOKEN);

		if(tokens.length != 3) {
			throw new DueRegisterException("Error parsing line. Invalid number of token");
		}

		Invoice invoice = new Invoice();
		invoice.setNumber(Long.decode(tokens[0].trim()));
		invoice.setIssueDate(parseDate(tokens[1].trim()));
		String payMode = tokens[2].trim();
		try {
			invoice.setPayMode(PayMode.valueOf(payMode));
		}
		catch(IllegalArgumentException iaex) {
			String msg = String.format("Error parsing line. Invalid value (%s) for field payMode",payMode);
			throw new IllegalArgumentException(msg,iaex);
		}

		return invoice;
	}
}
