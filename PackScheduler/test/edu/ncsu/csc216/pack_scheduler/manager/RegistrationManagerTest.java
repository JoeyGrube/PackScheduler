package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Tests the Registration Manager class
 * 
 * @author Alex aksland
 * @author Kunal Kapoor
 * @author joseph Grube
 */
public class RegistrationManagerTest {

	/** First name of student used for testing */
	public static final String FIRST_NAME = "Alan";
	/** Last name of student used for testing */
	public static final String LAST_NAME = "Turing";
	/** ID of student used for testing */
	public static final String ID = "Aturing";
	/** Email of student used for testing */
	public static final String EMAIL = "Aturing@ncsu.edu";
	/** Password of student used for testing */
	public static final String PASSWORD = "h3h3";
	/** Max credits of student used for testing */
	public static final int MAX_CREDITS = 15;
	/** student class used for testing */
	public Student student;
	/** Contains the registrar's information (name, id, etc.) */
	private static final String PROP_FILE = "registrar.properties";
	/** test file containing a catalog to be used for testing */
	private final String validTestFile = "test-files/course_records.txt";
	/** instance of a Registration manager */
	private RegistrationManager manager;

	/**
	 * Sets up the Registration Manager and clears the catalog and student directory
	 * 
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests getting the course catalog, makes sure it's the valid catalog
	 */
	@Test
	public void testGetCourseCatalog() {
		manager = RegistrationManager.getInstance();
		// Test that the CourseCatalog is initialized to an empty list
		CourseCatalog c = new CourseCatalog();
		assertEquals(0, c.getCourseCatalog().length);
		c.loadCoursesFromFile(validTestFile);
		assertEquals(8, c.getCourseCatalog().length);
		manager.getCourseCatalog().loadCoursesFromFile(validTestFile);
		assertTrue(c.getCourseCatalog().length == manager.getCourseCatalog().getCourseCatalog().length);
		
	}

	/**
	 * Tests getting the student directory, asserts that it's the valid directory
	 */
	@Test
	public void testGetStudentDirectory() {
		manager = RegistrationManager.getInstance();
		// Test that the Student Directory is initialized to an empty list
		StudentDirectory c = new StudentDirectory();
		assertEquals(0, c.getStudentDirectory().length);
		c.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		manager.getStudentDirectory().addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertTrue(manager.getStudentDirectory().getStudentDirectory().length == c.getStudentDirectory().length);
		
	}

	/** Tests login for both a student and a registrar */
	@Test
	public void testLogin() {
		manager.logout();
		manager = RegistrationManager.getInstance();

		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot load properties file");
		}

