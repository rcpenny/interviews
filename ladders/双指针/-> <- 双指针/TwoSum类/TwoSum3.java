import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设计b并实现一个 TwoSum 类。他需要支持以下操作:add 和 find。
 * add -把这个数添加到内部的数据结构。
 * find -是否存在任意一对数字之和等于这个值
 * 
 * add(1);add(3);add(5);
 * find(4)//返回true
 * find(7)//返回false
 */

public class TwoSum3 {
	private Map<Integer, Integer> map;
	private List<Integer> list;

	// 创建list能加快遍历数字
	public TwoSum3() {
		this.map = new HashMap<Integer, Integer>();
		this.list = new ArrayList<Integer>();
	}

	//O(1)
  public void add(int number) {
		if (!map.containsKey(number)) {
			map.put(number, 1);
			list.add(number);
		} else {
			map.put(number, map.get(number) + 1);
		}
	}

	//o(n)
	public boolean find(int value) {
		for (int i = 0; i < list.size(); i++) {
			int val1 = list.get(i);
			int val2 = value - val1;

			// 两种情况
			if (val1 != val2 && map.containsKey(val2)) return true;
			if (val1 == val2 && map.get(val1) > 1) return true;
		}
		return false;
	}
}
