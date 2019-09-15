// Given a list, rotate the list to the right by k places, where k is non-negative.

// Input:1->2->3->4->5  k = 2
// Output:4->5->1->2->3

public class RotateList {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) return head;
    
    int n = 0;
    // get length of List
    ListNode tmp = head;
    while (tmp != null) {
      n++;
      tmp = tmp.next;
    }
    
    k = k % n;
    
    // move 3
    int real = n - k;
    ListNode connect = head;
    while (connect.next != null) {
      connect = connect.next;
    }
    
    connect.next = head;
    
    for (int i = 0; i < real - 1; i++) {
      head = head.next;
    }
    
    ListNode cur = head;
    head = head.next;
    cur.next = null;
    
    return head;
  }
}
