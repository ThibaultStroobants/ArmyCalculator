package domain.model;

public class ModelException extends RuntimeException {
	
	private static final long serialVersionUID = Long.parseLong("5044190751079313744");
	
	public ModelException() {
		super();
	}
	
	public ModelException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public ModelException(String message) {
		super(message);
	}
	
	public ModelException(Throwable exception) {
		super(exception);
	}
	

}
