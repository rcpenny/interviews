public class UnionFindTemplate {
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
			// 所有a的师兄弟 都和b的老大哥b_bigbro混了
			parent[a_bigbro] = b_bigbro;
		}
	}
}
