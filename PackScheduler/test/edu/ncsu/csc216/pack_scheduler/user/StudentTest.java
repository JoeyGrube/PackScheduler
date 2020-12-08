package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the student class
 * @author Joseph Grube
 */
public class StudentTest {

	/** First name used for testing*/
	public static final String FIRST_NAME = "Alan";
	/** Last name used for testing*/
	public static final String LAST_NAME = "Turing";
	/** ID used for testing*/
	public static final String ID = "Aturing";
	/** Email used for testing*/
	public static final String EMAIL = "Aturing@ncsu.edu";
	/** Password used for testing*/
	public static final String PASSWORD = "h3h3";
	/** Max credits used for testing*/
	public static final int MAX_CREDITS = 15;
	/** student class used for testing*/
	public Student student;
	
	/**
	 * Tests student to make sure it isn't null despite being properly constructed
	 */
	@Test
	public void setUp() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertFalse("Student is not null", student == null);
	}
	
	/**
	 * Tests constructor
	 */
	@Test
	public void testConstructor() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		int max = student.getMaxCredits();
		assertTrue("testConstructor", max == 18);
	}
	
	/**
	 * Tests for getFirstName
	 */
	@Test
	public void testGetFirstName() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("test getFirstName", student.getFirstName(), FIRST_NAME);
	}
	
	/**
	 * Tests for getLastName
	 */
	@Test
	public void testGetLastName() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("test getLastName", student.getLastName(), LAST_NAME);
	}
	
	/**
	 * Tests for getId
	 */
	@Test
	public void testGetID() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("test getId", student.getId(), ID);
	}
	
	/**
	 * Tests for getEmail
	 */
	@Test
	public void testGetEmail() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("test getEmail", student.getEmail(), EMAIL);
	}
	
	/**
	 * Tests for getPassword method
	 */
	@Test
	public void testGetPassword() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("test getPassword", student.getPassword(), PASSWORD);
	}
	
	/**
	 * Tests for equals method
	 */
	@Test
	public void testGetMaxCredits() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("test getMaxCredits", student.getMaxCredits(), MAX_CREDITS);
	}
	
	/**
	 * Tests for equals method
	 */
	@Test 
	public void testToString() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		String expected = FIRST_NAME + "," + LAST_NAME + "," + ID + "," + EMAIL + "," + PASSWORD + "," + MAX_CREDITS;
		assertEquals("test toString", student.toString(), expected);
	}
	
	/**
	 * Tests for equals method
	 */
	@Test
	public void testEqualsPtO() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User studentT = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertTrue("same refernce point", student.equals(studentT));
		assertTrue("same refernce point", student.equals(student));
		User studentH = new Student("Joseph", "Grube", "Jegrube", "Jegrube@ncsu.edu", "h4h4h4", MAX_CREDITS);
		assertFalse("Different refernce point", student.equals(studentH));
		User studentA = new Student("Alexander", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User studentB = new Student(FIRST_NAME, "Hamilton", ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertFalse("Different refernce point", student.equals(studentA));
		assertFalse("Different refernce point", student.equals(studentB));
		User studentC = new Student(FIRST_NAME, LAST_NAME, "Ahamilton", EMAIL, PASSWORD, MAX_CREDITS);
		assertFalse("Different refernce point", student.equals(studentC));
		User studentD = new Student(FIRST_NAME, LAST_NAME, ID, "Aehamil@ncsu.edu", PASSWORD, MAX_CREDITS);
		assertFalse("Different refernce point", student.equals(studentD));
	}
	
	/**
	 * Tests for equals method
	 */
	@Test
	public void testEqualsPtTw() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User nullStu = null;
		assertFalse("null", student.equals(nullStu));	
	}
	
	/**
	 * Tests for equals method
	 */
	@Test
	public void testEqualsPtTh() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		String studentS = "h";
		assertFalse("different object", student.equals(studentS));	
		
	}
	
	/**
	 * Tests for equals method
	 */
	@Test
	public void testEqualsPtFr() {
		student = null;
		User newStu = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertFalse("different object", newStu.equals(student));		
	}
	
	/**
	 * Tests for testSetEmail
	 */
	@Test
	public void testSetEmail() {
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			student.setEmail("Aturing.ncsu@edu");
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(student.getEmail().equals(EMAIL));
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			student.setEmail("Aturing.ncsu");
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(student.getEmail().equals(EMAIL));
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			student.setEmail("Aturing@ncsu_edu");
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(student.getEmail().equals(EMAIL));
		}
	}
	
	/**
	 * Tests for setMaxCredits method
	 */
	@Test
	public void testSetMaxCredits() {
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			student.setMaxCredits(2);
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(student.getMaxCredits() == MAX_CREDITS);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			student.setMaxCredits(22);
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(student.getMaxCredits() == MAX_CREDITS);
		}
	}
	
	/**
	 * Tests for hashcode method
	 */
	@Test
	public void testHashCode() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertTrue("Same class", student.hashCode() == student.hashCode());
		User studentTwo = new Student("Alexander", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertFalse("Different objects", student.hashCode() == studentTwo.hashCode());
		String h = "";
		assertFalse("Very different classes", student.hashCode() == h.hashCode());
	}
	
	/**
	 * Tests for failed constructors
	 */
	@Test
	public void testErrors() {
		try {
			student = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, "Aturing.ncsu@edu", PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 0);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 19);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		try {
			student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 22);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(student);
		}
		
		
	}
	
	/**
	 * Test compareTo() method.
	 */
	@Test
	public void testCompareTo() {
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student studentTwo = new Student("Alexander", "Hamilton", "Ahamil", EMAIL, PASSWORD, MAX_CREDITS);
		Student studentThr = new Student("Joey", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		//Student studentFr = new Student("Alexander", "Hamilton", ID, EMAIL, PASSWORD, MAX_CREDITS);
		//Student studentFv = new Student(FIRST_NAME, "Hamilton", ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("compareTo()", student.compareTo(studentTwo) != 0, true);
		assertTrue("Are the same", student.compareTo(student) == 0);
		assertTrue("Are not the same, value", student.compareTo(studentTwo) == 1);
		assertTrue("Flip student one and two", studentTwo.compareTo(student) == -1);
		assertEquals("Different last name, same everything else", student.compareTo(studentThr) == -1, true);
		//assertTrue("Different last name, and first name, same ID", student.compareTo(studentFr) == 0);
	}	
	
	/**
	 * Checks compareTo() if class being compared is null.
	 */
	@Test
	public void testCompareToError() {
		
		student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student studentTwo = null;
		int i = 100;
		try {
			i = student.compareTo(studentTwo);
		} catch(IllegalArgumentException e) {
		assertTrue("Value of i should be 100", i == 100);
		}
	}
	
	
}
