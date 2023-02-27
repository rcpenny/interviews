import java.util.Stack;

// leet173
// 类似 flatten nested iterator的解法
class BSTIterator {
  Stack<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    this.stack = new Stack<>();
    pushToStack(root);
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode peek = stack.pop();

    if (peek.right != null) {
      pushToStack(peek.right);
    }

    return peek.val;
  }
  
  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  private void pushToStack(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }
}
