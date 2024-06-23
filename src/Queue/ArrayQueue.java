package Queue;


import java.util.Arrays;

public class ArrayQueue {
    private int[] queue;
    private int first;
    private int last;
    private int size;

    public ArrayQueue() {
        queue = new int[5];
        first = 0;
        last = 0;
        size = 0;
    }

    public void enQueue(int val) {
        if(size == queue.length) throw new IllegalArgumentException();
        if(last == queue.length) {
            last = 0;
        }
        queue[last] = val;
        last++;
        size++;
    }

    public int deQueue() {
        if(first == queue.length) {
            first = 0;
        }
        int item = queue[first];
        size--;
        queue[first++] = 0;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        return queue[first];
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }

}

/*
public static void reverse(Queue<Integer> q) {
        // allowed methods
        // add | remove | isEmpty
        var stack = new Stack<Integer>();
        while(!q.isEmpty()) {
            stack.push(q.remove());
        }
        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }
        System.out.println(q);
 */