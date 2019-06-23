import java.util.HashSet;
import java.util.Set;

/**
 * https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-tree-ii/
 * 
 * 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)
 * 最近公共祖先是两个节点的公共的祖先节点且具有最大深度
 * 
 * 假设给出的两个节点都在树中存在
 */
class ParentTreeNode {
	public ParentTreeNode parent, left, right;
}

public class LowestCommonAncestor2 {
	public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
		if (root == null) return null;
        
		Set<ParentTreeNode> ancestors = new HashSet<ParentTreeNode>();
		ParentTreeNode result = null;
		
		while (A != null) {
			ancestors.add(A);
			A = A.parent;
		}
		
		while (B != null) {
			if (ancestors.contains(B)) {
				result = B;
				break;
			}
			B = B.parent;
		}	
		return result;
	}
}
