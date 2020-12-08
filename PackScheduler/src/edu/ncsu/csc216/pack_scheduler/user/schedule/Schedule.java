package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * The schedule class creates a schedule that holds courses. You can set the
 * name of the title, as well as add and remove courses from the schedule, and
 * also the schedule can be reset
 * 
 * @author alex aksland
 * @author Kunal Kapoor
 *
 */
public class Schedule {
	/** private variable that holds courses in a schedule*/
	private ArrayList<Course> schedule;
	/** private variable of title that can be get and set*/
	private String title;

	/**
	 * Returns the title of the schedule
	 * 
	 * @return the schedule's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title as long as its not null
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Constructor for schedules, initializes the title to "My Schedule"
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Adds course to schedule if it is not a duplicate
	 * 
	 * @param c is the course that is being added
	 * @return true if the course can be added
	 * @throws IllegalArgumentException if it is a duplicate course
	 */
	public boolean addCourseToSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			if (c.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			try {
				c.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
		}
		schedule.add(c);
		return true;
	}

	/**
	 * Removes the course from the schedule
	 * 
	 * @param c is the course being added
	 * @return true if course is removed, false if c is not in the schedule
	 */
	public boolean removeCourseFromSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			if ((c != null) && c.getName().equals(schedule.get(i).getName())
					&& c.getSection().equals(schedule.get(i).getSection())) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule to the default which contains an empty schedule with
	 * title "My Schedule"
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Gets a 2-D array of the schedule courses with name, section, title, and
	 * meeting string
	 * 
	 * @return an array with the course and its name, section, title and meeting
	 *         string
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleCourses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			scheduleCourses[i][0] = c.getName();
			scheduleCourses[i][1] = c.getSection();
			scheduleCourses[i][2] = c.getTitle();
			scheduleCourses[i][3] = c.getMeetingString();
			scheduleCourses[i][4] = "" + c.getCourseRoll().getOpenSeats();
		}
		return scheduleCourses;
	}
	/**
	 * Get's the total credits for the schedule
	 * @return countCredits, the total credits in the schedule
	 */
	public int getScheduleCredits(){
		int countCredits = 0;
		for(int i = 0; i < schedule.size(); i++) {
			countCredits += schedule.get(i).getCredits();
		}
		return countCredits;
	}
	/**
	 * Check's to see if course can be added.
	 * @param course the course being evaluated 
	 * @return true if can be added, false if not.
	 */
	public boolean canAdd(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i))) {
				return false;
			}
			try {
				course.checkConflict(schedule.get(i));
			} catch(ConflictException e) {
				return false;
			}
		}
		if((course.getCredits() + getScheduleCredits()) > Student.MAX_CREDITS) {
			return false;
		}
		return true;
	}
}
