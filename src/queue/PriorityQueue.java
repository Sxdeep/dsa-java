package queue;

import java.util.Arrays;

public class PriorityQueue {
    private int[] items;
    private int size;

    public PriorityQueue() {
        items = new int[5];
        size = 0;
    }
    public void enQueue(int val) {
        if(size == items.length) throw new IllegalArgumentException();
        int i;
        for(i = size - 1; i >= 0; i--) {
            if(val < items[i]) {
                items[i + 1] = items[i];
            }
            else{
                break;
            }
        }
        items[i + 1] = val;
        size++;
    }
    public int deQueue() {
        if(isEmpty()) throw new IllegalArgumentException();
        var item = items[--size];
        items[size] = 0;
        return items[size];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int peek() {
        return items[size];
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
