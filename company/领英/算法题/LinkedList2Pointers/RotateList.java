// Given a list, rotate the list to the right by k places, where k is non-negative.

// Input:1->2->3->4->5  k = 2
// Output:4->5->1->2->3

//lc61

public class RotateList {
  public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null || k == 0) return head;
		
		int len = 1; 
    ListNode last = head;
		
		// 1. 找到最后一个node和list长度
		while (last.next != null) {
      len++;
      last = last.next;
    }

		// 重要！
    k = k % len;
    if (k == 0) return head;
		
    last.next = head;
		
		// 2. 找到切断点，切断
    // move foward and disconnect at position ???
    ListNode disconnect = head;
    for (int i = 0; i < len - k - 1; i++) {
      disconnect = disconnect.next;
    }

    head = disconnect.next;
		disconnect.next = null;

    return head;
  }
}
