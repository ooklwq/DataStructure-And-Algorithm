public interface Stack<E> {
    boolean isEmpty();
    int getSize();
    E pop();
    void push(E e);
    E peek();
}
