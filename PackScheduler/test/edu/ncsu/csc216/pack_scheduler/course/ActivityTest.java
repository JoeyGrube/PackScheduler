/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Activity classes implemented checkConflict() method
 * @author josephgrube
 *
 */
public class ActivityTest {

	/**
	 * Tests one scenario with no conflict,
	 * and one scenario that should result in a scheduling conflict
	 */
	@Test
	public void testcheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 12, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 12, "TH", 1330, 1445);
		 try {
		        a1.checkConflict(a2);
		        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
		        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
		        a2.checkConflict(a1);
		        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
		        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
		    } catch (ConflictException e) {
		        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		    }
		 a1.setMeetingDays("TH");
		 a1.setActivityTime(1445, 1530);
		 try {
		     a1.checkConflict(a2);
		     fail(); //ConflictException should have been thrown, but was not.
		 } catch (ConflictException e) {
		     //Check that the internal state didn't change during method call.
		     assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
		     assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		 }
	}
	
	/**
	 * Checks conflict when it's only on one day
	 */
	@Test
	public void testCheckCoflictOneDay() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 12, "MWF", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 12, "TWH", 1330, 1445);
		try {
		     a1.checkConflict(a2);
		     fail(); //ConflictException should have been thrown, but was not.
		 } catch (ConflictException e) {
		     //Check that the internal state didn't change during method call.
		     assertEquals("MWF 1:30PM-2:45PM", a1.getMeetingString());
		     assertEquals("TWH 1:30PM-2:45PM", a2.getMeetingString());
		 }
	}
	
	/**
	 * Checks for when end time of one activity is the same as the start time as another
	 */
	@Test
	public void testCheckConflictOverlappingTimes() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 12, "MW", 1330, 1445);
		Activity a2 = new Course("CSC226", "Discrete Math", "001", 3, "sesmith5", 12, "MW", 1445, 1545);
		try {
		     a1.checkConflict(a2);
		     fail(); //ConflictException should have been thrown, but was not.
		 } catch (ConflictException e) {
		     //Check that the internal state didn't change during method call.
		     assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		     assertEquals("MW 2:45PM-3:45PM", a2.getMeetingString());
		 }
	}

}
