// time O(n * 9)
// space is O(1)

class PaintHouseBest {
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }

    int n = costs.length;
    int[][] f = new int[2][3];

    int old = 0;
    int now = 0;

    f[now][0] = costs[0][0];
    f[now][1] = costs[0][1];
    f[now][2] = costs[0][2];

    int i, j, k;

    for (i = 1; i < n; i++) {
      // 在j loop前 update old now
      old = now;
      now = 1 - now;

      for (j = 0; j < 3; j++) { // j is the real color painted at house i
        f[now][j] = Integer.MAX_VALUE;
        for (k = 0; k < 3; k++) { // k is used to loop over house i - 1
          if (j == k) continue;
          f[now][j] = Math.min(f[old][k] + costs[i][j], f[now][j]);
        }
      }
    }

    int minCost = Integer.MAX_VALUE;
    for (i = 0; i < 3; i++) {
      minCost = Math.min(minCost, f[now][i]);
    }

    return minCost;
  }
}