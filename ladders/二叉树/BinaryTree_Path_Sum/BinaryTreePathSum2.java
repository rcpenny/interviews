import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给一棵二叉树和一个目标值，设计一个算法找到二叉树上的和为该目标值的所有路径。
 * 路径可以从任何节点出发和结束，但是需要是一条一直往下走的路线。
 * 也就是说，路径上的节点的层级是逐个递增的。
 */

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class BinaryTreePathSum2 {
  public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
    List<List<Integer>> results = new ArrayList<>();

    findSum(root, target, 0, new ArrayList<Integer>(), results);

    return results;
  }

  private void findSum(TreeNode node, int target, int level, List<Integer> path, List<List<Integer>> results) {
    if (node == null) return;

    path.add(node.val);
    int tmp_sum = 0;

    // 从当前level往root找，sum为target的list
    for (int i = level; i >= 0; i--) {
      tmp_sum += path.get(i);
      if (tmp_sum == target) {
        List<Integer> solution = new ArrayList<>();
        for (int j = i; j <= level; j++) {
          solution.add(path.get(j));
        }
        results.add(solution);
      }
    }

    findSum(node.left, target, level + 1, path, results);
    findSum(node.right, target, level + 1, path, results);

    path.remove(path.size() - 1);
  }
}