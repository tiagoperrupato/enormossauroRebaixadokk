package exception;

public class InvalidMap extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidMap() {
	      super();
	}
	
	public InvalidMap(String message) {
	      super(message);
	}
}
