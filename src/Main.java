import Tree.Tree;


public class Main {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    public static int factorial(int value) {
        int factorial = 1;
        for(int i = 1; i <= value; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }
}