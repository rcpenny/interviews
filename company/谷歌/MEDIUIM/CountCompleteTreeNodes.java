// O(h + log(2 ^ h))

public class CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    if (root == null) return 0;

    // root height is defined by me as 0
    int height = 0;
    TreeNode tmp = root;
    while (tmp.left != null) {
      height++;
      tmp = tmp.left;
    }

    // binary search to get position of last node
    int start = (int) Math.pow(2, height);
    int end = (int) Math.pow(2, height + 1) - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (exist(root, mid, height)) start = mid;
      else end = mid;
    }
    
    System.out.println(start);
    System.out.println(end);
    if (exist(root, end, height)) return end;
    return start;
  }
  
  private boolean exist(TreeNode root, int pos, int height) {
    int[] steps = new int[height];
    
    // get directions: 1 is right, 0 is left
    for (int i = 0; i < height; i++) {
      steps[i] = pos % 2;
      pos = pos / 2; 
    }
    
    for (int i = height - 1; i >= 0; i--) {
      int step = steps[i];
      if (step == 1) root = root.right;
      else root = root.left;
    }
    return root != null ? true : false;
  }
}