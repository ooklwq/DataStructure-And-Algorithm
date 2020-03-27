public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];//浪费一个空间，当(tail+1)%length=front时作为队列满的条件
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public E getFront(){
        if (isEmpty()) throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public void enqueue(E e){
        //队满
        if((tail + 1)%data.length == front){
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int capacity){
        E[] newData = (E[])new Object[capacity+1];
        for (int i = 0 ; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue(){
        if (isEmpty()) throw new IllegalArgumentException("queue is empty");
        E res = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (size == getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return res;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("LoopQueue: size:%d,capacity:%d\n",size,data.length-1 ));
        str.append("front [");
        for (int i = 0; i < size; i++){
            str.append(data[(i+front)%data.length]);
            if (i != size-1) str.append(", ");
        }
//        for (int i = front; i != tail ; i = (i + 1)%data.length) {
//            str.append(data[i]);
//            if ((i + 1)%data.length != tail ) {//不是最后一个元素
//                str.append(", ");
//            }
//        }
        str.append("] tail");
        return str.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if ( i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
