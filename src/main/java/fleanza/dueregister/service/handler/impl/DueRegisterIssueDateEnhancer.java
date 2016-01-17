package fleanza.dueregister.service.handler.impl;

import static fleanza.dueregister.util.DueRegisterHelper.to60Days;
import static fleanza.dueregister.util.DueRegisterHelper.toEndOfMonth;

import java.util.Date;

import fleanza.dueregister.model.Invoice;
import fleanza.dueregister.model.Invoice.PayMode;
import fleanza.dueregister.service.handler.DueRegisterEnhancer;

/**
 * @author fleanza
 */
public class DueRegisterIssueDateEnhancer implements DueRegisterEnhancer {

	@Override
	public void enhance(Invoice invoice) {

		Date issueDate = invoice.getIssueDate();

		if(invoice.getPayMode().equals(PayMode.DF)) {
			invoice.setDueDate(issueDate);
		}
		else if(invoice.getPayMode().equals(PayMode.DFFM)) {
			invoice.setDueDate(toEndOfMonth(issueDate));
		}
		else if(invoice.getPayMode().equals(PayMode.DF60)) {
			invoice.setDueDate(to60Days(issueDate));
		}
	}
}
