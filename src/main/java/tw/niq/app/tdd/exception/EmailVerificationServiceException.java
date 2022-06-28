package tw.niq.app.tdd.exception;

public class EmailVerificationServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailVerificationServiceException(String message) {
		super(message);
	}

}
