import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AVLTree<String, Integer> avlTree = new AVLTree<>();
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("AVLTree/pride-and-prejudice.txt", words)){
            for (String word : words) {
                if (!avlTree.contains(word)) {
                    avlTree.add(word, 1);
                }else {
                    avlTree.set(word, avlTree.get(word) + 1);
                }
            }
            System.out.println("words :" + avlTree.getSize());
            System.out.println("BST: " +avlTree.isBST());
            System.out.println("Balanced: "+avlTree.isBalanced());
            for (int i = 0; i < words.size(); i++) {
                avlTree.remove(words.get(i));
                if (!avlTree.isBalanced() || !avlTree.isBST())
                    throw new RuntimeException("error");
            }
            System.out.println("删除后：");
            System.out.println("words :" + avlTree.getSize());
            System.out.println("BST: " +avlTree.isBST());
            System.out.println("Balanced: "+avlTree.isBalanced());

        }
    }
}
