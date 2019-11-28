import java.util.Stack;

/** 在 BST iterator里用stack做这题 */

public class KthSmallestElemBST {
	// 中序遍历从小到大
  public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();

		while (root != null) {
			stack.push(root);
			root = root.left;
		}

		// pop k - 1 次就好了
		for (int i = 0; i < k - 1; i++) {
			TreeNode node = stack.peek();

			// 首先判断这个node有没有右子树

			// 右子树不为空，说明还有更大的可以push.
			if (node.right != null) {
				node = node.right;
				while (node != null) {
					stack.push(node);
					node = node.right;
				}
				continue;
			}
			
			if (node.right == null) {
				node = stack.pop();
				while (!stack.isEmpty() && stack.peek().right == node)
					node = stack.pop();
			}
		}

		return stack.peek().val;
	}
}
