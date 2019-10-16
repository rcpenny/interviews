import java.util.ArrayList;
import java.util.TreeMap;

// lc366
// 给定一个二叉树，像这样收集树节点：收集并移除所有叶子，重复，直到树为空。

// Input: {1,2,3,4,5}
// Output: [[4, 5, 3], [2], [1]].
// Explanation:

//     1
//    / \
//   2   3
//  / \     
// 4   5

// 每个node在output List中对应的index + 1 = max(左子height, 右子height) + 1
// 进阶版的max depth of tree

public class FindLeavesOfBinaryTree {
  List<List<Integer>> leaves = new ArrayList<>();

  public List<List<Integer>> findLeaves(TreeNode root) {
    int rootDepth = nodeReversedDepth(root);

    collectLeaf(root, rootDepth);

    return leaves;
  }

  // reversed depth means leaf depth is 1
  private int nodeReversedDepth(TreeNode node) {
    if (node == null) return 0;

    int leftDepth = nodeReversedDepth(node.left);
    int rightDepth = nodeReversedDepth(node.right);

    collectLeaf(node.left, leftDepth);
    collectLeaf(node.right, rightDepth);

    return Math.max(leftDepth, rightDepth) + 1;
  }

  private void collectLeaf(TreeNode node, int depth) {
    if (depth == 0) return;
    
    while (leaves.size() < depth) {
      leaves.add(new ArrayList<>());
    }
    leaves.get(depth - 1).add(node.val);
  }
}
