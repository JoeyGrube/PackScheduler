/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for LinkedList class
 * @author Ana Ratanaphruks
 *
 */
public class LinkedListTest {

    private LinkedList<String> l;
    private String s0 = null;
    private String s1 = "Once upon a midnight dreary";
    private String s2 = "As I pondered weak and weary";
    private String s3 = "Over many a quaint and curious";
    private String s4 = "Volume of forgotten lore--";
    private String s5 = "While I nodded, nearly napping";

    /**
     * Sets up the LinkedList object l for testing.
     */
    @Before
    public void setUp() {
        l = new LinkedList<String>();
    }

    /**
     * Tests the LinkedList object's constructor method
     */
    @Test
    public void testLinkedList() {
        LinkedList<Object> l1 = new LinkedList<Object>();
        assertEquals(0, l1.size());
        assertEquals(-1, l1.listIterator().previousIndex());
        assertFalse(l.listIterator().hasPrevious());
        assertFalse(l.listIterator().hasNext());
        try {
            l1.listIterator().next();
            fail();
        } catch (Exception e) {
            //
        }
        try {
            l.listIterator().previous();
            fail();
        } catch (Exception e) {
            //
        }

    }

    /**
     * Tests the LinkedList object's size method
     */
    @Test
    public void testSize() {
        assertEquals(l.size(), 0);
        l.add(l.size(), s1);
        assertEquals(l.size(), 1);
    }

    /**
     * Tests the LinkedList object's add method using an index and an element
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

        // Add element when list is null
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
     * Tests the Linkedlist object's set() method
     */
    @Test
    public void testSetIntE() {
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
        l.listIterator().next();
        l.set(1, s4);
        assertEquals(3, l.size());
        assertEquals(s4, l.get(1));

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

    /**
     * Tests the remove functionality
     */
    @Test
    public void testRemove() {

        l.add(0, s1);
        l.add(1, s2);
        l.add(2, s3);
        l.add(3, s4);
        l.add(4, s5);

        l.remove(2);
        assertEquals(l.size(), 4);
        l.remove(0);
        assertEquals(l.size(), 3);
    }

    /**
     * Tests that the ListIterator fails correctly when lastRetrieved == null
     */
    @Test
    public void testNullLastRetrieved() {
        LinkedList<String> l1 = new LinkedList<String>();
        try {
            l1.listIterator().set(s1);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(0, l1.size());
        }

    }

    /**
     * Tests the get() functionality
     */
    @Test
    public void testGet() {
        l.add(0, s1);
        l.add(1, s2);
        l.add(2, s3);
        l.add(3, s4);
        l.add(4, s5);

        assertEquals(s1, l.get(0));
        try {
            l.get(5);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    /**
     * Tests the indexOf functionality
     */
    @Test
    public void testIndexOf() {

        l.add(0, s1);
        l.add(1, s2);
        l.add(2, s3);
        l.add(3, s4);
        l.add(4, s5);

        int indexOfs1 = l.indexOf(s1);
        int indexOfs3 = l.indexOf(s3);
        int indexOfs5 = l.indexOf(s5);
        assertEquals(0, indexOfs1); 
        assertEquals(2, indexOfs3);
        assertEquals(4, indexOfs5);
    }
    
    /**
     * Tests the lastIndexOf functionality
     */
    @Test
    public void testLastIndexOf() {

        l.add(0, s1);
        l.add(1, s2);
        l.add(2, s3);
        l.add(3, s4);
        l.add(4, s5);

        int indexOfs1 = l.lastIndexOf(s1);
        int indexOfs3 = l.lastIndexOf(s3);
        assertEquals(0, indexOfs1); 
        assertEquals(2, indexOfs3);
        
    }
}
