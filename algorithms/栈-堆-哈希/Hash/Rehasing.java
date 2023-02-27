// 看看就行
public class Rehasing {
	public ListNode[] rehashing(ListNode[] hashTable) {
		if (hashTable.length <= 0) return hashTable;

		int newcapacity = 2 * hashTable.length;
		ListNode[] newTable = new ListNode[newcapacity];
		
		for (int i = 0; i < hashTable.length; i++) {
			while (hashTable[i] != null) {
				int newindex = (hashTable[i].val % newcapacity + newcapacity) % newcapacity;
				if (newTable[newindex] == null) {
						newTable[newindex] = new ListNode(hashTable[i].val);
						// newTable[newindex].next = null;
				} else {
					ListNode dummy = newTable[newindex];
					while (dummy.next != null) {
							dummy = dummy.next;
					}
					dummy.next = new ListNode(hashTable[i].val);
				}
				hashTable[i] = hashTable[i].next;
			}
		}
		return newTable;
	}
}
