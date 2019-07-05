import java.util.Comparator;
import java.util.PriorityQueue;

// Given k sorted integer arrays, merge them into one sorted array.

// Example
// Example 1:

// Input: 
//   [
//     [1, 3, 5, 7],
//     [2, 4, 6],
//     [0, 8, 9, 10, 11]
//   ]
// Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

// Do it in O(N log k).

// N is the total number of integers.
// k is the number of arrays.

class Element {
	int row;
	int col;
	int val;
	Element(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}
}

public class MergeKSortedArray {

	private Comparator<Element> cpt = new Comparator<Element>() {
		@Override
		public int compare(Element a, Element b) {
			return a.val - b.val;
		}
	};

  public int[] mergekSortedArrays(int[][] arrays) {
		if (arrays == null) return new int[] {};
		
		PriorityQueue<Element> heap = new PriorityQueue<>(arrays.length, cpt);
		int size = 0;

		// add first element of each array to heap
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] == null || arrays[i].length == 0) continue;
			size = size + arrays[i].length;
			Element tmp = new Element(i, 0, arrays[i][0]);
			heap.offer(tmp);
		}

		int[] result = new int[size];
		int index = 0;
		
		// O(n)
		while (!heap.isEmpty()) {
			Element current = heap.poll();
			result[index] = current.val;
			index++;
			if (current.col != arrays[current.row].length - 1) {
				int row = current.row;
				int col = current.col;
				Element after = new Element(row, col + 1, arrays[row][col + 1]);
				// O(LogK)
				heap.offer(after);
			}
		}

		return result;
	}
}
