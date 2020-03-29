import java.util.Random;

public class Main {
    private static double testStack(Stack stack, int optCount){
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < optCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int optCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        System.out.println("ArrayStack: "+testStack(arrayStack, optCount));
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        System.out.println("LinkedListStack: "+testStack(linkedListStack, optCount));
    }
}
