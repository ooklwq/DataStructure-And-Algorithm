public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(){
        data = new Array<>();
    }

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >=0 ; i--) {
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    public int parent(int i){
        if (i == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (i - 1)/2;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int i){
        return 2 * i + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int i){
        return 2 * i + 2;
    }

    //向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    //自下而上建堆
    private void siftUp(int i){
        while (i > 0 && data.get(parent(i)).compareTo(data.get(i)) < 0) {
            data.swap(i, parent(i));
            i = parent(i);
        }
    }

    //看堆中最大元素
    public E findMax(){
        if (isEmpty()){
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    //删除堆顶元素
    public E extractMax(){
        E ret= findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    //自上而下建堆
    public void  siftDown(int i){
        while (leftChild(i) < data.getSize()){
            int j = leftChild(i);//在本轮循环中，交换data[i]和data[j]的位置
            //若右孩子存在，j表示左右孩子中较大的那个值对应的索引
            if (j + 1 < data.getSize() && data.get(j).compareTo(data.get(j+1)) < 0){
                j++;
            }
            if (data.get(i).compareTo(data.get(j)) >= 0) break;

            data.swap(i, j);
            i = j;
        }

    }

    //取出堆中最大的元素，并且替换成元素e
    public E replace(E e){
        E ret = findMax();
        data.addFirst(e);
        siftDown(0);
        return ret;
    }


}
