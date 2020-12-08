/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;


import java.util.NoSuchElementException;

/**
 * ArrayQueue is queue of elements that acts as an array
 * @author Ana Ratanaphruks
 * @param <E> parameter of ArrayQueue
 *
 */
public class ArrayQueue<E> implements Queue<E> {
    /** array list called queue */
    private ArrayList<E> queue;
    /** integer for capacity of queue */
    int capacity;
    
    /**
     * Constructs new empty ArrayQueue
     * @param capacity for capacity of queue
     */
    public ArrayQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        queue = new ArrayList<E>();
        setCapacity(capacity);
        this.capacity = capacity;
    }

    /**
     * Adds element to back of queue
     * @param element for element to be added
     */
    @Override
    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (size() == capacity) {
            throw new IllegalArgumentException();
        }
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
     * Sets the Queue�s capacity
     * If the actual parameter is negative or if it is less than the number of elements,
     * IllegalArgumentException is thrown
     * @param capacity for capacity of queue list
     */
    @Override
    public void setCapacity(int capacity) {
        if (capacity < 0 || capacity < queue.size()) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }
    

}
