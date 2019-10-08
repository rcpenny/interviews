import java.util.Stack;

// lc341

public class NestedIterator implements Iterator<Integer> {
  private Stack<NestedInteger> stack;

  private NestedIterator(List<NestedInteger> nestedList) {
    this.stack = new Stack<>();
    pushNestedListToStack(nestedList);
  }

  @Override
  public Integer next() {
    if (!hasNext()) return null; // hasnext 强制处理结构
    return stack.pop().getInteger();
  }

  @Override
  public boolean hasNext() { // has next make sure current stack top is integer
    while (!stack.isEmpty() && !stack.peek().isInteger()) {
      pushNestedListToStack(nestedList);
    }
    return !stack.isEmpty();
  }

  @Override
  public void remove() {
    if (!hasNext()) return;
    stack.pop();
  }

  // 把这一层的nested integer push进stack, 倒装， 画图想一下
  private void pushNestedListToStack(List<NestedInteger> nestedList) {
    Stack<NestedInteger> buffer = new Stack<>();

    for (NestedInteger ni : nestedList) {
      buffer.push(ni);
    }

    while (!container.isEmpty()) {
      this.stack.push(buffer.pop());
    }
  }
}
