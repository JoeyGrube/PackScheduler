package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Contains information of a student
 * 
 * @author Joseph
 * @author Kunal Kapoor
 * @author Rujula
 */
public class Student extends User implements Comparable<Student> {

	/** students max credits allowed to take. */
	private int maxCredits;
	/** general amount of allowed max credits. */
	public static final int MAX_CREDITS = 18;
	/** instance of the schedule*/
	private Schedule instance;

	/**
	 * Constructor for the student class.
	 * 
	 * @param firstName  student's first name.
	 * @param lastName   student's last name.
	 * @param id         student's id.
	 * @param email      student's email.
	 * @param hashPW     student's password.
	 * @param maxCredits student's max credits.
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
	}

	/**
	 * Secondary constructor (without max credits)
	 * 
	 * @param firstName student's first name
	 * @param lastName  student's last name
	 * @param id        student's id
	 * @param email     student's email
	 * @param hashPW    student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}

	/**
	 * gets student's max credits
	 * 
	 * @return maxCredits student's max credits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * sets maxCredits
	 * 
	 * @param maxCredits student's maxCredits
	 * @throws IllegalArgumentException if the maxCredits is an invalid number
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns the student as a string containing his information
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCredits;
	}

	/**
	 * Compares two students to decide placement within the schedule
	 * @throws IllegalArgumentException if the student to be compared to is null
	 */
	@Override
	public int compareTo(Student i) {
		if (i == null) {
			throw new IllegalArgumentException();
		}

		int lastNameI = getLastName().compareTo(i.getLastName());
		if (lastNameI == 0) {
			int firstNameI = getFirstName().compareTo(i.getFirstName());
			if (firstNameI == 0) {
				int unityId = getId().compareTo(i.getId());
				if (unityId == 0) {
					return 0;
				} else if (unityId > 0) {
					return 1;
				} else {
					return -1;
				}
			} else if (firstNameI > 0) {
				return 1;
			} else {
				return -1;
			}
		} else if (lastNameI > 0) {
			return 1;
		} else {
			return -1;
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	/**
	 * Gets the schedule
	 * @return instance the instance of the schedule
	 */
	public Schedule getSchedule() {
		if(instance == null) {
			instance = new Schedule();
		}
		return instance;
	}
	/**
	 * Checks to see if course can be added to schedule
	 * @param course the course being examined
	 * @return true if can be added, false if not
	 */
	public boolean canAdd(Course course) {
		if(!getSchedule().canAdd(course)) {
			return false;
		}
		if((getSchedule().getScheduleCredits() + course.getCredits()) > getMaxCredits()) {
			return false;
		}
		return true;
	}

}