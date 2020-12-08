package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Class that reads and writes student objects from ad to files.
 * @author Jackson
 * @author Rujula
 * @author Joseph
 *
 */
public class StudentRecordIO {

	
	/**
	 * Method to read Student objects from a file
	 * @param fileName name of the file to read
	 * @return an sorted list of objects of students
	 * @throws FileNotFoundException when the given file is not found at the given location
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		 Scanner fileReader = new Scanner(new FileInputStream(fileName));
		 SortedList<Student> students = new SortedList<Student>();
		    while (fileReader.hasNextLine()) {
		        try {
		            Student student = readStudent(fileReader.nextLine());
		            boolean duplicate = false;
		            for (int i = 0; i < students.size(); i++) {
		                User c = students.get(i);
		                if (student.getFirstName().equals(c.getFirstName()) &&
		                	student.getLastName().equals(c.getLastName()) &&
		                    student.getId().equals(c.getId())) {
		                    //it's a duplicate
		                    duplicate = true;
		                }
		                
		            }
		            if(!duplicate) {
		                students.add(student);
		            }
		            
		        } catch(IllegalArgumentException e) {
		            //skip the line
		        	fileReader.nextLine();
		        }
		    }
		    fileReader.close();
		    return students;
	}
	
	/**
	 * Helper method for readStudentRecords to separate each line into a student object
	 * @param line  a singular line of a file holding the information of a student object
	 * @return a student object
	 */
	private static Student readStudent(String line) {
		try {
			Scanner reader = new Scanner(line);
			 reader.useDelimiter(",");
			 
			 String firstName = reader.next();
			 
			 String lastName = reader.next();
			 
			 String id = reader.next();
			
			 String email = reader.next();
			
			 String pw = reader.next();
			 
			 int maxCredits = reader.nextInt();
			 
			 Student s = new Student(firstName, lastName, id, email, pw, maxCredits);
			 reader.close();
			return s;
		} catch(NoSuchElementException e) {
			throw new IllegalArgumentException("Incomplete Student");
		}
	}

	/**
	 * Method to write students to a file given a list of student objects and the name of the file.
	 * @param fileName string to name the created file
	 * @param studentDirectory an sorted list of students
	 * @throws IOException if the process of writing to a file fails
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {			
			PrintStream fileWriter = new PrintStream(new File(fileName));
			
			
			for (int i = 0; i < studentDirectory.size(); i++) {
				fileWriter.println(studentDirectory.get(i).toString());
			} 
			
			fileWriter.close();
	}
		
}


