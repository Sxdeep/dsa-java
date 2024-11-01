package heap;

import java.util.Arrays;

public class MinHeap {
    public class Node {
        private int key;
        private String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node[] nodes;
    private int size;

    public MinHeap(int length) {
        nodes = new Node[length];
    }

    public void insert(int key, String value) {
        if(isFull()) throw new IllegalStateException();
        nodes[size] = new Node(key, value);
        size++;
        var index = size - 1;
        while(index > 0 && (nodes[index]).key < (nodes[parent(index)]).key) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public void remove() {
        if(isEmpty()) throw new IllegalStateException();
        var root = nodes[0];
        nodes[0] = nodes[size - 1];
        size--;
        var index = 0;
        while(index <= size && (nodes[index]).key > (nodes[getSmallerChildIndex(index)]).key) {
            var child = getSmallerChildIndex(index);
            swap(index, getSmallerChildIndex(index));
            index = child;
        }
    }

    private boolean isFull() {
        return size == nodes.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftIndex(int index) {
        return (index * 2) + 1;
    }

    private int rightIndex(int index) {
        return (index * 2) + 2;
    }

    private boolean hasLeftChild(int index) {
        return leftIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightIndex(index) < size;
    }

    private void swap(int index1, int index2) {
        var temp = (nodes[index2]);
        (nodes[index2]) = (nodes[index1]);
        (nodes[index1]) = temp;
    }

    private int getSmallerChildIndex(int index) {
        if(!hasLeftChild(index)) return index;

        if(!hasRightChild(index)) return leftIndex(index);

        return (nodes[rightIndex(index)]).key < (nodes[leftIndex(index)]).key ? rightIndex(index) : leftIndex(index);
    }

    public void printHeap() {
        int levels = (int) Math.ceil(Math.log(size + 1) / Math.log(2));
        int maxWidth = (int) Math.pow(2, levels) - 1;
        String[][] tree = new String[levels * 2 - 1][maxWidth * 2 + 1];

        for (String[] row : tree) {
            Arrays.fill(row, " ");
        }

        for (int i = 0; i < size; i++) {
            int level = (int) (Math.log(i + 1) / Math.log(2));
            int offset = i - (int) Math.pow(2, level) + 1;
            int position = (int) Math.pow(2, levels - level - 1) - 1 + offset * (int) Math.pow(2, levels - level);

            tree[level * 2][position * 2] = String.valueOf(nodes[i].key);

            if (leftIndex(i) < size) {
                tree[level * 2 + 1][position * 2 - (int) Math.pow(2, levels - level - 2)] = "/";
            }
            if (rightIndex(i) < size) {
                tree[level * 2 + 1][position * 2 + (int) Math.pow(2, levels - level - 2)] = "\\";
            }
        }

        for (String[] row : tree) {
            System.out.println(String.join("", row));
        }
    }
}
