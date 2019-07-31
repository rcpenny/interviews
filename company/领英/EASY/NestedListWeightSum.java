import java.util.List;
// 给一个嵌套的整数列表, 返回列表中所有整数由它们的深度加权后的总和. 每一个元素可能是一个整数或一个列表(其元素也可能是整数或列表)

// Input: the list [1,[4,[6]]], 
// Output: 27. 
// Explanation:
// one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27

public class NestedListWeightSum {

  public int depthSum(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
	}

	private int helper(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0) return 0;

		int sum = 0;
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger())
				sum = sum + ni.getInteger() * depth;
			else
				sum = sum + helper(ni.getList(), depth + 1);
		}
		return sum;
	}
}

public interface NestedInteger {
		public boolean isInteger();
		public Integer getInteger();
		public List<NestedInteger> getList(); // return the nested list that this NestedInteger holds if it holds a nested list
 }


