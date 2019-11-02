// leet347

class TopKFrequentElements {
  public List<Integer> topKFrequent(int[] nums, int k) {
    List<Integer> topK = new ArrayList<>();

    Map<Integer, Integer> numToFreq = new HashMap<>();
    for (int num : nums) {
      numToFreq.put(num, numToFreq.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<Map.Entry<Integer, Integer>> minheap = new PriorityQueue<>(k, (a, b) -> {
      return a.getValue() - b.getValue();
    });

    for (Map.Entry<Integer, Integer> entry : numToFreq.entrySet()) {
      if (minheap.size() < k) {
        minheap.offer(entry);
        continue;
      }
      if (entry.getValue() > minheap.peek().getValue()) {
        minheap.poll();
        minheap.offer(entry);
      }
    }

    while (!minheap.isEmpty()) {
      topK.add(minheap.poll().getKey());
    }

    Collections.reverse(topK);

    return topK;
  }
}
