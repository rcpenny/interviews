// 输入: nestedList = [1,[4,[6]]]
// 输出: 17
// 解释：一个深度为3的1， 一个深度为2的4，和一个深度为3的6。1*3 + 4*2 + 6*1 = 17
// lc364

public class NestedListWeightSumII {
  int depth = 0;

  public int depthSumInverse(List<NestedInteger> nestedList) {
    if (nestedList == null || nestedList.size() == 0) return 0;
    
    getDepth(nestedList, 1);

    return getSum(nestedList, depth);
  }
  
  private int getSum(List<NestedInteger> nestedList, int depth) {
    if (nestedList == null || nestedList.size() == 0) return 0;
    int sum = 0;

    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) sum += ni.getInteger() * depth;
      else sum += getSum(ni.getList(), depth - 1);
    }

    return sum;
  }
  
  private void getDepth(List<NestedInteger> nestedList, int level) {
    depth = Math.max(depth, level);
    
    for (NestedInteger ni : nestedList)
      if (!ni.isInteger())
        getDepth(ni.getList(), level + 1);
  }
}

// just to fix error hints in vscode. - - 
class NestedInteger {
	boolean isInteger() {return true;}
	int getInteger() {return 1;}
	List<NestedInteger> getList() {return new ArrayList<>();}
}
