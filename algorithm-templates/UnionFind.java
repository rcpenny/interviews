public class UnionFind {
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
	*/

	/**
	 代码模板 - 初始化
	 	使用哈希表或者数组来存储每个节点的父亲节点
		如果节点不是连续整数的话，就最好用哈希表来存储
		最开始所有的父亲节点都指向自己
	 */
	private int size;
	private int[] f;

	public UnionFind(int n) {
		this.size = n;
		this.f = new int[size];

		for (int i = 0; i < this.size; i++) {
			f[i] = i;
		}
	}

	/**
	  代码模板 - 查找老大哥
			沿着父亲节点一路往上走就能找到老大哥
		代码模板 - 路径压缩
			在找到老大哥以后，还需要把一路上经过的点都指向老大哥
	 */
	private int find(int x, int[] f) {
		// 用新变量j代替x找老大哥
		// 用新变量tmp来做swap
		int j, tmp;
		j = x;
		
		// 找到老大哥
		while (f[j] != j) {
			j = f[j];
		}

		// 把一路上经过的点都指向老大哥,
		while(x != j) {
			tmp = f[x];
			f[x] = j;
			x = tmp;
		}

		// return老大哥
		return j;
	}

	/**
	  代码模板 - 集合合并
			找到两个元素所在集合的两个老大哥 A 和 B
			将其中一个老大哥的父指针指向另外一个老大哥
	 */
	public void merge(int x, int y, int[] f) {
		int fx = find(x, f);
		int fy = find(y, f);

		if (fx != fy) {
			// 所有x的师兄弟 都和y的老大哥混了
			f[fx] = fy;
		}
	}

	/** 时间复杂度都是 O(log* n)，约等于 O(1) */
}
