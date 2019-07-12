import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个二进制的差异。
// 给定一个非负整数 n ，表示该代码中所有二进制的总数，请找出其格雷编码顺序。
// 一个格雷编码顺序必须以 0 开始，并覆盖所有的 2n 个整数。

// 错的，有order.
// 一个数字对应的格雷编码的计算方式是:

// 将其二进制第一位(从高位数)与0异或, 得到的结果为格雷码的第一位
// 之后依次将原数的第i位与生成的格雷码第i-1位做异或运算, 即可得到格雷码的第i位

public class GrayCode {
  public List<Integer> grayCode(int n) {
    List<Integer> codes = new ArrayList<>();
    if (n <= 0) return Arrays.asList(a);

    int[] power = new int[n];
    for (int i = 0; i < n; i++) power[i] = (int) Math.pow(2, i);

    generate(power, 0, 0, codes);
    
    return codes;
  }

  private void generate(int[] power, int index, int sum, List<Integer> codes) {
    if (index == power.length) {
      codes.add(sum);
      return;
    }

    // 递归拆解. power index位置 不选/选
    generate(power, index + 1, sum, codes);
    generate(power, index + 1, sum + power[index], codes);
  }
}