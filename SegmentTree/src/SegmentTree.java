public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] nums, Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        tree = (E[])new Object[4 * nums.length];
        buildSegmentTree(0, 0, nums.length - 1);

    }


    private void buildSegmentTree(int treeIndex, int l, int r){
        if ( l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftChildIndex] , tree[rightChildIndex]);
    }

    public E query(int i, int j){
        if (i <0 || i >= data.length ||j <0 || j >= data.length || i > j){
            throw new IllegalArgumentException("index is illegal.");
        }
        return query(0, 0, data.length -1 , i, j);
    }

    private E query(int treeIndex, int l, int r, int i, int j){
        if (i == l && j == r){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if ( j <= mid){
            return query(leftChildIndex, l, mid, i, j);
        }else if( i >= mid + 1){
            return query(rightChildIndex, mid + 1, r, i, j);
        }

        E left = query(leftChildIndex, l, mid, i, mid);
        E right = query(rightChildIndex, mid + 1, r, mid + 1, j);
        return merger.merge(left, right);

    }

    public void set(int i, E e){
        if (i < 0 || i >= data.length){
            throw new IllegalArgumentException("index is illegal.");
        }
        set(0, 0, data.length - 1, i, e);
    }

    private void set(int treeIndex, int l , int r, int i, E e){
        if (l == r){
            tree[treeIndex] = e;
        }
        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        if (i <= mid){
            set(leftChildIndex,l, mid, i , e);
        }else {
            set(rightChildIndex, mid + 1, r, i, e);
        }

        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    private int getSize(){
        return data.length;
    }

    private E get(int index){
        if (index <=0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal.");
        }
        return data[index];
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null){
                sb.append(tree[i]);
            }else {
                sb.append("null");
            }
            if (i != tree.length - 1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
