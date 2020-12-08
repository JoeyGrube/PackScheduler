/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedStack implements Stack
 * @author Ana Ratanaphruks
 * @param <E> generic type of stacks
 */
public class LinkedStack<E> implements Stack<E> {
    /** Linked abstract list called stack */
    private LinkedAbstractList<E> stack;
    /**
     * Constructs linked stack object
     * @param capacity for capacity of list
     */
    public LinkedStack(int capacity) {
        stack = new LinkedAbstractList<E>(capacity);
    }
    
    /**
     * Adds element to the top of the stack
     * if there is no room, IllegalArgumentException is thrown
     * @param element for element in the Stacks list
     */
    @Override
    public void push(E element) {
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
        stack.setCapacity(capacity);
    }

}
