package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests the methods in the FacultyDirectory class
 * @author josephgrube
 *
 */
public class FacultyDirectoryTest {
// nothing yet
	/**
	 * Tests the methods in FacultyDirectory
	 */
	@Test
	public void test() {
		FacultyDirectory facultyDir = new FacultyDirectory();
		facultyDir.loadFacultyFromFile("test-files/faculty_records.txt");
		assertEquals(facultyDir.getFacultyDirectory()[0][0].equals("Ashely"), true);
		facultyDir.addFaculty("Joey", "Grube", "jegrube", "jegrube@ncsu.edu", "pw", "pw", 3);
		assertEquals(facultyDir.getFacultyById("jegrube").getFirstName(), "Joey");
		facultyDir.removeFaculty("jegrube");
		assertNull(facultyDir.getFacultyById("jegrube"));
		facultyDir.saveFacultyDirectory("test-files/testFacDir");
		
		
	}
}
