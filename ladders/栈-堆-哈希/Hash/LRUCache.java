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

// @Todo(" 有空写Double linked list 版本吧，DLL指针控制比这个多一点....")
public class LRUCache {
	private int capacity;
	private int size;

	// 生成Singly linked list
	private ListNode dummy;
	private ListNode tail;

	// 哈希表 keyToPrev（current node's key -> this node's prev node）
	// 本质：使用哈希表对链表达到O(1)的查找速度 链表又有O(1)添加删除速度，空间换时间, wow!
	private Map<Integer, ListNode> keyToPrev;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.dummy = new ListNode(0, 0);
		this.tail = this.dummy;
		this.keyToPrev = new HashMap<Integer, ListNode>();
	}


	/**Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1. */
	public int get(int key) {
		if (!keyToPrev.containsKey(key)) return -1;	
		moveToTail(key); // // get后这个key是most rencently used，所以放到尾部变成tail
		return tail.val;
	}


	// Set or insert the value if the key is not already present. When the cache reached its capacity, 
	// it should invalidate the least recently used item before inserting a new item.
	public void set(int key, int value) {
		if (get(key) != -1) { // 调用get看key是否存在时，若存在get就会把key挪到尾部～
			tail.val = value;
			return;
		}k

		// 哈希表中不存在这个key，分两种情况
		// 1. 缓存未满，新数据加到末尾, 更新map
		if (size < capacity) {
			ListNode current = new ListNode(key, value);
			tail.next = current;
			keyToPrev.put(key, tail);
			tail = current;

			size++;
			return;
		}

		// 2. 缓存满了，把原链表头对应的 K->V pair删了，换成新K-V,更新map, 移到尾部
		ListNode head = dummy.next;
		keyToPrev.remove(head.key);

		head.key = key;
		head.val = value;
		keyToPrev.put(key, dummy);
		
		// 新头挪到尾部
		moveToTail(key);
	}



	// 1. 删node
	// 2. 添到尾部
	// 3. 更新map
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

		// tail变成current，yay!
		tail = current;
	}
}
