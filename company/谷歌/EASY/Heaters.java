import java.util.HashSet;
import java.util.Set;

// 冬天来啦！你的任务是设计出一个具有固定加热半径的加热器，使得所有房屋在这个冬天不至于太冷。
// 现在你能够获知所有房屋和加热器所处的位置，它们均分布在一条水平线中。
// 你需要找出最小的加热半径使得所有房屋都处在至少一个加热器的加热范围内。
// 所以，你的输入将会是所有房屋和加热器所处的位置，期望输出为加热器最小的加热半径。

// 输入：[1,2,3,4],[1,4]  输出：1
// 说明：两个加热器分别位于1和4，只需要加热半径为1，就能加热所有房屋了

// 1.Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
// 2.Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
// 3.As long as a house is in the heaters' warm radius range, it can be warmed.
// 4.All the heaters follow your radius standard and the warm radius will the same.

// 二分答案，一下子就想到啦！ 对每个house，进行heaters里面的二分答案
public class Heaters {

  public int findRadius(int[] houses, int[] heaters) {
		int max_radius = Integer.MIN_VALUE;
		Arrays.sort(heaters);

		for (int i = 0; i < houses.length; i++)
			max_radius = Math.max(max_radius, closestHeaterDistance(heaters, houses[i]));

		return max_radius;
	}

	public int closestHeaterDistance(int[] heaters, int pos) {
		int start = 0;
		int end = heaters.length - 1;

		while (start + 1 < end) {
			int middle = start + (end - start) / 2;
			if (heaters[middle] == pos) return 0;
			
			if (heaters[middle] > pos) end = middle;
			else start = middle; 
		}

		int start_diff = Math.abs(heaters[start] - pos);
		int end_diff = Math.abs(heaters[end] - pos);
    return Math.min(start_diff, end_diff);
	}
}
