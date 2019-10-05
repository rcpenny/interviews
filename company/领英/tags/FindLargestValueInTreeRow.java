import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 你需要找到在一棵二叉树中，每一行的最大值
// lint1195

public class FindLargestValueInTreeRow {

  public List<Integer> largestValues(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while(!queue.isEmpty()) {
      int size = queue.size();
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode tmp = queue.poll();
        max = Math.max(max, tmp.val);
        if (root.left != null) queue.offer(root.left);
        if (root.right != null) queue.offer(root.right);
      }
      result.add(max);
    }

    return result;
  }
}