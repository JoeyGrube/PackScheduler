package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * Maintains a directory of all faculty at a directory. All faculty 
 * members have a unique id.
 * @author josephgrube
 *
 */
public class FacultyDirectory {
	
	//private FacultyDirectory state;
	/** The directory of staff memebrs*/
	private LinkedList<Faculty> facultyDirectory;
	/** Password hash algorithm*/
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructor
	 */
	public FacultyDirectory(){
		newFacultyDirectory();
	}
	
	/**
	 * Creates a new directory for faculty
	 */
	public void newFacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Loads faculty members from a file
	 * @param fileName the file being loaded
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
		facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file thisFileDoesntExist");
		}
		
	}
	
	/**
	 * Adds a faculty member to the directory
	 * @param firstName first name of the faculty member
	 * @param lastName last name of the faculty member
	 * @param id the id of the faculty member
	 * @param email the email of the faculty member
	 * @param password the password of the faculty member
	 * @param repeatPassword the password of the faculty member
	 * @param maxCourses the max courses a faculty member teaches
	 * @return true if it can be added, false if not
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses){
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());

			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		for(int i = 0; i < facultyDirectory.size(); i++) {
			Faculty user = facultyDirectory.get(i);
			if(user.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}
	
	/**
	 * Removes faculty members
	 * @param facultyId the id of the faculty member being removed
	 * @return true if the member is removed, false if not
	 */
	public boolean removeFaculty(String facultyId) {
		for(int i = 0; i < facultyDirectory.size(); i++) {
			if(facultyDirectory.get(i).getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates an array containing the data of the faculty members first and last name, as well as id
	 * @return faculty an array containing the data of the faculty members first and last name, as well as id
	 */
	public String[][] getFacultyDirectory(){
		String[][] faculty = new String[facultyDirectory.size()][3];
		for(int i = 0; i < facultyDirectory.size(); i++) {
			faculty[i][0] = facultyDirectory.get(i).getFirstName();
			faculty[i][1] = facultyDirectory.get(i).getLastName();
			faculty[i][2] = facultyDirectory.get(i).getId();
		}
		return faculty;
	}
	/**
	 * Saves the directory as a txt file
	 * @param str the name of the file being made
	 */
	public void saveFacultyDirectory(String str) {
		try {
			FacultyRecordIO.writeFacultyRecords(str, facultyDirectory);
		} catch(IOException e) {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Gets faculty members based on id
	 * @param str the id of the faculty member
	 * @return the faculty member
	 */
	public Faculty getFacultyById(String str){
		Faculty faculty = null;
		for(int i = 0; i < facultyDirectory.size(); i++) {
			if(facultyDirectory.get(i).getId().equals(str)) {
				faculty = facultyDirectory.get(i);
			}
		}
		return faculty;
	}
	
	

}
