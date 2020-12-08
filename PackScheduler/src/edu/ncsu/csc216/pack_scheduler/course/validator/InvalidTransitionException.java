/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class that tells us that there was an invalid transition in the state 
 * @author josephgrube
 *
 */
public class InvalidTransitionException extends Exception {

	/**
	 * Sets the initial state
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Throws error with given message
	 * @param e is the string given for throwing the error
	 */
	InvalidTransitionException(String e){
		super(e);
	}
	
	/**
	 * default throw message if no error message is given
	 */
	InvalidTransitionException(){
		super("Invalid FSM Transition.");
	}
	
	

}
