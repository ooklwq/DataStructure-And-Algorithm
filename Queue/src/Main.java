import java.util.Random;

public class Main {

    private static double testQueue(Queue queue, int optCount){
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < optCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int optCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue: "+testQueue(arrayQueue, optCount)+"s");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue: "+testQueue(loopQueue, optCount)+"s");
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        System.out.println("LinkedListQueue: "+testQueue(linkedListQueue, optCount)+"s");
    }
}
