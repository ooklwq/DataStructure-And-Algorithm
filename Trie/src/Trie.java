import java.util.HashMap;
import java.util.Map;

class Trie {
    private class Node{
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            this.next = new HashMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    public int getSize(){
        return size;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}
