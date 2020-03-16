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
}
