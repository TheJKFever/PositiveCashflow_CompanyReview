package baseClasses;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */

public class ElementDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public ElementDoesNotExistException() {
		super();
	}

	public ElementDoesNotExistException(String message) {
		super(message);
	}

}
