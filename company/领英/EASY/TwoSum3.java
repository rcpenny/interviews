import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 设计b并实现一个 TwoSum 类。他需要支持以下操作:add 和 find。
// add -把这个数添加到内部的数据结构。
// find -是否存在任意一对数字之和等于这个值
// lint607

public class TwoSum3 {

	Map<Integer, Integer> numberToFreq; // 精华，用map记录出现freq保证 a+a=target
	List<Integer> list;                 // 用List加快visit速度

	TwoSum3() {
		this.numberToFreq = new HashMap<>();
		this.list = new ArrayList<>();
	}


	public void add(int number) {
		if (!numberToFreq.containsKey(number)) {
			numberToFreq.put(number, 1);
			list.add(number);
		} else {
			numberToFreq.put(number, 2);
		}
	}


	public boolean find(int value) {
		for (int i = 0; i < list.size(); i++) {
			int val1 = list.get(i);
			int val2 = value - val1;
			if (val1 != val2 && numberToFreq.containsKey(val2)) return true;
			if (val1 == val2 && numberToFreq.get(val1) == 2) return true;
		}
		return false;
	}
}
