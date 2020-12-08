/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * The class tests Faculty
 * @author Ana Ratanaphruks
 *
 */
public class FacultyTest {
    
    /** Default first name of Faculty member */
    private static final String FIRST_NAME = "Ada";
    /** Default last name of Faculty member */
    private static final String LAST_NAME = "Lovelace";
    /** Default ID of Faculty Member */
    private static final String ID = "alovelace";
    /** Default email of Faculty member */
    private static final String EMAIL = "alovelace@ncsu.edu";
    /** Default password for Faculty member */
    private static final String PASSWORD = "password";
    /** Default max number of classes */
    private static final int MAX_COURSES = 2;

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#Faculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testFaculty() {
        // TEST COURSES//
        Course c0 = null;
        assertTrue(c0 == null);
        Course c1 = new Course("CSC116", "Intro to Java I", "001", 3, "smith", 10, "MWF", 1200, 1330);
        assertTrue(c1 != null);
        Course c2 = new Course("CSC216", "Intro to Java II", "001", 3, "doe", 10, "MWF", 1400, 1530);
        assertTrue(c2 != null);
        Course c3 = new Course("CSC226", "Discrete Math", "001", 3, "miller", 10, "TH", 1200, 1330);
        assertTrue(c3 != null);
        Course c4 = new Course("CSC116", "Intro to Java I", "001", 3, "smith", 10, "MWF", 1200, 1330); 
        assertTrue(c4 != null);
    }
    /**
     * Tests the Faculty creator across all fields
     */
    @Test
    public void testFacultyAllFields() {
        Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        assertEquals(FIRST_NAME, f1.getFirstName());
        assertEquals(LAST_NAME, f1.getLastName());
        assertEquals(EMAIL, f1.getEmail());
        assertEquals(PASSWORD, f1.getPassword());
        assertEquals(MAX_COURSES, f1.getMaxCourses());
        Faculty f2 = null;
        try {
            f2 = new Faculty(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

        try {
            f2 = new Faculty("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

        // Last Name Fail//
        try {
            f2 = new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_COURSES);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

        // ID Fail//
        try {
            f2 = new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_COURSES);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

        // ID Fail 2//
        try {
           f2 = new Faculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_COURSES);
           fail();
        } catch (IllegalArgumentException e) {
           assertNull(f2);
        }

        // Email Fail//
        try {
            f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_COURSES);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

        // Password Fail//
        try {
            f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_COURSES);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }
        // Max Credits Fail 0//
        try {
            f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 0);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

        // Max Credits Fail 4//
        try {
            f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 4);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(f2);
        }

    }

    /**
     * Tests setFirstName
     */
    @Test
    public void testSetFirstName() {
        // Construct a valid Faculty
        User s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        try {
            s.setFirstName(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FIRST_NAME, s.getFirstName());

        }

        try {
            s.setFirstName("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FIRST_NAME, s.getFirstName());

        }

        // Test that a valid string changes only firstName //
        s.setFirstName("Mary");
        assertEquals("Mary", s.getFirstName());

    }

    /**
     * Tests setLastName()
     */
    @Test
    public void testSetLastName() {
        User s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        try {
            s.setLastName(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FIRST_NAME, s.getFirstName());

        }

        try {
            s.setLastName("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(LAST_NAME, s.getLastName());
        }

        s.setLastName("Smith");
        assertEquals("Smith", s.getLastName());

    }

    /**
     * Tests setEmail
     */
    @Test
    public void testSetEmail() {
        User s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        try {
            s.setEmail(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(EMAIL, s.getEmail());
        }

        try {
            s.setEmail("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(EMAIL, s.getEmail());
        }

        try {
            s.setEmail("email@ncsudotedu");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(EMAIL, s.getEmail());
        }

        try {
            s.setEmail("emailAtncsue.edu");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(EMAIL, s.getEmail());
        }

        try {
            s.setEmail("my.email@ncsudotedu");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(EMAIL, s.getEmail());

        }
    }

    /**
     * Tests setPassword()
     */
    @Test
    public void testSetPassword() {
        User s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        try {
            s.setPassword(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(PASSWORD, s.getPassword());
        }

        try {
            s.setPassword("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(PASSWORD, s.getPassword());
        }
    }

    /**
     * Tests setMaxCreidts();
     */
    @Test
    public void testSetMaxCredits() {
        Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        try {
            s.setMaxCourses(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MAX_COURSES, s.getMaxCourses());
        }

        try {
            s.setMaxCourses(4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MAX_COURSES, s.getMaxCourses());
        }
    }


    /**
     * Tests whether the faculty memeber is overloaded
     */
    @Test
    public void testIsOverLoaded() {
        Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        
        assertFalse(f1.isOverloaded());

    }
    /**
     * Tests hashCode generation
     */
    @Test
    public void testHashCode() {
        User f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f3 = new Faculty("Grace", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f4 = new Faculty(FIRST_NAME, "Hopper", ID, EMAIL, PASSWORD, MAX_COURSES);
        User f5 = new Faculty(FIRST_NAME, LAST_NAME, "newId", EMAIL, PASSWORD, MAX_COURSES);
        User f6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "email@unc.edu", PASSWORD, MAX_COURSES);
        User f7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "newPassword", MAX_COURSES);
        User f8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 3);

        // Test equal hash values //
        assertEquals(f1.hashCode(), f2.hashCode());

        // Test unequal hash values across all parameters //
        assertNotEquals(f1.hashCode(), f3.hashCode());
        assertNotEquals(f1.hashCode(), f4.hashCode());
        assertNotEquals(f1.hashCode(), f5.hashCode());
        assertNotEquals(f1.hashCode(), f6.hashCode());
        assertNotEquals(f1.hashCode(), f7.hashCode());
        assertNotEquals(f1.hashCode(), f8.hashCode());

    }

    /**
     * Tests equals()
     */
    @Test
    public void testEqualsObject() {
        Object o1 = new String("Hello, World");

        User f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f3 = new Faculty("Grace", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f4 = new Faculty(FIRST_NAME, "Hopper", ID, EMAIL, PASSWORD, MAX_COURSES);
        User f5 = new Faculty(FIRST_NAME, LAST_NAME, "newId", EMAIL, PASSWORD, MAX_COURSES);
        User f6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "email@unc.edu", PASSWORD, MAX_COURSES);
        User f7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "newPassword", MAX_COURSES);
        User f8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 3);

        User s9 = null;

        // Test Object Get Class//
        assertNotEquals(f1, o1);

        // Test Equals itself//
        assertTrue(f1.equals(f1));

        // Test Equals for both directions //
        assertTrue(f1.equals(f2));
        assertTrue(f2.equals(f1));

        // Test Individual Fields//
        assertFalse(f1.equals(f3));
        assertFalse(f1.equals(f4));
        assertFalse(f1.equals(f5));
        assertFalse(f1.equals(f6));
        assertFalse(f1.equals(f7));
        assertFalse(f1.equals(f8));

        // Test Equals against null//
        assertFalse(f1.equals(s9));

    }

    /**
     * Tests toString()
     */
    @Test
    public void testToString() {
        User f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        String f2 = "Ada,Lovelace,alovelace,alovelace@ncsu.edu,password,2";

        assertEquals(f2, f1.toString());

        User f3 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        assertEquals(f2, f3.toString());
    }

    /**
     * Tests the compareTo() method to sort Facultys alphabetically by last
     * name, first name, and ID
     */
    @Test
    public void testCompareTo() {
        Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        Faculty f2 = new Faculty("Grace", "Hopper", "ghopper", "ghopper@ncsu.edu", PASSWORD, MAX_COURSES);
        Faculty f3 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        Faculty f4 = new Faculty("Zenia", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        Faculty f5 = new Faculty("Aayla", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        Faculty f6 = new Faculty(FIRST_NAME, LAST_NAME, "alovelace2", "alovelace2@ncsu.edu", PASSWORD, MAX_COURSES);
        Faculty f7 = new Faculty(FIRST_NAME, LAST_NAME, "alove", "alove@ncsu.edu", PASSWORD, MAX_COURSES);

        Faculty f8 = null;

        assertEquals(1, f1.compareTo(f2));
        assertEquals(0, f1.compareTo(f3));
        assertEquals(-1, f2.compareTo(f1));
        assertEquals(-1, f1.compareTo(f4));
        assertEquals(1, f1.compareTo(f5));
        assertEquals(-1, f1.compareTo(f6));
        assertEquals(1, f1.compareTo(f7));

        try {
            f1.compareTo(f8);
            fail();
        } catch (NullPointerException e) {
            assertNull(f8);
        }

    }

}

