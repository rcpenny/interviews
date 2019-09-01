/**
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 
 * (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
 */

public class GraphValidTree {

  class UnionFind {
    int[] parent;
    int size;

    UnionFind(int n) {
      this.parent = new int[n];
      this.size = n;
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    int compressFind(int child) {
      int copyChild = child;
      while (copyChild != parent[copyChild])
        copyChild = parent[copyChild];
  
      int bigbro = copyChild;

      int prevParent;
      while (child != bigbro) {
        prevParent = parent[child];
        parent[child] = bigbro;
        child = prevParent;
      }
      return bigbro;
    }

    void union(int a, int b) {
      int aBigbro = compressFind(a);
      int bBigbro = compressFind(b);
      if (aBigbro != bBigbro) {
        parent[aBigbro] = parent[bBigbro];
        size--;
      }
    }
  }

  // n个孤立点，size为n，如果最后union完size为1，说明为tree
  // 每当两个big_bro不同时，说明两个小树合并了
  public boolean validTree(int n, int[][] edges) {
    if (n <= 0 || edges.length != n - 1) return false;

    UnionFind uf = new UnionFind(n);

    for (int[] edge : edges)
      uf.union(edge[0], edge[1]);

    return uf.size == 1;
  }
}
