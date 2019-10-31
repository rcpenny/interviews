// 设计b并实现一个 TwoSum 类。他需要支持以下操作:add 和 find。
// add -把这个数添加到内部的数据结构。
// find -是否存在任意一对数字之和等于这个值
// lint170

// 不需要list， loop over map key也是可以的
public class TwoSum3 {
  Map<Integer, Integer> numToFreq;

  /** Initialize your data structure here. */
  public TwoSum() {
    this.numToFreq = new HashMap<>();
  }
  
  /** Add the number to an internal data structure.. */
  public void add(int number) {
    numToFreq.putIfAbsent(number, 0);
    numToFreq.put(number, numToFreq.get(number) + 1);
  }
  
  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    for (int number : numToFreq.keySet()) {
			int complement = value - number;

			if (complement != number && numToFreq.containsKey(complement)) {
        return true;
      }

      if (complement == number && numToFreq.get(complement) > 1) {
        return true;
      }
    }
    return false;
  }
}
