/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Tests conflict exception
 * @author josephgrube
 *
 */
public class ConflictExceptionTest {

        /**
         * Tests conflict exception string when a custom message is added
         */
		@Test
		public void testConflictExceptionString() {
		    ConflictException ce = new ConflictException("Custom exception message");
		    assertEquals("Custom exception message", ce.getMessage());
		}
		
		/**
         * Tests conflict exception string when a custom message is not added
         */
        @Test
        public void testConflictExceptionNoString() {
        	ConflictException ce = new ConflictException();
        	assertEquals("Schedule conflict.", ce.getMessage());
        }
	

}
