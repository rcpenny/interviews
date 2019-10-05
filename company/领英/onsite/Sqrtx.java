public class Sqrtx {

  // 分int doulbe两种

  public int sqrt(int x) {
    if (x < 0)  throw new IllegalArgumentException();
    if (x <= 1)    return x;

    int start = 1, end = x;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (x / mid < mid)  end = mid;
      else if (x / mid > mid) start = mid;
      else end = mid;
    }

    if (end > x / end)  return start;
    return end;
  }

  public double sqrt(double x) {
    double l = 0; 
    double r = Math.max(x, 1.0);
    // if (x > 1.0) r = x;
    // else x = 1;

    double eps = 1e-12;
    
    while (l + eps < r) {
        double mid = l + (r - l) / 2;
        if (mid * mid < x) {
            l = mid;
        } else {
            r = mid;
        }
    }
    
    return l;
  }
}
