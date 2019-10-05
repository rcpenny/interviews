// Given a list, rotate the list to the right by k places, where k is non-negative.

// Input:1->2->3->4->5  k = 2
// Output:4->5->1->2->3

//lc61

public class RotateList {
  public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null) return head;
		
		// get length of linked list
    ListNode tmp = head;
    int len = 0; 
    while (tmp != null) {
      len++;
      tmp = tmp.next;
    }
    
    k = k % len;
    
    if (k == 0) return head;
    
    // find last node and connect it to head
    ListNode connect = head;
    while (connect.next != null) {
      connect = connect.next;
    }
    connect.next = head;
    
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
