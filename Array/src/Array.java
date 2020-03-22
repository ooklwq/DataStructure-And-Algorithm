public class Array {
    private int[] data;
    private int size;

    public Array(int capacity){
        data = new int[capacity];
        size = 0;
    }

    //默认构造函数，创建大小为10的数组
    public Array(){
        this(10);
    }

    //向索引index处添加元素e
    public void add(int index, int e){
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
    public void resize(int capacity){
        int[] newData = new int[capacity];
        for (int i = 0; i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addLast(int e){
        add(size,e);
    }

    public void addFirst(int e){
        add(0,e);
    }

    //查找指定元素，成功返回该元素的索引，否则返回-1
    public int find(int e){
        for (int i = 0;i<size;i++){
            if(e == data[i]) return i;
        }
        return -1;
    }

    public void set(int index, int e){
        data[index] = e;
    }

    //删除指定的元素，删除成功返回被删除的元素
    public int remove(int index){
        if(index<0 || index >=size) throw new IllegalArgumentException("index>=0&&index<size");
        int ret = data[index];
        for(int i = index;i<size-1; i++){
            data[i] = data[i+1];
        }
        size--;
        //缩容
        if(size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    public int removeFirst(){
        return remove(0);
    }

    public int removeLast(){
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
