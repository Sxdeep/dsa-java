package tree;

import AVLTree.DummyTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree {
    private class Node {
        private int height;
        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
        }
    }
    private Node root;

    public void printTree() {
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();
        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.data);
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        }
        else if(data < root.data) {
            root.left = insert(root.left, data);
        }
        else{
            root.right = insert(root.right, data);
        }
        root.height = Math.max(heightOfNode(root.left), heightOfNode(root.right)) + 1;

        return root;
    }

    public boolean find(int data) {
        if(root.data == data) {
            return true;
        }

        var current = root;

        while(current.data != data) {
            if(data < current.data) {
                if(current.left == null) {
                    return false;
                }
                current = current.left;
            }
            else{
                if(current.right == null) {
                    return false;
                }
                current = current.right;
            }
        }
        return true;
    }
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node root) {
        if(root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(Node root) {
        if(root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    public int height() {
        return height(root);
    }
    private int height(Node root) {
        if(root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
    public int min() {
        return min(root);
    }
    private int min(Node root) {
        if(root == null) return Integer.MAX_VALUE;
        return Math.min(Math.min(min(root.left), min(root.right)),
                root.data);
    }
    public boolean equals(Tree other) {
        if(other == null) return false;
        return equals(root, other.root);
    }
    private boolean equals(Node first, Node second) {
        if(first == null && second == null) return true;
        if(first != null & second != null) {
            return first.data == second.data
                    && equals(first.left, second.left)
                    && equals(first.right, second.right);
        }
        return false;
    }
    public boolean checkBST() {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean checkBST(Node root, int min, int max) {
        if(root == null) {
            return true;
        }

        return root.data > min
                && root.data < max
                && checkBST(root.left, min, root.data)
                && checkBST(root.right, root.data, max);
    }
    public void swapNodes() {
        var temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    public ArrayList nodesAtDistance(int k) {
        var list = new ArrayList<Integer>();
        nodesAtDistance(root, k, list);
        return list;
    }
    private void nodesAtDistance(Node root, int k, ArrayList<Integer> list) {
        if(root == null) {
            return;
        }
        if(k == 0) {
            list.add(root.data);
            return;
        }
        nodesAtDistance(root.left, k - 1, list);
        nodesAtDistance(root.right, k - 1, list);
    }
    public void levelOrder() {
        for(int i = 0; i <= height(); i++) {
            for(var item : nodesAtDistance(i)) {
                System.out.print(item);
            }
        }
    }
    public int size() {
        return size(root);
    }
    private int size(Node root) {
        if(root == null) {
            return 0;
        }
         return 1 + size(root.left) + size(root.right);
    }
    public int countLeaves() {
        return countLeaves(root);
    }
    private int countLeaves(Node root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }
    public int max() {
        return max(root);
    }
    private int max(Node root) {
        if(root == null) return Integer.MIN_VALUE;
        return Math.max(Math.max(max(root.left), max(root.right)),
                root.data);
    }
    public boolean contains(int value) {
        return contains(root, value);
    }
    private boolean contains(Node root, int value) {
        if(root == null) return false;
        if(root.data == value) return true;
        return contains(root.left, value) || contains(root.right, value);
    }
    public ArrayList<Integer> getAncestors(int data) {
        var list = new ArrayList<Integer>();
        getAncestors(root, data, list);
        return list;
    }
    private void getAncestors(Node root, int data, ArrayList<Integer> list) {
        if(root == null) return;

        getAncestors(root.left, data, list);
        getAncestors(root.right, data, list);

        if(contains(root, data) && root.data != data) {
            list.add(root.data);
        }
    }
    public boolean areSiblings(int first, int second) {
        return areSiblings(root, first, second);
    }
    private boolean areSiblings(Node root, int first, int second) {
        if(root == null) return false;

        if(root.left == null || root.right == null) return false;

        if((root.left.data == first && root.right.data == second) ||
        (root.left.data == second && root.right.data == first)) return true;

        return areSiblings(root.left, first, second) ||
                areSiblings(root.right, first, second) ;
    }

    private int heightOfNode(Node root) {
        if(root == null) {
            return -1;
        }
        return root.height;
    }

    private int balanceFactor(Node root) {
        return heightOfNode(root.left) - heightOfNode(root.right);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node root) {
        if(root == null) return true;
        return balanceFactor(root) == 0
                || balanceFactor(root) == 1
                || balanceFactor(root) == -1;
    }

    public boolean isPerfect() {
        return isPerfect(root);
    }

    private boolean isPerfect(Node root) {
        if(root == null) return true;

        if((root.left == null && root.right != null) ||
                (root.left != null && root.right == null)) return false;

        return isPerfect(root.right) && isPerfect(root.left);
    }

    private HashMap<Node, Node> getParentMap(Node root) {
        var map =  new HashMap<Node, Node>();
        var queue = new ArrayDeque<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.poll();

            if(current.left != null) {
                queue.offer(current.left);
                map.put(current.left, current);
            }

            if(current.right != null) {
                queue.offer(current.right);
                map.put(current.right, current);
            }
        }
        return map;
    }

    public List<Integer> distanceK(Node root, Node target, int k) {
        var parent = getParentMap(root);
        var visited = new HashMap<Node, Boolean>();
        var queue = new ArrayDeque<Node>();
        queue.offer(target);
        visited.put(target, true);

        int curr_level = 0;
        while(!queue.isEmpty()) {
            if(curr_level == k) break;

            curr_level++;

            var size = queue.size();

            for(int i = 0; i < size; i++) {
                var current = queue.poll();

                if(current.left != null && visited.get(current.left) == null) {
                    queue.offer(current.left);
                    visited.put(current.left, true);
                }

                if(current.right != null && visited.get(current.right) == null) {
                    queue.offer(current.right);
                    visited.put(current.right, true);
                }

                if(parent.get(current) != null && visited.get(parent.get(current)) == null) {
                    queue.offer(parent.get(current));
                    visited.put(parent.get(current), true);
                }
            }
        }
        var result = new ArrayList<Integer>();
        while(!queue.isEmpty()) {
            var current = queue.poll();
            result.add(current.data);
        }
        return result;
    }
}
