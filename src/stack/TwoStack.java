package stack;

import java.util.EmptyStackException;

public class TwoStack {
    private int[] items;
    private int top1;
    private int top2;

    public TwoStack() {
        items = new int[10];
        top1 = 0;
        top2 = 9;
    }

    public void print() {
        for(int i = 0; i < 10; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public void print1() {
        for(int i = 0; i < top1; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public void print2() {
        for(int i = 9; i > top2 ; i--) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public void push1(int val) {
        if(top1 <= top2) {
            items[top1] = val;
            top1++;
        }
        else{
            System.out.println("Stack is full.");
        }

    }

    public void push2(int val) {
        if(top2 >= top1) {
            items[top2] = val;
            top2--;
        }
        else{
            System.out.println("Stack is full.");
        }

    }

    public int pop1() {
        if(top1 == 0) {
            throw new EmptyStackException();
        }
        else{
            return items[--top1];
        }
    }

    public int pop2() {
        if(top2 == 9) {
            throw new EmptyStackException();
        }
        else{
            return items[++top2];
        }
    }

    public boolean isFull1() {
        return top1 > top2;
    }

    public boolean isFull2() {
        return top2 < top1;
    }

    public boolean isEmpty1() {
        return top1 == 0;
    }

    public boolean isEmpty2() {
        return top2 == 9;
    }
}
