import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    //二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    //以node为根节点的二分搜索树中是否包含元素e,递归实现
    private boolean contains(TreeNode node, E e){
        if (node == null) return false;
        if (e.compareTo(node.val) == 0) return true;
        else if (e.compareTo(node.val)<0){
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    //二分搜索树的前序遍历，递归实现
    private void preOrder(TreeNode node){
        if (node == null) return;
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    //二叉搜索树前序遍历的非递归实现
    public void preOrderNR(){
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }

    //二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    //二分搜索树的中序遍历，递归实现
    private void inOrder(TreeNode node){
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    //二叉搜索树中序遍历的非递归实现
    public void inOrderNR(){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.val);
            cur = cur.right;
        }
    }

    //二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    //二分搜索树的后序遍历，递归实现
    private void postOrder(TreeNode node){
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

    //二叉搜索树后序遍历的非递归实现
    public void postOrderNR(){
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<E> res = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                res.addFirst(cur.val);
                cur = cur.right;
            }
            TreeNode curr = stack.pop();
            cur = curr.left;
        }
        for (E e : res) {
            System.out.println(e);
        }
    }

    //二叉搜索树层序遍历
    public void levelOrder(){
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.remove();
            System.out.println(cur.val);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
    }

    //寻找二分搜索树的最小元素
    public E minimum(){
        if (size == 0) throw new IllegalArgumentException("BST is empty");
        TreeNode minNode = minimum(root);
        return minNode.val;
    }

    //寻找以node为根节点的二分搜索树的最小元素
    private TreeNode minimum(TreeNode node){
        if (node.left == null) return node;
        return minimum(node.left);
//        if (node == null) return node;
//        TreeNode cur = node;
//        while (cur.left != null) cur = cur.left;
//        return cur;
    }

    //寻找二分搜索树的最大元素
    public E maximum(){
        if (size == 0) throw new IllegalArgumentException("BST is empty");
        TreeNode maxNode = maximum(root);
        return maxNode.val;
    }

    //寻找以node为根节点的二分搜索树的最大元素
    private TreeNode maximum(TreeNode node){
        if (node.right == null) return node;
        return maximum(node.right);
    }

    //从二分搜索树中删除最小值的节点，返回最小值
    public E removeMin(){
        E res = minimum();
        root = removeMin(root);
        return res;
    }

    //删除以node为根节点的二分搜索树中的最小值节点
    //返回删除后的新的二分搜索树的根节点
    private TreeNode removeMin(TreeNode node){
       // if (node == null) return null;
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //从二分搜索树中删除最大值的节点，返回最大值
    public E removeMax(){
        E res = maximum();
        root = removeMax(root);
        return res;
    }

    //删除以node为根节点的二分搜索树中的最大值节点
    //返回删除后的新的二分搜索树的根节点
    private TreeNode removeMax(TreeNode node){
        // if (node == null) return null;
        if (node.right == null) {
            TreeNode leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //删除二分搜索树中值为e的节点
    public void removeElement(E e){
        root = removeElement(root, e);
    }

    //删除以node为根节点的二分搜索树中值为e的节点
    //返回删除后的二分搜索树的根节点
    private TreeNode removeElement(TreeNode node, E e){
        if (node == null) return null;
        if (e.compareTo(node.val) == 0){
            if (node.left == null){
                TreeNode rigthNode = node.right;
                node.right = null;
                size --;
                return rigthNode;
            }
            if (node.right == null){
                TreeNode leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            if (node.left != null && node.right != null){
                //左右子树都不为空时，将待删除节点的前驱或者后继节点替换待删除节点
                //此处采用后继节点，即右子树中的最小值节点
                TreeNode successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
                //法二，采用一个小技巧：将后继节点的值赋值给待删除节点，不是真的删除
//                node.val = successor.val;
//                node.right = removeElement(node.right, successor.val);
//                return node;
            }

        } else if (e.compareTo(node.val) < 0){
            node.left = removeElement(node.left, e);
        } else{
            node.right = removeElement(node.right, e);
        }
        return node;
    }


}
