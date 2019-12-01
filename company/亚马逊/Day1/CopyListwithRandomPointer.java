// leet138
// A linked list is given such that each node contains an additional random pointer 
// which could point to any node in the list or null.


class Node {
  public int val;
  public Node next;
  public Node random;

  public Node() {}

  public Node(int _val,Node _next,Node _random) {
      val = _val;
      next = _next;
      random = _random;
  }
}

@HashMap
@LinkedList
class CopyListwithRandomPointer {

  // hint1: old node -> new node mapping
  public Node copyRandomList(Node head) {
    if (head == null) return null;

    Map<Node, Node> orginToCopy = new HashMap<>();
    
    Node headCopy = head;
    while (headCopy != null) {
      orginToCopy.put(headCopy, new Node());
      headCopy = headCopy.next;
    }

    Node headCopy2 = head;
    while (headCopy2 != null) {
      Node tmp = orginToCopy.get(headCopy2);

      tmp.val = headCopy2.val;
      tmp.next = orginToCopy.get(headCopy2.next);
      tmp.random = orginToCopy.get(headCopy2.random);
      
      headCopy2 = headCopy2.next;
    }

    return orginToCopy.get(head);
  }

  // hint2: A -> A' -> B - > B' -> ... -> null
}
