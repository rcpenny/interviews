import java.util.LinkedList;

public class CompleteBinaryTree {
  public boolean isComplete(TreeNode root) {
    if (root == null) return true;

    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> indexes = new LinkedList<>();
    queue.offer(root);
    indexes.offer(0);

    int count = 0; // count of nodes polled out of queue

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        TreeNode tmp = queue.poll();
        count++;

        int index = indexes.poll();
        if (count - 1 != index) return false;

        if (tmp.left != null) {
          queue.offer(tmp.left);
          indexes.offer(index * 2 + 1);
        }
        if (tmp.right != null) {
          queue.offer(tmp.right);
          indexes.offer(index * 2 + 2);
        }
      }
    }
    return true;
  }
}
