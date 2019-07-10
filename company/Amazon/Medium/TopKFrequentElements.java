// 高频题

class Element {
  int val;
  int freq;
  Element (int val, int freq) {
    this.val = val;
    this.freq = freq;
  }
}

public class Solution {
	private Comparator<Element> cpt = new Comparator<Element>() {
		@Override public int compare(Element a, Element b) {return a.freq - b.freq;}
	};

	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> topk = new ArrayList<>();
		
		Map<Integer, Integer> numToFreq = new HashMap<>();
		for (int number : nums) numToFreq.put(number, numToFreq.getOrDefault(number, 0) + 1);
		
		PriorityQueue<Element> minheap = new PriorityQueue<>(k, cpt);
		for (int key : numToFreq.keySet()) {
			Element tmp = new Element(key, numToFreq.get(key));
			if (minheap.size() < k) {
				minheap.offer(tmp);
				continue;
			}
			if (cpt.compare(tmp, minheap.peek()) > 0) {
				minheap.poll();
				minheap.offer(tmp);
			}
		}
		
		for (int i = 0; i < k; i++) topk.add(minheap.poll().val);
		Collections.reverse(topk);
		return topk;
	}
}
