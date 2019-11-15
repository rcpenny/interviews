class MergeKSortedListAmazon {
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;

    PriorityQueue<ListNode> minheap = new PriorityQueue<>((a, b) -> {
      return a.val - b.val;
    });

    for (ListNode head : lists)
      if (head != null) minheap.offer(head);

    while (!minheap.isEmpty()) {
      ListNode tmp = minheap.poll();

      tail.next = tmp;
      tail = tail.next;

      if (tmp.next != null) {
        minheap.offer(tmp.next);
      }
    }

    return dummy.next;
  }
}