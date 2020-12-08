package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the valid names verse the invalid names for
 * course name validator
 * @author alexaksland
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Tests the CourseNameValidator to ensure that the FSM returns the correct T/F
	 * for whether a given course name is valid
	 * 
	 * @throws InvalidTransitionException if the course contains an illegal
	 *                                    character or invalid character at a
	 *                                    specific location in the course name
	 */
	@Test
	public void testCourseNameValidator() throws InvalidTransitionException {
		// Test a valid construction and make sure values are correct
		CourseNameValidator c = new CourseNameValidator();
		// test 2 letters
		assertTrue(c.isValid("CS236"));
		// test 1 letter
		assertTrue(c.isValid("C236"));
		// test 4 letters
		assertTrue(c.isValid("CSCS236"));
		// test 3 letters plus suffix
		assertTrue(c.isValid("CSC236Q"));
		// test 2 letters plus suffix
		assertTrue(c.isValid("CS236Q"));
		// test 1 letter plus suffix
		assertTrue(c.isValid("C236Q"));
		// test 4 letters plus suffix
		assertTrue(c.isValid("CSCS236Q"));
		// test 3 letters
		assertTrue(c.isValid("CSC236"));

		// test null name
		try {
			assertFalse(c.isValid(null));
			fail("An Exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Invalid FSM Transition.");
		}

		// test empty name
		try {
			assertFalse(c.isValid(""));
			fail("An Exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Invalid FSM Transition.");
		}

		// test invalid names that should not throw an InvalidTransitionException
		// test 1 letter
		assertFalse(c.isValid("C"));
		// test 2 letters
		assertFalse(c.isValid("CS"));
		// test 3 letters
		assertFalse(c.isValid("CSC"));
		// test 4 letters
		assertFalse(c.isValid("CSCS"));
		// test 1 letter 1 digit
		assertFalse(c.isValid("C1"));
		// test 1 letter 2 digits
		assertFalse(c.isValid("C12"));
		// test 2 letters 1 digit
		assertFalse(c.isValid("CS1"));
		// test 2 letters 2 digits
		assertFalse(c.isValid("CS12"));
		// test 3 letters 1 digit
		assertFalse(c.isValid("CSC1"));
		// test 3 letters 2 digits
		assertFalse(c.isValid("CSC12"));

		// test invalid names that should throw an InvalidTransitionException
		// test 1 letter 2 digits then suffix
		try {
			assertFalse(c.isValid("C12E"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}

		// test 3 letters 2 digits then suffix
		try {
			assertFalse(c.isValid("CSC12Q"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}

		// test 3 letters 3 digits and 2 letter suffix
		try {
			assertFalse(c.isValid("CSC123QQ"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
		}

		// test having a digit after the suffix in the course name
		try {
			assertFalse(c.isValid("CSC123Q1"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
		}

		// test 4 digits in course name
		try {
			assertFalse(c.isValid("CSC1234"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have 3 digits.");
		}

		// test 2 digits then suffix in course name
		try {
			assertFalse(c.isValid("CSC12A"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}

		// test invalid character (non alphanumeric)
		try {
			assertFalse(c.isValid("CSC$%^"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

		// test start course name with digit
		try {
			assertFalse(c.isValid("216CSC"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must start with a letter.");
		}

		// test 5 letter course name (before digits)
		try {
			assertFalse(c.isValid("CSCDE216"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
		}

		// test 1 digit then suffix in course name
		try {
			assertFalse(c.isValid("CSC1A"));
			fail("An exception should have been thrown");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}
	}
}