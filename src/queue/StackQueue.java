package queue;

import java.util.Stack;

public class StackQueue {
    private Stack<Integer> stack;

    public StackQueue() {
        stack = new Stack<>();
    }
    public void enQueue(int val) {
        stack.push(val);
    }

    public int deQueue() {
        Stack<Integer> stack2;
        stack2 = new Stack<>();
        while(!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        var item = stack2.pop();
        while(!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }
        return item;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
