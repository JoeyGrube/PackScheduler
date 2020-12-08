/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue type of list
 * @author Ana Ratanaphruks
 * @param <E> Generic parameter of the Queue
 *
 */
public interface Queue<E> {
    /**
     * Adds the element to the back of the Queue
     * If there is no room (capacity has been reached), 
     * an IllegalArgumentException is thrown
     * @param element for element to be added
     */
    void enqueue(E element);
    
    /**
     * Removes and returns the element at the front of the Queue
     * If the Queue is empty, an NoSuchElementException is thrown
     * @return E for element at the front of the queue
     */
    E dequeue();
    
    /**
     * Returns true if the Queue is empty
     * @return boolean for if queue is empty
     */
    boolean isEmpty();
    
    /**
     * Returns the number of elements in the Queue
     * @return integer for number of elements in queue list
     */
    int size();
    
    /**
     * Sets the Queues capacity
     * If the actual parameter is negative or if it is less than the number of elements,
     * IllegalArgumentException is thrown
     * @param capacity for capacity of queue list
     */
    void setCapacity(int capacity);
    
    

}
