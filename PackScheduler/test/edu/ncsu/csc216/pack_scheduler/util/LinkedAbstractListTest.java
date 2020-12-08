package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the LinkedAbstractList class
 * @author Kunal Kapoor
 *
 * @param <E> the type of object held by the LinkedAbstractList
 */
public class LinkedAbstractListTest<E> {
	
	/** test capacity of the LinkedAbstractList */
	private static final int CAPACITY = 5;
	
	/**
	 * Tests the ArrayList classes methods
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testArrayList() {
		LinkedAbstractList<String> arrays;
		arrays = (LinkedAbstractList<String>) new LinkedAbstractList<E>(CAPACITY);
		assertEquals(arrays.size(), 0);
		arrays.add(0, "One");
		assertEquals(1, arrays.size());
		assertEquals(arrays.get(0), "One");
		arrays.add(1, "Two");
		assertEquals(arrays.get(1), "Two");
		assertEquals(arrays.size(), 2);
		arrays.remove(0);
		assertEquals(arrays.get(0), "Two");
		arrays.set(0, "Three");
		assertEquals(arrays.get(0), "Three");
		try {
			arrays.add(-1, "One");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(arrays.get(0), "Three");
		}
		try {
			arrays.add(100, "One");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(arrays.get(0), "Three");
		}
		try {
			arrays.add(1, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(arrays.get(0), "Three");
		}
		try {
			arrays.add(0, "Three");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(arrays.get(0), "Three");
		}
		arrays.add(1, "One");
		assertEquals(arrays.size(), 2);
		assertEquals(arrays.get(1), "One");
		try {
			arrays.add(3, "x");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(arrays.get(1), "One");
		}
		arrays.add(2, "Two");
		assertEquals(arrays.size(), 3);
		assertEquals(arrays.get(2), "Two");
		arrays.add(3, "Num");
		assertEquals(arrays.size(), 4);
		assertEquals(arrays.get(3), "Num");
		try {
			arrays.add(4, "Num");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(arrays.get(3), "Num");
		}
		arrays.add(4, "Four");
		assertEquals(arrays.size(), 5);
		assertEquals(arrays.get(4), "Four");
		
		try {
			arrays.add(5, "five");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(arrays.get(4), "Four");
		}
		
		try {
			arrays.remove(8);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(arrays.get(4), "Four");
		}
		
		arrays.remove(2);
		assertEquals(arrays.get(2), "Num");
		assertEquals(arrays.size(), 4);
		
		try {
			arrays.set(2, "Num");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(arrays.get(3), "Four");
		}
		
		try {
			arrays.set(-1, "Number");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(arrays.get(3), "Four");
		}
		
		try {
			arrays.set(4, "Number");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(arrays.get(3), "Four");
		}
		
		assertEquals("Num", arrays.set(2, "Number"));
		assertEquals(arrays.get(2), "Number");
		try {
			arrays.set(2, null);
		} catch (NullPointerException e) {
			assertEquals(arrays.get(2), "Number");
		}
		
		try {
			arrays.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(arrays.size(), 4);
		}
		
		LinkedAbstractList<String> arrays2;
		arrays2 = (LinkedAbstractList<String>) new LinkedAbstractList<E>(CAPACITY);
		assertEquals(arrays2.size(), 0);
		arrays2.add(0, "Orange");
		assertEquals(arrays2.size(), 1);
		arrays2.add(0, "Apple");
		assertEquals(arrays2.get(0), "Apple");
		assertEquals(arrays2.get(1), "Orange");
		assertEquals(arrays2.size(), 2);
	}
}
