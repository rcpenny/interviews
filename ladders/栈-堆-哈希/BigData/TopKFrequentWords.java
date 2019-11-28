import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 给一个单词列表，求出这个列表中出现频次最高的K个单词。
// 用 O（n log k)的时间和 O(n) 的额外空间完成。

// 你需要按照单词的词频排序后输出，越高频的词排在越前面。如果两个单词出现的次数相同，则词典序小的排在前面。

// 1. 用一个 HashMap 来统计所有项的出现次数
// 2. 循环每一个出现过的项，用最大K项的方法，获得最大的前K项

// 这个算法，我们暂且称之为 标准离线算法。主要使用了两个数据结构：哈希表和最小堆
// 第一步统计所有单词的出现次数，需要 O(N) 的空间和 O(N) 的时间
// 第二步需要 O(K) 的空间和 O(NlogK) 的时间
// 总的时间耗费是 O(N log K)，空间耗费是 O(N)

class Pair {
	String word;
	int frequency;
	Pair(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
}

public class TopKFrequentWords {

	// 注意comparator的多种比较方式
	private Comparator<Pair> pairComparator = new Comparator<>((a, b) -> {
		if (a.frequency != b.frequency) return b.frequency - a.frequency;
		return a.word.compareTo(b.word);
	});

  public String[] topKFrequentWords(String[] words, int k) {
		String[] results = new String[k];
		if (k == 0) return results;

		Map<String, Integer> wordToFreq = new HashMap<>();
		for (String word : words) {
			wordToFreq.put(word, wordToFreq.getOrDefault(word, 0) + 1);
		}

		PriorityQueue<Pair> maxheap = new PriorityQueue<Pair>(k, pairComparator);

		for (String word : wordToFreq.keySet()) {
			Pair current = new Pair(word, wordToFreq.get(word));
			if (maxheap.size() < k) {
				maxheap.offer(current);
				continue;
			} 
			
			// 这里用自定义compartor来与peek比较
			if(pairComparator.compare(current, maxheap.peek()) > 0) {
				maxheap.poll();
				maxheap.offer(current);
			}
		}

		// comparator已经排好了顺序，不用reverse直接poll就行了
		for (int i = 0; i < k; i++) results[i] = maxheap.poll().word;
		return results;
	}
}
