/**
	底层数据结构
	• 父亲表示法，用一个数组/哈希表记录每个节点的父亲是谁。
	• father[“Nokia”] = “Microsoft”
	• father[“Instagram”] = “Facebook”
	查询所在集合
	• 用所在集合最顶层的老大哥节点来代表这个集合
	合并两个集合
	• 找到两个集合中最顶层的两个老大哥节点 A 和 B
	• father[A] = B // or father[B] = A 如果无所谓谁合并谁的话
	
	O(1) 合并两个集合 - Union
	O(1) 查询元素所属集合 - Find
*/

public class UnionFind {
	public int size;
	public int[] parent;

	/**
	 代码模板 - 初始化
 	 	使用哈希表或者数组来存储每个节点的父亲节点
		(如果节点不是连续整数的话，就最好用哈希表来存储)
		最开始所有的父亲节点都指向自己
	 */
	public UnionFind(int size) {
		this.size = size;
		this.parent = new int[size];
		for (int i = 0; i < this.size; i++) {
			this.parent[i] = i;
		}
	}

	/**
	  代码模板 - 查找老大哥
			沿着父亲节点一路往上走就能找到老大哥
		代码模板 - 路径压缩
			在找到老大哥以后，还需要把一路上经过的点都指向老大哥
	 */
	private int find(int child, int[] parent) {
		// 用新变量childCopy代替child找老大哥
		int childCopy = child;
		
		/** 找到老大哥 */
		while (f[childCopy] != childCopy) {
			childCopy = parent[childCopy];
		}
		// 用新变量bigbro作为返回值
		int bigbro = childCopy;

		int prevParent;
		/** 把一路上经过的点都指向老大哥, 路径压缩 */
		while(child != bigbro) {
			prevParent = parent[child]; // 用来prevParent记录之前的parent
			parent[child] = bigbro;   // point child's father to 老大哥
			child = prevParent;
		}

		// return老大哥
		return bigbro;
	}

	/**
	  代码模板 - 集合合并
			找到两个元素所在集合的两个老大哥 A 和 B
			将其中一个老大哥的父指针指向另外一个老大哥
	 */
	public void union(int a, int b, int[] parent) {
		int a_bigbro = find(a, parent);
		int b_bigbro = find(b, parent);

		if (a_bigbro != b_bigbro) {
			// 所有a的师兄弟 都和b的老大哥混了
			parent[a_bigbro] = b_bigbro;
		}
	}

	/** 时间复杂度都是 O(log* n)，约等于 O(1) */
	// prooof: https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2

	/**
	 * 跟连通性有关的问题
   * 都可以使用 BFS 和 Union Find
   * 什么时候无法使用 Union Find?
   * 需要拆开两个集合的时候无法使用Union Find
	 */

	 /**
		* 并查集总结
    * 1. 合并两个集合
    * 2. 查询某个元素所在集合
    * 3. 判断两个元素是否在同一个集合
    * 4. 获得某个集合的元素个数
    * 5. 统计当前集合个数
    * 关键操作：快速寻找老大哥节点
	  */
}
