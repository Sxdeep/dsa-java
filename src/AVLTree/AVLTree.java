package AVLTree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    private class AVLNode{
        private int data;
        private AVLNode left;
        private AVLNode right;
        private int height;

        private AVLNode(int data) {
            this.data = data;
        }
    }
    private AVLNode root;

    public void printTree() {
        List<List<String>> lines = new ArrayList<>();
        List<AVLTree.AVLNode> level = new ArrayList<>();
        List<AVLTree.AVLNode> next = new ArrayList<>();
        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (AVLTree.AVLNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.data);
                    line.add(aa + "(h=" + n.height + ")");
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<AVLTree.AVLNode> tmp = level;
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

    private AVLNode insert(AVLNode root, int data) {
        if(root == null) return new AVLNode(data);

        else if(data < root.data) {
            root.left = insert(root.left, data);
        }
        else{
            root.right = insert(root.right, data);
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        if(isRightHeavy(root)) {
            if(balanceFactor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }
        else if(isLeftHeavy(root)) {
            if(balanceFactor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        }
        return root;
    }
    private AVLNode rotateLeft(AVLNode root) {
        var newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }
    private AVLNode rotateRight(AVLNode root) {
        var newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }

    private void setHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right));
    }

    private int height(AVLNode root){
        return root == null ? -1 : root.height;
    }
    private int balanceFactor(AVLNode root) {
        return height(root.left) - height(root.right);
    }
    private boolean isLeftHeavy(AVLNode root) {
        return balanceFactor(root) > 1;
    }
    private boolean isRightHeavy(AVLNode root) {
        return balanceFactor(root) < -1;
    }
}
