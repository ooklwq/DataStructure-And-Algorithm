public class UnionFind5 implements UF {
    int[] parent;
    int[] rank;//以i节点为根的集合的高度

    public UnionFind5(int size){
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            this.rank[i] = 1;
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
        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return parent[p];
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = parent[pRoot];
        }else {
            parent[pRoot] = qRoot;
            rank[qRoot] +=1;
        }
    }
}
