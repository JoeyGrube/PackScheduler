package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Makes a course roll for a student. Has the lists of students as well as a cap for how many students can be in the course.
 * @author alexaksland
 *
 */
public class CourseRoll {

	/**
	 * List of student in course
	 */
	private LinkedAbstractList<Student> roll;
	
	/**
	 * Limit of students in a course
	 */
	private int enrollmentCap;
	
	/**
	 * integer to hold 10 people at minimum 
	 */
	private static final int MIN_ENROLLMENT = 10;
	
	/**
	 * integer to hold 250 people at max
	 */
	private static final int MAX_ENROLLMENT = 250;
	/** 
	 * The wait list
	 */
	private LinkedAbstractList<Student> waitlist;
	
	/**
	 * size of the wait list
	 */
	private static final int WAITLIST_SIZE = 10;
	/**
	 * The course in which the enrollment is being examined
	 */
	private Course c;
	
	/**
	 * returns the max number to be allowed in classroom
	 * @return enrollment cap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * sets the cap if it is in the parameters. 
	 * @param eCap is the new enrollment cap
	 * @throws IllegalArgumentException if the cap isn't between the max and minimum enrollment, as well as
	 * if the cap is less than the amount of kids already in the class
	 */
	public void setEnrollmentCap(int eCap) {
		
		if(eCap < MIN_ENROLLMENT || eCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment cap");
		}
		if(roll != null && eCap < roll.size()) {
			throw new IllegalArgumentException();
		}
		if (roll != null) {
		    roll.setCapacity(eCap);
		    this.enrollmentCap = eCap;
		}
	}
	
	/**
	 * Constructor for the course roll
	 * @param c the course that enrollment info is being taken for
	 * @param eCap is initial cap
	 */
	public CourseRoll(Course c, int eCap) {
		if(c == null) {
			throw new IllegalArgumentException();
		}
		this.c = c;
		roll = new LinkedAbstractList<Student>(eCap);
		setEnrollmentCap(eCap);
		waitlist = new LinkedAbstractList<Student>(WAITLIST_SIZE);
	}
	
	/**
	 * enrolls student into the course
	 * @param s is student being enrolled
	 * @throws IllegalArgumentException if student is null, if student can be enrolled, or if there is an exception adding
	 */
	public void enroll(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}
		if(roll.size() ==  getEnrollmentCap() ) {
			if(waitlist.size() < WAITLIST_SIZE) {
				waitlist.add(s);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		else {
			try {
				roll.add(s);
				//s.getSchedule().addCourseToSchedule(c);
			}
			catch(Exception e) {
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**
	 * Drops student from the course
	 * @param s is student being dropped
	 * @throws IllegalArgumentException if student is null,  or if there is an exception removing
	 */
	public void drop(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}
		try {
			for(int j = 0; j < waitlist.size(); j++) {
				if(waitlist.get(j) == s) {
					waitlist.remove(j);
				}
			}
			for(int i = 0; i < roll.size(); i++) {
				if(roll.get(i) == s) {
					//roll.get(i).getSchedule().removeCourseFromSchedule(c);
					roll.remove(i);
					if(waitlist.size() > 0) {
						waitlist.get(0).getSchedule().addCourseToSchedule(c);
						roll.add(waitlist.get(0));
						waitlist.remove(0);
					} 
				}
			}
		}
		catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * If student is not part of course roll, or if the size is bigger than the size cap, then return false
	 * @param s is student being added
	 * @return true if it can be enrolled, false if it can't
	 */
	public boolean canEnroll(Student s) {
		
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i) == s) {
				return false;
			}
		}
		for(int i = 0; i < waitlist.size(); i++) {
			if(waitlist.get(i) == s) {
				return false;
			}
		}
			if(roll.size() >= enrollmentCap && waitlist.size() >= WAITLIST_SIZE) {
				return false;
			}
			return true;
	}
	
	/**
	 * Returns the amount of seats in a course
	 * @return the amount of seats still open
	 */
	public int getOpenSeats() {
		return getEnrollmentCap() - roll.size();
	}
	
	/**
	 * Returns the amount of students on the wait list
	 * @return size of the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	
	
}
