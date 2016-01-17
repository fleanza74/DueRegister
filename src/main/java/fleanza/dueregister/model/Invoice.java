package fleanza.dueregister.model;

import java.util.Date;

/**
 * @author fleanza
 */
public class Invoice {

	private Long		number		= null;
	private Date		issueDate	= null;
	private Date		dueDate		= null;
	private PayMode	payMode		= null;

	public Invoice() {
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public PayMode getPayMode() {
		return payMode;
	}

	public void setPayMode(PayMode payMode) {
		this.payMode = payMode;
	}

	public enum PayMode {

		DF("DF"), DFFM("DFFM"), DF60("DF60");

		private String value;

		private PayMode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
