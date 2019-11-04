// time O(n * k)
// space is O(k)

class PaintHouse2Best {
  public int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }

    int n = costs.length;
    int k = costs[0].length;

    int[][] f = new int[2][k];

    int i, j;
    int old = 0;
    int now = 0;

    for (i = 0; i < k; i++) {
      f[now][i] = costs[now][i];
    }

    for (i = 1; i < n; i++) {
      old = now;
      now = 1 - now;

      int min = Integer.MAX_VALUE;
      int secMin = Integer.MAX_VALUE;

      for (j = 0; j < k; j++) {
        if (f[old][j] >= secMin) continue;
        if (f[old][j] < min) {
          secMin = min;
          min = f[old][j];
        } else {
          secMin = f[old][j];
        }
      }

      for (int m = 0; m < k; m++) {
        if (f[old][m] == min) f[now][m] = secMin + costs[i][m];
        else f[now][m] = min + costs[i][m];
      }
    }

    int minCost = Integer.MAX_VALUE;
    for (i = 0; i < k; i++) {
      minCost = Math.min(f[now][i], minCost);
    }

    return minCost;
  }
}