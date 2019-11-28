import java.util.ArrayList;
import java.util.List;

// 给一个嵌套的整数列表, 返回列表中所有整数由它们的深度加权后的总和. 
// 每一个元素可能是一个整数或一个列表(其元素也可能是整数或列表)

// Input: the list [1,[4,[6]]], 
// Output: 27. 
// Explanation: 1 * 1 + 4 * 2 + 6 * 3 = 27
// lc339

public class NestedListWeightSum {
  public int depthSum(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
	}

	// 定义：元数据nestedList  控制depth  状态sum通过return value传递
	private int helper(List<NestedInteger> nestedList, int depth) {
		// 其实不用这步
		if (nestedList == null || nestedList.size() == 0) {
			return 0;
		}
		
		int sum = 0; // 这一层每个NestedInteger的sum

		for (NestedInteger ni : nestedList) {
			if (ni.isInteger())
				sum += ni.getInteger() * depth; // 不要忘了乘以depth
			else
				sum += helper(ni.getList(), depth + 1);
		}

		return sum;
	}
}
