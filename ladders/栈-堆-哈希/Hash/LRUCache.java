import java.util.HashMap;
import java.util.Map;

// singly linked list version
public class LRUCache {

	class ListNode {
		public int key;
		public int val;
		public ListNode next;
		
		public ListNode(int key, int val) {
			this.key = key;
			this.val = val;
			this.next = null;
		}
	}

	private int capacity;
	private int size;
	private ListNode dummy;
	private ListNode tail;

	// 哈希表 keyToPrev（current node's key -> prev node）
	// 本质：使用哈希表对链表达到O(1)的查找速度
	// 链表又有O(1)添加删除速度，空间换时间, wow!
	private Map<Integer, ListNode> keyToPrev;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.dummy = new ListNode(0, 0);
		this.tail = this.dummy;
		this.keyToPrev = new HashMap<Integer, ListNode>();
	}

	private void moveToTail(int key) {
		ListNode prev = keyToPrev.get(key);
		ListNode current = prev.next;
		
		if (tail == current) return;

		// 从linked list中删除current node, 再添加到尾部.
		prev.next = prev.next.next;
		tail.next = current;

		// 将prev，和current新指向的node 对应的key - prev pair更新
		if (prev.next != null) keyToPrev.put(prev.next.key, prev);
		keyToPrev.put(current.key, tail);

		// tail变成current了，yay!
		tail = current;
	}

	public int get(int key) {
		if (!keyToPrev.containsKey(key)) return -1;
		// get后这个key是most rencently used，所以放到尾部变成tail
		moveToTail(key);
		return tail.val;
	}

	public void set(int key, int value) {
		// 调用get看key是否存在时，若存在就会把key挪到尾部～
		if (get(key) != -1) {
			tail.val = value;
			return;
		}

		// 哈希表中不存在这个key，分两种情况
		// 1. 缓存未满，新数据加到末尾就好了
		if (size < capacity) {
			size++;
			ListNode current = new ListNode(key, value);
			tail.next = current;
			keyToPrev.put(key, tail);
			tail = current;
			return;
		}

		// 2. 缓存满了，把原链表头对应的 K->V pair删了，再存进新的pair
		ListNode head = dummy.next;
		keyToPrev.remove(head.key);

		head.key = key;
		head.val = value;
		keyToPrev.put(key, dummy);
		
		// 新头挪到尾部
		moveToTail(key);
	}
}
