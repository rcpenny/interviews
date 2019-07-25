public class ReverseLinkedList {
  public ListNode reverse(ListNode head) {
    //prev表示前继节点
    ListNode prev = null;
    while (head != null) {
        //temp记录下一个节点，head是当前节点
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        head = temp;
    }
    return prev;
}
}