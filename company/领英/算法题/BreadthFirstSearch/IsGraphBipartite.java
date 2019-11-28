// https://leetcode-cn.com/problems/is-graph-bipartite/

// 对node进行染色... 比较, bfs也可解
public class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
       
		int n = graph.length;
		int[] colors = new int[n];
		Arrays.fill(colors, -1);
		
		for(int i = 0; i < graph.length; i++) {
				if(colors[i] == -1) {
						if(!isMatchingTheRules(i, colors, graph)) {
								return false;
						}
				}
		}
		
		return true;
}

private boolean isMatchingTheRules(int i, int[] colors, int[][] graph) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(i);
		colors[i] = 0;
		while(!queue.isEmpty()) {
				int indexOfNode = queue.poll();
				int[] neighbors = graph[indexOfNode];
				for(int nei : neighbors) {
						if(colors[nei] != -1) { //neighbor has been visited before
								if(colors[nei] == colors[indexOfNode]) {
										return false;
								}
						} else { //neighbor has NOT been visited before
								colors[nei] = 1 - colors[indexOfNode];
								queue.offer(nei);
						}
				}
		}
		
		return true;
}
}
