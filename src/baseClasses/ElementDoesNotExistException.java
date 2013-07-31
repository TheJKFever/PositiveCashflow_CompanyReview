package baseClasses;

@SuppressWarnings("serial")
public class ElementDoesNotExistException extends Exception {

	public ElementDoesNotExistException() {
		super();
	}

	public ElementDoesNotExistException(String message) {
		super(message);
	}

}
