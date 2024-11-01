package tries;

import java.util.ArrayList;
import java.util.HashMap;

public class Tries {
    private class Node {
        char value;
        HashMap<Character, Node> children;
        Boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
            children = new HashMap<>();
            isEndOfWord = false;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }
    }

    private Node root;

    public Tries() {
        root =  new Node(' ');
    }

    public void insert(String word) {
        var current = root;
        for(char ch : word.toCharArray()) {
            if(!current.hasChild(ch)) {
                current.addChild(ch);
            }
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        var current = root;
        for(char ch : word.toCharArray()) {
            if(!current.hasChild(ch)) {
                return false;
            }
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    public boolean contains2(String word) {
        if(root == null) return false;

        return contains2(word.toCharArray(), root, 0);
    }

    private boolean contains2(char[] word, Node root, int count) {
        if(root == null) return false;

        if(count == word.length) return root.isEndOfWord;

        return contains2(word, root.getChild(word[count]), count + 1);
    }

    public Node findLastNodeOf(String prefix) {
        var current = root;
        for(char ch : prefix.toCharArray()) {
            if(!current.hasChild(ch)) {
                return null;
            }
            current = current.getChild(ch);
        }
        return current;
    }

    public void delete(String word) {
        if(word == null) return;
        delete(word.toCharArray(), 0, root);
    }

    private boolean delete(char[] word, int count, Node root) {
        if(root == null) return false;

        if(count == word.length) {
            root.isEndOfWord = false;
            return root.children.values().isEmpty();
        }

        if(delete(word, count + 1, root.getChild(word[count]))) {
            root.children.remove(word[count]);
        }

        return root.children.values().isEmpty() && !root.isEndOfWord;
    }

    public ArrayList<String> auto(String prefix) {
        ArrayList<String> words = new ArrayList<>();
        var lastNode = findLastNodeOf(prefix);

        var newPrefix = prefix == "" ? "" : prefix.substring(0,prefix.length()-1);
        auto(newPrefix, lastNode, words);
        return words;
    }

    private void auto(String word, Node root, ArrayList<String> words) {
        if(root == null) return;

        if(root.value != '\0') word += root.value;

        if(root.isEndOfWord) words.add(word);

        for(var child : root.children.values()) {
            auto(word, child, words);
        }
    }

    public int countWords() {
        return countWords(root);
    }

    private int countWords(Node root) {
        int count = 0;

        if(root.isEndOfWord) count++;

        for(var child : root.children.values()) {
            count += countWords(child);
        }
        return count;
    }

    public String longestCommonPrefix(String[] words) {
        if (words == null)
            return "";

        var trie = new Tries();
        for (var word : words)
            trie.insert(word);

        var prefix = new StringBuilder();
        var maxCharacters = getShortestWord(words).length();
        var current = trie.root;

        while (prefix.length() < maxCharacters) {
            var children = root.children.values().toArray(new Node[0]);
            if (children.length != 1)
                break;
            prefix.append(current.value);
            current = children[0];
        }

        return prefix.toString();
    }

    private String getShortestWord(String[] words) {
        if(words == null || words.length == 0) return "";

        var shortestWord = words[0];
        for(int i = 0; i < words.length; i++) {
            if (words[i].length() < shortestWord.length())
                shortestWord = words[i];
        }
        return shortestWord;
    }



    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node root) {
        System.out.println(root.value);

        for(var child : root.children.values()) {
            preOrder(child);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node root) {
        for(var child : root.children.values()) {
            postOrder(child);
        }

        System.out.println(root.value);
    }

    public void printTrie() {
        printTrie(root, "", "");
    }

    private void printTrie(Node node, String prefix, String childrenPrefix) {
        if (node != root) {
            System.out.println(prefix + node.value);
        }

        int childCount = node.children.size();
        int i = 0;
        for (Node child : node.children.values()) {
            if (i == childCount - 1) {
                printTrie(child, childrenPrefix + "└── ", childrenPrefix + "    ");
            } else {
                printTrie(child, childrenPrefix + "├── ", childrenPrefix + "│   ");
            }
            i++;
        }
    }
}

