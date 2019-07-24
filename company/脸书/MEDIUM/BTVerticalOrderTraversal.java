import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

// 给定二叉树，返回其节点值的垂直遍历顺序。 (即逐列从上到下)。
// 如果两个节点在同一行和同一列中，则顺序应 从左到右

class Element {
  int col;
  TreeNode node;
  Element(int col, TreeNode node) {
    this.col = col;
    this.node = node;
  }
}

// 思路：vertical涉及了level,用bfs
public class BTVerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> left = new ArrayList<>(); // negative cols
    List<List<Integer>> right = new ArrayList<>(); // non-negative cols

    if (root == null) return right;

    Queue<Element> queue = new LinkedList<>();
    queue.offer(new Element(0, root));

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        Element elem = queue.poll();

        if (elem.col >= 0) {
          while (right.size() < elem.col + 1) right.add(new ArrayList<>());
          right.get(elem.col).add(elem.node.val);
        }

        if (elem.col < 0) {
          while (left.size() < Math.abs(elem.col)) left.add(new ArrayList<>());
          left.get(-1 - elem.col).add(elem.node.val);
        }

        // offer elem's children
        if (elem.node.left != null)
          queue.offer(new Element(elem.col - 1, elem.node.left));

        if (elem.node.right != null)
          queue.offer(new Element(elem.col + 1, elem.node.right));
      }
    }

    Collections.reverse(left);
    left.addAll(right);

    return left;
  }
}
