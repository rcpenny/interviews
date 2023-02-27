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

		search(n, new ArrayList<Integer>(), results);
		
		return results;
	}

	// 定义： 元数据n   控制cols(这行queen的位置)   结果results
	private void search(int n, List<Integer> cols, List<List<String>> results) {
		// 出口：放了N个queen
		if (cols.size() == n) {
			results.add(drawChessBoard(cols));
			return;
		}
		
		// 拆解：扫描board当前行
		for (int colIndex = 0; colIndex < n; colIndex++) {
			if (canAttack(cols, colIndex)) continue; // 进入递归的条件：所有queen不互相攻击
	
			cols.add(colIndex);
			search(n, cols, results);
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
