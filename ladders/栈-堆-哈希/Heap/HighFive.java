import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

// 每个学生有两个属性 id 和 scores 找到每个学生最高的5个分数的平均值 
// 输入: [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
// 输出: 1: 72.40  2: 97.40

// 和top k frequent words没啥区别呀，只不过多开几个heap
// 看到top 5，想到用heap :)

class Record {
	public int id, score;
		public Record(int id, int score){
		this.id = id;
		this.score = score;
	}
}

public class HighFive {
	public Map<Integer, Double> highFive(Record[] results) {
		Map<Integer, Double> scores = new HashMap<>();
		Map<Integer, PriorityQueue<Integer>> idToScores = new HashMap<>();
		
		for (Record record : results) {
			if (idToScores.containsKey(record.id)) {
				idToScores.get(record.id).offer(record.score);
				continue;
			}
			PriorityQueue<Integer> topfive = new PriorityQueue<>(Collections.reverseOrder());
			topfive.offer(record.score);
			idToScores.put(record.id, topfive);
		}

		for (Integer id : idToScores.keySet()) {
			int sum = 0;
			PriorityQueue<Integer> topfive = idToScores.get(id);
			for (int i = 0; i < 5; i++) sum = sum + (int) topfive.poll();
			scores.put(id,  (double) sum / 5);
		}

		return scores;
	}
}
