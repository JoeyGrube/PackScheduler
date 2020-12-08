package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman and Jackson Taylor
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}

				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * A helper method to get the information from a string and add it to a course
	 * object
	 * 
	 * @param nextLine string of the next line of the file
	 * @return the course given by the string
	 */
	private static Course readCourse(String nextLine) {

		Scanner nextLineReader = new Scanner(nextLine);
		nextLineReader.useDelimiter(",");

		try {
			String name = nextLineReader.next();
			String title = nextLineReader.next();
			String section = nextLineReader.next();
			int credits = nextLineReader.nextInt();
			String instructorId = nextLineReader.next();
			int enrollmentCap = nextLineReader.nextInt();
			String meetingDays = nextLineReader.next();

			if ("A".equals(meetingDays)) {
				if (nextLineReader.hasNext()) {
					nextLineReader.close();
					throw new IllegalArgumentException();
				}
				nextLineReader.close();
				Course c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
				RegistrationManager reg = RegistrationManager.getInstance();
				String[][] faculty = reg.getFacultyDirectory().getFacultyDirectory();
				for(int i = 0; i < faculty.length; i++) {
					if(faculty[i][2].equals(instructorId)) {
						reg.getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(c);
					}
				}
				return c;
			}

			int startTime = 0;
			int endTime = 0;

			if (nextLineReader.hasNext()) {
				startTime = nextLineReader.nextInt();
			}

			if (nextLineReader.hasNext()) {
				endTime = nextLineReader.nextInt();
			}

			Course c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime,
					endTime);
			RegistrationManager reg = RegistrationManager.getInstance();
			FacultyDirectory  dir = reg.getFacultyDirectory();
			String[][] faculty = dir.getFacultyDirectory();
			for(int i = 0; i < faculty.length; i++) {
				if(faculty[i][2].equals(instructorId)) {
					reg.getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(c);
				}
			}

			nextLineReader.close();
			return c;

		} catch (InputMismatchException e) {
			nextLineReader.close();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method to write courses to a file given a list of course objects and the name
	 * of the file.
	 * 
	 * @param fileName      string to name the created file
	 * @param courseCatalog an array list of courses
	 * @throws IOException if the process of writing to a file fails
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courseCatalog) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courseCatalog.size(); i++) {
			fileWriter.println(courseCatalog.get(i).toString());
		}

		fileWriter.close();
	}

}