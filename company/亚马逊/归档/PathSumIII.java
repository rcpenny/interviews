import java.util.ArrayList;
import java.util.List;

// 给定一个二叉树，它的每个结点都存放着一个整数值。

// 找出路径和等于给定数值的路径总数。

// 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

// 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

public class PathSumIII {
  int count = 0;
  
  public int pathSum(TreeNode root, int sum) {
    traverse(root, new ArrayList<>(), sum);
    
    return count;
  }
  
  private void traverse(TreeNode node, List<Integer> path, int sum) {
    if (node == null) {
      return;
    }
    
    // add current node
    path.add(node.val);
    
    // find possible sum to target
    int tmpSum = 0;
    for (int i = path.size() - 1; i >= 0; i--) {
      tmpSum += path.get(i);
      if (tmpSum == sum) count++;
    }
    
    traverse(node.left, path, sum);
    traverse(node.right, path, sum);
    
    path.remove(path.size() - 1);
  }
}