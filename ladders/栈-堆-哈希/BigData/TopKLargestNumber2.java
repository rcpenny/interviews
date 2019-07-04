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
	PriorityQueue<Integer> pq;
	int size;

  public TopKLargestNumber2(int k) {
		this.pq = new PriorityQueue<>(k);
		this.size = k;
  }

  public void add(int num) {
    if (pq.size() < this.size) {
      pq.offer(num);
      return;
    }
    if (num > pq.peek()) {
			pq.poll();
      pq.offer(num);
    }
  }

  public List<Integer> topk() {
    List<Integer> topKNumbers = new ArrayList<>();
    Iterator it = pq.iterator();
    while (it.hasNext()) topKNumbers.add((Integer) it.next());
    Collections.sort(topKNumbers, Collections.reverseOrder());
    return topKNumbers;
  }
}
