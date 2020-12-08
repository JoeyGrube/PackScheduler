/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack implements Stacks
 * @author Ana Ratanaphruks
 * @param <E> generic type of Stack lists
 */
public class ArrayStack<E> implements Stack<E> {

    /** Array List with type E */
    private ArrayList<E> stack;
    /** integer that represents the capacity of the list */
    int capacity;
    
    /**
     * Stack is set equal to ArrayList
     * @param capacity for the capacity of the ArrayStack
     */
    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        stack = new ArrayList<E>();
        this.capacity = capacity;
        setCapacity(capacity);
    }
    
    /**
     * Adds element to the top of the stack
     * if there is no room, IllegalArgumentException is thrown
     * @param element for element in the Stacks list
     */
    @Override
    public void push(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (size() == capacity) {
            throw new IllegalArgumentException();
        }
        stack.add(0, element);
    }

    /**
     * Removes and returns the element at the top of the stack
     * If Stack is empty, EmptyStackException() is thrown
     * @return E for element at the top of the stack
     */
    @Override
    public E pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        E old = stack.remove(0);
        return old;
    }

    /**
     * Returns true if list is empty
     * @return boolean for it empty or not
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returns the number of elements in the stack
     * @return int for the number of elements
     */
    @Override
    public int size() {
        return stack.size();
    }
    
    /**
     * Sets the stack's capacity
     * If actual parameter is negative or is less
     * than the number of elements IllegalArgumentException is thrown
     * @param capacity for capacity of stacks list
     */
    @Override
    public void setCapacity(int capacity) {
        if (capacity < 0 || capacity < stack.size()) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

}
