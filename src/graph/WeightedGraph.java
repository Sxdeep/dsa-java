package graph;

import java.util.*;

public class WeightedGraph {
    private class Node {
        private String label;
        private List<Edge> edges;

        public Node(String lable) {
            this.label = lable;
            this.edges = new LinkedList<>();
        }

        @Override
        public String toString() {
            return this.label;
        }

        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }
    private class Edge {
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return this.from + " -> " + this.to + "," + this.weight;
        }
    }

    private HashMap<String, Node> nodes;

    public WeightedGraph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(String label) {
        nodes.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        if(fromNode == null) throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if(toNode == null) throw new IllegalArgumentException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for(var node : nodes.values()) {
            var edges = node.getEdges();
            if(!edges.isEmpty()) {
                for(var edge : edges) {
                    System.out.println(node + " is connected to " + edge);
                }
            }
        }
    }

    public int shortestDistance(String from, String to) {
        var fromNode = nodes.get(from);
        var distances = new HashMap<Node, Integer>();

        for(var node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(fromNode, 0);

        var visited = new HashSet<Node>();
        var queue = new PriorityQueue<NodeEntry>(Comparator.comparing(ne -> ne.priority));
        var previous = new HashMap<Node, Node>();
        queue.offer(new NodeEntry(fromNode, 0));

        while(!queue.isEmpty()) {
            var nodeEntry = queue.poll();

            if(visited.contains(nodeEntry.node)) continue;

            visited.add(nodeEntry.node);

            for(var edge : nodeEntry.node.getEdges()) {
                if(edge.weight + distances.get(edge.from) < distances.get(edge.to)) {
                    distances.put(edge.to, edge.weight + distances.get(edge.from));
                    previous.put(edge.to, nodeEntry.node);
                }

                if(!visited.contains(edge.to)) {
                    queue.offer(new NodeEntry(edge.to, edge.weight + distances.get(edge.from)));
                }
            }
        }
        return distances.get(nodes.get(to));
    }

    public boolean hasCycle() {
        var visited = new HashSet<Node>();

        for(var node : nodes.values()) {
            if(hasCycle(node,null, visited)) return true;
        }
        return false;
    }

    private boolean hasCycle(Node root, Node parent, HashSet<Node> visited) {
        if(visited.contains(root)) return false;
        visited.add(root);

        for(var edge : root.getEdges()) {
            if(parent == edge.to) continue;

            if(visited.contains(edge.to)) return true;

            if(hasCycle(edge.to, root, visited)) return true;
        }

        return false;
    }

    public WeightedGraph getMST() {
        var tree = new WeightedGraph();
        if(nodes.isEmpty()) return tree;

        var edges = new PriorityQueue<Edge>(Comparator.comparing(e -> e.weight));
        var startNode = nodes.values().iterator().next();
        tree.addNode(startNode.label);
        edges.addAll(startNode.getEdges());
        if(edges.isEmpty()) return tree;

        while(tree.nodes.size() < nodes.size()) {
            var minEdge = edges.poll();
            var nextNode = minEdge.to;

            if(tree.containsNode(nextNode.label)) continue;

            tree.addNode(nextNode.label);
            tree.addEdge(minEdge.from.label, nextNode.label, minEdge.weight);

            for(var edge : nextNode.getEdges()) {
                if(!tree.containsNode(edge.to.label)) edges.offer(edge);
            }
        }

        return tree;
    }

    public boolean containsNode(String label) {
        return nodes.containsKey(label);
    }
}