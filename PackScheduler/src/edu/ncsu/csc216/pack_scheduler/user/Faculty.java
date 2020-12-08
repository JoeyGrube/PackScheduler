/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty represents an individual faculty record
 * 
 * @author Ana Ratanaphruks
 */
public class Faculty extends User implements Comparable<Faculty> {
	private FacultySchedule facultySchedule;
	/** Max Courses Faculty is allowed to have */
	private int maxCourses;

	/** Default max Courses */
	public static final int MAX_COURSES = 3;

	/** Default min Courses */
	public static final int MIN_COURSES = 1;

	/**
	 * Faculty constructor
	 * 
	 * @param firstName  for first name of faculty
	 * @param lastName   for last name of faculty
	 * @param id         for id of faculty
	 * @param email      for faculty's email
	 * @param password   for password of faculty
	 * @param maxCourses faculty is allowed to take
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		facultySchedule = new FacultySchedule(id);
		setMaxCourses(maxCourses);
	}

	/**
	 * Checks if faculty has too many courses
	 * 
	 * @return true if faculty has too many
	 */
	public boolean isOverloaded() {
		if (facultySchedule.getScheduledCourses().length > maxCourses) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the max credits a Faculty may take
	 * 
	 * @return maxCourses of Faculty
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the Faculty's max credits
	 * 
	 * @param maxCourses the maximum courses you can take
	 * @throws IllegalArgumentException for if max credits are wrong
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Gets the schedule
	 * 
	 * @return facultySchedule of the Faculty
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}

	/**
	 * Generates a String representation of the Faculty based on all fields
	 * 
	 * @return String representation of Faculty
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}

	/**
	 * Compares faculty objects to other faculties
	 * 
	 * @return integer 1 if Faculty is greater than another 0 if equal -1 if student
	 *         is less than another
	 */
	@Override
	public int compareTo(Faculty f) {
		if (f == null) {
			throw new NullPointerException();
		}

		if (this.getClass() != f.getClass()) {
			throw new ClassCastException();
		}

		int lastCompare = this.getLastName().compareTo(f.getLastName());
		int firstCompare = this.getFirstName().compareTo(f.getFirstName());
		int idCompare = this.getId().compareTo(f.getId());

		if (lastCompare < 0) {
			return -1;
		} else if (lastCompare > 0) {
			return 1;
		} else if (firstCompare < 0) {
			return -1;
		} else if (firstCompare > 0) {
			return 1;
		} else if (idCompare < 0) {
			return -1;
		} else if (idCompare > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Generates a hashCode representation for the Faculty
	 * 
	 * @return hashCode for Faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Returns true if this Faculty object is equal another Faculty object
	 * 
	 * @return true if the two Faculties are equal on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

}
