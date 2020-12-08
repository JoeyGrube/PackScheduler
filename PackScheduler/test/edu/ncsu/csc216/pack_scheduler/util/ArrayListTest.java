package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.fail;



import org.junit.Test;
/**
 * Tests the ArrayList class
 * @author josephgrube
 *
 * @param <E> the type of object held by the ArrayList
 */
public class ArrayListTest<E> {
	/**
	 * Tests the ArrayList classes methods
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testArrayList() {
		ArrayList<Object> arrays;
		
		arrays = (ArrayList<Object>) new ArrayList<E>();
		assertEquals(arrays.size(), 0);
		arrays.add(0, "One");
		assertEquals(arrays.size(), 1);
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
		} catch(IndexOutOfBoundsException e) {
			assertEquals(arrays.get(0), "Three");
		}
		try {
			arrays.add(100, "One");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(arrays.get(0), "Three");
		}
		try {
			arrays.add(1, null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(arrays.get(0), "Three");
		}
		try {
			arrays.add(0, "Three");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(arrays.get(0), "Three");
		}
		arrays.add(1, "One");
		assertEquals(arrays.size(), 2);
		assertEquals(arrays.get(1), "One");
		arrays.add(2, "Two");
		assertEquals(arrays.size(), 3);
		assertEquals(arrays.get(2), "Two");
		arrays.add(3, "Num");
		assertEquals(arrays.size(), 4);
		assertEquals(arrays.get(3), "Num");
		arrays.add(4, "Four");
		assertEquals(arrays.size(), 5);
		assertEquals(arrays.get(4), "Four");
		arrays.add(5, "Five");
		assertEquals(arrays.size(), 6);
		assertEquals(arrays.get(5), "Five");
		arrays.add(6, "Six");
		assertEquals(arrays.size(), 7);
		assertEquals(arrays.get(6), "Six");
		arrays.add(7, "Seven");
		assertEquals(arrays.size(), 8);
		assertEquals(arrays.get(7), "Seven");
		arrays.add(8, "Eight");
		assertEquals(arrays.size(), 9);
		assertEquals(arrays.get(8), "Eight");
		arrays.add(9, "Nine");
		assertEquals(arrays.size(), 10);
		assertEquals(arrays.get(9), "Nine");
		arrays.add(10, "Ten");
		assertEquals(arrays.size(), 11);
		assertEquals(arrays.get(10), "Ten");
		arrays.set(10, "Eleven");
		assertEquals(arrays.get(10), "Eleven");
		
	}
}
