import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.lintcode.com/problem/minimum-spanning-tree/
 *
 * 给出一些Connections，即Connections类，
 * 找到一些能够将所有城市都连接起来并且花费最小的边
 * 如果说可以将所有城市都连接起来，则返回这个连接方法；不然的话返回一个空列表
 *
 * 输入:
	["Acity","Bcity",1]
	["Acity","Ccity",2]
	["Bcity","Ccity",3]

	输出:
	["Acity","Bcity",1]
	["Acity","Ccity",2]

	返回按cost排序的连接方法，如果cost相同就按照city1进行排序，
	如果city1也相同那么就按照city2进行排序。
 */

/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */

public class MinimumSpanningTree {
  public List<Connection> lowestCost(List<Connection> connections) {
		List<Connection> results = new ArrayList<>();
		if (connections == null || connections.size() == 0) return results;

		Collections.sort(connections, new Comparactor<Connection>() {
			@Override
			public int compare(Connection a, Connection b) {
				if (a.cost != b.cost) return a.cost - b.cost;
				if (!a.city1.equals(b.city1)) return a.city1.compareTo(b.city1);
				return a.city2.compareTo(b.city2);
			}
		});

		// give each city an id starting from 1 & UnionFind initialization
		Map<String, Integer> cityToId = new HashMap<>();
		int count = 0;
		for (Connection ctn : connections) {
			if (!cityToId.containsKey(ctn.city1)) cityToId.put(ctn.city1, ++count);
			if (!cityToId.containsKey(ctn.city2)) cityToId.put(ctn.city2, ++count);
		}
		UnionFind uf = new UnionFind(cityToId);

		for (Connection ctn : connections) {
			int id_city1 = cityToId.get(ctn.city1);
			int id_city2 = cityToId.get(ctn.city2);
			if (!uf.union(id_city1, id_city2)) results.add(ctn);
		}

		// Tree: 边数 = 点数 － 1
		if (results.size() != count - 1) return new ArrayList<>();
		return results;
	}
}

class UnionFind {
	private int[] parent;

	UnionFind(Map<String, Integer> cityToId) {
		parent = new int[cityToId.size() + 1];
		for (int i = 1; i <= cityToId.size(); i++) parent[i] = i;
	}

	public int find(int id) {
		while (id != parent[id]) id = parent[id];
		return id;
	}

	// return true if id1 and id2 have same root
	public boolean union(int id1, int id2) {
		int root_id1 = find(id1);
		int root_id2 = find(id2);
		if (root_id1 == root_id2) return true;

		parent[root_id1] = parent[root_id2];
		return false;
	}
}