		assertTrue(manager.getCurrentUser() == null);
		assertTrue(manager.login(prop.getProperty("id"), prop.getProperty("pw")));
		assertFalse(manager.login(prop.getProperty("id"), prop.getProperty("pw")));
		User m = manager.getCurrentUser();
		assertTrue(m.getFirstName().equals(prop.getProperty("first")));
		assertFalse(manager.login(m.getId(), m.getPassword()));
		manager.logout();
		assertNull(manager.getCurrentUser());
		manager.getStudentDirectory().addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertTrue(manager.login(ID, PASSWORD));
		User l = manager.getCurrentUser();
		assertTrue(m != null);
		assertTrue(l.getFirstName().equals(FIRST_NAME));
		manager.logout();
		assertNull(manager.getCurrentUser());
		assertTrue(manager.login(prop.getProperty("id"), prop.getProperty("pw")));
		manager.logout();
		try {
			assertFalse(manager.login("Ahodges", "h3h3h3"));
		} catch(IllegalArgumentException e) {
			assertNull(manager.getCurrentUser());
		}
	}

	/** Tests logout, makes sure the currentUser = null after logout */
	@Test
	public void testLogout() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot load properties file");
		}
		manager.login(prop.getProperty("id"), prop.getProperty("pw"));
		manager = RegistrationManager.getInstance();
		assertFalse(manager.getCurrentUser() == null);
		manager.logout();
		assertNull(manager.getCurrentUser());
	}

	/**
	 * Tests getCurrentUser, asserts that the current user is equal to the registrar
	 * specified in registratar.properties
	 */
	@Test
	public void testGetCurrentUser() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot load properties file");
		}

		manager = RegistrationManager.getInstance();
		manager.logout();
		User m = manager.getCurrentUser();
		assertTrue(m == null);
		assertTrue(manager.login(prop.getProperty("id"), prop.getProperty("pw")));
		manager = RegistrationManager.getInstance();
		m = manager.getCurrentUser();
		assertTrue(m.getFirstName().equals(prop.getProperty("first")));
		assertTrue(m.getLastName().equals(prop.getProperty("last")));
	}
	/**
	 * Tests RegistrationManager.enrollStudentInCourse()
	 */
	@Test
	public void testEnrollStudentInCourse() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //test if not logged in
	    try {
	        manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login("registrar", "Regi5tr@r");
	    try {
	        manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.", "registrar", manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(3, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(1, scheduleFrostArray.length);
	    assertEquals("CSC226", scheduleFrostArray[0][0]);
	    assertEquals("001", scheduleFrostArray[0][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleFrostArray[0][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleFrostArray[0][3]);
	    assertEquals("9", scheduleFrostArray[0][4]);
	            
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    //Check Student Schedule
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(10, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(3, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC226", scheduleHicksArray[1][0]);
	    assertEquals("001", scheduleHicksArray[1][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
	    assertEquals("8", scheduleHicksArray[1][4]);
	    assertEquals("CSC116", scheduleHicksArray[2][0]);
	    assertEquals("003", scheduleHicksArray[2][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
	    assertEquals("9", scheduleHicksArray[2][4]);
	    
	    manager.logout();
	}

	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //test if not logged in
	    try {
	        manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login("registrar", "Regi5tr@r");
	    try {
	        manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.", "registrar", manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    assertFalse("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: False - student not enrolled in the course", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(0, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(0, scheduleFrostArray.length);
	    
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(10, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(3, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC226", scheduleHicksArray[1][0]);
	    assertEquals("001", scheduleHicksArray[1][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
	    assertEquals("9", scheduleHicksArray[1][4]);
	    assertEquals("CSC116", scheduleHicksArray[2][0]);
	    assertEquals("003", scheduleHicksArray[2][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
	    assertEquals("9", scheduleHicksArray[2][4]);
	     
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(7, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(2, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC116", scheduleHicksArray[1][0]);
	    assertEquals("003", scheduleHicksArray[1][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[1][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
	    assertEquals("9", scheduleHicksArray[1][4]);
	    
	    assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(3, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(1, scheduleHicksArray.length);
	    assertEquals("CSC116", scheduleHicksArray[0][0]);
	    assertEquals("003", scheduleHicksArray[0][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(0, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(0, scheduleHicksArray.length);
	    
	    manager.logout();
	}

	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //Test if not logged in
	    try {
	        manager.resetSchedule();
	        fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login("registrar", "Regi5tr@r");
	    try {
	        manager.resetSchedule();
	        fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.", "registrar", manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    manager.resetSchedule();
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(0, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(0, scheduleFrostArray.length);
	    
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    manager.resetSchedule();
	    //Check Student schedule
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(0, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(0, scheduleHicksArray.length);
	    
	    manager.logout();
	}
	
	/**
	 * Testing for the FacultyDirectory functionality
	 */
	@Test
	public void facultyRegTest() {
		manager.login("registrar", "Regi5tr@r");
		FacultyDirectory fact = manager.getFacultyDirectory();
		fact.loadFacultyFromFile("test-files/faculty_records.txt");
		fact.addFaculty("Joey", "Grube", "Jegrube", "Jegrube@ncsu.edu", "pw", "pw", 3);
		assertEquals(fact.getFacultyDirectory()[0][0], "Ashely");
		manager.logout();
		manager.login("Jegrube", "pw");
		manager.logout();
		manager.login("registrar", "Regi5tr@r");
		Faculty f = new Faculty("Alex", "Jones", "Ajones", "Ajones@ncsu.edu", "pw", 3);
		Course c = new Course("ENG282", "Intro to Film", "005", 3, null, 15, "A");
		manager.addFacultyToCourse(c, f);
		manager.removeFacultyFromCourse(c, f);
		manager.resetFacultySchedule(f);
		manager.logout();
		manager.login("Jegrube", "pw");
		try {
			manager.addFacultyToCourse(c, f);
		} catch (IllegalArgumentException e) {
			// Nothing
		}
		try {
			manager.removeFacultyFromCourse(c, f);
		} catch (IllegalArgumentException e) {
			// How's your day been?
		}
		try {
			manager.resetFacultySchedule(f);
		} catch (IllegalArgumentException e) {
			// Nothing
		}
		
		
	
		
	}



}