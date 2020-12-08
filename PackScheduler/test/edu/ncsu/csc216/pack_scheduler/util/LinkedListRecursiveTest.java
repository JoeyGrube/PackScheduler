/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for LinkedListRecursive
 * @author Ana Ratanaphruks
 *
 */
public class LinkedListRecursiveTest {
    
    private LinkedListRecursive<String> l;
    private String s0 = null;
    private String s1 = "Once upon a midnight dreary";
    private String s2 = "As I pondered weak and weary";
    private String s3 = "Over many a quaint and curious";
    private String s4 = "Volume of forgotten lore--";
    private String s5 = "While I nodded, nearly napping";
    
    /**
     * Sets up the LinkedListRecursive object for testing
     * @throws Exception if doesn't work
     */
    @Before
    public void setUp() throws Exception {
        l = new LinkedListRecursive<String>();
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        assertFalse(l.contains(s0));
        assertFalse(l.contains(s1)); 
        l.add(s1);
        l.add(s2);
        l.add(s3);
        l.add(s4); 
        l.add(s5);
        
        assertTrue(l.contains(s1));
        assertTrue(l.contains(s3));
        assertTrue(l.contains(s5));
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#LinkedListRecursive()}.
     */
    @Test
    public void testLinkedListRecursive() {
        LinkedListRecursive<String> l1 = new LinkedListRecursive<String>();
        assertEquals(0, l1.size());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(l.isEmpty());
        l.add(s1);
        assertFalse(l.isEmpty());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(0, l.size());
        l.add(s1);
        assertEquals(1, l.size());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(java.lang.Object)}.
     */
    @Test
    public void testAddE() {
        try {
            l.add(s0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, l.size());
        }

        try {
            l.add(s1);
        } catch (Exception e) {
            fail();
        }

        try {
            l.add(s2);
        } catch (Exception e) {
            fail();
        }

        try {
            l.add(s3);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(int, java.lang.Object)}.
     */
    @Test
    public void testAddIntE() {
        try {
            l.add(0, s0);
            fail();
        } catch (NullPointerException e) {
            assertEquals(0, l.size());
        }
        try {
            l.add(-1, s1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, l.size());
        }
        try {
            l.add(2, s1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, l.size());
        }

        // Add element when list is empty
        l.add(0, s1);
        assertEquals(1, l.size());
        assertEquals(s1, l.get(0));

        // Add element
        l.add(1, s2);
        assertEquals(2, l.size());
        assertEquals(s1, l.get(0));
        assertEquals(s2, l.get(1));

        l.add(2, s3);
        assertEquals(3, l.size());
        assertEquals(s1, l.get(0));
        assertEquals(s2, l.get(1));
        assertEquals(s3, l.get(2)); 

        l.add(3, s4);
        assertEquals(4, l.size());
        assertEquals(s1, l.get(0)); 
        assertEquals(s2, l.get(1));
        assertEquals(s3, l.get(2));
        assertEquals(s4, l.get(3));

        try { 
            l.add(4, s2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(4, l.size());
            assertEquals(s1, l.get(0));
            assertEquals(s2, l.get(1));
            assertEquals(s3, l.get(2));
            assertEquals(s4, l.get(3));
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#get(int)}.
     */
    @Test
    public void testGet() {
        l.add(s1);
        l.add(s2);
        l.add(s3);
        l.add(s4);
        l.add(s5);

        try {
            l.get(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(5, l.size());
        }

        try {
            l.get(5);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // Move on
        }

        assertEquals(s3, l.get(2));
        assertEquals(s1, l.get(0));
        assertEquals(s5, l.get(4));
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveE() {
        assertFalse(l.remove(s1));
        l.add(s1);
        l.add(s2);
        l.add(s3);
        l.add(s4);
        l.add(s5);

        // Test Remove at the middle of the list
        try {
            l.remove(s3);
            assertEquals(4, l.size());
            assertEquals(s1, l.get(0));
            assertEquals(s2, l.get(1));
            assertEquals(s4, l.get(2));
            assertEquals(s5, l.get(3));
        } catch (Exception e) {
            fail();
        }
        // Test Remove at the end of the list
        try {
            l.remove(s5);
            assertEquals(3, l.size());
            assertEquals(s1, l.get(0));
            assertEquals(s2, l.get(1));
            assertEquals(s4, l.get(2));
        } catch (Exception e) {
            fail();
        }
        // Test Remove at the start of the list
        try {
            l.remove(s1);
            assertEquals(2, l.size());
            assertEquals(s2, l.get(0));
            assertEquals(s4, l.get(1));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
     */
    @Test
    public void testRemoveInt() {
        try {
            l.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, l.size());
        }

        l.add(s1);
        l.add(s2);
        l.add(s3);
        l.add(s4);
        l.add(s5);

        // Test remove at the middle of the list
        try {
            l.remove(2);
            assertEquals(4, l.size());
            assertEquals(s1, l.get(0));
            assertEquals(s2, l.get(1));
            assertEquals(s4, l.get(2));
            assertEquals(s5, l.get(3));

        } catch (Exception e) {
            fail();
        }
        // Test remove at the end of the list
        try {
            l.remove(3);
            assertEquals(3, l.size());
            assertEquals(s1, l.get(0));
            assertEquals(s2, l.get(1));
            assertEquals(s4, l.get(2));
        } catch (Exception e) {
            fail();
        }

        // Test remove at the beginning of the list
        try {
            l.remove(0);
            assertEquals(2, l.size());
            assertEquals(s2, l.get(0));
            assertEquals(s4, l.get(1));

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#set(int, java.lang.Object)}.
     */
    @Test
    public void testSet() {
        try {
            l.set(0, s1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, l.size());
        }

        l.add(0, s1);
        l.add(1, s2);
        l.add(2, s3);

        assertEquals(3, l.size());
        l.set(1, s4);
        assertEquals(3, l.size());
        assertEquals(s4, l.get(1));
        l.set(0, s5);
        assertEquals(3, l.size());
        assertEquals(s5, l.get(0));
        assertEquals(s4, l.get(1));
        assertEquals(s3, l.get(2));

        try {
            l.set(1, s0);
            fail();
        } catch (NullPointerException e) {
            assertEquals(3, l.size());
        }

        try {
            l.set(5, s5);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(3, l.size());
        }
    }

}
