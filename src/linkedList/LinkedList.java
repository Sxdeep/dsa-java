package linkedList;

import java.util.NoSuchElementException;

public class LinkedList {
    private Node first;
    private Node last;
    private int size;

    public LinkedList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    public void print(){
        var current = first;
        while(current != null){
            if(current.getNext() != null){
                System.out.print(current.getData() + " -> ");
            }
            else{
                System.out.println(current.getData());
            }
            current = current.getNext();
        }
    }

    public void addLast(int data){
        var node = new Node(data);
        if(size == 0){
            first = node;
            last = node;
        }
        else{
            last.setNext(node);
            last = node;
        }
        size++;

    }

    public void addFirst(int data){
        var node = new Node(data);
        if(size == 0){
            first = node;
            last = node;
        }
        else{
            node.setNext(first);
            first = node;
        }
        size++;
    }
    public boolean contains(int data){
        var current = first;
        while(current != null){
            if(current.getData() != data){
                current = current.getNext();
            }
            else{
                return true;
            }
        }
        return false;
    }
    public int indexOf(int data){
        var position = 1;
        var current = first;
        while(current != null){
            if(current.getData() != data){
                position++;
                current = current.getNext();
            }
            else{
                return position;
            }
        }
        return -1;
    }
    public void deleteLast(){
        var current = first;
        if(size == 0){
            throw new NoSuchElementException();
        }
        else if(first == last){
            first = last = null;
            return;
        }
        else{
            while(current.getNext() != null){
                last = current;
                current = current.getNext();
            }
        }
        last.setNext(null);
        size--;
    }
    public void deleteFirst(){
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
            temp.setNext(null);

        }
        size--;
    }
    public void reverse(){
        if(size == 0){
            return;
        }
        var current = first;
        first = last;
        Node prev = null;
        while(current != null){
            var next = current.getNext();
            current.setNext(prev);
            prev = current;
            if(current.getNext() == null){
                last = current;
            }
            current = next;
        }
    }
    public int kth(int data){
        var current = first;
        var k = 1;
        while(current != null){
            if((size - data) + 1 == k){
                return current.getData();
            }
            else{
                k++;
                current = current.getNext();
            }
        }
        return -1;
    }
    public void getMiddleElement() {
        var a = first;
        var b = first;
        while (b != last && b.getNext() != last) {
            a = a.getNext();
            b = b.getNext().getNext();
        }
        if(b == last){
            System.out.println(a.getData());
        }
        else{
            System.out.println(a.getData());
            System.out.println(a.getNext().getData());
        }
    }
    public boolean hasLoop() {
        Node a = first;
        Node b = first;

        while(b != null || b.getNext() != null){
            a = a.getNext();
            b = b.getNext().getNext();
            if(a == b) {
                return true;
            }
        }
        return false;

    }
}
