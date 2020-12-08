/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Test class for the Course Catalog implementation
 * @author joseph grube and Jackson taylor
 *
 */
public class CourseCatalogTest {

	
	private final String validTestFile = "test-files/expected_course_records.txt";
	
	private static final String NAME = "CSC216";
	
	private static final String TITLE = "Java";
	
	private static final String SECTION = "049";
	
	private static final int CREDITS = 4;
	
	private static final String ID = "ccent";
	
	private static final String MD = "MWF";
		
	private static final int START = 1240;
	
	private static final int END = 1330;
	
	 private RegistrationManager mag;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_course_records.txt");
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
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("sesmith5", ""));
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.testNewCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		//Test that if there are courses in the directory, they 
		//are removed after calling newCourseCatalog().
		CourseCatalog cc = new CourseCatalog();
		
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(5, cc.getCourseCatalog().length);
		
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
				
		//Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(5, cc.getCourseCatalog().length);
		
		//test invalid file
		try {
			cc.loadCoursesFromFile("$$$");
			fail("Should have caught an error");
		} catch( IllegalArgumentException e) {
			assertEquals("Unable to read file $$$", e.getMessage());
		}
	}

	/**
	 * Tests CourseCatalog.addCourse().
	 */
	@Test
	public void testAddCourse() {
		CourseCatalog cc = new CourseCatalog();
		
		//Test valid Course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, ID, 12, MD, START, END);
		String [][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		
		//Test duplicate course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, ID, 12, MD, START, END);
		String impossible = null;
		try {
			impossible  = courseCatalog[1][0];
			fail("Should have caught an error");
		} catch(ArrayIndexOutOfBoundsException e) {
			//assertEquals("Index 1 out of bounds for length 1", e.getMessage());
			assertTrue(impossible == null);
		}
		
		//Test invalid password
		try {
			cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, ID, 12, "", START, END);
			fail("Should have caught an error");
		} catch( IllegalArgumentException e) {
			assertEquals("Invalid meeting days", e.getMessage());
		}
		
		try {
			cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, ID, 12, "", START, END);
			fail("Should have caught an error");
		} catch( IllegalArgumentException e) {
			assertEquals("Invalid meeting days", e.getMessage());
		}
					
	}
	
		
		

	/**
	 * Tests CourseCatalog.removeCourse().
	 */
	@Test
	public void testRemoveCourse() {
		CourseCatalog cc = new CourseCatalog();
				
		//Add courses and remove
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(5, cc.getCourseCatalog().length);
		assertTrue(cc.removeCourseFromCatalog("CSC116", "002"));
		String [][] courseCatalog = cc.getCourseCatalog();
		assertEquals(4, courseCatalog.length);
		assertEquals("CSC216", courseCatalog[1][0]);
		assertEquals("001", courseCatalog[1][1]);
		assertEquals("Programming Concepts - Java", courseCatalog[1][2]);
	}

	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		c.saveCourseCatalog("test-files/actual_course_records.txt");
		try {
			checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(c.getCourseCatalog().length, 5);
		//just a defined true
	}
	
	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		Course course = c.getCourseFromCatalog("CSC116", "002");
		String courseS = course.toString();
		assertEquals("getCourseFromCatalog", courseS, "CSC116,Intro to Programming - Java,002,3,spbalik,10,MW,1120,1310");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
				Scanner actScanner = new Scanner(new File(actFile));) {

			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
