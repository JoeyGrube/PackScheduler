package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Tests CouresRecordIO.
 * @author SarahHeckman
 */
public class CourseRecordIOTest {

	/** Valid course records */
	private final String validTestFile = "test-files/expected_course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Expected results for valid courses */
	private final String validCourse1 = "CSC116,Intro to Programming - Java,001,3,jdyoung2,10,MW,910,1100";
	private final String validCourse2 = "CSC116,Intro to Programming - Java,002,3,spbalik,10,MW,1120,1310";
	private final String validCourse3 = "CSC216,Programming Concepts - Java,001,4,sesmith5,10,TH,1330,1445";
	private final String validCourse4 = "CSC216,Programming Concepts - Java,002,4,jtking,10,MW,1330,1445";
	private final String validCourse5 = "CSC226,Discrete Mathematics for Computer Scientists,001,3,tmbarnes,10,MWF,935,1025";
	
	/** Array to hold expected results */
	private final String [] validCourses = {validCourse1, validCourse2, validCourse3, validCourse4,
			validCourse5};
	private RegistrationManager mag;

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if there is a problem with setup
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
		mag = RegistrationManager.getInstance();
		mag.getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records.txt");
		
	}	
	
	/**
	 * Tests to see if Course Record is able to be built and compares it to null.
	 */
	@Test
	public void testCourseRecordIO() {
		CourseRecordIO io = new CourseRecordIO();
		CourseRecordIO nullIo = null;
		assertFalse(io.equals(nullIo));
	}
	
	/**
	 * Tests readValidCourseRecords().
	 */
	@Test
	public void testReadValidCourseRecords() {
		try {
			SortedList<Course> courses = CourseRecordIO.readCourseRecords(validTestFile);
			assertEquals(5, courses.size());
			
			for (int i = 0; i < validCourses.length; i++) {
				assertEquals(validCourses[i], courses.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Tests readInvalidCourseRecords().
	 */
	@Test
	public void testReadInvalidCourseRecords() {
		SortedList<Course> courses;
		try {
			courses = CourseRecordIO.readCourseRecords(invalidTestFile);
			
			assertEquals(0, courses.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Tests writeCourseRecords()
	 */
	@Test
	public void testWriteCourseRecords() {
		SortedList<Course> courses = new SortedList<Course>();
		courses.add(new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100));
		courses.add(new Course("CSC116", "Intro to Programming - Java", "002", 3, "spbalik", 10, "MW", 1120, 1310));
		courses.add(new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445));
		courses.add(new Course("CSC216", "Programming Concepts - Java", "002", 4, "jtking", 10, "MW", 1330, 1445));
		courses.add(new Course("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", 10, "MWF", 935, 1025));
		
		try {
			CourseRecordIO.writeCourseRecords("test-files/actual_course_records.txt", courses);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File(expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}