public class BST<E extends Comparable<E>> {
    private class TreeNode{
        public E val;
        public TreeNode left, right;
        public TreeNode(E val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root, e);
    }

    private TreeNode add(TreeNode root, E e){
        if (root == null){
            size++;
            return new TreeNode(e);
        }
        if (e.compareTo(root.val) < 0 ){
            root.left = add(root.left, e);
        }else if (e.compareTo(root.val) > 0){
            root.right = add(root.right, e);

        }
        return root;
    }
}
