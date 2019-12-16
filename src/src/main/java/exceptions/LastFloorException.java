package exceptions;

public class LastFloorException extends RuntimeException {

	public LastFloorException(String message) {
		super(message);
	}
	
	public LastFloorException() {
		super();
	}

}
