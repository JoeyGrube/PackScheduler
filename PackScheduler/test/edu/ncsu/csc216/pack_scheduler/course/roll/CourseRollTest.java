package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests all the functionalities in course roll
 * @author alexaksland
 */
public class CourseRollTest {
	
	/** First name used for testing*/
	public static final String FIRST_NAME = "Alan";
	/** Last name used for testing*/
	public static final String LAST_NAME = "Turing";
	/** ID used for testing*/
	public static final String ID = "Aturing";
	/** Email used for testing*/
	public static final String EMAIL = "Aturing@ncsu.edu";
	/** Password used for testing*/
	public static final String PASSWORD = "h3h3";
	/** Max credits used for testing*/
	public static final int MAX_CREDITS = 15;
	
	/**
	 * Tests new course roll, the setters, enroll, and drop
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		CourseRoll r = c.getCourseRoll();
		assertEquals(r.getEnrollmentCap(), 20);
		
		try {
		r.setEnrollmentCap(5);
		}
		catch(IllegalArgumentException e) {
			assertEquals(r.getEnrollmentCap(), 20);
		}
		
		try {
			r.setEnrollmentCap(500);
			}
			catch(IllegalArgumentException e) {
				assertEquals(r.getEnrollmentCap(), 20);
			}
		
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student1 = new Student("Alex", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student2 = new Student("Alexa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student3 = new Student("Alexaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student4 = new Student("Alexaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student5 = new Student("Alexaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student6 = new Student("Alexaaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student7 = new Student("Alexaaaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student8 = new Student("Alexaaaaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student9 = new Student("Alexaaaaaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student student10 = new Student("Alexaaaaaaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		
		r.enroll(student);
		r.enroll(student1);
		r.enroll(student2);
		r.enroll(student3);
		r.enroll(student4);
		r.enroll(student5);
		r.enroll(student6);
		r.enroll(student7);
		r.enroll(student8);
		r.enroll(student9);
		r.enroll(student10);
		try {
		r.enroll(student10);
		}
		catch(IllegalArgumentException e) {
			assertEquals(r.getEnrollmentCap(), 20);
		}
		
		try {
			r.setEnrollmentCap(10);
			}
			catch(IllegalArgumentException e) {
				assertEquals(r.getEnrollmentCap(), 20);
			}
			
		Student student11 = null;
		try {
			r.enroll(student11);
			}
			catch(IllegalArgumentException e) {
				assertEquals(r.getEnrollmentCap(), 20);
			}
		try {
			r.drop(student11);
			}
			catch(IllegalArgumentException e) {
				assertEquals(r.getEnrollmentCap(), 20);
			}
		
		Student student12 = new Student("Alexaaaaaaaaa", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		r.setEnrollmentCap(11);
		try {
			r.enroll(student12);
			}
			catch(IllegalArgumentException e) {
				assertEquals(r.getEnrollmentCap(), 11);
			}
		r.setEnrollmentCap(20);
		try {
			r.enroll(student1);
			}
			catch(IllegalArgumentException e) {
				assertEquals(r.getEnrollmentCap(), 20);
			}
		System.out.println(r.getOpenSeats());
		System.out.println(r.getEnrollmentCap());
		//r.drop(student1);
		//assertEquals(r.getEnrollmentCap(), 20);
		c  = new Course("CSC116", "Programming Concepts - Java", "003", 4, "sesmith5", 10, "A");
		r = c.getCourseRoll();
		r.enroll(student);
		boolean i =	r.canEnroll(student1);
		assertTrue(i);
		r.enroll(student1);
		r.enroll(student2);
		r.enroll(student3);
		r.enroll(student4);
		r.enroll(student5);
		r.enroll(student6);
		r.enroll(student7);
		r.enroll(student8);
		r.enroll(student9);
		r.enroll(student10);
		int d = r.getNumberOnWaitlist();
		assertFalse(r.canEnroll(student10));
		assertFalse(r.canEnroll(student7));
		assertEquals(d, 1);
		r.drop(student10);
		assertTrue(r.getNumberOnWaitlist() == 0);
		r.enroll(student12);
		assertTrue(r.getNumberOnWaitlist() == 1);
		r.drop(student9);
		assertTrue(r.getNumberOnWaitlist() == 0);
	}
	
	
}
