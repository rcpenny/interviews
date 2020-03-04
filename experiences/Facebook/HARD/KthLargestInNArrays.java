import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 在N个数组中找到第K大元素

class Element {
	int row;
	int col;
	int val;
	Element (int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}
}

public class KthLargestInNArrays {
  public int KthInArrays(int[][] arrays, int k) {
		PriorityQueue<Element> maxheap = new PriorityQueue<>(k, (a, b) -> {
			return b.val - a.val;
		});

		// 要sort呀...
		for (int i = 0; i < arrays.length; i++) {
			Arrays.sort(arrays[i]);
			if (arrays[i].length == 0) continue;
			int col = arrays[i].length - 1;
			maxheap.offer(new Element(i, col, arrays[i][col]));
		}

		for (int i = 0; i < k - 1; i++) {
			Element tmp = maxheap.poll();
			if (tmp.col - 1 < 0) continue;
			Element next = new Element(tmp.row, tmp.col - 1, arrays[tmp.row][tmp.col - 1]);
			maxheap.offer(next);	
		}

		return maxheap.peek().val;
	}
}
