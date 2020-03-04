// 给一些movie（编号从0开始）的rating和他们的联系关系，联系关系可以传递(a和b有联系，b和c有联系，a和c也认为有联系)
// 给出每个movie的直接联系list。再给定一个movie编号为S，找到和S相联系的movie中的rating最高的K个movie
//（当与S相联系的movie少于K个时，输出所有。输出答案时可以按照任意次序，注意联系movie并不包括S

// movie个数 n <= 20000。
// 保证编号为0 ~ n-1。（n为movie个数)。
// 保证联系的边，2个编号都属于0 ~ n-1。
// 保证总边数 m <= 100000。
// 保证每个movie的rating都为int范围内的整数，且互不相同

// 输入: ratingArray = [10,20,30,40], 
// contactRelationship = [[1,3],[0,2],[1],[0]], S = 0, K = 2
// 输出:  [2,3]	

class Movie {
	int id;
	int rate;
	Movie(int id, int rate) {
		this.id = id;
		this.rate = rate;
	}
}

public class MovieNetwork {

	private Comparator<Movie> moive_cpt = new Comparator<Movie>() {
		@Override public int compare(Movie a, Movie b) {return a.rate - b.rate;}
	};

  public int[] topKMovie(int[] rating, int[][] G, int S, int K) {
		PriorityQueue<Movie> minheap = new PriorityQueue<>(K, moive_cpt);
		Queue<Movie> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		Movie first = new Movie(S, rating[S]);
		// S不用加入heap
		queue.offer(first);
		visited.add(S);

		while (!queue.isEmpty()) {
			Movie tmp = queue.poll();
			for (int relation_id : G[tmp.id]) {
				if (visited.contains(relation_id)) continue;
				Movie relation = new Movie(relation_id, rating[relation_id]);
				queue.offer(relation);
				visited.add(relation_id);
				addMovie(minheap, relation, K);
			}
		}

		int[] topk = new int[minheap.size()];
		for (int i = 0; i < topk.length; i++) topk[i] = minheap.poll().id;
		return topk;
	}

	private void addMovie(PriorityQueue<Movie> minheap, Movie movie, int k) {
		if (minheap.size() < k) {
			minheap.offer(movie);
			return;
		}
		if (movie.rate > minheap.peek().rate) {
			minheap.poll();
			minheap.offer(movie);
		}
	}
}
