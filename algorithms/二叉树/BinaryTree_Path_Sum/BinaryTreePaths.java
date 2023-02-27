import java.util.ArrayList;
import java.util.List;

/** 
 * Given a binary tree, return all root-to-leaf paths.
 * Input：{1,2,3,#,5}
 * Output：["1->2->5","1->3"]
 */

public class BinaryTreePaths {
	private List<String> results = new ArrayList<>();	

  public List<String> binaryTreePaths(TreeNode root) {
		if (root == null) return results;

		helper(root, String.valueOf(root.val));

		return results;
	}

	private void helper(TreeNode node, String s) {
		if (node.left == null && node.right == null) results.add(s);

		if (node.left != null) helper(node.left, s + "->" + String.valueOf(node.left.val));
		if (node.right != null) helper(node.right, s + "->" + String.valueOf(node.right.val));
	}
}
