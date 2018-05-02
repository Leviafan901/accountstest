package by.tut.accounttests.exceptions;

public class ParsingException extends RuntimeException {

	private static final long serialVersionUID = 4658547214377697126L;

	public ParsingException(String message, Throwable cause) {
        super(message);
    }
}
