import java.awt.List;
import java.util.ArrayList;

// 我们采用二维数组setList[][]表示集合数组，其中setList[i]中的每个元素都为整数，且不相同。
// 求集合setList[0],setList[1],...,setList[setList.length - 1]的笛卡尔积。
// 一般地，集合A和集合B的笛卡尔积A×B = {(x,y)|x∈A∧y∈B}。

// setList = [[1,2,3],[4],[5,6]]
// 输出: [[1,4,5],[1,4,6],[2,4,5],[2,4,6],[3,4,5],[3,4,6]]
// 解释: [1,2,3]和[4]和[5,6]的笛卡尔积为[[1,4,5],[1,4,6],[2,4,5],[2,4,6],[3,4,5],[3,4,6]]

// 递归定义第一次写的时候开多了几个变量

public class CartesianProduct {
  public List<List<Integer>> getSet(int[][] setList) {
		List<List<Integer>> result = new ArrayList<>();

		search(setList, 0, new ArrayList<>(), result);

		return result;
	}

	private void search(int[][] setList, int depth, List<Integer> products, List<List<Integer>> result) {
		if (depth == setList.length) {
			result.add(new ArrayList<>(products));
			return;
		}

		for (int i = 0; i < setList[depth].length; i++) {
			products.add(setList[depth][i]);
			search(setList, depth + 1, products, result);
			products.remove(products.size() - 1);
		}
	}
}
