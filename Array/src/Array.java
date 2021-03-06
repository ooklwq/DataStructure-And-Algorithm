public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    //默认构造函数，创建大小为10的数组
    public Array(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    //向索引index处添加元素e
    public void add(int index, E e){
        if(index<0 || index >size){
            throw new IllegalArgumentException("index needs to be index>0 &&index <=size");
        }
        if (size == data.length){
            resize(size*2);
        }
        for(int i = size -1 ; i>=index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //对数组重新分配空间，并拷贝数据
    private void resize(int capacity){
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    //查找指定元素，成功返回该元素的索引，否则返回-1
    public int find(E e){
        for (int i = 0;i<size;i++){
            if(e == data[i]) return i;
        }
        return -1;
    }

    public E get(int index){
        if (index < 0 || index >=size ) throw new IllegalArgumentException("index >= 0 && index < size");
        return data[index];
    }

    public E getLast(){
        return get(size -1);
    }

    public E getFirst(){
        return get(0);
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if(e == data[i]) return true;
        }
        return false;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public void set(int index, E e){
        if(index<0 || index >=size) throw new IllegalArgumentException("index>=0&&index<size");
        data[index] = e;
    }

    //删除指定的元素，删除成功返回被删除的元素
    public E remove(int index){
        if(index<0 || index >=size) throw new IllegalArgumentException("index>=0&&index<size");
        E ret = data[index];
        for(int i = index+1;i<size; i++){
            data[i-1] = data[i];
        }
        size--;
        //缩容
        if(size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("size:%d,capacity:%d\n",size,data.length ));
        str.append("[");
        for (int i = 0; i < size; i++){
            str.append(data[i]);
            if (i != size-1) str.append(", ");
        }
        str.append("]");
        return str.toString();
    }
}
