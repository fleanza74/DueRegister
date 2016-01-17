package fleanza.dueregister.exception;

/**
 * @author fleanza
 *
 */
public class DueRegisterException extends RuntimeException {

	private static final long serialVersionUID = -6285645760201228518L;

	public DueRegisterException() {
	}

	public DueRegisterException(String message) {
		super(message);
	}

	public DueRegisterException(Throwable cause) {
		super(cause);
	}

	public DueRegisterException(String message, Throwable cause) {
		super(message,cause);
	}

	public DueRegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
