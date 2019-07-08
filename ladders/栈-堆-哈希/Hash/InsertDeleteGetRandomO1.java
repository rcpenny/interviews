import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设计一个数据结构实现在平均 O(1) 的复杂度下执行以下所有的操作。
		insert(val): 如果这个元素不在set中，则插入。
		remove(val): 如果这个元素在set中，则从set中移除。
		getRandom: 随机从set中返回一个元素。每一个元素返回的可能性必须相同。
 */

public class InsertDeleteGetRandomO1 {
	// 有点像LRU cache啊，用map指向list，让list查找时间从O(n)降至O(1) 
	Map<Integer, Integer> valueToIndex;
	List<Integer> list;

	public InsertDeleteGetRandomO1() {
		this.valueToIndex = new HashMap<>();
		this.list = new ArrayList<>();
	}

	public boolean insert(int val) {
		if (valueToIndex.containsKey(val)) return false;
		list.add(val);
		valueToIndex.put(val, list.size() - 1);
		return true;
	}

	// 把 list最后一位填入删除的数字的位置
	public boolean remove(int val) {
		if (!valueToIndex.containsKey(val)) return false;

		int indexOfval = valueToIndex.get(val);
		int last = list.get(list.size() - 1);
		list.set(indexOfval, last);
		list.remove(list.size() - 1);
		valueToIndex.remove(val);
		valueToIndex.put(last, indexOfval);
		return true;
	}

	public int getRandom() {
		int index = (int) (Math.random() * list.size());
    return list.get(index);
	}
}
