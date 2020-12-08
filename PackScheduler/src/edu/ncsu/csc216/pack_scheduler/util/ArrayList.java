package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a custom ArrayList specifically for use in the student schedule
 * 
 * @author josephgrube
 * @author Kunal Kapoor
 * @param <E> the element type of the ArrayList
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Initial size of the array */
	final static int INIT_SIZE = 10;
	/** The array that will be used in this class */
	private E[] list;
	/** the size of the array */
	private int size;

	/**
	 * Initializes the array, sets it to empty
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		this.list = (E[]) (new Object[INIT_SIZE]);

	}

	/**
	 * Gets an element from the array.
	 * 
	 * @return list[index] the element at the index given.
	 * @throws IndexOutOfBoundsException if the specified element is not within the
	 *                                   bounds of the list
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Gets how many elements are in the array.
	 * 
	 * @return the amount of elements in the array.
	 */
	@Override
	public int size() {
		int j = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				j++;
			}
		}
		this.size = j;
		return j;
	}

	/**
	 * Adds an element to the array
	 * 
	 * @param index the index at which the element is added
	 * @param e     the element being added
	 * @throws IllegalArgumentException  if the element to be added is already in
	 *                                   the list
	 * @throws IndexOutOfBoundsException if the specified index for the new element
	 *                                   is not within the bounds of the list
	 * @throws NullPointerException      if the element to be added is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E e) {
		if (index % 10 == 0 && size() > 9) {
			growArray();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (e == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null && list[i].equals(e)) {
				throw new IllegalArgumentException();
			}
		}

		E[] listr = (E[]) (new Object[size() + 1]);
		for (int i = 0; i < listr.length; i++) {
			if (i < index) {
				listr[i] = list[i];
			}
			if (i == index) {
				listr[i] = e;

			}
			if (i > index) {
				listr[i] = list[i - 1];
			}
		}
		this.list = listr;
		this.size = size();
		listr = null;
	}

	/**
	 * Expands the size of the array.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int newSize = list.length * 2;
		E[] listr = (E[]) (new Object[newSize]);
		for (int i = 0; i < list.length; i++) {
			listr[i] = list[i];
		}
		this.list = listr;
		listr = null;

	}

	/**
	 * Removes an element from the array.
	 * 
	 * @throws IndexOutOfBoundsException if the specified index is not within the
	 *                                   bounds of the list
	 * @return the element that was removed.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		E obj = null;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E[] listr = (E[]) (new Object[size() - 1]);
		for (int i = 0; i <= listr.length; i++) {
			if (i < index) {
				listr[i] = list[i];
			}
			if (i > index) {
				listr[i - 1] = list[i];
			}
			if (i == index) {
				obj = list[i];
			}

		}

		this.list = listr;
		this.size = size();
		listr = null;
		return obj;

	}

	/**
	 * Sets an element at a specified array index
	 * 
	 * @param index the index that the element will be added to
	 * @param e     the element being added
	 * @return obj the element that was initially at the index
	 * @throws IllegalArgumentException  if the element to be set to is already in
	 *                                   the list
	 * @throws IndexOutOfBoundsException if the specified index for the element to
	 *                                   be set is not within the bounds of the list
	 * @throws NullPointerException      if the element to be set to is null
	 */
	@SuppressWarnings("unchecked")
	public E set(int index, E e) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (e == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null && list[i].equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		E obj = null;
		E[] listr = (E[]) (new Object[list.length]);
		for (int i = 0; i < listr.length; i++) {
			if (i < index) {
				listr[i] = list[i];
			}
			if (i == index) {
				listr[i] = e;
				obj = list[i];
			}
			if (i > index) {
				listr[i] = list[i];
			}
		}
		this.list = listr;
		listr = null;
		return obj;

	}

}
