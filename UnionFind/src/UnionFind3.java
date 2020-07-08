public class UnionFind3 implements UF {
    int[] parent;
    int[] size;//以i节点为根的集合的节点数

    public UnionFind3(int size){
        this.parent = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            this.size[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        if (p <0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        while (p != parent[p])
            p = parent[p];
        return parent[p];
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (size[pRoot] < size[qRoot]){
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }else {
            parent[qRoot] = parent[pRoot];
            size[pRoot] += size[qRoot];
        }
    }
}
