/**
 * 给一个图中的n个节点, 记为 1 到 n 在开始的时候图中没有边。
 * 你需要完成下面两个方法:
 * connect(a, b), 添加连接节点 a, b 的边.
 * query(a, b), 检验两个节点是否联通
 */
public class ConnectingGraph {
  public int[] parent;

  public ConnectingGraph(int n) {
    this.parent = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
    }
  }

  public void connect(int a, int b) { // union(a, b)
    int root_a = find(a);
    int root_b = find(b);
    if (root_a != root_b) {
      parent[root_a] = root_b;
    }
  }

  public boolean query(int a, int b) {
    return find(a) == find(b);
  }

  private int find(int a) {
    int copya = a;
    while (copya != parent[copya]) {
      copya = parent[copya];
    }
    int root = copya;

    int prevParent;
    while (a != parent[a]) {
      prevParent = parent[a];
      parent[a] = root;
      a = prevParent;
    }
    return root;
  }
}
