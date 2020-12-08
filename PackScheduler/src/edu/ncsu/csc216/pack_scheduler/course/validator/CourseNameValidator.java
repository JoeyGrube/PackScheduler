package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checks if the given course name is valid using a FSM
 * 
 * @author Kunal Kapoor
 */
public class CourseNameValidator {
	/** T/F if the currentState is in a final state */
	private boolean validEndState;
	/** number of letters in the name, used for FSM purposes */
	private int letterCount;
	/** number of digits in the name, used for FSM purposes */
	private int digitCount;
	/**
	 * instance of state that describes the current state the name is in with
	 * regards to the FSM
	 */
	private State currentState;
	/** instance of suffix state, a final state in the FSM */
	private SuffixState stateSuffix;
	/** instance of initial state (of FSM) state of FSM object */
	private InitialState stateInitial;
	/** instance of letter state (of FSM) */
	private LetterState stateLetter;
	/** instance of number state (of FSM) */
	private NumberState stateNumber;

	/** Constructor, initializes the current state to the initial state */
	public CourseNameValidator() {
		stateInitial = new InitialState();
		stateLetter = new LetterState();
		stateNumber = new NumberState();
		stateSuffix = new SuffixState();
	}

	/**
	 * Checks if the given course name is valid using an FSM
	 * 
	 * @param name name of course
	 * @return T/F if the course name is valid
	 * @throws InvalidTransitionException if any of the characters of the course's
	 *                                    name are invalid according to the
	 *                                    requirements of the FSM (a valid course
	 *                                    name)
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		currentState = stateInitial;
		letterCount = 0;
		digitCount = 0;
		if (name == null || name.isEmpty()) {
			throw new InvalidTransitionException();
		}

		for (char ch : name.toCharArray()) {
			if (Character.isDigit(ch)) {
				try {
					currentState.onDigit();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			}

			else if (Character.isLetter(ch)) {
				try {
					currentState.onLetter();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			}

			else {
				try {
					currentState.onOther();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			}
		}

		validEndState = (currentState instanceof NumberState && digitCount == 3) || currentState instanceof SuffixState;
		return validEndState;
	}

	/**
	 * Final State of FSM which describes the case of there being an optional letter
	 * suffix in the course's name
	 * 
	 * @author Kunal Kapoor
	 */
	public class SuffixState extends State {

		/**
		 * Private constructor for inner class
		 */
		private SuffixState() {

		}

		/**
		 *throws an invalid transition error if there is more than one letter suffix
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 *throws an invalid transition error if there is more digits after the suffix
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}

	/**
	 * Initial State of FSM
	 * 
	 * @author Kunal Kapoor
	 *
	 */
	public class InitialState extends State {

		/**
		 * private constructor for initial state
		 */
		private InitialState() {

		}

		/**
		 *Increments to change state, then sets new state
		 */
		public void onLetter() {
			letterCount++;
			currentState = stateLetter;
		}

		/**
		 *on digit throws error if it doesn't begin with letter 
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * checks for beginning letters in the name
	 * @author alex aksland
	 *
	 */
	public class LetterState extends State {

		/**
		 * constant for first 4 letters
		 */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 *private constructor for first 4 letters 
		 */
		private LetterState() {

		}

		/**
		 *throws error if beginning isn't 1-4 letters 
		 *increments the state and sets the new state
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCount == MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}

			else {
				letterCount++;
				currentState = stateLetter;
			}

		}

		/**
		 *checks if next state is a digit and increments the state and sets it if it is correct
		 */
		public void onDigit() {
			digitCount++;
			currentState = stateNumber;
		}
	}

	/**
	 * checks if there are 3 numbers in this inner class
	 * @author alex aksland
	 *
	 */
	public class NumberState extends State {

		/**
		 * there are 3 numbers
		 */
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * private constructor for number state class
		 */
		private NumberState() {

		}

		/**
		 * checks if there are 3 letters near end
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = stateSuffix;
			}

			else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 *throws error if there is more or less 3 numbers
		 *increments state and sets state
		 */
		public void onDigit() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}

			else {
				digitCount++; 
				currentState = stateNumber;
			}
		}
	}

	/**
	 * Abstract class that calls all classes above
	 * @author alex aksland
	 *
	 */
	public abstract class State {

		/**
		 * Checks if there is a letter
		 * @throws InvalidTransitionException if there is not a letter in appropriate spot
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Checks if there is a digit
		 * @throws InvalidTransitionException if there is not a digit in appropriate spot
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Checks if there is anything other than letter or digit
		 * @throws InvalidTransitionException if there is not a letter or digit in name
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
}
