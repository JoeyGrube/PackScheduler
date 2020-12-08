/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Contains the information for a course
 * 
 * @author josephgrube
 *
 */
public class Course extends Activity implements Comparable<Course> {

	private static final int SECTION_LENGTH = 3;

	private static final int MAX_CREDITS = 5;

	private static final int MIN_CREDITS = 1;

	private CourseNameValidator validator;

	/** Course name */
	private String name;

	/** Course section */
	private String section;

	/** Course credit hours */
	private int credits;

	/** Course instructor */
	private String instructorId;

	/** Instance of the CourseRoll class */
	private CourseRoll roll;

	/**
	 * Constructs the Course class
	 * 
	 * @param name          the name of the course
	 * @param title         the title of the course
	 * @param section       the course's section
	 * @param credits       amount of credit hours the course is worth
	 * @param instructorId  the ID of the instructor
	 * @param meetingDays   meeting days
	 * @param startTime     the start time of the class
	 * @param endTime       the end time of the class
	 * @param enrollmentCap the enrollment cap of the class
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Constructs the Course class
	 * 
	 * @param name          the name of the course
	 * @param title         the title of the course
	 * @param section       the course's section
	 * @param credits       amount of credit hours the course is worth
	 * @param instructorId  the ID of the instructor
	 * @param meetingDays   meeting days
	 * @param enrollmentCap the enrollment cap of the course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the course name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the course name
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or is not a valid name
	 */

	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name");
		}

		validator = new CourseNameValidator();

		try {
			if (validator.isValid(name)) {
				this.name = name;
			}

			else {
				throw new IllegalArgumentException("Invalid course name");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name");
		}
	}

	/**
	 * Returns the section
	 * 
	 * @return the section
	 */

	public String getSection() {
		return section;
	}

	/**
	 * Sets the section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException whenever section is either null, not three
	 *                                  characters long, or contains a character
	 *                                  that is not a digit
	 */

	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section number");
			}
		}
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section number");
		}
		this.section = section;
	}

	/**
	 * Gets the credit hours
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the credit hours
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException when credits is less than one or greater
	 *                                  than five
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the instructor ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets instructor ID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is either null or empty
	 */
	public void setInstructorId(String instructorId) {

		//if (instructorId.equals("")) {
		//	throw new IllegalArgumentException("Invalid instructor unity id");
		//}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();

		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];
		shortDisplay[0] = name;
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = "" + roll.getEnrollmentCap();
		return shortDisplay;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = name;
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = "" + credits;
		longDisplay[4] = instructorId;
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";

		return longDisplay;
	}

	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (!(meetingDays.charAt(i) == 'M' || meetingDays.charAt(i) == 'T' || meetingDays.charAt(i) == 'W'
					|| meetingDays.charAt(i) == 'H' || meetingDays.charAt(i) == 'F' || meetingDays.charAt(i) == 'A')) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() > 1) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Checks to see if activity is a duplicate of the object
	 * 
	 * @param activity , the activity being checked
	 * @return true if is a duplicate, false if not
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			return ((Course) activity).getName().equals(getName());
		} else {
			return false;
		}

	}

	@Override
	public int compareTo(Course c1) {
		char course1[] = c1.getName().toCharArray();
		char course2[] = getName().toCharArray();
		for (int i = 0; i < course1.length; i++) {
			if (course1[i] != course2[i] && Character.isDigit(course1[i]) && Character.isDigit(course2[i])) {
				if (Character.getNumericValue(course1[i]) > Character.getNumericValue(course2[i])) {
					return -1;
				}
				if (Character.getNumericValue(course1[i]) < Character.getNumericValue(course2[i])) {
					return 1;
				}
			}
		}

		char section1[] = c1.getSection().toCharArray();
		char section2[] = getSection().toCharArray();
		for (int i = 0; i < section1.length; i++) {
			if (section1[i] != section2[i]) {
				if (Character.getNumericValue(section1[i]) > Character.getNumericValue(section2[i])) {
					return -1;
				}
				if (Character.getNumericValue(section1[i]) < Character.getNumericValue(section2[i])) {
					return 1;
				}
			}
		}

		return 0;

	}

	/**
	 * Gets the course roll
	 * 
	 * @return roll the course roll of the class
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

}
