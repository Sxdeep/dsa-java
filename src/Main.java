import graph.Graph;
import graph.WeightedGraph;
import tries.Tries;
import tries.TriesUsingArray;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "D", 4);
        graph.addEdge("D", "C", 5);
        graph.addEdge("C", "A", 1);
        graph.addEdge("C", "B", 2);
        graph.getMST().print();

    }

    public static int leftIndex(int index) {
        return (index * 2) + 1;
    }
    public static int rightIndex(int index) {
        return (index * 2) + 2;
    }

    public static void heapify(ArrayList<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) < list.get(getLargerChildIndex(i, list))) {
                swap(i, getLargerChildIndex(i, list), list);
            }
        }
    }

    public static int getLargerChildIndex(int index, ArrayList<Integer> list) {
        if(!hasLeftChild(index, list)) {
            return index;
        }
        if(!hasRightChild(index, list)) {
            return leftIndex(index);
        }
        return list.get(rightIndex(index)) > list.get(leftIndex(index)) ? rightIndex(index) : leftIndex(index);
    }

    public static boolean hasLeftChild(int index, ArrayList<Integer> list) {
        return leftIndex(index) < list.size();
    }

    public static boolean hasRightChild(int index, ArrayList<Integer> list) {
        return rightIndex(index) < list.size();
    }

    public static void swap(int index1, int index2, ArrayList<Integer> list) {
        var temp = list.get(index2);
        list.set(index2, list.get(index1));
        list.set(index1, temp);
    }

    public static boolean isMaxHeap(ArrayList<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) < list.get(getLargerChildIndex(i, list))) {
                return false;
            }
        }
        return true;
    }
}