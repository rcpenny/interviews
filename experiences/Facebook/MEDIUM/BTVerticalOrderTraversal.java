import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

// 给定二叉树，返回其节点值的垂直遍历顺序。 (即逐列从上到下)。
// 如果两个节点在同一行和同一列中，则顺序应 从左到右

class Elem {
  TreeNode node;
  int height;
  Elem(TreeNode node, int col) {
    this.node = node;
    this.height = col;
  }
}

// 思路：vertical涉及了level,用bfs
public class BTVerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    if (root == null) return null;
    
    List<List<Integer>> left = new ArrayList<>();
    List<Integer> mid = new ArrayList<>();
    List<List<Integer>> right = new ArrayList<>();
    
    Elem e = new Elem(root, 0);
    Queue<Elem> queue = new LinkedList<>();
    queue.offer(e);
    
    
    while (!queue.isEmpty()) {
      Elem tmp = queue.poll();
      
      if (tmp.height == 0) mid.add(tmp.node.val);
      
      if (tmp.height < 0) {
        if (-tmp.height > left.size()) left.add(new ArrayList<>());
        left.get(-tmp.height - 1).add(tmp.node.val);
      }
      
      if (tmp.height > 0) {
        if (right.size() < tmp.height) right.add(new ArrayList<>());
        right.get(tmp.height - 1).add(tmp.node.val);
      }
      
      if (tmp.node.left != null) {
        queue.offer(new Elem(tmp.node.left, tmp.height - 1));
      }
      
      if (tmp.node.right != null) {
        queue.offer(new Elem(tmp.node.right, tmp.height + 1));
      }
    }
    
    Collections.reverse(left);
    left.add(mid);
    left.addAll(right);
    
    return left;
  }
}
