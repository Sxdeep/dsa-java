package queue;

import java.util.ArrayDeque;
import java.util.Stack;

public class QueueReverser {
    public static ArrayDeque<Integer> reverse(int k, ArrayDeque<Object> queue) {
        if(k > queue.size() || k < 0) throw new IllegalArgumentException();

        var stack = new Stack<Integer>();
        var que = new ArrayDeque<Integer>();
        int i;
        for(i = 0; i < k; i++) {
            stack.push((Integer) queue.remove());
        }
        while(!stack.isEmpty()) {
            que.add(stack.pop());
        }
        while(!queue.isEmpty()) {
            que.add((Integer) queue.remove());
        }
        return que;
    }
}
