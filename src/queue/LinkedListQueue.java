package queue;

import linkedList.Node;

import java.util.NoSuchElementException;

public class LinkedListQueue {
    private Node first;
    private Node last;
    private int size;

    public LinkedListQueue() {
        this.first = null;
        this.last = null;
        size = 0;
    }
    public void print(){
        var current = first;
        while(current != null){
            if(current.getNext() != null){
                System.out.print(current.getData() + " ");
            }
            else{
                System.out.println(current.getData());
            }
            current = current.getNext();
        }
    }
    public void push(int val) {
        var node = new Node(val);
        if(size == 0) {
            first = last = node;
        }
        else{
            last.setNext(node);
            last = node;
        }
        size++;
    }
    public int pop() {
        if(size == 0){
            throw new NoSuchElementException();
        }
        int value;
        if(first == last){
            var item = first.getData();
            first = last = null;
            value = item;
        }
        else{
            var temp = first;
            first = first.getNext();
            temp.setNext(null);
            value = temp.getData();
        }
        size--;
        return value;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int peek() {
        if(size == 0) throw new IllegalArgumentException();
        return first.getData();
    }
}
