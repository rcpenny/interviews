// 给出一个正整数 num,写一个函数，要求当这个当num为完全平方数时函数返回True，否则返回False

// leet367

public class ValidPerfectSquare {
  public boolean isPerfectSquare(int num) {
    int start = 1;
    int end = num;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;

      if (isSquare(num, mid)) return true;

      if (num / mid > mid) start = mid;
      else end = mid;

    }

    return start * start == num || end * end == num;
  }

  private boolean isSquare(int number, int a) {
    return (number % a == 0 && number / a == a);
  }
}