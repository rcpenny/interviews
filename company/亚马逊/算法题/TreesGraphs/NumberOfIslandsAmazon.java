class Coordinate {
  int x;
  int y;
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class NumberOfIslandsAmazon {
	private char SEA = '0';
  private char ISLAND = '1';

  private int[] dx = {0, 1, 0, -1};
  private int[] dy = {-1, 0, 1, 0};

	int row;
	int col;

  public int numIslands(char[][] grid) {
		int islandsCount = 0;
    if (grid == null || grid.length == 0 || grid[0].length == 0) return islandsCount;

    row = grid.length;
    col = grid[0].length;

    for (int i = 0; i < row; i++)
      for (int j = 0; j < col; j++)
        if (grid[i][j] == ISLAND) {
          traverseIslands(grid, i, j);
          islandsCount++;
        }

    return islandsCount;
  }

  private void traverseIslands(char[][] grid, int x, int y) {
		Queue<Coordinate> queue = new LinkedList<Coordinate>();
		grid[x][y] = SEA;
    queue.offer(new Coordinate(x, y));

    while (!queue.isEmpty()) {
      Coordinate coor = queue.poll();
      for (int i = 0; i < 4; i++) {
        Coordinate adj = new Coordinate(coor.x + dx[i], coor.y + dy[i]);
				if (!inBound(adj, grid)) continue;
					grid[adj.x][adj.y] = SEA;
          queue.offer(adj);
      }
    }
  }

  private boolean inBound(Coordinate coor, char[][] grid) {
		return 0 <= coor.x && coor.x < row && 0 <= coor.y && coor.y < col
			&& grid[coor.x][coor.y] == ISLAND;
  }
}
