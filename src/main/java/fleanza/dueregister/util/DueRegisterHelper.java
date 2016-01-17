package fleanza.dueregister.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import fleanza.dueregister.exception.DueRegisterException;
import fleanza.dueregister.model.Invoice;

/**
 * @author fleanza
 */
public class DueRegisterHelper {

	public static String formatDate(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public static Date parseDate(String date) {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.setLenient(false);
			return df.parse(date);
		}
		catch(ParseException pex) {
			throw new DueRegisterException(pex.getMessage(),pex);
		}
	}

	public static Date toEndOfMonth(Date date) {

		Calendar in = Calendar.getInstance();
		in.setTime(date);

		Calendar out = (Calendar)in.clone();
		out.add(Calendar.MONTH,1);
		out.set(Calendar.DATE,1);
		out.add(Calendar.DATE,-1);
		return out.getTime();
	}

	public static Date to60Days(Date date) {

		Calendar in = Calendar.getInstance();
		in.setTime(date);

		Calendar out = (Calendar)in.clone();
		out.add(Calendar.MONTH,2);
		return out.getTime();
	}

	public static List<String> toStringList(String token, List<Invoice> invoices) {

		if(invoices == null || invoices.isEmpty()) {
			throw new DueRegisterException("Error formatting invoices. Invoices list NULL or EMPTY");
		}

		List<String> lines = new ArrayList<String>(0);

		int row = 1;

		Iterator<Invoice> it = invoices.iterator();
		while(it.hasNext()) {
			try {
				lines.add(toString(token,it.next()));
				row++;
			}
			catch(Exception ex) {
				String msg = String.format("Error formatting invoice at row %d. " + ex.getMessage(),row);
				throw new DueRegisterException(msg,ex);
			}
		}

		return lines;
	}

	public static String toString(String token, Invoice invoice) {

		if(token == null) {
			throw new NullPointerException("TOKEN object NULL");
		}

		if(invoice == null) {
			throw new NullPointerException("Invoice object NULL");
		}

		if(invoice.getNumber() == null) {
			throw new NullPointerException("Field number NULL");
		}

		if(invoice.getIssueDate() == null) {
			throw new NullPointerException("Field issueDate NULL");
		}

		if(invoice.getDueDate() == null) {
			throw new NullPointerException("Field dueDate NULL");
		}

		StringBuilder sb = new StringBuilder();
		sb.append(invoice.getNumber());
		sb.append(token);
		sb.append(formatDate(invoice.getIssueDate()));
		sb.append(token);
		sb.append(formatDate(invoice.getDueDate()));
		return sb.toString();
	}
}
