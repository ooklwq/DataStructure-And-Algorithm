public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    int find(int p);
    void unionElements(int p, int q);
}
