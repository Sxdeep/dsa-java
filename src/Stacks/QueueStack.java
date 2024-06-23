package Stacks;

import java.util.ArrayDeque;

public class QueueStack {
    private ArrayDeque<Integer> queue1;
    private ArrayDeque<Integer> queue2;
    private int size;

    public QueueStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
        size = 0;
    }
    public void push(int val) {
        queue1.add(val);
        size++;
    }
    public int pop() {
        while(queue1.size() > 1) {
            queue2.add(queue1.remove());
        }
        var temp = queue1.remove();
        while(!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }
        size--;
        return temp;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int peek() {
        while(queue1.size() > 1) {
            queue2.add(queue1.remove());
        }
        var temp = queue1.remove();
        while(queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }
        queue1.add(temp);
        return temp;
    }
    @Override
    public String toString() {
        return queue1.toString();
    }
}
