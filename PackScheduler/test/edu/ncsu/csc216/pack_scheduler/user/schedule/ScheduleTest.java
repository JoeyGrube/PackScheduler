package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test for our schedule which includes adding courses and removing courses as
 * well as creating new schedules
 * 
 * @author alexaksland
 *
 */
public class ScheduleTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Creates course to be used on all the tests
	 */
	Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 12, MEETING_DAYS, START_TIME, END_TIME);

	/**
	 * Tests the title being set correctly and initailized to my schedule
	 */
	@Test
	public void testSetTitle() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());

		schedule.setTitle("Title");
		assertEquals("Title", schedule.getTitle());

		try {
			schedule.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Title cannot be null.");
		}
	}

	/**
	 * Tests making a new schedule with default title
	 */
	@Test
	public void testSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());

	}

	/**
	 * Tests if add course adds valid courses, and throws error if it is a duplicate
	 * course
	 */
	@Test
	public void testAddCourseToSchedule() {

		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());

		schedule.addCourseToSchedule(course);

		// Test invalid course of same name

		try {
			schedule.addCourseToSchedule(course);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "You are already enrolled in " + course.getName());
		}

		Course course2 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10,
				"MW", 1300, END_TIME);
		
		try {
			schedule.addCourseToSchedule(course2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "You are already enrolled in " + course2.getName());
		}

	}

	/**
	 * tests if the course is correctly being removed also tests getScheduled
	 * courses
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());

		schedule.addCourseToSchedule(course);

		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(NAME, schedule.getScheduledCourses()[0][0]);
		assertEquals(SECTION, schedule.getScheduledCourses()[0][1]);
		assertEquals(TITLE, schedule.getScheduledCourses()[0][2]);

		schedule.removeCourseFromSchedule(course);

		assertEquals(0, schedule.getScheduledCourses().length);

		assertFalse(schedule.removeCourseFromSchedule(course));

	}

	/**
	 * Tests a reset schedule and if default title is set
	 */
	@Test
	public void testResetSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());

		assertEquals(0, schedule.getScheduledCourses().length);

		schedule.addCourseToSchedule(course);

		assertEquals(1, schedule.getScheduledCourses().length);

		schedule.resetSchedule();

		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}

}
