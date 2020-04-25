public class LinkedListSet<E> implements Set<E>{
    private LinkedList<E> link;

    public LinkedListSet(){
        link = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!link.contains(e))
            link.addFirst(e);
    }

    @Override
    public void remove(E e) {
        link.removeElement(e);
    }

    @Override
    public int getSize() {
        return link.getSize();
    }

    @Override
    public boolean isEmpty() {
        return link.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return link.contains(e);
    }
}
