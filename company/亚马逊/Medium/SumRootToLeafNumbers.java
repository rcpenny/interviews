// 给定仅包含来自0-9的数字的二叉树，每个根到叶路径可以表示数字。
// 举个例子：root-to-leaf路径1-> 2-> 3，它代表数字123，找到所有根到叶的数的总和

// 亚麻题是下面这道： 不过差不多了. 乘10改乘2 就好了
// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

public class SumRootToLeafNumbers {
	private int sum = 0;

  public int sumNumbers(TreeNode root) {
		dfs(root, 0);
		return sum;
	}

	private void dfs(TreeNode node, int current_sum) {
		if (node == null) return;
		
		current_sum = current_sum + node.val;

		if (node.left == null && node.right == null) {
      sum = sum + current_sum;
			return;
		}

		dfs(node.left, current_sum * 10);
		dfs(node.right, current_sum * 10);
	}
}
