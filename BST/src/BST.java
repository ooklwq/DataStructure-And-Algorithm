import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.LinkedList;
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

//    public TreeNode remove(E e){
//        return remove(root, e);
//    }

//    public TreeNode getMin(TreeNode node){
//        if (node == null) return null;
//        TreeNode cur = node;
//        while (cur.left != null) cur = cur.left;
//        return cur;
//    }
//
//    private TreeNode remove(TreeNode root, E e){
//        if (root == null) return null;
//        if (root.val.equals(e)){
//            if (root.left == null) return root.right;
//            if (root.right == null) return root.left;
//            if (root.left != null && root.right != null){
//                //找到该节点的后继或前驱
//                //此处采用后继，即右子树的最小值
//                TreeNode next = getMin(root.right);
//
//            }
//        }
//    }
}
