// Given a 2D board and a word, find if the word exists in the grid.
// The word can be constructed from letters of sequentially adjacent cell, 
// where "adjacent" cells are those horizontally or vertically neighboring. 
// The same letter cell may not be used more than once.

// Input：["ABCE","SFCS","ADEE"]，"ABCCED" Output：true

public class WordSearch {
  private boolean found = false;

  private int[] dx = new int[] {0, 0, -1, 1};
  private int[] dy = new int[] {1, -1, 0, 0};

  private int row;
  private int col;

  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0) return false;
    if (word == null || word.length() == 0) return false;

    row = board.length;
    col = board[0].length;
    boolean[][] visisted = new boolean[row][col];

    for (int i = 0; i < row; i++)
      for (int j = 0; j < col; j++)
        if (!found && board[i][j] == word.charAt(0)) {
          visisted[i][j] = true;
          dfs(board, i, j, word.substring(1), visisted);
          visisted[i][j] = false;
        }

    return found;
  }

  private void dfs(char[][] board, int x, int y, String s, boolean[][] visisted) {
    if (s.length() == 0) {
      found = true;
      return;
    }

    char head = s.charAt(0);

    for (int i = 0; i < 4; i++) {
      int x_ = x + dx[i], y_ = y + dy[i];
      if (!canMove(x_, y_, visisted) || board[x_][y_] != head) continue;
      visisted[x_][y_] = true;
      dfs(board, x_, y_, s.substring(1), visisted);
      visisted[x_][y_] = false;
    }
  }

  // 不越界，未访问过
  private boolean canMove(int x, int y, boolean[][] visisted) {
    return 0 <= x && x < row && 0 <= y && y < col && !visisted[x][y];
  }
}