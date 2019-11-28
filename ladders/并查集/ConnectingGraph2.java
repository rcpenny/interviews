/**
 * 连接图 II
 * 给一个图中的 n 个节点, 记为 1 到 n .在开始的时候图中没有边.
 * 你需要完成下面两个方法：
 * connect(a, b), 添加一条连接节点 a, b的边
 * query(a), 返回图中含 a 的联通区域内节点个数
 */

public class ConnectingGraph2 {
  private int[] parent;
  private int[] size;

  public ConnectingGraph2(int n) {
    parent = new int[n + 1];
    size = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public void connect(int a, int b) {
    int a_root = find(a);
    int b_root = find(b);
    if (a_root != b_root) {
      parent[a_root] = b_root;
      size[b_root] = size[b_root] + size[a_root];
    }
  }

  public int query(int a) {
    int a_root = find(a);
    return size[a_root];
  }

  private int find(int a) {
    int tmp = a, prevParent;
    while (tmp != parent[tmp]) tmp = parent[tmp];
    // 压缩路径是Optional，因为这步会大幅加快find速度，不过缺少它不会影响结果正确性
    while (a != parent[a]) {
      prevParent = parent[a];
      parent[a] = tmp;
      a = prevParent;
    }
    return tmp;
  }
}