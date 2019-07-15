// 给出一个 非负 整数 num，
// 对所有满足 0 ≤ i ≤ num 条件的数字 i 均需要计算其二进制表示中数字 1 的个数并以数组的形式返回

// 输入： 5
// 输出： [0,1,1,2,1,2]
// 解释：
// 0~5的二进制表示分别是：
// 000
// 001
// 010
// 011
// 100
// 101
// 每个数字中1的个数为： 0,1,1,2,1,2

// trick: 5 = 101, 5/2=2～1 等于 5>>1 -> 010 (2) + （5%2）
public class CountingBits {
  public int[] countBits(int num) {
		int[] bits = new int[num + 1];
		bits[0] = 0;

		for (int i = 1; i <= num; i++) {
			bits[i] = bits[i / 2] + i % 2;
		}

		return bits;
	}
}
