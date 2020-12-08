/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Test to work with files that contain catalogs of available courses for students
 * @author joseph grube and jackson taylor
 *
 */
public class CourseCatalog {
	
	/** A sorted list of courses*/
	private SortedList<Course> courseCatalog;	
	
	/**
	 * Course catalog setup
	 */
	public CourseCatalog() {
		newCourseCatalog(); 
	}
	
	/**
	 * Method to make a new catalog
	 */
	public void newCourseCatalog() {
		courseCatalog = new SortedList<Course>();
	}
	
	/**
	 * Get a catalog from a file
	 * @param fileName name of the file
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Method to add a course to the catalog
	 * @param name of the course
	 * @param title of the course
	 * @param section of the course
	 * @param credits of the course
	 * @param instructorId of the course
	 * @param meetingDays of the course
	 * @param startTime of the course
	 * @param endTime of the course
	 * @param enrollmentCap of the course
	 * @return if the course was added
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
				return false;
			}
		}
		return courseCatalog.add(course);
	}
	
	/**
	 * Remove a course from the catalog
	 * @param name of the course
	 * @param section of the course
	 * @return if it was removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				courseCatalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets a course object from the catalog
	 * @param name of the course
	 * @param section of the course
	 * @return a course object
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < courseCatalog.size(); i++) {
			if(courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
				return courseCatalog.get(i);
			}
		}
		return null;
		
	}
	
	/**
	 * Gets the parameters of the object in a 2D array
	 * @return a 2D array of object's parameters
	 */
	public String[][] getCourseCatalog() {
		String [][] directory = new String[courseCatalog.size()][5];
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			directory[i][0] = c.getName();
			directory[i][1] = c.getSection();
			directory[i][2] = c.getTitle();
			directory[i][3] = c.getMeetingString(); //meeting string or days?
			directory[i][4] = "" + c.getCourseRoll().getOpenSeats();
		} 
		return directory;
	}
	
	/**
	 * Method to save the catalog to a text file
	 * @param fileName name of the file
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, courseCatalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}