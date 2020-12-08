package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Control class of the project, creates instances of student directory and
 * course catalog and handles login/logout functionality with registrars and
 * students (users)
 * 
 * @author Kunal Kapoor
 *
 */
public class RegistrationManager {
	/** instance of Registration Manager */
	private static RegistrationManager instance;
	/** instance of a course catalog */
	private CourseCatalog courseCatalog;
	/** instance of a student directory */
	private StudentDirectory studentDirectory;
	/** A registrar user */
	private User registrar;
	/** currentUser in the system */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Contains the registrar's information (name, id, etc.) */
	private static final String PROP_FILE = "registrar.properties";
	/** The directory for faculty members */
	private FacultyDirectory faculty;

	/**
	 * Constructs the registrar and an empty course catalog and student directory
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		faculty = new FacultyDirectory();
	}

	/**
	 * Constructs the registrar using the information given in registrar.properties
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
			currentUser = null;
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/** Hashes the password based on the user's password */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns the instance of RegistrationManager, constructs a new instance if it
	 * is already null
	 * 
	 * @return instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the course catalog
	 * 
	 * @return course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the student directory
	 * 
	 * @return student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Returns T/F if the specified user was able to login to the system, only the
	 * specified registrar and students in the student directory can be logged in
	 * 
	 * @param id       id of user to be logged in
	 * @param password password of user to be logged in
	 * @return T/F if the user was able to be logged in
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
		Student s = studentDirectory.getStudentById(id);

		if (s != null) {
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		
		Faculty f = faculty.getFacultyById(id);
		if(s == null && f == null && !(registrar.getId().equals(id))) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		if(f != null) {
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		

		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}

		return false;
	}

	/**
	 * Logs out the current user, and sets currentUser to null
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns the current user of the scheduler system
	 * 
	 * @return current user of scheduler system
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Sets the course catalog and studentDirectory to empty
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		faculty.newFacultyDirectory();
	}

	/** Registrar class, a child class of User */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the information specified in the
		 * registrar.properties
		 * 
		 * @param firstName first name of registrar
		 * @param lastName  last name of registrar
		 * @param id        id of registrar
		 * @param email     email of registrar
		 * @param hashPW    hashed password of registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
		
	}
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	/**
	 * Gets the FacultyDirectory
	 * @return faculty the FacultyDirectory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return faculty;
	}
	
	/**
	 * Adds a course to faculty schedule
	 * @param c course being added
	 * @param f faculty member who's schedule is being adjusted
	 * @return true if the course can be added, false if not
	 * @throws IllegalArgumentException if currentUser is null or not the registrar
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if(currentUser == null || currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if(currentUser != null && currentUser == registrar) {
			return f.getSchedule().addCourseToSchedule(c);
		}
		return false;
	}
	/**
	 * Resets the given faculty members schedule
	 * @param f the faculty member who's schedule is being cleared
	 */
	public void resetFacultySchedule(Faculty f) {
		if(currentUser == null || currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if(currentUser != null && currentUser == registrar) {
			f.getSchedule().resetSchedule();
		}
		
	}
	/**
	 * Removes a course to faculty schedule
	 * @param c course being removed
	 * @param f faculty member who's schedule is being adjusted
	 * @return true if the course can be removed, false if not
	 * @throws IllegalArgumentException if currentUser is null or not the registrar
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if(currentUser == null || currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if(currentUser != null && currentUser == registrar) {
			return f.getSchedule().removeCourseFromSchedule(c);
		}
		return false;
	}
	

}