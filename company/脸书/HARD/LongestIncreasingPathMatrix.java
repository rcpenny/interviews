// Given an integer matrix, find the length of the longest increasing path.

// From each cell, you can either move to four directions: left, right, up or down. 
// You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

// Input: nums = 
// [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ] 
// Output: 4 
// Explanation: The longest increasing path is [1, 2, 6, 9]
//leet329

// 精华： 用cache[][] 记住 从这个点出发的最长increasing path length
// 记忆化搜索

class LongestIncreasingPathMatrix {
  private final int[] dx = {1, -1, 0, 0};
  private final int[] dy = {0, 0, 1, -1};

  private int row;
  private int col;
  private int[][] cache;

  public int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0) return 0;

    row = matrix.length;
    col = matrix[0].length;

    cache = new int[row][col];

    int max = 1;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int tmpMax = dfs(matrix, i, j, cache);
        max = Math.max(max, tmpMax);
      }
    }

    return max;
  }

  private int dfs(int[][] matrix, int x, int y, int[][] cache) {
    if (cache[x][y] != 0) return cache[x][y];

    int maxDis = 1;

    for (int i = 0; i < 4; i++) {
      int tmpDis = 0;

      int dx_ = x + dx[i];
      int dy_ = y + dy[i];

      // 越界
      if (0 > dx_ || dx_ >= row || 0 > dy_ || dy_ >= col) continue;

      // 不符合increase
      if (matrix[x][y] >= matrix[dx_][dy_]) continue;

      tmpDis = 1 + dfs(matrix, dx_, dy_, cache);

      maxDis = Math.max(tmpDis, maxDis);
    }

    cache[x][y] = maxDis; 
    return maxDis;
  }
}
