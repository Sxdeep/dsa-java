package stack;

import java.util.Stack;

public class BalancedExpressions {
    public static boolean bracketCheck(String str) {
        var stack = new Stack<Character>();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '(' || c == '[' || c == '{' || c == '<') {
                stack.push(c);
            }
            else if(c == ')' && stack.pop() != '(') {
                return false;
            }
            else if(c == ']' && stack.pop() != '[') {
                return false;
            }
            else if(c == '}' && stack.pop() != '{') {
                return false;
            }
            else if(c == '>' && stack.pop() != '<') {
                return false;
            }
            else{
                continue;
            }
        }
        return stack.isEmpty();
    }
}
