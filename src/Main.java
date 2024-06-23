import Queue.PriorityQueue;
import Queue.QueueReverser;
import Queue.StackQueue;
import Queue.ArrayQueue;
import Stacks.QueueStack;

import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        var q = new QueueStack();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        System.out.println(q.pop());
        System.out.println(q);


    }
}