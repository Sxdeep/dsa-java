package stack;

import java.util.EmptyStackException;

public class Stack {
    private int[] items;
    private int count;

    public Stack() {
        items = new int[10];
        count = 0;
    }
    public void push(int val) {
        if(count != items.length) {
            items[count] = val;
            count++;
        }
        else{
            throw new StackOverflowError();
        }
    }
    public int pop() {
        if(count == 0) {
            throw new EmptyStackException();
        }
        else{
            return items[--count];
        }
    }
    public int peek() {
        if(count == 0) {
            throw new EmptyStackException();
        }
        return items[count - 1];
    }
    public boolean isEmpty() {
        if(count == 0) {
            return true;
        }
        return false;
    }
    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
}
