public class IslandPerimeter {
  int[] dx = new int[] {0, 0, -1, 1};
  int[] dy = new int[] {1, -1, 0, 0};

  public int islandPerimeter(int[][] grid) {
    int perimeter = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 0) continue;
        perimeter = perimeter + 4;
        for (int d = 0; d < 4; d++) 
          if (hasNeighbor(grid, i + dx[d], j + dy[d])) perimeter--;
      }
    }
    return perimeter;
  }

  private boolean hasNeighbor(int[][] grid, int x, int y) {
    return 0 <= x && x < grid.length && 
      0 <= y && y < grid[0].length 
      && grid[x][y] == 1;
  }
}