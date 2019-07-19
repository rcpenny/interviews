import java.util.Stack;

import javax.swing.tree.TreeNode;

/**
 * https://www.lintcode.com/problem/kth-smallest-element-in-a-bst/
 * 在 BST iterator里用stack做这题
 */
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

			// 这个node的右子树为空，说明对于这个node作为root，没有更大的可以加了，向上回溯.
			// 如果是在node 3 在这个/枝上         如果node 3在 \ 这个枝上    10
			//				5                                1              /
			//			/																		 \           1
			//    3                                        3          \
			// 直接 pop() 下一个peek就是5了       一路pop()到拐点10          3
			if (node.right == null) {
				node = stack.pop();
				while (!stack.isEmpty() && stack.peek().right == node)
					node = stack.pop();
			}
		}

		return stack.peek().val;
	}
}
