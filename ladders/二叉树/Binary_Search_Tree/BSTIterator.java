import java.util.Stack;

/**
	* https://www.lintcode.com/problem/binary-search-tree-iterator/
	*
	* Example of iterate a tree:
	* BSTIterator iterator = new BSTIterator(root);
	* while (iterator.hasNext()) {
	*    TreeNode node = iterator.next();
	*    do something for node
	* }
  */

// 多写几遍
public class BSTIterator {
	private Stack<TreeNode> stack = new Stack<>();

	public BSTIterator(TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	public boolean hasNext() {
		return !stack.empty();
	}

	// return stack peek(current min in BST), then move stack peek to next node(min)
	public TreeNode next() {
		TreeNode result = stack.peek();
		TreeNode node = stack.peek();

		if (node.right != null) {
			node = node.right;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}

		else if (node.right == null) {
			node = stack.pop();
			while (!stack.isEmpty() && stack.peek().right == node) {
				node = stack.pop();
			}
		}

		return result;
	}
}
