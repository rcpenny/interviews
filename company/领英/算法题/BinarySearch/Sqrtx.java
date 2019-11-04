public class Sqrtx {

  // 分int doulbe两种

  public int sqrt(int x) {
    if (x < 0)  throw new IllegalArgumentException();
    if (x <= 1)    return x;

    int start = 1, end = x;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (x / mid > mid) start = mid;
      else end = mid;
    }

    if (end > x / end)  return start;
    return end;
  }

  // double 要考虑 x <= 1.0的情况
  public double sqrt(double x) {
    double start = end = 0.0;    

    if (x > 1.0) end = x;
    else end = 1.0;

    double eps = 1e-12;
    
    while (start + eps < end) {
			double mid = start + (end - start) / 2;
			if (x / mid > mid) start = mid;
			else end = mid;
    }
    
    return start;
  }
}
