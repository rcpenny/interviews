// leet572

@TreeDivideAndConquer
public class SubtreeofAnotherTree {
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null && t == null) return true;
    if (s == null || t == null) return false;

    boolean result = false;
    if (s.val == t.val) {
      result = sameTree(s, t);
    }

    return result || isSubtree(s.left, t) || isSubtree(s.right, t);
  }

  private boolean sameTree(TreeNode a, TreeNode b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return false;

    return a.val == b.val && sameTree(a.left, b.left) && sameTree(a.right, b.right);
  }
}