import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

// Implement a data structure, provide two interfaces:

// add(number). Add a new number in the data structure.
// topk(). Return the top k largest numbers in this data structure. 
// k is given when we create the data structure.

public class TopKLargestNumber2 {
	PriorityQueue<Integer> minheap;
	int size;

  public TopKLargestNumber2(int k) {
		this.minheap = new PriorityQueue<>(k);
		this.size = k;
  }

  public void add(int num) {
    if (minheap.size() < this.size) {
      minheap.offer(num);
      return;
    }
    if (num > minheap.peek()) {
			minheap.poll();
      minheap.offer(num);
    }
  }

  public List<Integer> topk() {
    List<Integer> topKNumbers = new ArrayList<>();
    Iterator it = minheap.iterator();
    while (it.hasNext()) {
      topKNumbers.add((Integer) it.next());
    }
    Collections.sort(topKNumbers, Collections.reverseOrder());
    return topKNumbers;
  }
}
