import LinkedList.LinkedList;

public class Main {
    public static void main(String[] args) {
        var list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        //list.addLast(40);
        list.addFirst(5);
        //System.out.println(list.contains(5));
        //System.out.println(list.indexOf(5));
        //list.deleteLast();
        //list.deleteFirst();
        //list.reverse();
        //System.out.println(list.kth(2));
        list.getMiddleElement();
        list.print();
    }
}