import java.util.ArrayList;
import java.util.Stack;

/** 二叉树的中序遍历(非递归) */
class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class BinaryTreeIntOrderTraversal {

  public List<Integer> inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		
		while (root != null) {
			stack.push(root);
			root = root.left;
		}

		while (!stack.empty()) {
			TreeNode node = stack.peek();
			list.add(node.val);

			if (node.right == null) {
				node = stack.pop();
				while (!stack.empty() && stack.peek().right == node) {
					node = stack.pop();
				}
			} else if (node.right != null) {
				node = node.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}

		return list;
	}
}
