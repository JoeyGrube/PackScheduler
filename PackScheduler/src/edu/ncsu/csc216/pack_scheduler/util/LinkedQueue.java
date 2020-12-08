/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * LinkedQueue is a queue like a linkedlist
 * @author Ana Ratanaphruks
 * @param <E> parameter for queue
 *
 */
public class LinkedQueue<E> implements Queue<E> {
    /** linked abstract list called queue */
    private LinkedAbstractList<E> queue;

    /**
     * Constructs linked queue object
     * @param capacity for capacity of list
     */
    public LinkedQueue(int capacity) {
        queue = new LinkedAbstractList<E>(capacity);
    }
    
    /**
     * Adds element to back of queue
     * @param element for element to be added
     */
    @Override
    public void enqueue(E element) {
        queue.add(queue.size(), element);        
    }

    /**
     * Removes element from front of queue
     * @return E for element at front
     */
    @Override
    public E dequeue() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        E old = queue.remove(0);
        return old;
    }

    /**
     * Returns if queue is empty or not
     * @return boolean for if queue is empty
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns size of queue
     * @return integer for the size of the queue
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * Sets the Queues capacity
     * If the actual parameter is negative or if it is less than the number of elements,
     * IllegalArgumentException is thrown
     * @param capacity for capacity of queue list
     */
    @Override
    public void setCapacity(int capacity) {
        queue.setCapacity(capacity);
    }

}
