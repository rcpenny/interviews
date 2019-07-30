import java.util.ArrayList;
import java.util.HashMap;

// leet366

// 给定一个二叉树，像这样收集树节点：收集并移除所有叶子，重复，直到树为空。

// Input: {1,2,3,4,5}
// Output: [[4, 5, 3], [2], [1]].
// Explanation:

//     1
//    / \
//   2   3
//  / \     
// 4   5

// 用map记录，不用手动扩results，dfs return depth. 不用ResultType，因为只记录了一个depth
public class FindLeavesOfBinaryTree {
  private Map<Integer, List<Integer>> depthToLeaves = new HashMap<>();

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();

    traverse(root);
    addLeaf(root, traverse(root));

    for (int key : depthToLeaves.keySet())
      results.add(depthToLeaves.get(key));

    return results;
  }

  // return depth of this node, leaf is zero
  private int traverse(TreeNode node) {
    if (node == null) return -1;

    int left = traverse(node.left);
    addLeaf(node.left, left);
    int right = traverse(node.right);
    addLeaf(node.right, right);

    return Math.max(left, right) + 1;
  }

  private void addLeaf(TreeNode node, int depth) {
    if (depth == -1) return;
    depthToLeaves.putIfAbsent(depth, new ArrayList<>());
    depthToLeaves.get(depth).add(node.val);
  }
}
