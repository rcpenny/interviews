import java.util.ArrayList;

/**
 * https://www.lintcode.com/problem/binary-tree-level-order-traversal/
 */

public class BinaryTreeLevelOrderTraversal {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) return results;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while(!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        level.add(node.val);
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }
      results.add(level);
    }

    return results;
  }
}