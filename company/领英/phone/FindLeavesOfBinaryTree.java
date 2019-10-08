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
	// 或者先max depth一下tree，就不用map了
  private Map<Integer, List<Integer>> depthToLeaves = new TreeMap<>();

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();

		int root_height = reverseDepthOfNode(root);
    addLeaf(root, root_height);

    // convert map to list
    for (int key : depthToLeaves.keySet()) {
      results.add(depthToLeaves.get(key));
		}

    return results;
  }

  // return depth of this node, leaf is zero
  private int reverseDepthOfNode(TreeNode node) {
    if (node == null) return -1;

    int left = reverseDepthOfNode(node.left);
		addLeaf(node.left, left);

    int right = reverseDepthOfNode(node.right);
    addLeaf(node.right, right);

    return Math.max(left, right) + 1;
  }

  // add node to the map
  private void addLeaf(TreeNode node, int depth) {
    if (depth == -1) return;
    depthToLeaves.putIfAbsent(depth, new ArrayList<>());
    depthToLeaves.get(depth).add(node.val);
  }
}
