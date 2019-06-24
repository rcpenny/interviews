/**
 * 上一题的Follow up: 二叉树经常被修改?
 * 在 TreeNode 中增加一个 counter，代表整个树的节点个数
 * 
 * 也可以用一个 HashMap<TreeNode, Integer> 来存储某个节点为代表的子树的节点个数
 * 在增删查改的过程中记录不断更新受影响节点的 counter
 * 在 kthSmallest 的实现中用类似 Quick Select 的算法去找到 kth smallest element
 * 时间复杂度为 O(h)，h 为树的高度。
 */
public class KthSmallestElemBST2 {

	public int kthSmallest(TreeNode root, int k) {
			Map<TreeNode, Integer> numOfChildren = new HashMap<>();
			countNodes(root, numOfChildren);
			return quickSelectOnTree(root, k, numOfChildren);
	}
	
	private int countNodes(TreeNode root, Map<TreeNode, Integer> numOfChildren) {
			if (root == null) {
					return 0;
			}
			
			int left = countNodes(root.left, numOfChildren);
			int right = countNodes(root.right, numOfChildren);
			numOfChildren.put(root, left + right + 1);
			return left + right + 1;
	}
	
	private int quickSelectOnTree(TreeNode root, int k, Map<TreeNode, Integer> numOfChildren) {
			if (root == null) {
					return -1;
			}
			
			int left = root.left == null ? 0 : numOfChildren.get(root.left);
			if (left >= k) {
					return quickSelectOnTree(root.left, k, numOfChildren);
			}
			if (left + 1 == k) {
					return root.val;
			}
			
			return quickSelectOnTree(root.right, k - left - 1, numOfChildren);
	}
}
