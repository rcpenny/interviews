// 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
// 输入: {1,2,3,#,5,#,4}
// 输出: [1,3,4]

public class BinaryTreeRightSideView {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while(!queue.isEmpty()) {
      int size = queue.size();
      
      for (int i = 0; i < size; i++) {
        TreeNode tmp = queue.poll();
        if (tmp.left != null) queue.offer(tmp.left);
        if (tmp.right != null) queue.offer(tmp.right);
        
        if (i == size - 1) result.add(tmp.val);
      }
    }
    
    return result;
  }
}
