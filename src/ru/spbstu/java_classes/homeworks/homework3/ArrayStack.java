package ru.spbstu.java_classes.homeworks.homework3;

import java.util.EmptyStackException;
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    private Object[] stack;
    private int pos = 0;

    public ArrayStack() {
        stack = new Object[0];
    }

    @Override
    public T push(T item) {
        Object[] newStack = new Object[stack.length + 1];
        System.arraycopy(stack, 0, newStack, 0, stack.length);

        newStack[stack.length] = item;
        stack = newStack;

        return item;
    }

    @Override
    public T pop() {
        if(stack.length == 0){
            throw new EmptyStackException();
        }
        else{
            Object returnValue = stack[stack.length - 1];

            Object[] newStack = new Object[stack.length - 1];
            System.arraycopy(stack, 0, newStack, 0, stack.length - 1);
            stack = newStack;

            return (T)returnValue;
        }
    }

    @Override
    public T peek() {
        if(stack.length == 0){
            throw new EmptyStackException();
        }
        else{
            return (T)stack[stack.length - 1];
        }
    }

    @Override
    public boolean isEmpty() {
        if(stack.length == 0){
            return true;
        }
        return false;
    }

    @Override
    public long getSize() {
        return stack.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
             int nextIndex = 0;

             @Override
             public boolean hasNext() {
                 return nextIndex < stack.length;
             }

             @Override
             public T next() {
                 return (T)stack[nextIndex++];
             }
         };
    }
}
