/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This class is used to write the faculty records into a text file and read the
 * text file containing elements of Faculty class in the form of toString()
 * method.
 * 
 * @author parinpatel
 *
 */
public class FacultyRecordIO {
	/**
	 * This method reads Faculty object from the text file.
	 * 
	 * @param fileName name of the file to read
	 * @return a LinkedList of FacultyRecords
	 * @throws FileNotFoundException when the given file is not found at the given
	 *                               location
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyRecords = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < facultyRecords.size(); i++) {
					User c = facultyRecords.get(i);
					if (faculty.getFirstName().equals(c.getFirstName()) && faculty.getLastName().equals(c.getLastName())
							&& faculty.getId().equals(c.getId())) {
						// it's a duplicate
						duplicate = true;
					}

				}
				if (!duplicate) {
					facultyRecords.add(facultyRecords.size(), faculty);
				}

			} catch (IllegalArgumentException e) {
				// skip the line
				if(fileReader.hasNext()) {
					fileReader.nextLine();
				}
			}
		}
		fileReader.close();
		return facultyRecords;
	}

	/**
	 * Helper method for readFacultyRecords to separate each line into a Faculty
	 * object
	 * 
	 * @param line a singular line of a file holding the information of a student
	 *             object
	 * @return a Faculty object
	 */
	private static Faculty processFaculty(String line) {
		try {
			Scanner reader = new Scanner(line);
			reader.useDelimiter(",");

			String firstName = reader.next();

			String lastName = reader.next();

			String id = reader.next();

			String email = reader.next();

			String pw = reader.next();

			int maxCredits = reader.nextInt();

			Faculty faculty = new Faculty(firstName, lastName, id, email, pw, maxCredits);
			reader.close();
			return faculty;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid Faculty");
		}
	}

	/**
	 * Method to write Faculty objects to a file given a list of Faculty objects and
	 * the name of the file.
	 * 
	 * @param fileName       string to name the created file
	 * @param facultyRecords LinkedList of facultyRecords
	 * @throws IOException if the process of writing to a file fails
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyRecords) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyRecords.size(); i++) {
			fileWriter.println(facultyRecords.get(i).toString());
		}

		fileWriter.close();
	}

}
