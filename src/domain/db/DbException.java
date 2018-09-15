package domain.db;

public class DbException extends RuntimeException {
	
	private static final long serialVersionUID = Long.parseLong("5044190751079313744");
	
	public DbException() {
		super();
	}
	
	public DbException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public DbException(String message) {
		super(message);
	}
	
	public DbException(Throwable exception) {
		super(exception);
	}
	

}
