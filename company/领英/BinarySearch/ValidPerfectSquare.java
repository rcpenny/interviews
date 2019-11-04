// 给出一个正整数 num,写一个函数，要求当这个当num为完全平方数时函数返回True，否则返回False

// lint777
public class ValidPerfectSquare {
  public boolean isPerfectSquare(int num) {
    if (num == 1) return true;

    int start = 1, end = num / 2;
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (isSquare(num, middle)) return true;
      else if (num / middle > middle) start = middle;
      else end = middle;
    }

    if(isSquare(num, start)) return true;
    if(isSquare(num, end)) return true;

    return false;
  }

  private boolean isSquare(int num, int a) {
    return (num / a == a) && (num % a == 0);
  }
}