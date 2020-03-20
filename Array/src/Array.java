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
        if (size == data.length){
            throw new IllegalArgumentException("Add failed.Array is full");
        }
        if(index<0 || index >size){
            throw new IllegalArgumentException("index needs to be index>0 &&index <=size");
        }
        for(int i = size -1 ; i>=index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addLast(int e){
        add(size,e);
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
