// 给定一棵非空二叉搜索树以及一个target值，找到 BST 中最接近给定值的 k 个数

// 暴力解法：in-order traverse find two points find k elements
// O(N) O(N)

// O(K) O(K)
// 思路等同于从指定节点开始分别向前和向后遍历，直到找到k个最接近target的节点
// 使用prev和next两个栈分别记录前驱和后继，goPrev相当于反向中序遍历，goNext相当于正向中序遍历

public class ClosestBSTValueII {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> result = new ArrayList<>();
		if (root == null || k == 0) return result;

		Stack<TreeNode> next = new Stack<>();
		Stack<TreeNode> prev = new Stack<>();

		while (root != null) {
			if (root.val < target) {
				prev.push(root);
				root = root.right;
			} else {
				next.push(root);
				root = root.left;
			}
		}

		while (result.size() < k) {
			double diff_p = prev.isEmpty() ? Double.MAX_VALUE :  target - prev.peek().val;
			double diff_n = next.isEmpty() ? Double.MAX_VALUE : next.peek().val - target;

			if (diff_p < diff_n) {
				res.add(prev.peek().val);
				movePrev(prev);
			} else {
				res.add(prev.peek().val);
				moveNext(next);
			}
		}

		return result;
	}

	private void movePrev(Stack<TreeNode> prev) {
		TreeNode tmp = prev.pop().left;
		while (tmp != null) {
			prev.push(tmp);
			tmp = tmp.right;
		}
	}

	private void moveNext(Stack<TreeNode> next) {
		TreeNode tmp = s.pop().right;
		while (tmp != null) {
			next.push(tmp);
			tmp = tmp.left;
		}
	}
}
