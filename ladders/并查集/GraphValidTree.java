/**
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 
 * (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
 */

// leet261

// 判断一个图是否是树有三个条件：
// 联通性
// 边数 = 节点数 - 1
// 是否有环
// 另外根据图论：以上三条满足任意两条即可

public class GraphValidTree {
	// BFS解法
	public boolean validTree1(int n, int[][] edges) {
    if (n <= 0) {
      return false;
    }

    if (edges.length != n - 1) {
      return false;
    }

    Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();

    queue.offer(0);
    visited.add(0);
    
    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (int neighbor : graph.get(node)) {
        if (visited.contains(neighbor)) continue;
        queue.offer(neighbor);
        visited.add(neighbor);
      }
    }

		// 因为在n个节点，n-1条边的情况下，只有两种可能：1、树 2、几个有环的不联通的图 因此从节点0出发，最后得到的hash size不是n的话，一定不是树
		// 不会是一个联通的图
    return visited.size() == n;
  }

  private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (int i = 0; i < n; i++) {
      graph.put(i, new HashSet<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    return graph;
  }





	// Union Find solution
  // n个孤立点，size为n，如果最后union完size为1，说明为tree
  // 每当两个big_bro不同时，说明两个小union合并了
  public boolean validTree(int n, int[][] edges) {
    if (n <= 0) {
			return false;
		}

		if (edges.length != n - 1) {
			return false;
		}

    UnionFind uf = new UnionFind(n);

    for (int[] edge : edges)
      uf.union(edge[0], edge[1]);

    return uf.size == 1;
  }

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
			// find union root with a copy of child
      int copyChild = child;
      while (copyChild != parent[copyChild])
        copyChild = parent[copyChild];
      int bigbro = copyChild;

			// all children along this path points to union root
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
}
