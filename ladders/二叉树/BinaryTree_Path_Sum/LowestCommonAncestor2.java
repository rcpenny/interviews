import java.util.HashSet;
import java.util.Set;

/** 
 * 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)
 * 最近公共祖先是两个节点的公共的祖先节点且具有最大深度
 * 
 * 假设给出的两个节点都在树中存在
 */
class ParentTreeNode {
	public ParentTreeNode parent, left, right;
}

// 用set waste memory，use 2 times traverse
public class LowestCommonAncestor2 {
	
	// solution like linked list intersecion detection with no cycles
	public ParentTreeNode lowestCommonAncestorII2(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
    ParentTreeNode copyA = A;
    ParentTreeNode copyB = B;
    
    while (copyA != copyB) {
      copyA = (copyA == root) ? B : copyA.parent;
      copyB = (copyB == root) ? A : copyB.parent;
    }    

    return copyA;
  }

	
	
	
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
