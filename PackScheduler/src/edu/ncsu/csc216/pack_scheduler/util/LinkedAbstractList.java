package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a custom LinkedList specifically for use in the course roll
 * functionality
 * 
 * @author Kunal Kapoor
 *
 * @param <E> the type of object held by the LinkedAbstractList
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** max number of elements that the List may contain */
	private int capacity;
	/** number of elements currently in the list */
	private int size;
	/** an instance of a ListNode that refers to the front of the chained list */
	private ListNode front;
	/** instance of a ListNode that refers to the back of the chained list*/
	private ListNode back;

	/**
	 * Initializes the LinkedList, sets the capacity and sets it to empty
	 * 
	 * @param capacity capacity of the linkedlist (course roll)
	 */
	public LinkedAbstractList(int capacity) {
		setCapacity(capacity);
		front = null;
		this.size = 0;
	}

	/**
	 * Gets an element from the list.
	 * 
	 * @param index index of the element to retrieve
	 * @return the element at the index given.
	 * @throws IndexOutOfBoundsException if the index given is outside the bounds of
	 *                                   the list
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		int i = index;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		ListNode node = front;
		while (i > 0) {
			if (node == null) {
				throw new IndexOutOfBoundsException();
			}

			node = node.next;
			i--;
		}

		return node.data;
	}

	/**
	 * Returns the size of the list (# of elements in the list)
	 * 
	 * @return the amount of elements in the list.
	 */
	@Override
	public int size() {
		int count = 0;
		ListNode current = front;
		while (current != null) {
			count++;
			current = current.next;
		}
		size = count;
		return size;
	}

	/**
	 * Adds an element to the linked list
	 * 
	 * @param index the index at which the element is added
	 * @param e     the element being added
	 * @throws IllegalArgumentException  if the element to be added is already in
	 *                                   the list or if the list is already at
	 *                                   capacity
	 * @throws IndexOutOfBoundsException if the specified index for the new element
	 *                                   is not within the bounds of the list
	 * @throws NullPointerException      if the element to be added is null
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E e) {
		int i = index;
		if (size == capacity) {
			throw new IllegalArgumentException();
		}

		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}

		if (e == null) {
			throw new NullPointerException();
		}

		if (front == null) {
			front = new ListNode(e);
			back = front;
			size++;
		}

		else {
			ListNode base = front;
			while (base != null) {
				if (base.data.equals(e)) {
					throw new IllegalArgumentException();
				}
				base = base.next;
			}
			back = base;
			ListNode node = front;

			if (i == 0) {
				ListNode nnode = new ListNode(e, front);
				front = nnode;
				size++;
			}

			else {
				while (i - 1 > 0) {
					if (node == null) {
						throw new IndexOutOfBoundsException();
					}

					node = node.next;
					i--;
				}

				back = new ListNode(e);
				back.next = node.next;
				node.next = back;
				size++;
			}
		}
	}

	/**
	 * Removes an element from the list.
	 * 
	 * @param index index of element to removed from the list
	 * @throws IndexOutOfBoundsException if the specified index is not within the
	 *                                   bounds of the list
	 * @return the element that was removed.
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			ListNode removed = front;
			front = front.next;
			back = front;
			size--;
			return removed.data;
		}

		else {
			ListNode node = front;
			for (int i = 0; i < index - 1; i++) {
				node = node.next;
			}
			ListNode removed = node.next;
			node.next = node.next.next;
			size--;
			return removed.data;
		}
	}

	/**
	 * Sets the capacity of the list (course roll)
	 * 
	 * @param capacity capacity of the list (course roll)
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}

		this.capacity = capacity;
	}

	/**
	 * Sets an element at a specified index within the list
	 * 
	 * @param index the index that the element will be added to
	 * @param e     the element being added
	 * @return the element that was initially at the index
	 * @throws IllegalArgumentException  if the element to be set to is already in
	 *                                   the list
	 * @throws IndexOutOfBoundsException if the specified index for the element to
	 *                                   be set is not within the bounds of the list
	 * @throws NullPointerException      if the element to be set to is null
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E e) {
		int i = index;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		ListNode base = front;
		while (base != null) {
			if (base.data.equals(e)) {
				throw new IllegalArgumentException();
			}
			base = base.next;
		}

		ListNode node = front;

		if (node == null || e == null) {
			throw new NullPointerException();
		}

		if (i == 0) {
			E value = front.data;
			front.data = e;
			return value;
		}

		else {
			while (i > 0) {
				node = node.next;
				i--;
			}

			if (node == null) {
				throw new NullPointerException();
			}

			E value = node.data;
			node.data = e;
			return value;
		}
	}

	/**
	 * Builds the node for a linked list
	 * 
	 * @author Kunal Kapoor
	 */
	private class ListNode {
		/** the element of that specific node */
		private E data;
		/** the next element in the linkedlist */
		private ListNode next;

		/**
		 * Constructor for a node in the list with no reference to the next node in the
		 * list
		 * 
		 * @param data element of the node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * Constructor for a node in the list with a reference to the next node in the
		 * list
		 * 
		 * @param data element of the node
		 * @param next next node in the linkedlist
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}