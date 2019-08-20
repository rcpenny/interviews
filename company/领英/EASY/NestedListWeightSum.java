import java.util.ArrayList;
import java.util.List;

// 给一个嵌套的整数列表, 返回列表中所有整数由它们的深度加权后的总和. 
// 每一个元素可能是一个整数或一个列表(其元素也可能是整数或列表)

// Input: the list [1,[4,[6]]], 
// Output: 27. 
// Explanation: 1 + 4 * 2 + 6 * 3 = 27

public class NestedListWeightSum {

  public int depthSum(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
	}

	// 定义：元数据nestedList, 控制depth
	private int helper(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0) return 0;
		int sum = 0; // 这一层每个NestedInteger的sum

		for (NestedInteger ni : nestedList) {
			if (ni.isInteger())
				sum = sum + ni.getInteger() * depth;
			else
				sum = sum + helper(ni.getList(), depth + 1);
		}

		return sum;
	}
}

// just to fix error hints in vscode. - - 
class NestedInteger {
	boolean isInteger() {
		return true;
	}
	int getInteger() {
		return 1;
	}
	List<NestedInteger> getList() {
		return new ArrayList<>();
	}
}
