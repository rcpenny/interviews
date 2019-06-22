import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/** https://www.lintcode.com/problem/binary-tree-zigzag-level-order-traversal */

public class BinaryTreeZigZagOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) return results;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 1;

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        list.add(node.val);
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      } 
      if (level % 2 == 0) Collections.reverse(list);
      level++;
      results.add(list);
    }

    return results;
  }
}