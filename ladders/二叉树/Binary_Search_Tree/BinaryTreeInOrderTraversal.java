import java.util.ArrayList;
import java.util.Stack;

/** 二叉树的中序遍历(非递归) */
// stack 中保存一路走到当前节点的所有节点，stack.peek() 一直指向 iterator 指向的当前节点。
// 因此判断有没有下一个，只需要判断 stack 是否为空
// 获得下一个值，只需要返回 stack.peek() 的值，并将 stack 进行相应的变化，挪到下一个点。

// 挪到下一个点的算法如下：

// 如果当前点存在右子树，那么就是右子树中“一路向左下”的那个点
// 如果当前点不存在右子树，则是走到当前点的路径中，第一个左拐的点
// 访问所有节点用时O(n)，所以均摊下来访问每个节点的时间复杂度时O(1)

public class BinaryTreeInOrderTraversal {
	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;

		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
					stack.push(curr);
					curr = curr.left;
			}
			curr = stack.pop();
			res.add(curr.val);
			curr = curr.right;
		}

		return res;
	}
}
