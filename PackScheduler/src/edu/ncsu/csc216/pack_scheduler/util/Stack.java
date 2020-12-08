package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Stack list where elements are added/removed from the top
 * @author Ana Ratanaphruks
 * @param <E> generic type of Stacks
 */
public interface Stack<E> {
    /**
     * Adds element to the top of the stack
     * if there is no room, IllegalArgumentException is thrown
     * @param element for element in the Stacks list
     */
    void push(E element);
    
    /**
     * Removes and returns the element at the top of the stack
     * If Stack is empty, EmptyStackException() is thrown
     * @return E for element at the top of the stack
     */
    E pop();
    
    /**
     * Returns true if list is empty
     * @return boolean for it empty or not
     */
    boolean isEmpty();
    
    /**
     * Returns the number of elements in the stack
     * @return int for the number of elements
     */
    int size();
    
    /**
     * Sets the stack's capacity
     * If actual parameter is negative or is less
     * than the number of elements IllegalArgumentException is thrown
     * @param capacity for capacity of stacks list
     */
    void setCapacity(int capacity);

}
