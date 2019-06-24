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
	
stack 中保存一路走到当前节点的所有节点，stack.peek() 一直指向 iterator 指向的当前节点。
因此判断有没有下一个，只需要判断 stack 是否为空
获得下一个值，只需要返回 stack.peek() 的值，并将 stack 进行相应的变化，挪到下一个点。

挪到下一个点的算法如下：

如果当前点存在右子树，那么就是右子树中“一路向西”最左边的那个点
如果当前点不存在右子树，则是走到当前点的路径中，第一个左拐的点
访问所有节点用时O(n)，所以均摊下来访问每个节点的时间复杂度时O(1)
	*/

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

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
		TreeNode currentNode = stack.peek();
		TreeNode node = currentNode;

		if (node.right == null) { // -1的情况和6的情况
			node = stack.pop();
			while (!stack.empty() && stack.peek().right == node) {
				node = stack.pop();
			}
		} else if (node.right != null) {
			// 1的情况
			node = node.right;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}
		return currentNode;
	}
}
/**
      10
  	  /\
 	   1 11
    /	\  \
  -1 	 6  12
 */
