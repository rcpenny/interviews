import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 给定非空二叉树，以数组的形式返回每一层上的节点的平均值
public class AvgLevelBinaryTree {
	public List<Double> averageOfLevels(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		List<Double> results = new ArrayList<>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			int sum = 0;
			for (int i = 0; i < size; i++) {
				TreeNode tmp = queue.poll();
				sum = sum + tmp.val;
				if (tmp.left != null) queue.offer(tmp.left);
				if (tmp.right != null) queue.offer(tmp.right);
			}
			Double avg = sum * 1.0 / size;
			results.add(avg);
		}

		return results;
	}
}
