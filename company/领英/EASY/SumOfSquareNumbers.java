// 给一个整数 c, 你需要判断是否存在两个整数 a 和 b 使得 a^2 + b^2 = c.

public class SumOfSquareNumbers {
  public boolean checkSumOfSquareNumbers(int num) {
    if (num < 0) return false;
    
    for (int a = (int) Math.sqrt(num); a >= 0; a--) {
      if (a * a == num) return true;

      int comp = num - a * a;
      int b = (int) Math.sqrt(comp);

      if (b * b == comp) return true;
    }
    return false;
  }
}