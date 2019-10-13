// 二分答案
// lc774 https://leetcode-cn.com/problems/minimize-max-distance-to-gas-station/

class MinimizeMaxDistancetoGasStation {
  public double minmaxGasDist(int[] stations, int K) {
    double[] gaps = new double[stations.length - 1];

    double start = 0.0;
    double end = 0.0;

    for (int i = 0; i < stations.length - 1; i++) {
      gaps[i] = (double) stations[i + 1] - stations[i];
      end = Math.max(end, gaps[i]);
    }

    while (start + 1e-6 < end) {
      double mid = start + (end - start) / 2;
      if (stationsRequired(gaps, mid) > K) start = mid;
      else end = mid;
    }

    return start;
  }

  private int stationsRequired(double[] gaps, double D) {
    int count = 0;
    for (double gap : gaps) {
      count += (int) (gap / D);
    }
    return count;
  }
}
