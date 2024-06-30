package Tree;

public class Tree {
    private class Node{
        private final int data;
        private Node rightChild;
        private Node leftChild;

        public Node(int data) {
            this.data = data;
        }
    }
    private Node root;

    public void insert(int data) {
        Node node = new Node(data);
        if(root == null) {
            root = node;
            return;
        }

        var current = root;

        while(true) {
            if(data < current.data) {
                if(current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            }
            else {
                if(current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }
    public boolean find(int data) {
        if(root.data == data) {
            return true;
        }

        var current = root;

        while(current.data != data) {
            if(data < current.data) {
                if(current.leftChild == null) {
                    return false;
                }
                current = current.leftChild;
            }
            else{
                if(current.rightChild == null) {
                    return false;
                }
                current = current.rightChild;
            }
        }
        return true;
    }
}
