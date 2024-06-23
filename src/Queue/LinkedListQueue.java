package Queue;

import LinkedList.Node;

import java.util.LinkedList;
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
    public void pop() {
        if(size == 0){
            throw new NoSuchElementException();
        }
        else if(first == last){
            first = last = null;
            return;
        }
        else{
            var temp = first;
            first = first.getNext();
            System.out.println(temp);
            temp.setNext(null);
        }
        size--;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int peek() {
      return first.getData();
    }
}
