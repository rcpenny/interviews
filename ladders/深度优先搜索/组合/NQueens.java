import java.awt.List;

/**
 * n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。
 * 给定一个整数n，返回所有不同的n皇后问题的解决方案。
 * 每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
 */
public class NQueens {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> results = new ArrayList<>();
		if (n <= 0) return results;

		search(results, new ArrayList<Integer>(), n);
		
		return results;
}

	private void search(List<List<String>> results, List<Integer> cols, int n) {
		if (cols.size() == n) {
			results.add(drawChessBoard(cols));
			return;
		}
		
		// 扫描此行
		for (int colIndex = 0; colIndex < n; colIndex++) {
			if (canAttack(cols, colIndex)) continue;
			cols.add(colIndex);
			search(results, cols, n);
			cols.remove(cols.size() - 1);
		}
	}

	private List<String> drawChessBoard(List<Integer> cols) {
		List<String> board = new ArrayList<>();
		for (int index : cols) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < cols.size(); i++) {
						sb.append(i == index ? 'Q' : '.');
				}
				board.add(sb.toString());
		}
		return board;
	}

	private boolean canAttack(List<Integer> cols, int column) {
		int row = cols.size();
		for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
				// in same column
				if (cols.get(rowIndex) == column) return true;
				// in same \ line
				if (rowIndex - cols.get(rowIndex) == row - column) return true;
				// in same / line
				if (rowIndex + cols.get(rowIndex) == row + column) return true;
		}
		return false;
	}
}
