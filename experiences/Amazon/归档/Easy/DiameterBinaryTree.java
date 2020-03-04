// 给定一颗二叉树，您需要计算树的直径长度。 
// 二叉树的直径是树中任意两个节点之间最长路径的长度。 此路径不一定会通过树根。
// 给定一棵二叉树 
//           1
//          / \
//         2   3
//        / \     
//       4   5    
// 返回3, 这是路径[4,2,1,3] 或者 [5,2,1,3]的长度.

// leet534

public class DiameterBinaryTree {
  private int max = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) return 0;

    int depth = getDepth(root);

    return Math.max(depth, max) - 1;
  }

  private int getDepth(TreeNode node) {
    if (node == null) return 0;

    int leftDepth = getDepth(node.left);
    int rightDepth = getDepth(node.right);

    max = Math.max(max, leftDepth + 1 + rightDepth);

    return Math.max(leftDepth, rightDepth) + 1;
  }
}
