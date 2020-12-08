package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Raises an exception when a conflict emerges
 * @author josephgrube
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates an exception with a specific string going to the Exception constructor
	 * @param message the message that the method wanted to pass through
	 * to the exception
	 */
	public ConflictException(String message) {
		super(message);
	}
	/**
	 * Creates an exception with the string "Schedule conflict."
	 * going to the Exception constructor
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
 
}
