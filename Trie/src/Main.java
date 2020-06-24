public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.getSize());//1
        System.out.println(trie.search("apple"));//True
        System.out.println(trie.search("app"));//False
        System.out.println(trie.startsWith("app"));//True
        trie.insert("app");
        System.out.println(trie.getSize());//2

    }
}
