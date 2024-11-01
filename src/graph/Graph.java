package graph;

import java.util.*;

public class Graph {
    private class Node {
        private String label;

        public Node(String lable) {
            this.label = lable;
        }
        @Override
        public String toString() {
            return this.label;
        }
    }

    private HashMap<String, Node> nodes;
    private HashMap<Node, List<Node>> adjacencyList;

    public Graph() {
        this.nodes = new HashMap<>();
        this.adjacencyList = new HashMap<>();
    }

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new LinkedList<>());
    }

    public void removeNode(String lable) {
        var node = nodes.get(lable);
        if(node == null) return;

        else{
            nodes.remove(node);
            adjacencyList.remove(node);
            for(var source : adjacencyList.keySet()) {
                adjacencyList.get(source).remove(node);
;            }
        }
    }

    public void addEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if(fromNode == null) throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if(toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if(fromNode == null) throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if(toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void print() {
        for(var source : adjacencyList.entrySet()) {
            if(!source.getValue().isEmpty()){
                System.out.println(source.getKey() + " is connected with " + source.getValue());
            }
        }
    }

    public void recursiveDFS(String label) {
        var visited = new HashSet<Node>();
        var node = nodes.get(label);
        if(node == null) return;
        recursiveDFS(node, visited);
    }

    private void recursiveDFS(Node root, HashSet<Node> visited) {
       if(visited.contains(root)) return;

       visited.add(root);
       System.out.print(root + " ");
       for(var target : adjacencyList.get(root)) {
           recursiveDFS(target, visited);
       }
    }

    public ArrayList<String> topologicalSort() {
        var stack = new Stack<String>();
        var visited = new HashSet<Node>();
        for(var node : nodes.values()) {
            topologicalSort(node, stack, visited);
        }
        var list = new ArrayList<String >();
        while(!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    private void topologicalSort(Node root, Stack<String> stack, HashSet<Node> visited) {
        if(visited.contains(root)) return;
        visited.add(root);
        for(var neighbour : adjacencyList.get(root)) {
            topologicalSort(neighbour, stack, visited);
        }
        stack.push(root.label);
    }

    public boolean hasCycle() {
        boolean hasCycle = false;
        var visiting = new HashSet<Node>();
        var visited = new HashSet<Node>();

        for(var node : nodes.values()) {
            hasCycle |= hasCycle(node, visiting, visited);
        }
        return hasCycle;
    }

    private boolean hasCycle(Node root, HashSet<Node> visiting, HashSet<Node> visited) {
        if(visited.contains(root)) return false;
        visiting.add(root);

        for(var neighbour : adjacencyList.get(root)) {
            if(visiting.contains(neighbour)) return true;

            if(hasCycle(neighbour, visiting, visited)) return true;
        }

        visited.add(root);
        visiting.remove(root);

        return false;
    }

    public void iterativeDFS(String label) {
        var node = nodes.get(label);
        if(node == null) throw new IllegalArgumentException();

        var visited = new HashSet<Node>();
        var stack = new Stack<Node>();
        stack.push(node);

        while (!stack.isEmpty()) {
            var current = stack.pop();
            visited.add(current);
            System.out.print(current + " ");
            for(var target : adjacencyList.get(current)) {
                if(!visited.contains(target)) {
                    stack.push(target);
                }
            }
        }
    }

    public void iterativeBFS(String label) {
        var node = nodes.get(label);
        if(node == null) throw new IllegalArgumentException();

        var visited = new HashSet<Node>();
        var queue = new ArrayDeque<Node>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (visited.contains(current))
                continue;

            System.out.print(current + " ");
            visited.add(current);
            for(var target : adjacencyList.get(current)) {
                if(!visited.contains(target)) {
                    queue.offer(target);
                }
            }
        }
    }
}
