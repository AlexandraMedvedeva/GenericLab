package ru.spbstu.java_classes.homeworks.homework3;

import java.util.EmptyStackException;
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    private Object[] stack;
    private int currentSize = 0;

    public ArrayStack() {
        stack = new Object[8];
    }

    private void setCapacity(int capacity){
        Object[] newStack = new Object[capacity];
        System.arraycopy(stack, 0, newStack, 0, currentSize);
        stack = newStack;
    }

    @Override
    public T push(T item) {
        if(stack.length == currentSize){
            setCapacity(stack.length * 2);
        }
        currentSize++;
        stack[currentSize - 1] = item;
        return item;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }

        if(currentSize < stack.length / 4){
            setCapacity(stack.length / 2);
        }

        T returnValue = (T)stack[currentSize - 1];
        stack[currentSize - 1] = null;

        int ans = (currentSize <= 0) ? currentSize = 0 : currentSize--;

        return (T)returnValue;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return (T)stack[currentSize - 1];
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public long getSize() {
        return currentSize;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
             int nextIndex = 0;

             @Override
             public boolean hasNext() {
                 return nextIndex < currentSize;
             }

             @Override
             public T next() {
                 return (T)stack[nextIndex++];
             }
         };
    }
}
