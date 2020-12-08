/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class creates a LinkedList that can be iterated through
 * @author Ana Ratanaphruks
 * @param <E> generic parameter
 *
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
    /** Represents ListNode element at the front of the list */
    ListNode front;
    /** Represents ListNode element at the back of the list */
    ListNode back;
    /** Represents size of list */
    int size;
    
    /**
     * Constructs a new empty LinkedList object
     */
    public LinkedList() {
        front = new ListNode(null);
        back = new ListNode(null, front, null);
        front.next = back;
        size = 0;
    }
    
    /**
     * Returns new LinkedListIterator
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        LinkedListIterator it = new LinkedListIterator(index);
        return it;
    }

    /**
     * Returns size of list
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Allows for elements to be added at specific indexes
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();

        }
        for (int i = 0; i < size; i++) {
            if (get(i).equals(element)) {
                throw new IllegalArgumentException();
            }
        }

        LinkedListIterator it = new LinkedListIterator(index);
        it.add(element);

    }
    /**
     * Allows for indexes to be set to certain elements
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < size; i++) {
            if (get(i).equals(element)) {
                throw new IllegalArgumentException();
            }
        }

        LinkedListIterator it = new LinkedListIterator(index);
        
        E replaced = it.next(); 
        it.set(element);
        return replaced;
    }
    
    
    /**
     * This is an inner class of LinkedList that defines the ListNodes
     * @author Ana Ratanaphruks
     */
    private class ListNode {
        /** public data element type e */
        public E data;
        /** next ListNode element */
        public ListNode next;
        /** previous ListNode element */
        public ListNode prev;
        
        /**
         * Constructs new ListNode  
         * @param data element of the node
         */
        ListNode(E data) {
            this.data = data;
            next = null;
            prev = null;
        }
        /**
         * Constructs a ListNode and defines the element, next and previous ListNode
         * @param data element of ListNode
         * @param prev previous ListNode
         * @param next next ListNode
         */
        ListNode(E data, ListNode prev, ListNode next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    /**
     * Inner class of LinkedList that traverses through elements
     * @author Ana Ratanaphruks
     */
    private class LinkedListIterator implements ListIterator<E> {
        /** Represents previous ListNode */
        ListNode previous;
        /** Represents next ListNode */
        ListNode next;
        /** represents previous index of ListNode */
        int previousIndex;
        /** Represents next index of the ListNOde */
        int nextIndex;
        /** most recent ListNode that was called */
        ListNode lastRetrieved;
        /**
         * Constructor for LinkedListIterator
         * @param index where to iterate in list
         */
        public LinkedListIterator(int index) { 
            if (index < 0 || index > size()) {
                throw new IndexOutOfBoundsException();
            }
            previous = front;
            next = previous.next;
            previousIndex = index - 1;
            nextIndex = index;

            if (index != 0) {
                for (int i = 0; i < index; i++) {
                    previous = next;
                    next = next.next;

                }  
            }
             
            lastRetrieved = null;
        }
        /**
         * Returns if list has next element
         */
        @Override
        public boolean hasNext() {
            return (next.data != null);
        }
        /**
         * Returns next element
         */
        @Override
        public E next() {
            if (next.data == null) {
                throw new NoSuchElementException();
            }
            lastRetrieved = next;
            E returnMe = next.data;
            previousIndex++;
            nextIndex++;
            next = next.next; 
            return returnMe;
        }
        /**
         * Returns if list has a previous element
         */
        @Override
        public boolean hasPrevious() {
            if (previous.data == null) {
                return false;
            }
            return true;
        }
        /**
         * Returns previous element
         */
        @Override
        public E previous() {
            if (previous.data == null) {
                throw new NoSuchElementException();
            }
            lastRetrieved = previous;
            previousIndex--;
            nextIndex--;
            previous = previous.prev;
            return previous.data;
        }
        /**
         * Returns the next index in the list
         */
        @Override
        public int nextIndex() {
            if (next == null) {
                return size; 
            }
            return nextIndex - 1;
        }
        /**
         * Returns the previous index in the list
         */
        @Override
        public int previousIndex() {
            if (previous == null) {
                return -1;
            }
            return previousIndex;
        }
        /**
         * Removes element in list
         */
        @Override
        public void remove() {
            if (lastRetrieved == null) {
                throw new IllegalStateException();
            }
            lastRetrieved.prev.next = next;
            next = next.next;

            size--;
        }
        /**
         * Sets elements in the list
         */
        @Override
        public void set(E e) {
            if (lastRetrieved == null) {
                throw new IllegalStateException();
            }
            if (e == null) {
                throw new NullPointerException();
            }

            lastRetrieved.data = e;
        }
        /**
         * Allows for elements to be added to the list
         */
        @Override
        public void add(E e) {
            if (e == null) {
                throw new NullPointerException();
            }
            ListNode newNode = new ListNode(e);
            next.prev = newNode;
            newNode.next = next;
            newNode.prev = previous;

            previous.next = newNode;
 
            size++;
            lastRetrieved = null; 
        }
        
    }

}
