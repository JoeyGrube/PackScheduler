/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for LinkedQueue class
 * @author Ana Ratanaphruks
 *
 */
public class LinkedQueueTest {
    
    private LinkedQueue<String> queue;
    private String s1 = "Test string 1";
    private String s2 = "Test string 2";
    private String s3 = "Test string 3";
    private String s4 = "Test string 4";
    private String s5 = "Test string 5";
    private String s6 = "Test string 6";
    private int initCapacity = 5;

    /**
     * Sets up the ArrayStack object for testing
     */
    @Before
    public void setUp() {
        queue = new LinkedQueue<String> (initCapacity);
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#enqueue(java.lang.Object)}.
     */
    @Test
    public void testEnqueue() {
        assertEquals(queue.size(), 0);
        try {
            queue.enqueue(s1);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#dequeue()}.
     */
    @Test
    public void testDequeue() {
        assertEquals(queue.size(), 0);
        try {
            queue.dequeue();
            fail();
        } catch (NoSuchElementException e) {
            assertEquals(queue.size(), 0);
        }
        queue.enqueue(s1);
        queue.enqueue(s2);
        queue.enqueue(s3);
        queue.enqueue(s4);
        queue.enqueue(s5);
        assertEquals(queue.dequeue(), s1);
        assertEquals(queue.dequeue(), s2);
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(s1);
        assertFalse(queue.isEmpty());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(queue.size(), 0);
        queue.enqueue(s1);
        queue.enqueue(s2);
        queue.enqueue(s3);
        assertEquals(queue.size(), 3);
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#setCapacity(int)}.
     */
    @Test
    public void testSetCapacity() {
        queue.enqueue(s1);
        queue.enqueue(s2);
        queue.enqueue(s3);
        queue.enqueue(s4);
        queue.enqueue(s5);
        
        try {
            queue.enqueue(s6);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(queue.size(), 5);
        }
        
        queue.setCapacity(6);
        
        try {
            queue.enqueue(s6);
        } catch (Exception e) {
            fail();
        }
    }

}
