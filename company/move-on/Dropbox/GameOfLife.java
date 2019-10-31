// 生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

// 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
// 每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。
// 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

// 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
// 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
// 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
// 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
// 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态
// 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的

public class GameOfLife {
  private final int LIVE = 1;
  private final int DEAD = 0;

  private final int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
  private final int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};

  int row;
  int col;

  public void gameOfLife(int[][] board) {
    row = board.length;
    col = board[0].length;

    int[][] status = new int[row][col];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int number = liveCellsNumber(board, i, j);
        if (board[i][j] == DEAD) {
          if (number == 3) status[i][j] = LIVE;
          continue;
        }

        if (number < 2 || number > 3) status[i][j] = DEAD;
        else status[i][j] = LIVE;
      }
    }

    // 更新board
    for (int i = 0; i < row; i++)
      for (int j = 0; j < col; j++)
        board[i][j] = status[i][j];
  }

  private int liveCellsNumber(int[][] board, int x, int y) {
    int count = 0;
    for (int i = 0; i < 8; i++) {
      int x_ = x + dx[i], y_ = y + dy[i];
      if (0 <= x_ && x_ < row && 0 <= y_ && y_ < col)
        if (board[x_][y_] == LIVE) count++;
    }
    return count;
  }
}