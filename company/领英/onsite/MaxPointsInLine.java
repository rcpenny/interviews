class Point {
  int x;
  int y;

  Point(int a, int b) {
    this.x = a; 
    this.y = b;
  }
}

// 枚举所有points pair
public class MaxPointsInLine {
  public int maxPoints(Point[] points) {
    if (points == null || points.length == 0) return 0;
    if (points.length == 1) return 1;
    
    int result = 0;
    
    // 选两个不同点作为构成 y=ax+b
    for (int i = 0; i < points.length - 1; i++) {
      for (int j = i + 1; j < points.length; j++) {

        Point a = points[i];
        Point b = points[j];
  
        // 1. ab同点
        if (a.x == b.x && a.y == b.y) {
          int cnt = 0;
          for (int k = 0; k < points.length; k++)
            if (points[k].x == a.x && points[k].y == a.y) cnt++;

          result = Math.max(result, cnt);
        }
        
        // 2. ab垂直X轴
        if (a.x == b.x && a.y != b.y) {
          int cnt = 0;
          for (int k = 0; k < points.length; k++)
            if (points[k].x == a.x) cnt++;

          result = Math.max(result, cnt);
        }
        
        // 3. ab垂直Y轴
        if (a.x != b.x && a.y == b.y) {
          int cnt = 0;
          for (int k = 0; k < points.length; k++)
            if (points[k].y == a.y) cnt++;
          
          result = Math.max(result, cnt);
        }
        
        // 4. ab有斜率
        if (a.x != b.x && a.y != b.y) {
          int cnt = 0;
          // 枚举所有点
          for (int k = 0; k < points.length; k++) {
            // 是a点
            if (points[k].x == a.x && points[k].y == a.y) {
              cnt++;
              continue;
            }
            
            // 是b点
            if (points[k].x == b.x && points[k].y == b.y) {
                cnt++;
                continue;
            }
            
						// 符合y=ax+b
						// 防止65536 * 65536 = 0 把乘积变成Long
            if ((points[k].y - a.y) * (a.x - b.x) == (a.y - b.y) * (points[k].x - a.x)) {
              cnt++;
              continue;
            }
          }
          result = Math.max(result, cnt);
        }
      }
    }
    
    return result;
  }
}
