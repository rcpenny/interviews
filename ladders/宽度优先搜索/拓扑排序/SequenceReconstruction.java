import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 
 * https://www.lintcode.com/problem/sequence-reconstruction/
 * 
 * 判断是否序列 org 能唯一地由 seqs重构得出. org是一个由从1到n的正整数排列而成的序列，
 * 1 ≤ n ≤ 10^4。 重构表示组合成seqs的一个最短的父序列 (意思是，一个最短的序列使得所有 seqs里的序列都是它的子序列).
 * 判断是否有且仅有一个能从 seqs重构出来的序列，并且这个序列是org
 * 
 * 输入:org = [1,2,3], seqs = [[1,2],[1,3]]
 * 输出: false
 * 解释:
 * [1,2,3] 并不是唯一可以被重构出的序列，还可以重构出 [1,3,2]
 * 
 * 求是否存在且仅存在一个拓扑序 Queue中最多同时只有1个节点
 */
public class SequenceReconstruction {
  public boolean sequenceReconstruction(int[] org, int[][] seqs) {
		// 建有向图 (pre_seq -> post_seqs)
		HashMap<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < org.length; i++) graph.put(i, new ArrayList<>());
		for (int[] seq : seqs) {
			int pre_seq = seq[0];
			int post_seq = seq[1];
			graph.get(pre_seq).add(post_seq);
		}

		// 建立入度
		int[] indegrees = new int[org.length];
		for (int pre_seq : graph.keySet()) {
			for (int post_seq : graph.get(pre_seq)) indegrees[post_seq - 1]++;
		}

		// 将所有入度为 0 的点，也就是那些没有任何依赖的点，放到宽度优先搜索的队列中
		// 其实这道题就是看同时入度为0的seq是否只有一个,或者说queue的size一直为1
		// 这道题做的不对。。有很多问题要考虑。还有corner case
		Queue<Integer> queue = new LinkedList<>();
		for (int orgNum = 1; orgNum <= org.length; orgNum++) {
			if (indegrees[orgNum - 1] == 0) queue.offer(orgNum);
		}

		while (!queue.isEmpty()) {
			if (queue.size() != 1) return false;
			Integer pre_seq = queue.poll();
			for (Integer post_seq : graph.get(pre_seq)) {
				indegrees[post_seq - 1]--;
				if (indegrees[post_seq - 1] == 0) queue.offer(post_seq);
			}
		}

		return true;
	}
}
