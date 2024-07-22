package heap;

import java.util.ArrayList;

public class MaxHeap {
    private int[] heap;
    private int count;

    public MaxHeap() {
        this.heap = new int[10];
        this.count = 0;
    }

    public boolean isFull() {
        return count == heap.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(int value) {
        if(isFull()) throw new IllegalStateException();

        heap[count] = value;
        bubbleUp(count);
        count++;
    }

    private void bubbleUp(int index) {
        if(index == 0) return;
        if(heap[index] > heap[parent(index)]) {
            var temp = heap[parent(index)];
            heap[parent(index)] = heap[index];
            heap[index] = temp;
            index = parent(index);
            bubbleUp(index);
        }
    }

    public void remove() {
        if(isEmpty()) throw new IllegalStateException();

        heap[0] = heap[count - 1];
        count--;
        bubbleDown(0);
    }

    private void bubbleDown(int index) {
        if(index <= count || leftIndex(index) <= count || rightIndex(index) <= count) return;
        if(heap[index] < heap[leftIndex(index)] || heap[index] < heap[rightIndex(index)]) {
            if(heap[leftIndex(index)] > heap[rightIndex(index)]) {
                var temp = heap[index];
                heap[index] = heap[leftIndex(index)];
                heap[leftIndex(index)] = temp;
                index = leftIndex(index);
            }
            else{
                var temp = heap[index];
                heap[index] = heap[rightIndex(index)];
                heap[rightIndex(index)] = temp;
                index = rightIndex(index);
            }
            bubbleDown(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftIndex(int index) {
        return (index * 2) + 1;
    }

    private int rightIndex(int index) {
        return (index * 2) + 2;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(heap[i]);
        }
    }
    
    private int getLargerChildIndex(int index) {
        if(hasLeftChild(index)) {
            return index;
        }
        if(hasRightChild(index)) {
            return leftIndex(index);
        }
        return heap[rightIndex(index)] > heap[rightIndex(index)] ? rightIndex(index) : leftIndex(index);
    }
    
    private boolean hasLeftChild(int index) {
        return leftIndex(index) < count;
    }
    
    private boolean hasRightChild(int index) {
        return rightIndex(index) < count;
    }

}
