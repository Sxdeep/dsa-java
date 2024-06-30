package stack;

import java.util.Stack;

public class StringReverser {
    public static String reverse (String str) {
        if(str == null) {
            throw new IllegalArgumentException();
        }
        var stack = new Stack<String>();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            stack.push(String.valueOf(c));
        }
        StringBuilder reversedString = new StringBuilder();
        while(!stack.isEmpty()) {
            reversedString.append(stack.pop());
        }
        return reversedString.toString();
    }

}
