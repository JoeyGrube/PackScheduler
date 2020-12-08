package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * Tests the FacultyRecordIO class
 * @author Parin
 *
 */
public class FacultyRecordIOTest {

	/** Valid faculty records */
	private final String validTestFile = "test-files/expected_faculty_records.txt";

	/**
	 * This method tests the reading of a file.
	 */
	@Test
	public void testreadFaculty() {

		LinkedList<Faculty> faculty = null;
		try {
			faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(3, faculty.size());
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		try {
			faculty = FacultyRecordIO.readFacultyRecords("test-files/student_records.txt");
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			assertNull(faculty);
		}

	}

	/**
	 * This method tests writing of a faculty object into a text file.
	 */
	@Test
	public void testWriteFaculty() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(faculty.size(), new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", 2));
		faculty.add(faculty.size(), new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", 3));
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/testFaculty", faculty);
			assertEquals(2, faculty.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
