package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of a sorted list
 * @author Jackson Taylor
 *
 */
public class SortedListTest {

	/**
	 * Tests the sorted list functionality
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		
		assertEquals(11, list.size());
		
	}

	/**
	 * Tests adding to a sorted list
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		
		//TODO Test adding to the front, middle and back of the list
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		
		assertEquals("apple", list.get(0));
		assertEquals("azerbaijan", list.get(1));
		assertEquals("car", list.get(3));
		assertEquals(4, list.size());
		
		//TODO Test adding a null element
		try {
			list.add(null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		
		
		//TODO Test adding a duplicate element
		try {
			list.add("apple");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("Element already in list.", e.getMessage());
		}
		
		
	}
	
	/**
	 * Tests getting from a sorted list
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		assertEquals(0, list.size());
		try {
			list.get(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//TODO Add some elements to the list
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		
		//TODO Test getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
		//TODO Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
	}
	
	/**
	 * Tests removing from a sorted list
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//TODO Add some elements to the list - at least 4
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		list.add("banana");
		
		//TODO Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//TODO Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//TODO Test removing a middle element
		list.remove(1);
		assertEquals(3, list.size());
		
		//TODO Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(2, list.size());
		
		//TODO Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		
		
		//TODO Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests finding the index of an elemnt in a sorted list
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));
			
		
		
		//TODO Add some elements
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		list.add("banana");
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(2, list.indexOf("banana"));
		assertEquals(3, list.indexOf("car"));
		
		assertEquals(-1, list.indexOf("gerry"));
		
		assertEquals(-1, list.indexOf("not apple"));
		
		//TODO Test checking the index of null
		assertEquals(-1, list.indexOf("null"));
	}
	
	/**
	 * Tests clearing a sorted list
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		list.add("banana");
		
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests if a sorted list is empty
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//TODO Add at least one element
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		list.add("banana");
		
		//TODO Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests if a sorted list contains an element
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertFalse(list.contains("apple"));
		
		//TODO Add some elements
		list.add("apple");
		list.add("azerbaijan");
		list.add("car");
		list.add("banana");
		
		//TODO Test some true and false cases
		assertFalse(list.contains("angle"));
		assertFalse(list.contains("apples"));
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("banana"));
	}
	
	/**
	 * Tests if a sorted list equals another
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		
		list2.add("apple");
		list2.add("banana");
		
		list3.add("azerbaijan");
		list3.add("car");
		
		
		//TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
	}
	
	/**
	 * Tests a sorted list's hashcode functionality
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		
		list2.add("apple");
		list2.add("banana");
		
		list3.add("azerbaijan");
		list3.add("car");
		
		//TODO Test for the same and different hashCodes
		assertTrue(list1.hashCode() == list2.hashCode());
		assertFalse(list1.hashCode() == list3.hashCode());
	}

}
 