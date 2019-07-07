import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 给一个连续的数据流,写一个函数返回终止数字到达时的第一个唯一数字（包括终止数字）
// 如果找不到这个终止数字, 返回 -1.

// 面试官期望的解法：使用 Hash & Linked List 处理 Data Stream 的问题
class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}

public class FristUniqNumInDataStream {
  public int firstUniqueNumber(int[] nums, int number) {
		DataStream ds = new DataStream();
		for (int i = 0; i < nums.length; i++) {
			ds.add(nums[i]);
			if (nums[i] == number) return ds.firstUnique();
		}
		return -1;
	}
}

class DataStream {
	private ListNode dummy;
	private ListNode tail;
	private Map<Integer, ListNode> numToPrev;
	private Set<Integer> duplicates;

	public DataStream() {
		this.dummy = new ListNode(0);
		this.tail = dummy;
		numToPrev = new HashMap<>();
		duplicates = new HashSet<>(); 
	}

	public void remove(int number) {
		ListNode prev = numToPrev.get(number);
		prev.next = prev.next.next;
		numToPrev.remove(number);

		if (prev.next != null) numToPrev.put(prev.next.val, prev);
		else tail = prev;
	}

	public void add(int number) {
		if (duplicates.contains(number)) return;

		// 第一次出现，加入哈希表，加入链表尾部
		if (!numToPrev.containsKey(number)) {
			ListNode node = new ListNode(number);
			numToPrev.put(number, tail);
			tail.next = node;
			tail = node;
			return;
		}

		// 已出现过（在哈希表里，remove掉，再加入dupes set）
		remove(number);
		duplicates.add(number);
	}

	public int firstUnique() {
		if (dummy.next != null) return dummy.next.val;
		return -1;
	}
}
