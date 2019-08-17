// 输入: nestedList = [1,[4,[6]]]
// 输出: 17
// 解释：一个深度为3的1， 一个深度为2的4，和一个深度为3的6。1*3 + 4*2 + 6*1 = 17

public class NestedListWeightSumII {
  private int result = 0;
  private int depth = 1;

  public int depthSumInverse(List<NestedInteger> nestedList) {
    if (nestedList == null) return result;

    getDepth(nestedList, 1);

    sum(nestedList, depth);

    return result;
  }

  private void getDepth(List<NestedInteger> nestedList, int level) {
    depth = Math.max(depth, level);
    for (NestedInteger ni : nestedList)
      if (!ni.isInteger())
        getDepth(ni.getList(), level + 1);
  }

  private void sum(List<NestedInteger> nestedList, int height) {
    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) {
        result = result + ni.getInteger() * height;
        continue;
      }

      sum(ni.getList(), height - 1);
    }
  }
}

// just to fix error hints in vscode. - - 
class NestedInteger {
	boolean isInteger() {return true;}

	int getInteger() {return 1;}

	List<NestedInteger> getList() {return new ArrayList<>();}
}
