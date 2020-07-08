import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>  {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        private int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }

//        public int getHeight(){
//            return height;
//        }
//
//        public int getBalanceFactor(){
//            return left.getHeight() - right.getHeight();
//        }

    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    //获得node节点的高度
    private int getHeight(Node node){
        if (node == null) return 0;
        return node.height;
    }

    //获得node节点的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断以node为根节点的二叉树是否为二分搜索树
    //中序遍历性质：从小到大
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inorder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) return false;
        }
        return true;
    }

    //中序遍历
    private void inorder(Node node, ArrayList<K> keys){
        if (node == null) return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }


    public boolean isBalanced(){
        return isBalanced(root);
    }

    //判断以node为根节点的二叉树是否是平衡二叉树
    private boolean isBalanced(Node node){
        if (node == null) return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    //右旋操作
    //           y                      x
    //         /   \                  /   \
    //        x     T4     ->        z     y
    //       /  \                   / \   / \
    //      z    T3                T1 T2 T3 T4
    //     / \
    //    T1  T2
    private Node rightRotate(Node node){
        Node leftNode = node.left;
        Node leftRightNode = leftNode.right;

        //右旋
        leftNode.right = node;
        node.left = leftRightNode;

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        leftNode.height = 1 + Math.max(getHeight(leftNode.left), getHeight(leftNode.right));
        return leftNode;
    }

    //左旋操作
    //           y                      x
    //         /   \                  /   \
    //        T4    X      ->        y     z
    //             /  \             / \   / \
    //            T3   z           T4 T3 T1 T2
    //                / \
    //              T1   T2
    private Node leftRotate(Node node){
        Node rightNode = node.right;
        Node rightLeftNode = rightNode.left;

        //左旋
        rightNode.left = node;
        node.right = rightLeftNode;

        //更新Height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        rightNode.height = 1 + Math.max(getHeight(rightNode.left),getHeight(rightNode.right));
        return rightNode;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        }else {// key.compareTo(node.key) == 0
            node.value = value;
        }

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

//        if (Math.abs(balanceFactor) > 1)
//            System.out.println("unbalanced: "+ balanceFactor);

        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0 ){
            return rightRotate(node);
//            node = rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
//            node = leftRotate(node);
        }
        //LR
        //左旋再右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0 ){
            node.left = leftRotate(node.left);
            return rightRotate(node);
//            node = rightRotate(node);
        }
        //RL
        //右旋再左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
//            node = leftRotate(node);
        }
        return node;
    }

    private Node getNode(Node root, K key){
        if (root == null) return  null;
        if (key.compareTo(root.key) < 0)
            return getNode(root.left, key);
        else if (key.compareTo(root.key) > 0)
            return getNode(root.right, key);
        else
            return root;

    }

    //返回以node为根节点的二分搜索树的最小值所在节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //删除以node为根节点的二分搜索树中的最小值节点
    //返回删除后的二分搜索树的根节点
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //删除以node为根节点的二分搜索树中键为key的节点
    //返回删除后的节点
    private Node remove(Node node, K key){
        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        }else {
            //待删除节点左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }
            //待删除节点右子树为空
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }
            else {
                //待删除节点左右子树都不为空
                //找到待删除节点的后继节点，即右子树中的最小值节点
                //使该节点代替待删除节点
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode =  successor;
            }
        }

        if (retNode == null) return null;
        //更新Height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0 ){
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        //LR
        //左旋再右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0 ){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        //右旋再左旋
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist");
        node.value = newValue;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        Node node = getNode(root, key);
        return node != null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
