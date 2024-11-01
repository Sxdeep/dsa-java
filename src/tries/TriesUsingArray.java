package tries;

public class TriesUsingArray {
    private class Node {
        char value;
        Node[] children;
        Boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
            children = new Node[26];
            isEndOfWord = false;
        }
    }

    private Node root;

    public TriesUsingArray() {
        root =  new Node('\0');
    }

    public void insert(String word) {
        var current = root;
        for(char ch : word.toCharArray()) {
            int index = ch - 'a';
            if(current.children[index] == null) {
                current.children[index] = new Node(ch);
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }


    public void printTrie() {
        printTrie(root, "", "");
    }

    private void printTrie(Node node, String prefix, String childrenPrefix) {
        if (node != root) {
            System.out.println(prefix + node.value);
        }

        Node[] children = node.children;
        int childCount = 0;
        for (Node child : children) {
            if (child != null) {
                childCount++;
            }
        }

        int i = 0;
        for (Node child : children) {
            if (child != null) {
                if (i == childCount - 1) {
                    printTrie(child, childrenPrefix + "└── ", childrenPrefix + "    ");
                } else {
                    printTrie(child, childrenPrefix + "├── ", childrenPrefix + "│   ");
                }
                i++;
            }
        }
    }
}
