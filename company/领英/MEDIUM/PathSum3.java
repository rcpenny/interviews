import java.awt.List;
import java.util.ArrayList;

// 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径
// lint1357

// 嘻嘻 bug free
public class PathSum3 {

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    List<Integer> path = new ArrayList<>();
    path.add(root.val);

    find(root, sum, root.val, path, result);

    return result;
  }

  private void find(TreeNode node, int sum, int curSum, List<Integer> path, List<List<Integer>> result) {
    if (node.left == null && node.right == null) {
      if (curSum == sum) result.add(new ArrayList<>(path));
      return;
    }

    if (node.left != null) {
      path.add(node.left.val);
      find(node.left, sum, curSum + node.left.val, path, result);
      path.remove(path.size() - 1);
    }
    
    if (node.right != null) {
      path.add(node.right.val);
      find(node.right, sum, curSum + node.right.val, path, result);
      path.remove(path.size() - 1);
    }
  }
}